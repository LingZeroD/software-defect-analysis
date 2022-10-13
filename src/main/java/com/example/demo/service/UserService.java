package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)

public class UserService {

    @Resource
    private UserMapper userMapper;

    public User LoginIn(String username, String password) {
        System.out.println("username"+username+"password"+password);
        return userMapper.getInfo(username,password);
    }

    public User getInfo(String username) {
        return userMapper.selectByName(username);
    }

    public int register(User user){
        System.out.println("user"+user);
        User info = userMapper.selectByName(user.getUsername());
        if(info==null){
            return userMapper.insert(user);
        }else return -1;
    }

    public int update(User user){
        System.out.println("user"+user);
        User info = userMapper.selectByName(user.getUsername());
        user.setId(info.getId());
        return userMapper.updateById(user);
    }

}
