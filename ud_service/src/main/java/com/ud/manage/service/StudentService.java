package com.ud.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ud.manage.dao.mapper.StudentAssetMapper;
import com.ud.manage.dao.mapper.StudentMapper;
import com.ud.manage.domain.Student;
import com.ud.manage.domain.StudentAsset;
import com.ud.util.ToolsUtil;

@Service
public class StudentService {

	private static final int STUDY_AWARD_BEAN = 10;
	@Autowired
	private StudentMapper mapper;

	@Autowired
	private StudentAssetMapper assetMapper;

	public List<Student> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public Student getById(int id) {
		return mapper.getById(id);
	}

	public Student getByOpenid(String openid) {
		return mapper.getByOpenid(openid);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Student entity) {
		mapper.create(entity);
	}

	@Transactional
	public void register(Student entity) {
		entity.setBean(100);
		entity.setUb(10);
		entity.setCreateTime(new Date());
		mapper.create(entity);
		createBeanAward(entity);
		createUBAward(entity);
	}

	private void createBeanAward(Student entity) {
		StudentAsset asset = new StudentAsset();
		asset.setAmount(100);
		asset.setCreateTime(new Date());
		asset.setRemark("注册赠送100U豆");
		asset.setSource("register");
		asset.setStudentId(entity.getId());
		asset.setType("bean");
		assetMapper.create(asset);
	}

	private void createUBAward(Student entity) {
		StudentAsset asset = new StudentAsset();
		asset.setAmount(10);
		asset.setCreateTime(new Date());
		asset.setRemark("注册赠送10U币");
		asset.setSource("register");
		asset.setStudentId(entity.getId());
		asset.setType("ub");
		assetMapper.create(asset);
	}

	public void update(Student entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

	/**
	 * 学习奖励
	 * 
	 * @param student
	 */
	@Transactional
	public String createStudyAward(Student student) {
		Date now = new Date();
		List<StudentAsset> list = assetMapper.getListBySource(student.getId(), "study",
				ToolsUtil.date2String(now, "yyyy-MM-dd"));
		if (CollectionUtils.isNotEmpty(list)) {
			return "awarded";
		}
		StudentAsset asset = new StudentAsset();
		asset.setAmount(STUDY_AWARD_BEAN);
		asset.setCreateTime(now);
		asset.setRemark("每天学习奖励U豆");
		asset.setSource("study");
		asset.setStudentId(student.getId());
		asset.setType("bean");
		assetMapper.create(asset);
		student.setBean(student.getBean() + STUDY_AWARD_BEAN);
		mapper.update(student);
		return "ok";
	}
}
