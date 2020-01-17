package com.guazi.web.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultVO<T> implements Serializable {


	private static final long serialVersionUID = -1678756500415065399L;
	/**
	 * 工具类
	 * 
	 */
	private Integer code;
	private String msg;
	private T data;
	
}
