package com.ud.test.mapper;

import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.*;
import com.ud.test.BaseTest;
import com.ud.util.MD5;

public class AdminUserMapperTest extends BaseTest {

	@Test
	public void getList() {
		List<AdminUser> list = adminUserMapper.getList(0, 5);
		System.out.println(list.size());
	}

	@Test
	public void getById() {
		AdminUser user = adminUserMapper.getById(1);
		System.out.println(user.getUsername());
	}

	@Test
	public void getCount() {
		Integer count = adminUserMapper.getCount();
		System.out.println(count);
	}

	@Test
	public void create() {
		AdminUser user = new AdminUser();
		user.setPassword(MD5.md5("123456"));
		user.setUsername("xie");
		user.setRoleType("admin");
		adminUserMapper.create(user);
	}

	@Test
	public void update() {
		AdminUser user = adminUserMapper.getById(7);
		user.setPassword("xxxx");
		adminUserMapper.update(user);
	}

	@Test
	public void delete() {
		adminUserMapper.delete(4);
	}
}
