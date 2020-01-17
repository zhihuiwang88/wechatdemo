package com.guazi.web.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guazi.web.dto.CartDto;
import com.guazi.web.entity.ProductInfo;

public interface ProductInfoService {

	/**
	 * 商品表的Service
	 */
	
	// 根据product_id查询商品
	ProductInfo findByProductId(Integer productId);
	// 查询在架的商品
	List<ProductInfo> findUpAll();
	//商品下架
	ProductInfo soldOut(String productId);
	//商品上架
	ProductInfo putAway(String productId);
	// 查询所有商品并分页
	Page<ProductInfo> findAll(Pageable pageable);
	//保存商品 
	ProductInfo save(ProductInfo productInfo);
	// 减库存，下单
	void  inventoryReduction(List<CartDto> cartDtoList);
	// 增库存，取消订单
	void inventoryIncrease(List<CartDto> cartDtoList);
}
