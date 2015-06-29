package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class Recruit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer driverId;
	private String schoolName;
	private Integer price;
	private String scope;
	private String address;
	private double longitude;
	private double latitude;
	private String phone;
	private String introduce;
	private String status;
	private Date createTime;
	private Date updateTime;

	public Recruit() {
	}

	public Recruit(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setSchoolName(String value) {
		this.schoolName = value;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setPrice(Integer value) {
		this.price = value;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setScope(String value) {
		this.scope = value;
	}

	public String getScope() {
		return this.scope;
	}

	public void setAddress(String value) {
		this.address = value;
	}

	public String getAddress() {
		return this.address;
	}

	public void setPhone(String value) {
		this.phone = value;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setIntroduce(String value) {
		this.introduce = value;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public String getStatus() {
		return this.status;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
}
