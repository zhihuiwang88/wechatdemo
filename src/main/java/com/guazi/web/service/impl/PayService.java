package com.guazi.web.service.impl;

import com.guazi.web.dto.OrderMasterDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {

	
	PayResponse createOrder(OrderMasterDto orderMasterDto);

	PayResponse notify(String notifyData);
	
	RefundResponse refundOrder(OrderMasterDto orderMasterDto);
	
	
}
