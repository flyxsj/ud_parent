package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.Schedule;

public interface ScheduleMapper {

	public List<Schedule> getList(@Param("skip") int start, @Param("max") int offset);

	public List<Schedule> getListByDay(@Param("day") String day);

	public Schedule getById(@Param("id") int id);

	public Integer getCount();

	public void create(Schedule entity);

	public void update(Schedule entity);

	public void delete(int id);

}
