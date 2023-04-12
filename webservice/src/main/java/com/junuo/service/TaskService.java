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
}
