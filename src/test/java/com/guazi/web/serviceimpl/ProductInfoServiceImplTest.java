package com.guazi.web.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.entity.ProductInfo;
import com.guazi.web.service.impl.ProductInfoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

	@Autowired
	private ProductInfoServiceImpl pisImpl;

	@Test
	public void findByProductId() {
		ProductInfo productId = pisImpl.findByProductId(1230);
		System.out.println(productId);
		Assert.assertNotNull(productId);
	}

	@Test
	public void findUpAll() {
		List<ProductInfo> upAll = pisImpl.findUpAll();
		System.out.println(upAll);
		Assert.assertNotEquals(0, upAll.size());

	}

	@Test
	public void findAll() {
		PageRequest pageable = new PageRequest(0, 2);
		Page<ProductInfo> result = pisImpl.findAll(pageable);
		Assert.assertNotEquals(0, result.getTotalElements());
	}

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
		ProductInfo result = pisImpl.save(entity);
		Assert.assertNotNull(result);
	}

}
