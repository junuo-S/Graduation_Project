package com.junuo.service;

import com.junuo.mapper.TaskMapper;
import com.junuo.pojo.Task;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TaskService {
	private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
	public List<Task> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		List<Task> tasks = mapper.selectAll();
		sqlSession.close();
		return tasks;
	}
	public int setDone(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		int count = mapper.setDone(id);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}

	public int deleteById(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		int count = mapper.deleteByIdInt(id);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}

	public int addTask(Task task) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		int count = mapper.addTask(task);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}

	public String selectRobotIdById(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		String robotId = mapper.selectRobotIdById(id).getRobotId();
		sqlSession.close();
		return robotId;
	}

	public Task selectByBeltId(String beltId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		Task task = mapper.selectByBeltId(beltId);
		sqlSession.close();
		return task;
	}

	public Task selectByRobotIdAndBeltId(String robotId, String beltId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
		Task task = mapper.selectByRobotIdAndBeltId(robotId, beltId);
		sqlSession.close();
		return task;
	}
}
