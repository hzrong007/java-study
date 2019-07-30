package com.mybatis.dao.impl;

import com.mybatis.dao.UserDaoService3;
import com.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserServiceImpl extends SqlSessionDaoSupport implements UserDaoService3 {

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<User> queryUsers() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDaoService3 mapper = sqlSession.getMapper(UserDaoService3.class);
        List<User> users = mapper.queryUsers();
        System.out.println(users);
        sqlSession.close();
        return users;
    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
