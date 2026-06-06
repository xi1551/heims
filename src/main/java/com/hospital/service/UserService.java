package com.hospital.service;

import com.hospital.entity.User;

public interface UserService {

    User findByUsername(String username);

    int insert(User user);
}
