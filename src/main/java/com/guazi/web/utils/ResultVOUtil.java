package com.guazi.web.utils;

import com.guazi.web.vo.ResultVO;

public class ResultVOUtil {

	// 成功信息提醒
	public static  <T> ResultVO<T> success(Object object) {
		ResultVO<Object> resultVO = new ResultVO<Object>();
		resultVO.setCode(200);
		resultVO.setMsg("成功");
		resultVO.setData(object);
		return (ResultVO<T>) resultVO;
	}
	
	public static  <T> ResultVO<T> success(){
		return success(null);
	}
	
	// 错误信息提醒
	public static  ResultVO<Object> error(Integer code,String msg){
		ResultVO<Object> resultVO = new ResultVO<Object>();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		return resultVO;
	}
	
}
