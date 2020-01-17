package com.guazi.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;
import com.guazi.web.service.impl.OrderMasterService;
import com.guazi.web.service.impl.PayService;
import com.lly835.bestpay.model.PayResponse;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
	private PayService payService;
    @Autowired
	private OrderMasterService ordMasService;
	
	/**
	 * 微信支付SDK：https://github.com/Pay-Group/best-pay-sdk
	 * 微信支付开发文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * 支付订单
	 * @param orderId,商品id
	 * @param returnUrl,重定向URL
	 * @return,http://xxx.com/abc/order/1618990
	 */
	
	@GetMapping("/create")
	public ModelAndView createOrder(@RequestParam("orderId") String orderId,
			@RequestParam("returnUrl") String returnUrl,Map<String, Object> map) {
		//判断订单存在不
		OrderMasterDto payOrder = ordMasService.findOne(orderId);
		if(payOrder == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST) ;
		}
		//支付订单
		PayResponse  payResponse = payService.createOrder(payOrder);
		map.put("payResponse", payResponse);
		map.put("returnUrl", returnUrl);
		return new ModelAndView("/pay/create",map);
	}
	
	
	//异步通知商户支付结果
	@PostMapping("/notify")
	public ModelAndView wxPyqt(@RequestBody String notifyData) {
		payService.notify(notifyData);
		//告诉微信处理结果
		return new ModelAndView("pay/success");
	}
	
	
	
	
	
}
