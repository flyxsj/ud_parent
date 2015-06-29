package com.ud.admin.web.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ScaleImage {

	private String sourcePath;

	private String destPath;

	private int x;

	private int y;

	private int width;

	private int height;

	public ScaleImage(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void cut() throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			is = new FileInputStream(sourcePath);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, "jpg", new File(destPath));
		} finally {
			if (is != null) {
				is.close();
			}
			if (iis != null) {
				iis.close();
			}
		}

	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static void main(String[] args) throws Exception {
		String name = "d:/cut/test.jpg";
		ScaleImage o = new ScaleImage(200, 0, 600, 700);
		o.setSourcePath(name);
		o.setDestPath("D:/cut/test_cut.jpg");
		o.cut();
	}

}