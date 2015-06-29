package com.ud.manage.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.Driver;

public interface DriverMapper {

	public List<Driver> getList(@Param("skip") int start, @Param("max") int offset);

	public List<Map<String, Object>> matchByGeo(@Param("lon") double lon, @Param("lat") double lat,
			@Param("max") int maxSize);

	public Driver getById(@Param("id") int id);

	public Driver getByOpenid(@Param("openid") String openid);

	public Integer getCount();

	public void create(Driver entity);

	public void update(Driver entity);

	public void delete(int id);

}
