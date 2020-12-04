package com.zhang.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;


@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //未授权界面
        bean.setUnauthorizedUrl("/unauthorized");

        //添加shiro的内置过滤器
        //anon:无需认证就可以访问
        //authc:必须认证才能访问
        //user:必须拥有记住我功能才能使用
        //perms:拥有某个资源的权限才能访问
        //role:拥有某个角色权限才能访问

        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        //配置映射关系
        filterMap.put("/login", "anon");
        filterMap.put("/index", "authc");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/admin/**", "roles[admin]");
        filterMap.put("/teacher/**", "roles[teacher]");
        filterMap.put("/student/**", "roles[student]");

        bean.setLoginUrl("/login");
        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }

    //设置md5盐值加密
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        //设置属性值
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置加密算法
        matcher.setHashAlgorithmName("MD5");
        //设置加密次数
        matcher.setHashIterations(10000);

        return matcher;
    }


    @Bean(name="ehCacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    //ShiroDialect 用来整合Shiro-Thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    //SecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm,@Qualifier("ehCacheManager") EhCacheManager ehCacheManager){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        //注入权限缓存
        securityManager.setCacheManager(ehCacheManager);
        return securityManager;
    }

    //创建realm对象,需要自定义!
    @Bean(name="userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //配置加密
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }
}
