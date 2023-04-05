package com.junuo.pojo;

public class Robot {
	String robotId;
    // 剩余电量
	String battery;
	// 状态 0:停止 1:运行
	String status;

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Robot{" +
				"robotId='" + robotId + '\'' +
				", battery='" + battery + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
