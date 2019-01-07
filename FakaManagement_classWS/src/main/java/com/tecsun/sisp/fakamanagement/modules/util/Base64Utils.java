package com.tecsun.sisp.fakamanagement.modules.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {

	public final static Logger logger = Logger.getLogger(Base64Utils.class);

	public static String getImageStr(File file) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);// 返回Base64编码过的字节数组字符串
		} catch (IOException e) {
			logger.info("图片转换base64字符串出错" + e.getMessage());
		}
		return "";
	}

	public static String getImageStr(String path) {// voice转化成base64字符串
		String imgFile = path;
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);// 返回Base64编码过的字节数组字符串
		} catch (IOException e) {
			logger.info("图片转换base64字符串出错" + e.getMessage());
		}
		return "";
	}

	public static boolean GenerateImage(String imgStr,String path) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
