package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class StudentAsset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer studentId;
	private String type;
	private Integer amount;
	private String source;
	private String remark;
	private Date createTime;

	public StudentAsset() {
	}

	public StudentAsset(java.lang.Integer id) {
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

	public void setAmount(Integer value) {
		this.amount = value;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setSource(String value) {
		this.source = value;
	}

	public String getSource() {
		return this.source;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
