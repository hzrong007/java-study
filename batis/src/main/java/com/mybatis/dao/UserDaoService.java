package com.mybatis.dao;

import com.mybatis.entity.User;

import java.util.List;

public interface UserDaoService {
    List<User> queryUsers();
}
