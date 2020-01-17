package com.guazi.web.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeyUtil implements Serializable{

	/**
	 * 生成唯一的主键
	 * 格式: 时间+随机数
	 */
	private static final long serialVersionUID = 8795007167536462968L;

	public static synchronized String getUniqueKey() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		long millis = System.currentTimeMillis();
		String longKey = date + millis;
		return longKey;
	}
}
