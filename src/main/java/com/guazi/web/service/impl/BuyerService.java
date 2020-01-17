package com.guazi.web.service.impl;

import com.guazi.web.dto.OrderMasterDto;

public interface BuyerService {

	//查询一个订单
	OrderMasterDto findOrderOne(String openid,String orderId);

	//取消订单
	OrderMasterDto cancelOrder(String openid, String orderId);
}
