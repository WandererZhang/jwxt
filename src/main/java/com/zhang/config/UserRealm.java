package com.zhang.config;


import com.zhang.pojo.User;
import com.zhang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //获取User对象
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户权限
        info.addRole(currentUser.getRole_db().getRoleName());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //将AuthenticationToken对象转换成UsernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        //获取UsernamePasswordToken中的用户名
        String username = usernamePasswordToken.getUsername();

        //连接数据库
        User user = userService.queryUserByName(username);

        //用户不存在
        if (user == null) {
            return null;
        }

        ByteSource salt = ByteSource.Util.bytes(user.getUserName());

        //密码认证
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }

}
