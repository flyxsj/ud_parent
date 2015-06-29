package com.ud.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static void main(String[] args) {
		// System.out.println(postRequest("http://localhost:89/ad//external/check_image.action",
		// null));
		System.out
				.println(downloadFile(
						"http://mmbiz.qpic.cn/mmbiz/PqkFPEkpx9IW5EbykNtyWmiaicZ2TMNsjPVpvqcbCke0TiaFJjrhInhic5EbOP0YDibpv6p7VVgibogVD1jLyaQZ7vXQ/0",
						"E:/temp/test.png"));
	}

	public static String postRequest(String url, Map<String, String> parameters) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		if (MapUtils.isNotEmpty(parameters)) {
			NameValuePair[] pairs = new NameValuePair[parameters.size()];
			Iterator<Entry<String, String>> iterator = parameters.entrySet().iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
				pairs[index++] = new NameValuePair(entry.getKey(), entry.getValue());
			}
			method.setRequestBody(pairs);
		}

		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("post method fail.url={},status={}", url, statusCode);
				return "";
			}
			byte[] responseBody = method.getResponseBody();
			return new String(responseBody, "UTF-8");
		} catch (Exception e) {
			logger.error("url=" + url, e);
			return "";
		} finally {
			method.releaseConnection();
		}
	}

	public static boolean downloadFile(String url, String filePath) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("downloadFile fail.url={},status={}", url, statusCode);
				return false;
			}
			BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int len;
			while ((len = bis.read()) != -1) {
				bos.write(len);
			}
			bos.flush();
			bos.close();
			fos.close();
			bis.close();
			return true;
		} catch (Exception e) {
			logger.error("url=" + url, e);
			return false;
		} finally {
			method.releaseConnection();
		}
	}
}
