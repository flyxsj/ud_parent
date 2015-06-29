package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class Luck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer studentId;
	private Integer bean;
	private Integer isShare;
	private Date createTime;

	public Luck() {
	}

	public Luck(java.lang.Integer id) {
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

	public void setBean(Integer value) {
		this.bean = value;
	}

	public Integer getBean() {
		return this.bean;
	}

	public void setIsShare(Integer value) {
		this.isShare = value;
	}

	public Integer getIsShare() {
		return this.isShare;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
