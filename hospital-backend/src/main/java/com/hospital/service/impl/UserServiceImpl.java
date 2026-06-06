package com.hospital.service.impl;

import com.hospital.entity.User;
import com.hospital.mapper.UserMapper;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
