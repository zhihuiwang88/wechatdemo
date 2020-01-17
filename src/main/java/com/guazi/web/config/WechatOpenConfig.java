package com.guazi.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Component
public class WechatOpenConfig {

	@Autowired
	private WechatAccountConfig wechatAccountConfig;
	
	@Bean
	public WxMpService  wxOpenService() {
		WxMpService wxOpenService = new WxMpServiceImpl();
		wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
		return wxOpenService;
	}
	
	@Bean
	public WxMpConfigStorage wxOpenConfigStorage() {
		WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
//		wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
		wxMpInMemoryConfigStorage.setAppId("wxd55cbe0463386cc7");
//		wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
		wxMpInMemoryConfigStorage.setSecret("5ebdf752c78ac294139b97cada7a4d93");
		return wxMpInMemoryConfigStorage;
	}
	
	
	
	
	
	
}
