package com.ud.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.manage.dao.mapper.AdminUserMapper;
import com.ud.manage.domain.AdminUser;

@Service
public class AdminUserService {

	@Autowired
	private AdminUserMapper mapper;

	public List<AdminUser> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public AdminUser getById(int id) {
		return mapper.getById(id);
	}

	public AdminUser getByUsername(String username) {
		return mapper.getByUsername(username);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(AdminUser entity) {
		mapper.create(entity);
	}

	public void update(AdminUser entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
