package com.hospital.mapper;

import com.hospital.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

    int insert(User user);
}
