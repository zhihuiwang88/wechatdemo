package com.guazi.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.guazi.web.config.ProjectUrlConfig;
import com.guazi.web.exception.ResponseBankException;
import com.guazi.web.exception.SellException;
import com.guazi.web.exception.SellerAuthorizeException;
import com.guazi.web.utils.ResultVOUtil;
import com.guazi.web.vo.ResultVO;


/**
 * 拦截登录异常
 * @author Dell
 *
 */
@ControllerAdvice
public class SellExceptionHandler {
	 @Autowired
	 private ProjectUrlConfig projectUrlConfig;
	  
	@ExceptionHandler(value = SellerAuthorizeException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ModelAndView handlerAuthorizeException() {
//localhost:8080/sell/wechat/qrauthorize?returnUrl=localhost:8080/sell/seller/login
		return new ModelAndView("redirect:" .concat(projectUrlConfig.getWechatOpenAuthorize())
				.concat("/sell/wechat/qrauthorize").concat("?returnUrl=")
//				.concat(projectUrlConfig.getSell())
				.concat("localhost:8080")
				.concat("/sell/seller/login"));
	}
	
	/**
	 * 使用postman测试，返回正确的信息
	 */
	@ExceptionHandler(value = SellException.class)
	@ResponseBody
	public ResultVO handlerSellerException(SellException e) {
		return ResultVOUtil.error(e.getCode(), e.getMessage());
	}
	
	/**
	 * postman测试，返回错误信息(msg,code)，Status状态码必须与之对应
	 */
	 @ExceptionHandler(value = ResponseBankException.class)
	 @ResponseStatus(HttpStatus.FORBIDDEN)
	 public void handleResponseBankException() {

	 }
}
