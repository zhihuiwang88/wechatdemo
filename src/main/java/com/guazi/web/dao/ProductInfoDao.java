package com.guazi.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guazi.web.entity.ProductInfo;

public interface ProductInfoDao  extends JpaRepository<ProductInfo, String>{

	//查询商品状态
	List<ProductInfo> findByProductStatus(Integer productStatus);

}
