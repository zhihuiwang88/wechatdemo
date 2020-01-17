package com.guazi.web.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guazi.web.dto.OrderMasterDto;

public interface OrderMasterService {

	// 创建订单
	OrderMasterDto createNewOrder(OrderMasterDto orderMasterDto);

	// 根据orderId查询单个订单
	OrderMasterDto findOne(String orderId);

	// 根据buyerOpenid查询订单列表
	Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable);

	// 完结订单
	OrderMasterDto finishOrder(OrderMasterDto orderMasterDto);

	//取消订单
	OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto);
	
	// 支付订单
	OrderMasterDto payOrder(OrderMasterDto orderMasterDto);
	
	// 只有分页
	Page<OrderMasterDto> findListPage(Pageable pageable);

}
