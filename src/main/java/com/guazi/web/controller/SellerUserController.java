package com.guazi.web.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.guazi.web.config.ProjectUrlConfig;
import com.guazi.web.constant.CookieConstant;
import com.guazi.web.constant.RedisConstant;
import com.guazi.web.entity.SellerInfo;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.service.impl.SellerInfoService;
import com.guazi.web.utils.CookieUtil;
import com.guazi.web.utils.KeyUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户登录信息
 * @author Dell
 *
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerUserController {
	
	@Autowired
	private SellerInfoService  sellerInfoService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ProjectUrlConfig projectUrlConfig;
	
	
	/**
	 * localhost:8080/sell/seller/login?openid=
	 * 卖家登录
	 * @param openid
	 * @param response
	 *  expire,时间
	 * TimeUnit.SECONDS，秒
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView loginUser(@RequestParam("openid") String openid,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		//openid与数据库数据比对
		SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
		if(sellerInfo == null) {
			log.error("【微信扫码登录】错误");
			map.put("message", ResultEnum.LOGIN_FAIL.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("/common/error",map);
		}
		//设置token至redis
		String token = KeyUtil.getUniqueKey();
		Integer expire = RedisConstant.EXPIRE;
		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), 
				openid, expire, TimeUnit.SECONDS);
		//设置token至cookie
		CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
		return new ModelAndView("redirect:" + projectUrlConfig.getSell()+"/sell/seller/order/list");
	}
	
	/**
	 * localhost:8080/sell/seller/logout
	 * 卖家退出
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// 从cookie里查询
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null) {
			// 清除redis
			redisTemplate.opsForValue().getOperations()
					.delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
			// 清除cookie
			CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
		}
		map.put("message", ResultEnum.LOGOUT_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success", map);
	}
	
}
