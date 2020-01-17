package com.guazi.web.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductCategoryVO implements Serializable{


	private static final long serialVersionUID = -3281357904874052088L;
	/**
	 * 类目表的工具类
	 * 请看doc下的API.md
	 *  JsonProperty,属性的名称序列化为另外一个名称
	 */
	
	@JsonProperty("name")
	private String categoryName;
	@JsonProperty("type")
	private Integer categoryType;
	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;

	@Override
	public String toString() {
		return "ProductCategoryVO [categoryName=" + categoryName + ", categoryType=" + categoryType
				+ ", productInfoVOList=" + productInfoVOList + "]";
	}


	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public Integer getCategoryType() {
		return categoryType;
	}



	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}



	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}



	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}



	
	
	


	
	
	
	
	
	
}
