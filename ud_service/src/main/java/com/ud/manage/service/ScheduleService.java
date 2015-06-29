package com.ud.manage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.manage.dao.mapper.*;
import com.ud.manage.domain.*;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleMapper mapper;

	public List<Schedule> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public List<Schedule> getListByDay(@Param("day") String day) {
		return mapper.getListByDay(day);
	}

	public Schedule getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Schedule entity) {
		mapper.create(entity);
	}

	public void update(Schedule entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
