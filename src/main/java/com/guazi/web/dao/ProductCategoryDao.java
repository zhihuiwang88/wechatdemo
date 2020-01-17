package com.guazi.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guazi.web.entity.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{

	//根据category_type字段查询数据
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
