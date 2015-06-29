package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface ExamMapper {

	public List<Exam> getList(@Param("skip") int start, @Param("max") int offset);

	public Exam getById(@Param("id") int id);

	public Integer getCount();

	public void create(Exam entity);

	public void update(Exam entity);

	public void delete(int id);

}
