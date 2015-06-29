package com.ud.manage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ud.manage.dao.mapper.RaceMapper;
import com.ud.manage.dao.mapper.RaceRankMapper;
import com.ud.manage.dao.mapper.RaceSummaryMapper;
import com.ud.manage.dao.mapper.StudentAssetMapper;
import com.ud.manage.dao.mapper.StudentMapper;
import com.ud.manage.domain.Race;
import com.ud.manage.domain.RaceRank;
import com.ud.manage.domain.RaceSummary;
import com.ud.manage.domain.Student;
import com.ud.manage.domain.StudentAsset;
import com.ud.util.ToolsUtil;

@Service
public class RaceService {

	private static final Logger logger = LoggerFactory.getLogger(ExamService.class);
	@Autowired
	private RaceMapper mapper;
	@Autowired
	private RaceSummaryMapper summaryMapper;
	@Autowired
	private RaceRankMapper rankMapper;
	@Autowired
	private StudentAssetMapper studentAssetMapper;
	@Autowired
	private StudentMapper studentMapper;

	public List<Race> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public Race getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public void create(Race entity) {
		mapper.create(entity);
	}

	public void update(Race entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

	public void handleSimulateRank(Date examDate) {
		handleRank(examDate, "simulate");
	}

	public void handleEventRank(Date examDate) {
		handleRank(examDate, "event");
	}

	/**
	 * 对考试分数进行排名
	 * 
	 * @param examDate
	 */
	private void handleRank(Date examDate, String raceType) {
		String dateStr = ToolsUtil.date2String(examDate, "yyyy-MM-dd");
		List<RaceRank> existingRankList = rankMapper.getListByExamDate(dateStr, raceType);
		if (CollectionUtils.isNotEmpty(existingRankList)) {
			logger.warn("has handled rank statistic task of exam date={}", dateStr);
			return;
		}
		List<Race> simulateList = mapper.getListByExamDate(dateStr, raceType);
		Map<Integer, List<Race>> map = joinByStudent(simulateList);
		List<RaceSummary> summaryList = summarizeByDate(examDate, map, raceType);
		List<RaceRank> rankList = rank(summaryList, raceType);
		reward(raceType, rankList);
	}

	/**
	 * 奖励前3名
	 * 
	 * @param raceType
	 * @param rankList
	 */
	@Transactional
	public void reward(String raceType, List<RaceRank> rankList) {
		for (int j = 0; j < rankList.size(); j++) {
			RaceRank item = rankList.get(j);
			StudentAsset asset = new StudentAsset();
			asset.setCreateTime(new Date());
			asset.setStudentId(item.getStudentId());
			if (raceType.equals("simulate")) {
				asset.setSource("simulate_rank");
				asset.setType("bean");
				asset.setRemark("模拟比赛第" + (j + 1) + "名奖励");
			} else if (raceType.equals("event")) {
				asset.setSource("event_rank");
				asset.setType("ub");
				asset.setRemark("月赛第" + (j + 1) + "名奖励");
			}
			switch (j + 1) {
			case 1:
				asset.setAmount(100);
				break;
			case 2:
				asset.setAmount(60);
				break;
			case 3:
				asset.setAmount(30);
				break;
			default:
				asset = null;
				break;
			}
			if (asset != null) {
				studentAssetMapper.create(asset);
				Student student = studentMapper.getById(asset.getStudentId());
				if (raceType.equals("simulate")) {
					student.setBean(student.getBean() + asset.getAmount());
				} else if (raceType.equals("event")) {
					student.setUb(student.getUb() + asset.getAmount());
				}
				studentMapper.update(student);
			}
		}
	}

	/**
	 * 根据考试汇总统计前X名
	 * 
	 * @param summaryList
	 */
	private List<RaceRank> rank(List<RaceSummary> summaryList, String raceType) {
		List<RaceRank> list = new ArrayList<RaceRank>();
		Collections.sort(summaryList, new Comparator<RaceSummary>() {
			@Override
			public int compare(RaceSummary o1, RaceSummary o2) {
				boolean swap = false;
				if (o1.getScore() < o2.getScore()) {
					swap = true;
				} else if (o1.getScore() == o2.getScore()) {
					if (o1.getConsumedTime() > o2.getConsumedTime()) {
						swap = true;
					}
				}
				if (swap) {
					return 1;
				}
				return 0;
			}

		});
		int topN = 3;
		if (summaryList.size() < topN) {
			topN = summaryList.size();
		}
		for (int i = 0; i < topN; i++) {
			RaceSummary summary = summaryList.get(i);
			RaceRank rank = new RaceRank();
			rank.setStudentId(summary.getStudentId());
			rank.setExamDate(summary.getExamDate());
			rank.setRank(i + 1);
			rank.setScore(summary.getScore());
			rank.setType(raceType);
			rank.setConsumedTime(summary.getConsumedTime());
			rankMapper.create(rank);
			list.add(rank);
		}
		return list;
	}

	/**
	 * 汇总用户当天的最好考试结果
	 * 
	 * @param examDate
	 * @param map
	 * @return
	 */
	private List<RaceSummary> summarizeByDate(Date examDate, Map<Integer, List<Race>> map, String raceType) {
		Set<Entry<Integer, List<Race>>> entrySet = map.entrySet();
		Iterator<Entry<Integer, List<Race>>> iterator = entrySet.iterator();
		List<RaceSummary> summaryList = new LinkedList<RaceSummary>();
		while (iterator.hasNext()) {
			Map.Entry<Integer, List<Race>> entry = (Map.Entry<Integer, List<Race>>) iterator.next();
			int studentId = entry.getKey();
			List<Race> list = entry.getValue();
			RaceSummary summary = new RaceSummary();
			summary.setType(raceType);
			summary.setExamDate(examDate);
			summary.setStudentId(studentId);
			Race bestRace = getBestRace(list);
			summary.setConsumedTime(bestRace.getEndTime().getTime() - bestRace.getStartTime().getTime());
			summary.setScore(bestRace.getScore());
			summaryMapper.create(summary);
			summaryList.add(summary);
		}
		return summaryList;
	}

	/**
	 * 获取当天考试结果最好的记录
	 * 
	 * @param list
	 * @return
	 */
	private Race getBestRace(List<Race> list) {
		Race tmpRace = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			Race item = list.get(i);
			if (item.getScore() > tmpRace.getScore()) {
				tmpRace = item;
			} else if (item.getScore() == tmpRace.getScore()) {
				if ((item.getEndTime().getTime() - item.getStartTime().getTime()) < (tmpRace.getEndTime().getTime() - tmpRace
						.getStartTime().getTime())) {
					tmpRace = item;
				}
			}
		}
		return tmpRace;
	}

	/**
	 * 对所有考试数据按学员id进行分类
	 * 
	 * @param simulateList
	 * @return
	 */
	private Map<Integer, List<Race>> joinByStudent(List<Race> simulateList) {
		Map<Integer, List<Race>> map = new LinkedHashMap<Integer, List<Race>>();
		for (Iterator<Race> iterator = simulateList.iterator(); iterator.hasNext();) {
			Race race = (Race) iterator.next();
			if (map.get(race.getStudentId()) == null) {
				List<Race> list = new ArrayList<Race>();
				list.add(race);
				map.put(race.getStudentId(), list);
			} else {
				map.get(race.getStudentId()).add(race);
			}
		}
		return map;
	}
}
