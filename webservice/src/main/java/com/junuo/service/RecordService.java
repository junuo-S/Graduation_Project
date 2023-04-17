package com.junuo.service;

import com.junuo.mapper.RecordMapper;
import com.junuo.pojo.Record;
import com.junuo.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RecordService {
	private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

	public List<Record> selectAllByRobotIdAndBeltId(String robotId, String beltId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RecordMapper mapper = sqlSession.getMapper(RecordMapper.class);
		List<Record> records = mapper.selectAllByRobotIdAndBeltId(robotId, beltId);
		sqlSession.close();
		return records;
	}
	public Record selectOneByRobotIdAndBeltId(String robotId, String beltId, int beginIndex) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RecordMapper mapper = sqlSession.getMapper(RecordMapper.class);
		Record record = mapper.selectOneByRobotIdAndBeltId(robotId, beltId, beginIndex);
		sqlSession.close();
		return record;
	}
}
