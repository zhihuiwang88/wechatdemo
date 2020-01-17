package com.guazi.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.dao.ProductInfoDao;
import com.guazi.web.entity.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

	/**
	 * 商品DAO测试类
	 */
	
	@Autowired
	private ProductInfoDao piDao;
	
	// 插入商品
	@Test
	public void save() {
		ProductInfo entity = new ProductInfo();
		entity.setProductId("1250");
		entity.setProductName("豆腐脑");
		entity.setProductPrice(new BigDecimal(1.6));
		entity.setProductStock(100);
		entity.setProductDescription("吃豆腐脑");
		entity.setProductIcon("http://XXX.jpg");
		entity.setProductStatus(0);
		entity.setCategoryType(9);
		ProductInfo result = piDao.save(entity);
		Assert.assertNotNull(result);
	}
	
	
	// 查询在架的商品
	@Test
	public void findByStatus() {
		List<ProductInfo> list = piDao.findByProductStatus(0);
		System.out.println("findByStatus:" + list );
		Assert.assertNotEquals(0, list.size());
	}


	
	
}
