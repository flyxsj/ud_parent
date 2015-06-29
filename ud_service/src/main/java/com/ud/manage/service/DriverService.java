package com.ud.manage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ud.manage.dao.mapper.DriverAssetMapper;
import com.ud.manage.dao.mapper.DriverMapper;
import com.ud.manage.domain.Driver;
import com.ud.manage.domain.DriverAsset;

@Service
public class DriverService {

	@Autowired
	private DriverMapper mapper;
	@Autowired
	private DriverAssetMapper assetMapper;

	public List<Driver> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public List<Map<String, Object>> matchByGeo(@Param("lon") double lon, @Param("lat") double lat,
			@Param("max") int maxSize) {
		return mapper.matchByGeo(lon, lat, maxSize);
	}

	public Driver getById(int id) {
		return mapper.getById(id);
	}

	public Driver getByOpenid(String openid) {
		return mapper.getByOpenid(openid);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void register(Driver driver) {
		mapper.create(driver);
	}

	/**
	 * 审核通过
	 * 
	 * @param driver
	 */
	@Transactional
	public void authPass(Driver driver) {
		if (!driver.getStatus().equals("I")) {
			return;
		}
		driver.setStatus("A");
		int score = driver.getRegisterAwardScore();
		int ub = driver.getRegisterAwardUB();
		driver.setScore(score);
		driver.setUb(ub);
		driver.setUpdateTime(new Date());
		createScoreAsset(driver);
		createUBAsset(driver);
		mapper.update(driver);
	}

	private void createScoreAsset(Driver driver) {
		int score = driver.getRegisterAwardScore();
		if (score > 0) {
			DriverAsset asset = new DriverAsset();
			asset.setAmount(score);
			asset.setCreateTime(new Date());
			asset.setDriverId(driver.getId());
			asset.setSource("register");
			asset.setRemark("教练注册奖励积分");
			asset.setType("score");
			assetMapper.create(asset);
		}
	}

	private void createUBAsset(Driver driver) {
		int ub = driver.getRegisterAwardUB();
		if (ub > 0) {
			DriverAsset asset = new DriverAsset();
			asset.setAmount(ub);
			asset.setCreateTime(new Date());
			asset.setDriverId(driver.getId());
			asset.setSource("register");
			asset.setRemark("教练注册奖励U币");
			asset.setType("ub");
			assetMapper.create(asset);
		}
	}

	public void update(Driver entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}
