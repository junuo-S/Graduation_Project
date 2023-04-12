package com.junuo.pojo;

public class Task {
	private Integer id;
	private Integer time;
	private String robotId;
	private String beltId;
	// 0: 已完成 1: 进行中
	private Integer status;

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", time=" + time +
				", robotId='" + robotId + '\'' +
				", beltId='" + beltId + '\'' +
				", status=" + status +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getBeltId() {
		return beltId;
	}

	public void setBeltId(String beltId) {
		this.beltId = beltId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
