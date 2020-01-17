package com.guazi.web.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.entity.SellerInfo;
import com.guazi.web.utils.KeyUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoDaoTest {

	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Test
	public void saveData() {
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtil.getUniqueKey());
		sellerInfo.setUsername("李四");
		sellerInfo.setPassword("lisi");
		sellerInfo.setOpenid("121");
		sellerInfoDao.save(sellerInfo);
	}
}
