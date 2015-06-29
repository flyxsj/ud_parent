package com.ud.manage.domain;

public class DriverMatchInfo {
	private Driver driver;
	private double distance;

	public DriverMatchInfo() {
		super();
	}

	public DriverMatchInfo(Driver driver, double distance) {
		super();
		this.driver = driver;
		this.distance = distance;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
