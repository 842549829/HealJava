package com.his.heal.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.heal.entity.User;
import com.his.heal.mapper.UserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServices extends ServiceImpl<UserMapper,User> implements IUserServices {
    private final UserMapper userMapper;

    public UserServices(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Cacheable(value = "name", key = "#id")
    public User findById(String id) {
        return userMapper.findById(id);
    }
}
