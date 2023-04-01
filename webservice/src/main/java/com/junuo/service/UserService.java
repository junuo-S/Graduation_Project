package com.junuo.service;

import com.junuo.mapper.UserMapper;
import com.junuo.pojo.User;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User selectByUsernameAndPassword(String userName, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByUsernameAndPassword(userName, password);
        sqlSession.close();
        return user;
    }
}
