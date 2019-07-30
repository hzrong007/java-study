package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@CacheNamespace
public interface UserDaoService2 {

    @Select("select id, name from user")
    List<User> queryUsers();
}
