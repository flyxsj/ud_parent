package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface LuckMapper {

	public List<Luck> getList(@Param("skip") int start, @Param("max") int offset);

	public Luck getById(@Param("id") int id);

	public List<Luck> getListByStudent(@Param("studentId") int studentId, @Param("createDay") String createDay);

	public Integer getCount();

	public void create(Luck entity);

	public void update(Luck entity);

	public void delete(int id);

}
