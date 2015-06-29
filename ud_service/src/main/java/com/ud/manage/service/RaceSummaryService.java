package com.ud.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.manage.dao.mapper.*;
import com.ud.manage.domain.*;

@Service
public class RaceSummaryService {

	@Autowired
	private RaceSummaryMapper mapper;

	public List<RaceSummary> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public RaceSummary getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(RaceSummary entity) {
		mapper.create(entity);
	}

	public void update(RaceSummary entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
