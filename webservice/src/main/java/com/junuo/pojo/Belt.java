package com.junuo.pojo;

public class Belt {
	String beltId;
	Integer length;

	public String getBeltId() {
		return beltId;
	}

	public void setBeltId(String beltId) {
		this.beltId = beltId;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Belt{" +
				"beltId='" + beltId + '\'' +
				", length=" + length +
				'}';
	}
}
