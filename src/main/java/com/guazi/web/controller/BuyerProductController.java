package com.guazi.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guazi.web.entity.ProductCategory;
import com.guazi.web.entity.ProductInfo;
import com.guazi.web.service.impl.ProductCategoryService;
import com.guazi.web.service.impl.ProductInfoService;
import com.guazi.web.utils.ResultVOUtil;
import com.guazi.web.vo.ProductCategoryVO;
import com.guazi.web.vo.ProductInfoVO;
import com.guazi.web.vo.ResultVO;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	/**
	 * 买家类
	 * 查询类目表对应的商品信息
	 * http://localhost:8080/sell/buyer/product/list
	 * 
	 */
	@Autowired
	private ProductCategoryService categoryService;
	@Autowired
	private ProductInfoService infoService;
	
	
	@SuppressWarnings("rawtypes")
	@Cacheable(cacheNames="productInfo",key="1234")
	@GetMapping("/list")
	public ResultVO findByProductList() {
		// 查询所有上架商品
		List<ProductInfo> categoryUpList = infoService.findUpAll();
		// 根据CategoryType查询信息
		List<Integer> categoryType = categoryUpList.stream()
				.map(e -> e.getCategoryType())
		        .collect(Collectors.toList());
		List<ProductCategory> list = categoryService.findByCategoryTypeIn(categoryType);
		// 数据拼装
		List<ProductCategoryVO> categoryVOList = new ArrayList<>();
        for(ProductCategory productCategory : list) {
        	ProductCategoryVO categoryVO = new ProductCategoryVO();
        	categoryVO.setCategoryName(productCategory.getCategoryName());
        	categoryVO.setCategoryType(productCategory.getCategoryType());
        	//上架商品信息
        	List<ProductInfoVO> InfoVOList = new ArrayList<>();
        	for(ProductInfo productInfo : categoryUpList) {
        		if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
        			ProductInfoVO infoVO = new ProductInfoVO();
        			// 把ProductInfo信息COPY到ProductInfoVO
        			BeanUtils.copyProperties(productInfo, infoVO);
        			InfoVOList.add(infoVO);
        		}
        	}
        	categoryVO.setProductInfoVOList(InfoVOList);
        	categoryVOList.add(categoryVO);
		}
		return ResultVOUtil.success(categoryVOList);
	}
	
	
	
}
