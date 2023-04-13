package com.junuo.service;

import com.junuo.mapper.RobotMapper;
import com.junuo.pojo.Robot;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RobotService {
	private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

	public List<Robot> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RobotMapper mapper = sqlSession.getMapper(RobotMapper.class);
		List<Robot> robots = mapper.selectAll();
		sqlSession.close();
		return robots;
	}

	public List<Robot> getFreeRobots() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RobotMapper mapper = sqlSession.getMapper(RobotMapper.class);
		List<Robot> freeRobots = mapper.getFreeRobots();
		sqlSession.close();
		return freeRobots;
	}
}
