package com.junuo.mapper;

import com.junuo.pojo.Robot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RobotMapper {
	List<Robot> selectAll();
	List<Robot> getFreeRobots();
	int setRunning(@Param("robotId") String robotId, @Param("status") int status);
	Robot selectByRobotId(@Param("robotId") String robotId);
	int getBattery(@Param("robotId") String robotId);
}
