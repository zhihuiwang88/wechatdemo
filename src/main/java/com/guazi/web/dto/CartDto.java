package com.guazi.web.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartDto implements Serializable{

	private static final long serialVersionUID = -4047930043987160754L;
	/**
	 * 增加和减少库存信息
	 */
	
	private String productId;
	private Integer productQuantity;
	
	public CartDto(String productId, Integer productQuantity) {
		super();
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
	

	
}
