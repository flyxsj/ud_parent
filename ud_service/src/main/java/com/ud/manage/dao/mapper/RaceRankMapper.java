package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface RaceRankMapper {

	public List<RaceRank> getList(@Param("skip") int start, @Param("max") int offset);

	public List<RaceRank> getListByExamDate(@Param("examDate") String examDate, @Param("type") String type);

	public RaceRank getById(@Param("id") int id);

	public Integer getCount();

	public void create(RaceRank entity);

	public void update(RaceRank entity);

	public void delete(int id);

}
