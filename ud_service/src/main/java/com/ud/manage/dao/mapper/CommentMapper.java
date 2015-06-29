package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface CommentMapper {

	public List<Comment> getList(@Param("skip") int start, @Param("max") int offset);

	public Comment getById(@Param("id") int id);

	public Comment getByOrderId(@Param("orderId") int orderId);

	public Integer getCount();

	public void create(Comment entity);

	public void update(Comment entity);

	public void delete(int id);

}
