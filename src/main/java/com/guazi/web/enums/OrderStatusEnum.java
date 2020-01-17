package com.guazi.web.enums;

public enum OrderStatusEnum implements CodeEnum{

	/**
	 * 订单状态枚举
	 */
	
	NEW_ORDER(0,"新订单"),
	CANNCEL_ORDER(1,"订单取消了"),
	FINISH_ORDER(2,"订单完结"),
	;
	
	private Integer code;
	private String message;

	OrderStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	 
	 
}
