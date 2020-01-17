package com.guazi.web.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{

	/**
	 * 商品枚举
	 * 商品上下架
	 */
	
	PRODUCT_UP(0,"产品在架"),
	PRODUCT_DOWN(1,"产品下架")
	;
	
	private Integer code;
	private String message;
	
	ProductStatusEnum(Integer code,String message) {
		this.code = code;
		this.message = message;
	}

	
	
}
