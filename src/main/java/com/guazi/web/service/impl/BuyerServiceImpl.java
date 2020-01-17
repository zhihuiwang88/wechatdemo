package com.guazi.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;

@Service
public class BuyerServiceImpl implements BuyerService {

	private static final Logger log = LoggerFactory.getLogger(BuyerServiceImpl.class);

	@Autowired
	private OrderMasterService ordMasService;
	
	
	private OrderMasterDto orderOneAndCancel(String openid, String orderId) {
		//查询商品存在不
		OrderMasterDto orderMasterDto = ordMasService.findOne(orderId);
		//比较openid
		if(!orderMasterDto.getBuyerOpenid().equalsIgnoreCase(openid)) {
			log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderMasterDto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderMasterDto;
	}
	
	//查询订单详情
	@Override
	public OrderMasterDto findOrderOne(String openid, String orderId) {
		return orderOneAndCancel(openid,orderId);
	}

	//取消订单
	@Override
	public OrderMasterDto cancelOrder(String openid, String orderId) {
		OrderMasterDto orderMasterDto = orderOneAndCancel(openid,orderId);
		if(orderMasterDto == null) {
			log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return ordMasService.cancelOrder(orderMasterDto);
	}
	
	
	
	
}
