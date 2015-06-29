package com.ud.manage.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ud.util.ConfigHelper;
import com.ud.util.DateUtil;

@Service
public class FileStoreService {
	private static String ROOT_IMG_PATH = ConfigHelper.getInstance().getValue("image_store_path");

	public String storeFile(String type, MultipartFile file) {
		if (file == null || StringUtils.isBlank(type)) {
			throw new RuntimeException();
		}
		String filePath = "";
		String fileName = "";
		String subFolder = File.separator + type + File.separator + DateUtil.formatDate(new Date(), "yyyyMM")
				+ File.separator + DateUtil.formatDate(new Date(), "dd") + File.separator;
		if (file.isEmpty()) {
			System.out.println("文件未上传");
		} else {
			String orgFileName = file.getOriginalFilename();
			String suffix = orgFileName.substring(orgFileName.lastIndexOf("."));
			fileName = UUID.randomUUID().toString() + suffix;
			String folder = ROOT_IMG_PATH + subFolder;
			File dir = new File(folder);
			dir.mkdirs();
			filePath = folder + fileName;
			File restore = new File(filePath);
			try {
				file.transferTo(restore);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return subFolder + fileName;
	}
}
