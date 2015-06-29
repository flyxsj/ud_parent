package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.*;

public interface StudentAssetMapper {

	public List<StudentAsset> getList(@Param("skip") int start, @Param("max") int offset);

	public List<StudentAsset> getListBySource(@Param("studentId") int studentId, @Param("source") String source,
			@Param("createTime") String day);

	public StudentAsset getById(@Param("id") int id);

	public Integer getCount();

	public void create(StudentAsset entity);

	public void update(StudentAsset entity);

	public void delete(int id);

}
