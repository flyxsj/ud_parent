package com.ud.test.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ibm.icu.text.SimpleDateFormat;
import com.ud.manage.domain.DriverMatchInfo;
import com.ud.manage.domain.Order;
import com.ud.test.BaseTest;

public class OrderServiceTest extends BaseTest {

	@Test
	public void matchDriver() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date s = sdf.parse("09:00");
		Date e = sdf.parse("10:00");
		List<DriverMatchInfo> list = orderService.matchDriver("drive", 113.45796600, 23.16187700, new Date(), s, e);
		System.out.println(list.size());
	}

	@Test
	public void getOrderNumber() {
		System.out.println(orderService.getOrderNumber());
	}

	@Test
	public void getListByDriver() {
		List<Order> list = orderService.getListByDriver(6, "accepted", 0, 10);
		System.out.println(list.size());
	}

	@Test
	public void getListByStudent() {
		List<Order> list = orderService.getListByStudent(1, "reserved", 0, 10);
		System.out.println(list.size());
	}

	@Test
	public void getServiceCode() {
		System.out.println(orderService.getServiceCode());
		System.out.println(orderService.getServiceCode());
		System.out.println(orderService.getServiceCode());
		System.out.println(orderService.getServiceCode());
		System.out.println(orderService.getServiceCode());
		System.out.println(orderService.getServiceCode());
	}

	@Test
	public void statisticAward() {
		orderService.statisticAward();
	}
}
