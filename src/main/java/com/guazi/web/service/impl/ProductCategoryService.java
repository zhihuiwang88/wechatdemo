package com.guazi.web.service.impl;

import java.util.List;

import com.guazi.web.entity.ProductCategory;

public interface ProductCategoryService {

	/*
	 * 类目表
	 */
	
	// 查询一个
	ProductCategory findById(Integer categoryId);
	
	//根据category_type字段查询数据
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
	
	// 查询所有分页
	List<ProductCategory> findAll();
	
	//保存
	ProductCategory save(ProductCategory pCategory);
}
