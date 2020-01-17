package com.guazi.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Component
public class WechatPayConfig {

	
	
	@Bean
	public BestPayServiceImpl bestPayService() {
		BestPayServiceImpl bestPayService = new BestPayServiceImpl();
		bestPayService.setWxPayH5Config(payH5Config());
		return bestPayService;
	}
	
	@Bean
	public WxPayH5Config payH5Config() {
		WxPayH5Config wxPayH5Config = new WxPayH5Config();
		wxPayH5Config.setAppId("wxd55cbe0463386cc7");
		wxPayH5Config.setAppSecret("5ebdf752c78ac294139b97cada7a4d93");
		wxPayH5Config.setMchId("1483469312");
		wxPayH5Config.setMchKey("C5245D70627C1F8E9964D494B0735025");
		wxPayH5Config.setKeyPath("/doc/h5.p12");
		wxPayH5Config.setNotifyUrl("http://zgpayment.natapp1.cc/sell/pay/notify");
		return wxPayH5Config;
	}
	
	
	
	
}
