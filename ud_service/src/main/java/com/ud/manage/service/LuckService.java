package com.ud.manage.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ud.manage.dao.mapper.LuckMapper;
import com.ud.manage.dao.mapper.StudentAssetMapper;
import com.ud.manage.dao.mapper.StudentMapper;
import com.ud.manage.domain.Luck;
import com.ud.manage.domain.Student;
import com.ud.manage.domain.StudentAsset;
import com.ud.util.ToolsUtil;

@Service
public class LuckService {

	@Autowired
	private LuckMapper mapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private StudentAssetMapper studentAssetMapper;

	public List<Luck> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public List<Luck> getListByStudent(int studentId, Date createDay) {
		String date = ToolsUtil.date2String(createDay, "yyyy-MM-dd");
		return mapper.getListByStudent(studentId, date);
	}

	public Luck getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Luck entity) {
		mapper.create(entity);
	}

	public void update(Luck entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

	public Luck getLuck(Student student) {
		Random r = new Random();
		int b = r.nextInt(11);
		while (b == 0) {
			b = r.nextInt(11);
		}
		Luck luck = new Luck();
		luck.setBean(b * 10);
		luck.setCreateTime(new Date());
		luck.setIsShare(0);
		luck.setStudentId(student.getId());
		mapper.create(luck);
		return luck;
	}

	@Transactional
	public void shareSuccess(Luck luck) {
		Student student = studentMapper.getById(luck.getStudentId());
		StudentAsset asset = new StudentAsset();
		asset.setType("bean");
		asset.setStudentId(luck.getStudentId());
		asset.setCreateTime(new Date());
		asset.setRemark("每次摇奖U豆奖励");
		asset.setAmount(luck.getBean());
		asset.setSource("luck");
		studentAssetMapper.create(asset);
		student.setBean(student.getBean() + luck.getBean());
		studentMapper.update(student);
		luck.setIsShare(1);
		mapper.update(luck);
	}
}
