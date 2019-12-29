package com.xcy.community2.service;


import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User creatOrUpdateUser(User user){

        //先获取到原来的user
        User dbUser = userMapper.findUserByAccountId(user.getAccountId());
        //如果没有就添加
        if(dbUser == null){
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insertUser(user);
        }else{
            //如果有就更新
            dbUser.setGmtModified(user.getGmtModified());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            userMapper.updateUser(dbUser);
        }
        return user;
    }
}
