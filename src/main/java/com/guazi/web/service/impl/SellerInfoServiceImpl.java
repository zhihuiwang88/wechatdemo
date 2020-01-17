package com.guazi.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.guazi.web.dao.SellerInfoDao;
import com.guazi.web.entity.SellerInfo;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService {

	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	/**
	 * 查询openid
	 */
	@Override
	public SellerInfo findSellerInfoByOpenid(String openid) {
		//判断ID
		if(StringUtils.isEmpty(openid)) {
			log.error("【登录】openid为空");
			throw new SellException(ResultEnum.ERROR);
		}
		//查询
		return sellerInfoDao.findSellerInfoByOpenid(openid);
	}

	/**
	 * 新增
	 */
	@Override
	public SellerInfo save(SellerInfo sellerInfo) {
	
		return sellerInfoDao.save(sellerInfo);
	}

	
	
}
