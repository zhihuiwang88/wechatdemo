package com.guazi.web.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.entity.ProductCategory;
import com.guazi.web.service.impl.ProductCategoryServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryServiceImplTest {
	/**
	 * 类目实现类测试
	 */

	@Autowired
	private ProductCategoryServiceImpl pcServiceImpl;

	@Test
	public void findOne() {
		ProductCategory result = pcServiceImpl.findById(2);
		Assert.assertNotEquals(new Integer(1), result.getCategoryId());
	}

	// 根据category_type字段查询数据
	@Test
	public void findByType() {
		List<ProductCategory> list = pcServiceImpl.findByCategoryTypeIn(Arrays.asList(6, 10));
		Assert.assertNotEquals(0, list.size());
	}

	@Test
	public void findAll() {
		List<ProductCategory> result = pcServiceImpl.findAll();
		Assert.assertNotEquals(0, result.size());
	}

	@Test
	public void save() {
		ProductCategory one = new ProductCategory("喝1", 1120);
		ProductCategory result = pcServiceImpl.save(one);
	}
}
