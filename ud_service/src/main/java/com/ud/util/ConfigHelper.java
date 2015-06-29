package com.ud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigHelper {

	private Properties props = new Properties();
	private static String defaultConfFileName = "conf/system.properties";
	private static Map<String, ConfigHelper> map = new HashMap<String, ConfigHelper>();

	private static long readFileTime = System.currentTimeMillis();
	private static final int REFRESH_TIME = 5 * 60 * 1000;

	private static Logger logger = LoggerFactory.getLogger(ConfigHelper.class);

	public synchronized static ConfigHelper getInstance(String conFileName) {

		ConfigHelper me = map.get(conFileName);
		if (me == null) {
			me = new ConfigHelper(conFileName);
			map.put(conFileName, me);
		} else if (System.currentTimeMillis() - readFileTime > REFRESH_TIME) {
			me.refreshConfigure();
			map.put(conFileName, me);
		}
		return me;
	}

	public synchronized static ConfigHelper getInstance() {

		ConfigHelper me = map.get(defaultConfFileName);
		if (me == null) {
			me = new ConfigHelper(defaultConfFileName);
			map.put(defaultConfFileName, me);
		} else if (System.currentTimeMillis() - readFileTime > REFRESH_TIME) {
			me.refreshConfigure();
			map.put(defaultConfFileName, me);
		}
		return me;
	}

	private ConfigHelper(String conFileName) {

		defaultConfFileName = conFileName;
		refreshConfigure();
	}

	private void refreshConfigure() {

		FileInputStream in = null;
		InputStreamReader reader = null;
		try {
			props.clear();
			File f = new File(ConfigHelper.class.getClassLoader().getResource(defaultConfFileName).toURI());
			in = new FileInputStream(f);
			reader = new InputStreamReader(in);
			props.load(reader);
			readFileTime = System.currentTimeMillis();
			logger.info("load config file at " + readFileTime);
		} catch (IOException e) {
			logger.error("reload config file fail!", e);
		} catch (URISyntaxException e) {
			logger.error("reload config file fail!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return
	 */
	public String getValue(String key) {

		String value = props.getProperty(key);
		if (value == null || value.trim().equals("")) {
			return null;
		}
		return value;
	}

	/**
	 * @return
	 */
	public String[] getStringArray(String key) {

		String value = props.getProperty(key);
		if (value == null || value.trim().equals("")) {
			return null;
		}

		return value.split(",");
	}

	public Set<String> getStringSet(String key) {

		String[] ss = getStringArray(key);
		if (ss == null) {
			return null;
		}
		return new HashSet<String>(Arrays.asList(ss));
	}

	/**
	 * @return
	 */
	public Integer getIntValue(String key) {

		if (getValue(key) == null)
			return null;
		return Integer.parseInt(getValue(key));
	}

	/**
	 * 
	 * @return
	 */
	public Long getLongValue(String key) {

		if (getValue(key) == null)
			return null;
		return Long.parseLong(getValue(key));
	}

}
