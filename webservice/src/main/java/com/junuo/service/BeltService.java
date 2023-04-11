package com.junuo.service;

import com.junuo.mapper.BeltMapper;
import com.junuo.pojo.Belt;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BeltService {
	private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

	public List<Belt> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BeltMapper mapper = sqlSession.getMapper(BeltMapper.class);
		List<Belt> belts = mapper.selectAll();
		sqlSession.close();
		return belts;
	}
}
