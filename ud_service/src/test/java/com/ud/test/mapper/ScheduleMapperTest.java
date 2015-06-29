package com.ud.test.mapper;

import java.util.List;

import org.junit.Test;

import com.ud.manage.domain.Schedule;
import com.ud.test.BaseTest;

public class ScheduleMapperTest extends BaseTest {

	@Test
	public void getList() {
		List<Schedule> list = scheduleMapper.getList(0, 5);
		System.out.println(list.size());
	}

	@Test
	public void getListByDay() {
		List<Schedule> list = scheduleMapper.getListByDay("2015-07-02");
		System.out.println(list.size());
	}

	@Test
	public void getById() {
		Schedule entity = scheduleMapper.getById(1);
	}

	@Test
	public void getCount() {
		Integer count = scheduleMapper.getCount();
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
		scheduleMapper.delete(4);
	}
}
