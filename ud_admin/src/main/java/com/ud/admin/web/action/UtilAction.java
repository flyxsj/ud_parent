/**
 * 
 */

package com.ud.admin.web.action;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ud.manage.service.FileStoreService;

@Controller
@RequestMapping("/admin/util/")
public class UtilAction extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(UtilAction.class);
	@Autowired
	protected FileStoreService storeService;

	@RequestMapping("exam_upload.do")
	public void upload(HttpServletRequest request, HttpServletResponse response, PrintWriter out,
			@RequestParam(value = "action", required = false, defaultValue = "upload") String action) {
		response.setCharacterEncoding("utf8");
//		response.setContentType("application/json");
		if ("config".equals(action)) {
			try {
				File f = new File(UtilAction.class.getClassLoader().getResource("conf/ue_config.properties").toURI());
				out.print(FileUtils.readFileToString(f, "UTF-8"));
			} catch (Exception e) {
				logger.error("", e);
			}
		} else {
			String callback = request.getParameter("callback");
			response.setContentType("text/html");
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			MultipartFile multipartFile = null;
			String filePath = null;
			for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
				multipartFile = set.getValue();
			}
			filePath = storeService.storeFile("exam", multipartFile);
//			filePath = "exam/201506/26/07ccab59-d148-487c-868a-1ae10216407b.png";
			// out.print(fileName);
			String result = "{\"name\":\"test\", \"originalName\": \"testo\", \"size\": " + multipartFile.getSize()
					+ ", \"state\": \"SUCCESS\", \"type\": \".png\", \"url\": \"" + filePath + "\"}";
			result = result.replaceAll("\\\\", "\\\\");
			// out.print(result);
			if (callback == null) {
				out.print(result);
			} else {
				out.print("<script>" + callback + "(" + result + ")</script>");
			}
		}
	}
}
