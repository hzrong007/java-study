package com.mybatis;

import com.mybatis.dao.UserDaoService;
import com.mybatis.dao.UserDaoService2;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class MybatisTest {

    /**
     * XML方式注入SQL
     *
     * @throws IOException e
     */
    @Test
    public void queryUserByMapperXml() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("spring-mybatis.xml");
        Reader reader = new InputStreamReader(resourceAsStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        resourceAsStream.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDaoService mapper = sqlSession.getMapper(UserDaoService.class);
        List<User> users = mapper.queryUsers();
        System.out.println(users);
        close(sqlSession);
    }

    /**
     * 异常测试用例：
     * -- 目的：为了测试单通过接口是否能找到mapper.xml
     *
     * @throws IOException e
     */
    @Test
    public void queryUserByInterface() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("spring-mybatis2.xml");
        Reader reader = new InputStreamReader(resourceAsStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        resourceAsStream.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDaoService mapper = sqlSession.getMapper(UserDaoService.class);
        List<User> users = mapper.queryUsers();
        System.out.println(users);
        close(sqlSession);
    }

    /**
     * 注解的方式注入SQL
     *
     * @throws IOException e
     */
    @Test
    public void queryUserByAnnotationInterface() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("spring-mybatis3.xml");
        Reader reader = new InputStreamReader(resourceAsStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        resourceAsStream.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDaoService2 mapper = sqlSession.getMapper(UserDaoService2.class);
        List<User> users = mapper.queryUsers();
        System.out.println(users);
        close(sqlSession);
    }

    private void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            try {
                sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
