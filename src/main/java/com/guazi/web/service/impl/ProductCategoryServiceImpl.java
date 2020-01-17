package com.guazi.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guazi.web.dao.ProductCategoryDao;
import com.guazi.web.entity.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryDao categoryDao;

	@Override
	public ProductCategory findById(Integer categoryId) {
		return categoryDao.findOne(categoryId);
	}

	@Override
	public List<ProductCategory> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public ProductCategory save(ProductCategory pCategory) {
		return categoryDao.save(pCategory);
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
		return categoryDao.findByCategoryTypeIn(categoryType);
	}

	
	
	
}
