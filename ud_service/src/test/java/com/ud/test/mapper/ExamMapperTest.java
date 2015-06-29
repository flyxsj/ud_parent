package com.ud.test.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.Exam;
import com.ud.test.BaseTest;

public class ExamMapperTest extends BaseTest {

	@Test
	public void getList() {
		List<Exam> list = examMapper.getList(0, 5);
		System.out.println(list.size());
	}

	@Test
	public void getById() {
		Exam entity = examMapper.getById(1);
	}

	@Test
	public void getCount() {
		Integer count = examMapper.getCount();
		System.out.println(count);
	}

	@Test
	public void create() {
		Exam exam = new Exam();
		exam.setType("course1");
		exam.setQuestion("<p></p");
		exam.setAnswer("A");
		exam.setOptions("{\"A\":\"test\"}");
		exam.setCreateTime(new Date());
		examMapper.create(exam);
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
		examMapper.delete(4);
	}
}
