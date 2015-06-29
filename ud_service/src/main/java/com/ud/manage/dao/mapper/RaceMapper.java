package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface RaceMapper {

	public List<Race> getList(@Param("skip") int start, @Param("max") int offset);

	public List<Race> getListByExamDate(@Param("examDate") String examDate, @Param("type") String type);

	public Race getById(@Param("id") int id);

	public Integer getCount();

	public void create(Race entity);

	public void update(Race entity);

	public void delete(int id);

}
