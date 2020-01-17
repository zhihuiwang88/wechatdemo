package com.guazi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.guazi.web.entity.ProductCategory;
import com.guazi.web.form.CategoryForm;
import com.guazi.web.service.impl.ProductCategoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

	@Autowired
	private ProductCategoryService  proCateService;
	
	/**
	 * 商品类目展示数据
	 * localhost:8080/sell/seller/category/list
	 */
	@GetMapping("/list")
	public ModelAndView findList() {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询所有的数据
		List<ProductCategory> resultList = proCateService.findAll();
		map.put("productCategoryList", resultList);
		return new ModelAndView("category/categorylist", map);
	}
	
	/**
	 * localhost:8080/sell/seller/category/categoryview?categoryId=
	 * 点击修改和新增后显示页面信息
	 * @param categoryId,类目ID
	 * @return
	 */
	@GetMapping("/categoryview")
	public ModelAndView categoryView(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(categoryId)) {
			ProductCategory productCategory = proCateService.findById(categoryId);
			map.put("productCategory", productCategory);
		}
		return new ModelAndView("category/categoryupdate", map);
	}
	
	/**
	 * localhost:8080/sell/seller/category/save
	 * 商品类目修改和新增
	 * @param categoryId
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView categorySave(@Valid CategoryForm categoryForm) {
		Map<String, Object> map = new HashMap<>();
		ProductCategory productCategory = new ProductCategory();
		try {
			if(!StringUtils.isEmpty(categoryForm.getCategoryId())) {
				 productCategory = proCateService.findById(Integer.valueOf(categoryForm.getCategoryId()));
			}
			BeanUtils.copyProperties(categoryForm, productCategory);
			proCateService.save(productCategory);
		} catch (Exception e) {
			log.error("【商品类目】新增或修改失败", e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/category/list");
			return new ModelAndView("common/error",map);
		}
		map.put("url","/sell/seller/category/list");
		return new ModelAndView("common/success",map);
	}
	
}
