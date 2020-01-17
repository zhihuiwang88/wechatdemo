package com.guazi.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guazi.web.entity.OrderMaster;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String>{

	/**
	 * 订单表
	 * 根据买家openId查询信息并进行分页
	 * @param buyerOpenid ，买家微信openid
	 * @param pageable
	 * @return
	 */
	
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
	
}
