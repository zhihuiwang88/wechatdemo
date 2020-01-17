package com.guazi.web.service.impl;

import com.guazi.web.dto.OrderMasterDto;

public interface PushMessageService {

	/**
	 * 订单消息推送
	 * @return 
	 */
	
	void orderPush(OrderMasterDto orderMasterDto);
	
}
