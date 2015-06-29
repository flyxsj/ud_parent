package com.ud.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ud.manage.dao.mapper.AdminUserMapper;
import com.ud.manage.dao.mapper.ExamMapper;
import com.ud.manage.dao.mapper.ScheduleMapper;
import com.ud.manage.service.AdminUserService;
import com.ud.manage.service.DriverService;
import com.ud.manage.service.ExamService;
import com.ud.manage.service.OrderService;
import com.ud.manage.service.RaceService;
import com.ud.manage.service.ScheduleService;

public class BaseTest {

	protected static AdminUserService adminUserService;
	protected static ExamService examService;
	protected static AdminUserMapper adminUserMapper;
	protected static ExamMapper examMapper;
	protected static ScheduleMapper scheduleMapper;
	protected static ScheduleService scheduleService;
	protected static OrderService orderService;
	protected static DriverService driverService;
	protected static RaceService raceService;

	@Before
	public void setUp() {
		if (adminUserService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "classpath:spring/service.xml" });
			adminUserService = context.getBean(AdminUserService.class);
			adminUserMapper = context.getBean(AdminUserMapper.class);
			examMapper = context.getBean(ExamMapper.class);
			examService = context.getBean(ExamService.class);
			scheduleMapper = context.getBean(ScheduleMapper.class);
			scheduleService = context.getBean(ScheduleService.class);
			orderService = context.getBean(OrderService.class);
			driverService = context.getBean(DriverService.class);
			raceService = context.getBean(RaceService.class);
		}
	}
}
