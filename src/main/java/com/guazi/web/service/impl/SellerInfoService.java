package com.guazi.web.service.impl;

import com.guazi.web.entity.SellerInfo;

public interface SellerInfoService {

	/**
	 * 根据openid查询信息
	 */
	SellerInfo findSellerInfoByOpenid(String openid);
	/**
	 * 新增信息
	 */
	SellerInfo save(SellerInfo sellerInfo);
}
