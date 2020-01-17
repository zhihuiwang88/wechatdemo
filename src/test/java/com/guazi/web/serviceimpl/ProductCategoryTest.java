package com.guazi.web.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.dao.ProductCategoryDao;
import com.guazi.web.entity.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryTest {

	/**
	 * 类目测试类
	 */

	@Autowired
	private ProductCategoryDao pcDao;

	@Test
	public void findById() {
		ProductCategory result = pcDao.findOne(2);
		System.out.println("findById:" + result);
	}

	@Test
	// @Transactional
	public void save() {
		ProductCategory pc = new ProductCategory("吃1", 201);
		ProductCategory result = pcDao.save(pc);
		Assert.assertNotNull(result);
	}

	/*
	 * Assert.assertNotEquals(A,B)。A不等于B，则测试通过 根据category_type字段查询数据
	 */
	@Test
	public void findByType() {
		List<Integer> list = Arrays.asList(6, 10);
		List<ProductCategory> result = pcDao.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0, result.size());

	}

}
