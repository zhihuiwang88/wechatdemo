package com.guazi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.guazi.web.entity.ProductCategory;
import com.guazi.web.entity.ProductInfo;
import com.guazi.web.exception.SellException;
import com.guazi.web.form.ProductForm;
import com.guazi.web.service.impl.ProductCategoryService;
import com.guazi.web.service.impl.ProductInfoService;
import com.guazi.web.utils.KeyUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
	@Autowired
	private ProductInfoService proInfoService;
	
	@Autowired
	private ProductCategoryService proCateService;

	
	/**
	 * 商品列表
	 * localhost:8080/sell/seller/product/list
	 * @param page,第几页
	 * @param size，每页几条数据
	 * ProductInfo
	 * @return
	 */
	@GetMapping("/list")
    public ModelAndView proInfoList(@RequestParam(value="page",defaultValue="1") Integer page,
    		@RequestParam(value="size",defaultValue="3") Integer size) {
		Map<String, Object> map = new HashMap<>();
		PageRequest pageable = new PageRequest(page - 1, size);
		Page<ProductInfo> result = proInfoService.findAll(pageable);
		map.put("page", page);
        map.put("size", size);
		map.put("productInfoPage",result);
        return new ModelAndView("/product/productlist",map);
	}

	/**
	 * localhost:8080/sell/seller/product/putaway?productId=
	 * 商品上架
	 * 
	 */
	@GetMapping("/putaway")
	public ModelAndView putAway(@RequestParam("productId") String productId) {
		Map<String,Object> map = new HashMap<>();
		//成功和失败都返回商品列表
		try {
			proInfoService.putAway(productId);
		} catch (SellException e) {
			log.error("【商品上架】错误",e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("/common/error",map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("/common/success",map);
	}
	
	/**
	 * localhost:8080/sell/seller/product/soldout?productId=
	 * 商品下架
	 * @param productId,商品ID
	 */
	@GetMapping("/soldout")
	public ModelAndView soldOut(@RequestParam("productId") String productId) {
		Map<String,Object> map = new HashMap<>();
		//成功和失败都返回商品列表
		try {
			proInfoService.soldOut(productId);
		} catch (SellException e) {
			log.error("【商品下架】错误",e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/error",map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success",map);
	}
	
	/**
	 * localhost:8080/sell/seller/product/productview?productId=
	 * 商品修改页面显示
	 * @param productForm
	 * Valid,表单验证
	 * @return
	 */
	@GetMapping("/productview")
	public ModelAndView updateProduct(@RequestParam(value = "productId", required = false) Integer productId) {
		Map<String, Object> map = new HashMap<>();
		//查询商品
		ProductInfo productInfo = proInfoService.findByProductId(productId);
		if(!StringUtils.isEmpty(productInfo)) {
			map.put("productInfo", productInfo);
		}
		List<ProductCategory> productCategory = proCateService.findAll();
		map.put("productCategoryList", productCategory);
		return new ModelAndView("product/productupdate",map);
	}
	
	/**
	 * localhost:8080/sell/seller/product/save
	 * 商品列表新增和修改
	 * @param productForm
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView productSave(@Valid ProductForm productForm) {
		Map<String, Object> map = new HashMap<>();
		ProductInfo productInfo = new ProductInfo();
		try {
			//productID不为空修改
			if(!StringUtils.isEmpty(productForm.getProductId())) {
			  productInfo = proInfoService.findByProductId(Integer.parseInt(productForm.getProductId()));
			} else {
				//新增
				productForm.setProductId(KeyUtil.getUniqueKey());
			}
			BeanUtils.copyProperties(productForm, productInfo);
			proInfoService.save(productInfo);
		} catch (SellException e) {
			log.error("【商品列表】新增或修改失败", e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/error",map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success",map);
	}
	
	
}
