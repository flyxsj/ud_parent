package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String openid;
	private String mobile;
	private Integer bean;
	private Integer ub;
	private Date createTime;

	public Student() {
	}

	public Student(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setOpenid(String value) {
		this.openid = value;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setBean(Integer value) {
		this.bean = value;
	}

	public Integer getBean() {
		return this.bean;
	}

	public void setUb(Integer value) {
		this.ub = value;
	}

	public Integer getUb() {
		return this.ub;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
