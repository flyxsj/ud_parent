package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface VideoMapper {

	public List<Video> getList(@Param("skip") int start, @Param("max") int offset);

	public Video getById(@Param("id") int id);

	public Integer getCount();

	public void create(Video entity);

	public void update(Video entity);

	public void delete(int id);

}
