package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String openid;
	private String driverLicense;
	private String name;
	private String idNumber;
	private Boolean hasShop;
	private String address;
	private double longitude;
	private double latitude;
	private int driveAge;
	private String mobile;
	private Integer score = 0;
	private Integer ub = 0;
	private String status = "I";
	private Date createTime;
	private Date updateTime;

	public Driver() {
	}

	public Driver(java.lang.Integer id) {
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

	public void setDriverLicense(String value) {
		this.driverLicense = value;
	}

	public String getDriverLicense() {
		return this.driverLicense;
	}

	public void setIdNumber(String value) {
		this.idNumber = value;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setHasShop(Boolean value) {
		this.hasShop = value;
	}

	public Boolean getHasShop() {
		return this.hasShop;
	}

	public void setAddress(String value) {
		this.address = value;
	}

	public String getAddress() {
		return this.address;
	}

	public void setLongitude(Long value) {
		this.longitude = value;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLatitude(Long value) {
		this.latitude = value;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setDriveAge(int value) {
		this.driveAge = value;
	}

	public int getDriveAge() {
		return this.driveAge;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setScore(Integer value) {
		this.score = value;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setUb(Integer value) {
		this.ub = value;
	}

	public Integer getUb() {
		return this.ub;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegisterAwardScore() {
		int result = 0;
		int age = this.getDriveAge();
		if (age <= 1) {
			result = 100;
		} else if (age >= 2 && age <= 4) {
			result = 200;
		} else if (age >= 5 && age <= 8) {
			result = 300;
		} else {
			result = 500;
		}
		return result;
	}

	public int getRegisterAwardUB() {
		int result = 0;
		int age = this.getDriveAge();
		if (age <= 1) {
			result = 10;
		} else if (age >= 2 && age <= 4) {
			result = 20;
		} else if (age >= 5 && age <= 8) {
			result = 30;
		} else {
			result = 50;
		}
		return result;
	}
}
