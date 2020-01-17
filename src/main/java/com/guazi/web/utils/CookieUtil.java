package com.guazi.web.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * 
 * @author Dell
 *
 */
public class CookieUtil {

	/**
	 * 设置
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param expiry
	 */
	public static void set(HttpServletResponse response, String name, String value, int expiry) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}

	/**
	 * 将cookie封装成Map
	 */
	public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie resultCookie : cookies) {
				cookieMap.put(resultCookie.getName(), resultCookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 获取cookie
	 */
	public static Cookie get(HttpServletRequest request, String name) {
		Map<String, Cookie> mapCookie = readCookieMap(request);
		if (mapCookie.containsKey(name)) {
			return mapCookie.get(name);
		} else {
			return null;
		}

	}

}
