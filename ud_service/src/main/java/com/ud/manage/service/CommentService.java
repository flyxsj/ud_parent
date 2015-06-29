package com.ud.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.manage.dao.mapper.*;
import com.ud.manage.domain.*;

@Service
public class CommentService {

	@Autowired
	private CommentMapper mapper;

	public List<Comment> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public Comment getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Comment entity) {
		mapper.create(entity);
	}

	public void update(Comment entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
