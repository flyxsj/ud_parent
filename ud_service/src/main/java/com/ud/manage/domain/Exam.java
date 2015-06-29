package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String type;
	private String question;
	private String options;
	private String answer;
	private Date createTime;

	public Exam() {
	}

	public Exam(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return this.type;
	}

	public void setQuestion(String value) {
		this.question = value;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setOptions(String value) {
		this.options = value;
	}

	public String getOptions() {
		return this.options;
	}

	public void setAnswer(String value) {
		this.answer = value;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
