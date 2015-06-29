package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class RaceRank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer studentId;
	private String type;
	private Integer score;
	private Integer rank;
	private Long consumedTime;
	private Date examDate;

	public RaceRank() {
	}

	public RaceRank(java.lang.Integer id) {
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

	public void setRank(Integer value) {
		this.rank = value;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setConsumedTime(Long value) {
		this.consumedTime = value;
	}

	public Long getConsumedTime() {
		return this.consumedTime;
	}

	public void setExamDate(Date value) {
		this.examDate = value;
	}

	public Date getExamDate() {
		return this.examDate;
	}
}
