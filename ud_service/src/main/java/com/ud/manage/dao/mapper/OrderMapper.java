package com.ud.manage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ud.manage.domain.Order;

public interface OrderMapper {

	public List<Order> getList(@Param("skip") int start, @Param("max") int offset);

	public List<Order> getList(@Param("status") String status, @Param("trainEndTime") String trainEndTime);

	public Order getById(@Param("id") int id);

	public Order getByOrderNumber(@Param("orderNumber") String orderNumber);

	public Order getByServiceCode(@Param("serviceCode") String serviceCode);

	public Integer getCount();

	public void create(Order entity);

	public void update(Order entity);

	public void delete(int id);

	public List<Order> getListByDriver(@Param("driverId") int driverId, @Param("status") String status,
			@Param("skip") int start, @Param("max") int offset);

	public Integer getCountByDriver(@Param("driverId") int driverId, @Param("status") String status);

	public List<Order> getListByStudent(@Param("studentId") int studentId, @Param("status") String status,
			@Param("skip") int start, @Param("max") int offset);

	public Integer getCountByStudent(@Param("driverId") int driverId, @Param("status") String status);

}
