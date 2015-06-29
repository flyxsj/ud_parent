package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface DriverAssetMapper {

	public List<DriverAsset> getList(@Param("skip") int start, @Param("max") int offset);

	public List<DriverAsset> getList(@Param("driverId") int driverId, @Param("source") String source,
			@Param("createTime") String day);

	public DriverAsset getById(@Param("id") int id);

	public Integer getCount();

	public void create(DriverAsset entity);

	public void update(DriverAsset entity);

	public void delete(int id);

}
