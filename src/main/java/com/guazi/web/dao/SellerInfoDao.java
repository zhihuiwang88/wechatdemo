package com.guazi.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guazi.web.entity.SellerInfo;

public interface SellerInfoDao extends JpaRepository<SellerInfo, String>{

	SellerInfo findSellerInfoByOpenid(String openid);


}
