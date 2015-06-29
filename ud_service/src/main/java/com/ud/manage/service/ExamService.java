package com.ud.manage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.manage.dao.mapper.ExamMapper;
import com.ud.manage.dao.mapper.RaceMapper;
import com.ud.manage.domain.Answer;
import com.ud.manage.domain.Exam;
import com.ud.manage.domain.Race;
import com.ud.util.ToolsUtil;

@Service
public class ExamService {

	private static final Logger logger = LoggerFactory.getLogger(ExamService.class);

	private static final int MAX_EXAM_TIME = 60 * 60 * 1000;

	private static final int QUESTION_SIZE = 6;

	private static final Map<String, List<Integer>> examQuestionMap = new LinkedHashMap<String, List<Integer>>();
	private static final Map<String, List<Answer>> examAnswerMap = new LinkedHashMap<String, List<Answer>>();
	private static final Map<String, Date> examTimeMap = new LinkedHashMap<String, Date>();

	@Autowired
	private ExamMapper mapper;

	@Autowired
	private RaceMapper raceMapper;

	public List<Exam> getList(int start, int offset) {
		return mapper.getList(start, offset);
	}

	public List<Exam> getListByType(String type, int start, int offset) {
		return mapper.getList(type, start, offset);
	}

	public List<Integer> getIdList(String type) {
		return mapper.getIdList(type);
	}

	public Exam getById(int id) {
		return mapper.getById(id);
	}

	public Integer getCount() {
		return mapper.getCount();
	}

	public Integer getCountByType(String type) {
		return mapper.getCount(type);
	}

	public void create(Exam entity) {
		mapper.create(entity);
	}

	public void update(Exam entity) {
		mapper.update(entity);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

	public List<Integer> getRandomExamIdList(List<String> typeList, int size) {
		List<Integer> idList = new ArrayList<Integer>();
		if (CollectionUtils.isEmpty(typeList)) {
			idList = mapper.getIdList(null);
		} else {
			for (String type : typeList) {
				idList.addAll(mapper.getIdList(type));
			}
		}
		if (size >= idList.size()) {
			return idList;
		} else {
			List<Integer> result = new ArrayList<Integer>();
			Set<Integer> set = new HashSet<Integer>();
			Random r = new Random();
			while (set.size() < size) {
				set.add(idList.get(r.nextInt(idList.size())));
			}
			result.addAll(set);
			return result;
		}
	}

	public List<Exam> getSimulateExamList(String examSessionUUID, int start, int offset) {
		List<Integer> idList = examQuestionMap.get(examSessionUUID);
		List<Exam> result = new ArrayList<Exam>(offset);
		if (start >= idList.size()) {
			return new ArrayList<Exam>();
		} else {
			int index = start;
			while (index < (start + offset) && index < idList.size()) {
				result.add(getById(idList.get(index)));
				index++;
			}
		}
		return result;
	}

	public void initSimulateExam(String type, String examSessionUUID) {
		// TODO simulate and event have different exam sources.
		examQuestionMap.put(examSessionUUID, this.getRandomExamIdList(null, QUESTION_SIZE));
		examTimeMap.put(examSessionUUID, new Date());
	}

	public void saveAnswer(String examSessionUUID, Answer answer) {
		List<Answer> list = examAnswerMap.get(examSessionUUID);
		if (CollectionUtils.isEmpty(list)) {
			list = new LinkedList<Answer>();
			list.add(answer);
			examAnswerMap.put(examSessionUUID, list);
		} else {
			boolean flag = false;
			for (Answer a : list) {
				if (a.getExamId() == answer.getExamId()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				examAnswerMap.get(examSessionUUID).add(answer);
			}
		}
	}

	public Race endExam(String type, String examSessionUUID) {
		List<Answer> list = examAnswerMap.get(examSessionUUID);
		Date end = new Date();
		if (CollectionUtils.isNotEmpty(list)) {
			int total = 0;
			int studentId = 0;
			for (Answer answer : list) {
				Exam exam = mapper.getById(answer.getExamId());
				studentId = answer.getStudentId();
				String answerText = answer.getAnswer().toUpperCase();
				String[] array = answerText.split(",");
				String[] standard = exam.getAnswer().toUpperCase().split(",");
				Arrays.sort(array);
				Arrays.sort(standard);
				if (ArrayUtils.isEquals(array, standard)) {
					total++;
				}
			}
			Race race = new Race();
			race.setScore(total);
			race.setStudentId(studentId);
			race.setStartTime(examTimeMap.get(examSessionUUID));
			race.setEndTime(end);
			race.setType(type);
			if (type.equals("simulate")) {
				if (total >= 90) {
					raceMapper.create(race);
				} else {
					logger.warn("student={} score is less than 90.don't record it.", studentId);
				}
			} else {
				raceMapper.create(race);
			}
			clearExamSessionInfo(examSessionUUID);
			return race;
		}
		return null;
	}

	public void clearExamSessionInfo(String examSessionUUID) {
		examQuestionMap.remove(examSessionUUID);
		examAnswerMap.remove(examSessionUUID);
		examTimeMap.remove(examSessionUUID);
	}

	/**
	 * 
	 * @param examSessionUUID
	 * @return the remaining seconds
	 */
	public int getRemainingTime(String examSessionUUID) {
		Date start = examTimeMap.get(examSessionUUID);
		Date end = new Date();
		if (ToolsUtil.isSameDay(start, end)) {
			long usingTime = end.getTime() - start.getTime();
			return (int) ((MAX_EXAM_TIME - usingTime) / 1000);
		} else {
			return 0;
		}
	}
}
