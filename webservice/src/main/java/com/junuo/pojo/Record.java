package com.junuo.pojo;

public class Record {
	Integer id;
	Integer time;
	String robotId;
	String beltId;
	// 检测第几个托辊，默认第0个
	Integer roller;
	// -1故障，0工作异常，1工作正常
	Integer status;
	String bzText;

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

	public Integer getRoller() {
		return roller;
	}

	public void setRoller(Integer roller) {
		this.roller = roller;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBzText() {
		return bzText;
	}

	public void setBzText(String bzText) {
		this.bzText = bzText;
	}

	@Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", time=" + time +
				", robotId='" + robotId + '\'' +
				", beltId='" + beltId + '\'' +
				", roller=" + roller +
				", status=" + status +
				", bzText='" + bzText + '\'' +
				'}';
	}
}
