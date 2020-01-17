package com.guazi.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guazi.web.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String>{

	/**
	 * 订单详情表
	 * 根据orderId查询信息
	 * @param orderId 
	 * @return
	 */
	List<OrderDetail> findByOrderId(String orderId);
	
}
