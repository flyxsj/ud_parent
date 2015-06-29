package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface RaceSummaryMapper {

	public List<RaceSummary> getList(@Param("skip") int start, @Param("max") int offset);

	public RaceSummary getById(@Param("id") int id);

	public Integer getCount();

	public void create(RaceSummary entity);

	public void update(RaceSummary entity);

	public void delete(int id);

}
