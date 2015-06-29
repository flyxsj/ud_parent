package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer driverId;
	private String type;
	private Date day;
	private Date startTime;
	private Date endTime;
	private Boolean isAlwaysEnable;
	private Date createTime;

	public Schedule() {
	}

	public Schedule(java.lang.Integer id) {
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

	public void setDay(Date value) {
		this.day = value;
	}

	public Date getDay() {
		return this.day;
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

	public void setIsAlwaysEnable(Boolean value) {
		this.isAlwaysEnable = value;
	}

	public Boolean getIsAlwaysEnable() {
		return this.isAlwaysEnable;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
