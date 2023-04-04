package com.junuo.service;

import com.junuo.mapper.UserMapper;
import com.junuo.pojo.User;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User selectByUsernameAndPassword(String userName, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByUsernameAndPassword(userName, password);
        sqlSession.close();
        return user;
    }

    public List<User> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        sqlSession.close();
        return users;
    }

    public int deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    public int insertUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }
}
