package com.ud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static SimpleDateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat shortFormatter = new SimpleDateFormat("yyyy-MM-dd");

	public synchronized static String formatSQLDate(Date date) {
		return sqlDateFormatter.format(date);
	}

	public synchronized static Date formatSQLDate(String date) {
		try {
			return sqlDateFormatter.parse(date);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public synchronized static String formatShortDate(Date date) {
		return shortFormatter.format(date);
	}

	public synchronized static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date getLastTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		String date1Str = formatShortDate(date1);
		String date2Str = formatShortDate(date2);
		return date1Str.equals(date2Str);
	}

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 20);
		c.set(Calendar.DAY_OF_MONTH, 12);
		System.out.println(isSameDay(new Date(), c.getTime()));
	}

}
