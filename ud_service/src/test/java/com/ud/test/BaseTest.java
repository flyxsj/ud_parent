package com.ud.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ud.manage.dao.mapper.AdminUserMapper;
import com.ud.manage.dao.mapper.ExamMapper;
import com.ud.manage.service.AdminUserService;
import com.ud.manage.service.ExamService;

public class BaseTest {

	protected static AdminUserService adminUserService;
	protected static ExamService examService;
	protected static AdminUserMapper adminUserMapper;
	protected static ExamMapper examMapper;

	@Before
	public void setUp() {
		if (adminUserService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "classpath:spring/service.xml" });
			adminUserService = context.getBean(AdminUserService.class);
			adminUserMapper = context.getBean(AdminUserMapper.class);
			examMapper = context.getBean(ExamMapper.class);
			examService = context.getBean(ExamService.class);
		}
	}
}
