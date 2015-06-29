package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732787428980L;

	private Integer examId;
	private String answer; // Like "A,B"
	private Integer studentId;
	private String openid;
	private Date submitTime;

	public Answer() {
	}

	public Answer(Integer examId, Integer studentId, String openid, String answer, Date submitTime) {
		super();
		this.examId = examId;
		this.studentId = studentId;
		this.openid = openid;
		this.answer = answer;
		this.submitTime = submitTime;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

}
