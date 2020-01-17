package com.guazi.web.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{

	/**
	 * 支付状态枚举
	 */
	
	PAYMENT_WAITING(0,"支付等待"),
	PAYMENT_SUCCESS(1,"支付成功"),
	;

	private Integer code;
	private String message;

	PayStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}



	
}
