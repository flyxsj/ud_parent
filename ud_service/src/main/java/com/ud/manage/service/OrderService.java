package com.ud.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ud.manage.dao.mapper.CommentMapper;
import com.ud.manage.dao.mapper.DriverAssetMapper;
import com.ud.manage.dao.mapper.DriverMapper;
import com.ud.manage.dao.mapper.OrderMapper;
import com.ud.manage.dao.mapper.ScheduleMapper;
import com.ud.manage.dao.mapper.StudentAssetMapper;
import com.ud.manage.dao.mapper.StudentMapper;
import com.ud.manage.domain.Comment;
import com.ud.manage.domain.Driver;
import com.ud.manage.domain.DriverAsset;
import com.ud.manage.domain.DriverMatchInfo;
import com.ud.manage.domain.Order;
import com.ud.manage.domain.Schedule;
import com.ud.manage.domain.Student;
import com.ud.manage.domain.StudentAsset;
import com.ud.util.ToolsUtil;

//TODO transaction support?
@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	private static Map<String, List<Schedule>> daySchedules = new LinkedHashMap<String, List<Schedule>>();
	private static Map<String, Long> scheduleUpdateTime = new LinkedHashMap<String, Long>();

	private static final long MAX_LIVE_TIME = 30 * 1000;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Autowired
	private DriverMapper driverMapper;

	@Autowired
	private DriverAssetMapper driverAssetMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private StudentAssetMapper studentAssetMapper;

	public List<Order> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	/**
	 * 匹配教练
	 * 
	 * @param type
	 * @param lon
	 * @param lat
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<DriverMatchInfo> matchDriver(String type, double lon, double lat, Date day, Date startTime, Date endTime) {
		List<Map<String, Object>> matchByGeo = driverMapper.matchByGeo(lon, lat, 30);
		List<Map<String, Object>> tmpResult = new LinkedList<Map<String, Object>>();
		List<DriverMatchInfo> matchList = new LinkedList<DriverMatchInfo>();
		String dayStr = ToolsUtil.date2String(day, "yyyy-MM-dd");
		if (startTime == null || endTime == null || day == null) {
			return matchList;
		}
		if (CollectionUtils.isNotEmpty(matchByGeo)) {
			if (daySchedules.get(dayStr) == null
					|| (System.currentTimeMillis() - scheduleUpdateTime.get(dayStr)) > MAX_LIVE_TIME) {
				daySchedules.put(dayStr, scheduleMapper.getListByDay(dayStr));
				scheduleUpdateTime.put(dayStr, System.currentTimeMillis());
			}
			List<Schedule> schedules = daySchedules.get(dayStr);
			if (CollectionUtils.isNotEmpty(schedules)) {
				Set<Integer> set = new HashSet<Integer>();
				for (Map<String, Object> map : matchByGeo) {
					for (Schedule s : schedules) {
						int driverId = Integer.parseInt(map.get("id").toString());
						if (s.getDriverId() == driverId && s.getType().equals(type)) {
							if (startTime.getTime() >= s.getStartTime().getTime()
									&& startTime.getTime() < s.getEndTime().getTime()) {
								if (!set.contains(driverId)) {
									Map<String, Object> item = new HashMap<String, Object>();
									item.put("driverId", map.get("id"));
									item.put("gap", map.get("gap"));
									tmpResult.add(item);
									set.add(driverId);
								}
							}
						}
					}
				}
			}
		}
		System.out.println(tmpResult);
		for (Map<String, Object> map : tmpResult) {
			Driver d = driverMapper.getById(Integer.parseInt(map.get("driverId").toString()));
			double distance = Double.parseDouble(map.get("gap").toString());
			matchList.add(new DriverMatchInfo(d, distance));
		}
		return matchList;
	}

	public Order getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Order entity) {
		mapper.create(entity);
	}

	public void update(Order entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

	public String getOrderNumber() {
		final String format = "yyyyMMddHHmm";
		String number = ToolsUtil.date2String(new Date(), format) + ToolsUtil.getRandomNumber(8);
		while (mapper.getByOrderNumber(number) != null) {
			number = ToolsUtil.date2String(new Date(), format) + ToolsUtil.getRandomNumber(8);
		}
		return number;
	}

	public String getServiceCode() {
		String code = ToolsUtil.getRandomNumber(10);
		while (mapper.getByServiceCode(code) != null) {
			code = ToolsUtil.getRandomNumber(10);
		}
		return code;
	}

	public List<Order> getListByDriver(@Param("driverId") int driverId, @Param("status") String status,
			@Param("skip") int start, @Param("max") int offset) {
		return mapper.getListByDriver(driverId, status, start, offset);
	}

	public Integer getCountByDriver(@Param("driverId") int driverId, @Param("status") String status) {
		return mapper.getCountByDriver(driverId, status);
	}

	public List<Order> getListByStudent(@Param("studentId") int studentId, @Param("status") String status,
			@Param("skip") int start, @Param("max") int offset) {
		return mapper.getListByStudent(studentId, status, start, offset);
	}

	public Integer getCountByStudent(@Param("studentId") int studentId, @Param("status") String status) {
		return mapper.getCountByStudent(studentId, status);
	}

	public Order getByOrderNumber(String orderNumber) {
		return mapper.getByOrderNumber(orderNumber);
	}

	public Order getByServiceCode(String serviceCode) {
		return mapper.getByServiceCode(serviceCode);
	}

	@Transactional
	public void commentOrder(String orderNumber, String level, String comment) {
		Order o = mapper.getByOrderNumber(orderNumber);
		Comment c = commentMapper.getByOrderId(o.getId());
		if (c != null) {
			throw new RuntimeException("orderNumber=" + orderNumber + " has been commented");
		}
		if (!"end".equals(o.getStatus())) {
			throw new RuntimeException("orderNumber=" + orderNumber
					+ " can not be commented since its status isn't end");
		}
		c = new Comment();
		c.setCreateTime(new Date());
		c.setDescription(comment);
		c.setLevel(level);
		c.setOrderId(o.getId());
		commentMapper.create(c);
		updateOrderStatus(o);
		awardDriver(o, c);
		awardStudent(o, c);
	}

	private void awardStudent(Order o, Comment c) {
		Student student = studentMapper.getById(o.getStudentId());
		student.setBean(student.getBean() + c.getAwardBean());
		studentMapper.update(student);

		StudentAsset asset = new StudentAsset();
		asset.setAmount(c.getAwardBean());
		asset.setCreateTime(new Date());
		asset.setRemark("评价奖励U豆");
		asset.setSource("feedback");
		asset.setStudentId(student.getId());
		asset.setType("bean");
	}

	private void awardDriver(Order o, Comment c) {
		Driver driver = driverMapper.getById(o.getDriverId());
		driver.setScore(driver.getScore() + c.getAwardScore());
		driver.setUpdateTime(new Date());
		driverMapper.update(driver);

		DriverAsset asset = new DriverAsset();
		asset.setAmount(c.getAwardScore());
		asset.setCreateTime(new Date());
		asset.setDriverId(o.getDriverId());
		asset.setRemark("接单奖励分");
		asset.setSource("feedback");
		asset.setType("score");
		driverAssetMapper.create(asset);
	}

	private void updateOrderStatus(Order o) {
		o.setStatus("commented");
		mapper.update(o);
	}

	/**
	 * 订单日结奖励统计，当天运行一次，统计前一天的订单情况
	 */
	@Transactional
	public void statisticAward() {
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date prevDay = c.getTime();
		List<Order> endOrderList = mapper.getList("end", ToolsUtil.date2String(prevDay, "yyyy-MM-dd"));
		List<Order> commentedOrderList = mapper.getList("commented", ToolsUtil.date2String(prevDay, "yyyy-MM-dd"));
		List<Order> all = new LinkedList<Order>();
		all.addAll(endOrderList);
		all.addAll(commentedOrderList);
		Map<Integer, List<Order>> map = joinOrder(all);
		Iterator<Entry<Integer, List<Order>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, List<Order>> entry = (Map.Entry<Integer, List<Order>>) iterator.next();
			Integer driverId = entry.getKey();
			List<Order> orderList = entry.getValue();
			logger.info("order awarding to driver={}", driverId);
			List<DriverAsset> assetList = driverAssetMapper.getList(driverId, "order",
					ToolsUtil.date2String(today, "yyyy-MM-dd"));
			if (CollectionUtils.isNotEmpty(assetList)) {
				logger.warn("has been awarded to driver={}", driverId);
			} else {
				Driver driver = driverMapper.getById(driverId);
				driver.setUpdateTime(new Date());
				DriverAsset asset = new DriverAsset();
				int count = orderList.size();
				int award = 0;
				if (count <= 3) {
					award = count * 10;
				} else if (count > 3 && count <= 6) {
					award = count * 15;
				} else {
					award = count * 10 * 2;
				}
				asset.setAmount(award);
				asset.setDriverId(driverId);
				asset.setCreateTime(new Date());
				asset.setRemark("订单日结奖励，订单数：" + count);
				asset.setSource("order");
				asset.setType("ub");
				driverAssetMapper.create(asset);
				driver.setUb(driver.getUb() + award);
				driverMapper.update(driver);
			}
		}
	}

	private Map<Integer, List<Order>> joinOrder(List<Order> orderList) {
		Map<Integer, List<Order>> map = new HashMap<Integer, List<Order>>();
		for (Order order : orderList) {
			List<Order> list = map.get(order.getDriverId());
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<Order>();
				list.add(order);
				map.put(order.getDriverId(), list);
			} else {
				map.get(order.getDriverId()).add(order);
			}
		}
		return map;
	}
}
