package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class DriverAsset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer driverId;
	private String type;
	private int amount;
	private String source;
	private String remark;
	private Date createTime;

	public DriverAsset() {
	}

	public DriverAsset(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setDriverId(Integer value) {
		this.driverId = value;
	}

	public Integer getDriverId() {
		return this.driverId;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return this.type;
	}

	public void setAmount(int value) {
		this.amount = value;
	}

	public int getAmount() {
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
