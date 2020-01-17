package com.guazi.web.exception;

import com.guazi.web.enums.ResultEnum;

import lombok.Getter;

@Getter
public class SellException extends RuntimeException{

	/**
	 * 异常处理
	 */
	private static final long serialVersionUID = -5120594318290589184L;

	private Integer code;
	
	public  SellException(ResultEnum resultEnum) {
            super(resultEnum.getMessage());
            this.code = resultEnum.getCode();
	}
	
	public SellException(Integer code,String message) {
		super(message);
		this.code = code;
	}


	
	
	
}
