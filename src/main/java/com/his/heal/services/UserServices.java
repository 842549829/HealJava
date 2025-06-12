package com.his.heal.services;

import com.his.heal.entity.User;
import com.his.heal.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserMapper userMapper;

    public UserServices(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findById(String id) {
        return userMapper.findById(id);
    }
}
