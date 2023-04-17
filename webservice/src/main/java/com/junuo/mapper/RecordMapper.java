package com.junuo.mapper;

import com.junuo.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
	List<Record> selectAllByRobotIdAndBeltId(@Param("robotId") String robotId, @Param("beltId") String beltId);
	Record selectOneByRobotIdAndBeltId(@Param("robotId") String robotId, @Param("beltId") String beltId, @Param("beginIndex") int beginIndex);
}
