package com.ud.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ToolsUtil {

	private static final long ROOT_PARENTMESSAGEID = 0;

	private static final char[] zeroArray = "0000000000000000".toCharArray();

	private static final StringBuffer characters = new StringBuffer(
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");

	public static final String zeroPadString(String string, int length) {

		if (string == null || string.length() > length) {
			return string;
		}
		StringBuffer buf = new StringBuffer(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

	public static final String dateToMillis(long now) {

		return zeroPadString(Long.toString(now), 15);
	}

	public static Long getParentIDOfRoot() {

		return new Long(ROOT_PARENTMESSAGEID);
	}

	public static String getUuid() {

		return UUID.randomUUID().toString();
	}

	public static boolean isRoot(Long parentID) {

		if (parentID.longValue() == ROOT_PARENTMESSAGEID)
			return true;
		else
			return false;

	}

	public static final String dateToMillis(Date date) {

		return zeroPadString(Long.toString(date.getTime()), 15);
	}

	public static String dateToNoMillis(Date date) {

		// 001184284800000
		String s = dateToMillis(date);
		StringBuffer sb = new StringBuffer(s.substring(0, 10));
		sb.append("00000");
		return sb.toString();
	}

	public static java.util.Date toDate(String date, String time, String split) {

		if (date == null || time == null)
			return null;
		String month;
		String day;
		String year;
		String hour;
		String minute;
		String second;

		int dateSlash1 = date.indexOf(split);
		int dateSlash2 = date.lastIndexOf(split);

		if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
			return null;
		int timeColon1 = time.indexOf(":");
		int timeColon2 = time.lastIndexOf(":");

		if (timeColon1 <= 0)
			return null;
		year = date.substring(0, dateSlash1);
		month = date.substring(dateSlash1 + 1, dateSlash2);
		day = date.substring(dateSlash2 + 1);
		hour = time.substring(0, timeColon1);

		if (timeColon1 == timeColon2) {
			minute = time.substring(timeColon1 + 1);
			second = "0";
		} else {
			minute = time.substring(timeColon1 + 1, timeColon2);
			second = time.substring(timeColon2 + 1);
		}

		return toDate(month, day, year, hour, minute, second);
	}

	public static String toDateString(java.util.Date date, String splite) {

		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		String monthStr;
		String dayStr;
		String yearStr;

		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = "" + month;
		}
		if (day < 10) {
			dayStr = "0" + day;
		} else {
			dayStr = "" + day;
		}
		yearStr = "" + year;
		return yearStr + splite + monthStr + splite + dayStr;
	}

	public static String toDateTimeString(java.util.Date date) {

		if (date == null)
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String toDateHourString(java.util.Date date) {

		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		StringBuffer sb = new StringBuffer(toDateString(date));
		sb.append(toHourString(calendar.get(Calendar.HOUR_OF_DAY)));
		return sb.toString();
	}

	private static String toHourString(int hour) {

		String hourStr;

		if (hour < 10) {
			hourStr = "0" + hour;
		} else {
			hourStr = "" + hour;
		}
		return hourStr;
	}

	public static java.util.Date toDate(String monthStr, String dayStr, String yearStr, String hourStr,
			String minuteStr, String secondStr) {

		int month, day, year, hour, minute, second;

		try {
			month = Integer.parseInt(monthStr);
			day = Integer.parseInt(dayStr);
			year = Integer.parseInt(yearStr);
			hour = Integer.parseInt(hourStr);
			minute = Integer.parseInt(minuteStr);
			second = Integer.parseInt(secondStr);
		} catch (Exception e) {
			return null;
		}
		return toDate(month, day, year, hour, minute, second);
	}

	public static Date toDate(String dateStr, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static java.util.Date toDate(int month, int day, int year, int hour, int minute, int second) {

		Calendar calendar = Calendar.getInstance();

		try {
			calendar.set(year, month - 1, day, hour, minute, second);
		} catch (Exception e) {
			return null;
		}
		return new java.util.Date(calendar.getTime().getTime());
	}

	public static String toDateString(java.util.Date date) {

		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		String monthStr;
		String dayStr;
		String yearStr;

		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = "" + month;
		}
		if (day < 10) {
			dayStr = "0" + day;
		} else {
			dayStr = "" + day;
		}
		yearStr = "" + year;
		return monthStr + "/" + dayStr + "/" + yearStr;
	}

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	public synchronized static final String hash(String data) {

		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. "
						+ "Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String encodeHex(byte[] bytes) {

		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String getParameterFromQueryString(String queryString, String paramName) {

		String[] s = getParamsFromQueryString(queryString, paramName);
		if (s != null && s.length >= 1) {
			return s[0];
		}
		return null;
	}

	public static String[] getParamsFromQueryString(String queryString, String paramName) {

		if (paramName == null || paramName.length() < 1 || paramName == null || paramName.length() < 1) {
			return new String[0];
		}
		List<String> rsl = new ArrayList<String>();
		String params[] = queryString.split("&");
		for (int i = 0; i < params.length; i++) {
			// System.out.println(params[i]);
			if (params[i] != null && params[i].startsWith(paramName + "=")) {
				try {
					rsl.add(java.net.URLDecoder.decode(params[i].substring(paramName.length() + 1), "UTF-8")); // 与tomcat中URIEncoding="UTF-8"。
				} catch (UnsupportedEncodingException ex) {
				}
			}
		}
		return rsl.toArray(new String[0]);
	}

	public static String gbToUtf8(String str) throws UnsupportedEncodingException {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (s.charAt(0) > 0x80) {
				byte[] bytes = s.getBytes("Unicode");
				String binaryStr = "";
				for (int j = 2; j < bytes.length; j += 2) {
					// the first byte
					String hexStr = getHexString(bytes[j + 1]);
					String binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
					// the second byte
					hexStr = getHexString(bytes[j]);
					binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
				}
				// convert unicode to utf-8
				String s1 = "1110" + binaryStr.substring(0, 4);
				String s2 = "10" + binaryStr.substring(4, 10);
				String s3 = "10" + binaryStr.substring(10, 16);
				byte[] bs = new byte[3];
				bs[0] = Integer.valueOf(s1, 2).byteValue();
				bs[1] = Integer.valueOf(s2, 2).byteValue();
				bs[2] = Integer.valueOf(s3, 2).byteValue();
				String ss = new String(bs, "UTF-8");
				sb.append(ss);
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	private static String getHexString(byte b) {

		String hexStr = Integer.toHexString(b);
		int m = hexStr.length();
		if (m < 2) {
			hexStr = "0" + hexStr;
		} else {
			hexStr = hexStr.substring(m - 2);
		}
		return hexStr;
	}

	private static String getBinaryString(int i) {

		String binaryStr = Integer.toBinaryString(i);
		int length = binaryStr.length();
		for (int l = 0; l < 8 - length; l++) {
			binaryStr = "0" + binaryStr;
		}
		return binaryStr;
	}

	public static String replaceBlank(String s, String reg) {

		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(s);
		return m.replaceAll("");
	}

	public static String getRandomStr(int length) {

		StringBuffer sbf = new StringBuffer();
		Random rand = new Random();
		int charSize = characters.length();
		for (int i = 0; i < length; i++) {
			sbf.append(characters.charAt(rand.nextInt(charSize)));
		}

		return sbf.toString();
	}

	public static String date2String(Date date, String format) {

		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	// string ip to long
	public static long ip2Long(String ipaddress) {

		long[] ip = new long[4];
		int position1 = ipaddress.indexOf(".");
		int position2 = ipaddress.indexOf(".", position1 + 1);
		int position3 = ipaddress.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(ipaddress.substring(0, position1));
		ip[1] = Long.parseLong(ipaddress.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(ipaddress.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(ipaddress.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	// ip long to String
	public String long2Ip(long ipaddress) {

		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf((ipaddress >>> 24)));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x000000FF)));
		return sb.toString();
	}

	public static boolean isValidEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static void main(String[] args) {
		System.out.println(isValidEmail("ads@fdas.com"));
		System.out.println(isValidEmail("ads@fda's.com"));
		System.out.println(isValidEmail("ads"));
		System.out.println(isValidEmail("adsfdsa'da@fascom"));
		System.out.println(isValidEmail("21-a@fas.com"));
		System.out.println(isValidEmail("21_a@fas.com"));
	}
}
