package com.ud.test.service;

import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.*;
import com.ud.manage.dao.mapper.*;
import com.ud.test.BaseTest;

public class ExamServiceTest extends BaseTest {

	@Test
	public void getList() {
		List<Exam> list = examService.getList(0, 10);
		System.out.println(list);
	}

	@Test
	public void getById() {
	}

	@Test
	public void getCount() {
		Integer count = examService.getCount();
		System.out.println(count);
	}

	@Test
	public void create() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
		examService.delete(4);
	}

}
