package com.ud.test.service;

import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.AdminUser;
import com.ud.test.BaseTest;

public class AdminUserServiceTest extends BaseTest {

	@Test
	public void getList() {
		List<AdminUser> list = adminUserService.getList(0, 10);
		System.out.println(list);
	}

	@Test
	public void getById() {
		AdminUser user = adminUserService.getById(1);
		System.out.println(user.getUsername());
	}

	@Test
	public void getCount() {
		Integer count = adminUserService.getCount();
		System.out.println(count);
	}

	@Test
	public void create() {
		AdminUser user = new AdminUser();
		user.setPassword("123456");
		user.setUsername("xie1");
		adminUserService.create(user);
	}

	@Test
	public void update() {
		AdminUser user = adminUserService.getById(7);
		user.setPassword("xxxx");
		adminUserService.update(user);
	}

	@Test
	public void delete() {
		adminUserService.delete(4);
	}

}
