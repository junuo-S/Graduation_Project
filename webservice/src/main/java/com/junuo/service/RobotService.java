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

	public int setRunning(String robotId, int status) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RobotMapper mapper = sqlSession.getMapper(RobotMapper.class);
		int count = mapper.setRunning(robotId, status);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}

	public Robot selectByRobotId(String robotId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RobotMapper mapper = sqlSession.getMapper(RobotMapper.class);
		Robot robot = mapper.selectByRobotId(robotId);
		sqlSession.close();
		return robot;
	}
	public int getBattery(String robotId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RobotMapper mapper = sqlSession.getMapper(RobotMapper.class);
		int battery = mapper.getBattery(robotId);
		sqlSession.close();
		return battery;
	}
}
