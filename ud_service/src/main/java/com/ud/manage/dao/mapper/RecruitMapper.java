package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface RecruitMapper {

	public List<Recruit> getList(@Param("skip") int start, @Param("max") int offset);

	public Recruit getById(@Param("id") int id);

	public Integer getCount();

	public void create(Recruit entity);

	public void update(Recruit entity);

	public void delete(int id);

}
