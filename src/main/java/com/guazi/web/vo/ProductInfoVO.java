package com.guazi.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductInfoVO implements Serializable{

	private static final long serialVersionUID = -6216154210536320191L;


	/**
	 * 商品表的工具类
	 * 请看doc下的API.md
	 */
	
	    @JsonProperty("id")
	    private String productId;

	    @JsonProperty("name")
	    private String productName;

	    @JsonProperty("price")
	    private BigDecimal productPrice;

	    @JsonProperty("description")
	    private String productDescription;

	    @JsonProperty("icon")
	    private String productIcon;

		

	
	
}
