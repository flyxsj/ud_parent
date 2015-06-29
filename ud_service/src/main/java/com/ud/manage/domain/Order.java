package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String orderNumber;
	private Integer studentId;
	private Integer driverId;
	private String type;
	private String course;
	private String address;
	private double longitude;
	private double latitude;
	private Date reserveDay;
	private Date reserveStartTime;
	private Date reserveEndTime;
	private String status;
	private String serviceCode;
	private Date trainStartTime;
	private Date trainEndTime;
	private Date createTime;

	public Order() {
	}

	public Order(java.lang.Integer id) {
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

	public void setCourse(String value) {
		this.course = value;
	}

	public String getCourse() {
		return this.course;
	}

	public void setAddress(String value) {
		this.address = value;
	}

	public String getAddress() {
		return this.address;
	}

	public void setLongitude(double value) {
		this.longitude = value;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLatitude(double value) {
		this.latitude = value;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setReserveDay(Date value) {
		this.reserveDay = value;
	}

	public Date getReserveDay() {
		return this.reserveDay;
	}

	public void setReserveStartTime(Date value) {
		this.reserveStartTime = value;
	}

	public Date getReserveStartTime() {
		return this.reserveStartTime;
	}

	public void setReserveEndTime(Date value) {
		this.reserveEndTime = value;
	}

	public Date getReserveEndTime() {
		return this.reserveEndTime;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public String getStatus() {
		return this.status;
	}

	public void setServiceCode(String value) {
		this.serviceCode = value;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setTrainStartTime(Date value) {
		this.trainStartTime = value;
	}

	public Date getTrainStartTime() {
		return this.trainStartTime;
	}

	public void setTrainEndTime(Date value) {
		this.trainEndTime = value;
	}

	public Date getTrainEndTime() {
		return this.trainEndTime;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
