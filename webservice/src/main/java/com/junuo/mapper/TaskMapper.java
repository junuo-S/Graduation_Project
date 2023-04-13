package com.junuo.mapper;

import com.junuo.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
	List<Task> selectAll();
	int setDone(@Param("id") int id);
	int deleteByIdInt(@Param("id") int id);
	int addTask(Task task);
	Task selectRobotIdById(@Param("id") int id);
}
