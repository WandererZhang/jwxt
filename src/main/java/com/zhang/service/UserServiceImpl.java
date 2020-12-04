package com.zhang.service;

import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String userName){
        return userMapper.queryUserByName(userName);
    }
}
