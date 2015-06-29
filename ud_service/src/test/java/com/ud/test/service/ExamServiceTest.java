package com.ud.test.service;

import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.Exam;
import com.ud.test.BaseTest;

public class ExamServiceTest extends BaseTest {

	@Test
	public void getList() {
		List<Exam> list = examService.getList(0, 10);
		System.out.println(list);
	}
	
	@Test
	public void getIdList() {
		List<Integer> list = examService.getIdList("course1");
		System.out.println(list);
	}
	
	@Test
	public void getRandomIdList() {
		List<Integer> list = examService.getRandomExamIdList(null,2);
		System.out.println(list);
	}
	
	@Test
	public void getSimulateExamList() {
		examService.initSimulateExam("simulate","testId");
		List<Exam> list = examService.getSimulateExamList("testId",5,2);
		for (Exam exam : list) {
			System.out.println(exam.getId());
		}
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
