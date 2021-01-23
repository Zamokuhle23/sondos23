package com.javainuse.model;

public class Student {

	private String studId;
	private String studName;

	public String getStudId() {
		return studId;
	}

	public void setStudId(String empId) {
		this.studId = empId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studName=" + studName + "]";
	}

}