package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface AdminUserMapper {

	public List<AdminUser> getList(@Param("skip") int start, @Param("max") int offset);

	public AdminUser getById(@Param("id") int id);

	public AdminUser getByUsername(@Param("username") String username);

	public Integer getCount();

	public void create(AdminUser entity);

	public void update(AdminUser entity);

	public void delete(int id);

}
