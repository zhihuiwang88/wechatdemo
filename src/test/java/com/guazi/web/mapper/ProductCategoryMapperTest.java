package com.guazi.web.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.guazi.web.entity.ProductCategory;



/**
 * WebSocket与SpringBoot单元测试结合需要添加webEnvironment=XXX
 * 原因：websocket是需要依赖tomcat等容器的启动。所以在测试过程中我们要真正的启动一个tomcat作为容器
 * @author Dell
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
//	@Test
	public void insertObject() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("很好1");
		productCategory.setCategoryType(200);
		int result = productCategoryMapper.insertProductCategory(productCategory);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void findCategoryType() {
		Integer categoryType = 18;
//		ProductCategory result = productCategoryMapper.findByCategoryType(categoryType);
		ProductCategory result = productCategoryMapper.selectCategoryType(categoryType);
		Assert.assertNotNull(result);
	}
	
//	@Test
	public void findByCategoryName() {
		List<ProductCategory> resultList = productCategoryMapper.findByCategoryName("男生区");
		Assert.assertNotEquals(0, resultList.size());
	}
	
	
}
