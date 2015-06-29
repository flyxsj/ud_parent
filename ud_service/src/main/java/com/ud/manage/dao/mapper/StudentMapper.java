package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface StudentMapper {

	public List<Student> getList(@Param("skip") int start, @Param("max") int offset);

	public Student getById(@Param("id") int id);

	public Student getByOpenid(@Param("openid") String openid);

	public Integer getCount();

	public void create(Student entity);

	public void update(Student entity);

	public void delete(int id);

}
