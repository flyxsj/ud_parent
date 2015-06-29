package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class Race implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer studentId;
	private String type;
	private Integer score;
	private Date startTime;
	private Date endTime;

	public Race() {
	}

	public Race(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setStudentId(Integer value) {
		this.studentId = value;
	}

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return this.type;
	}

	public void setScore(Integer value) {
		this.score = value;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setStartTime(Date value) {
		this.startTime = value;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Date value) {
		this.endTime = value;
	}

	public Date getEndTime() {
		return this.endTime;
	}
}
