package com.guazi.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Component
public class WechatMpConfig {

	@Autowired
	private WechatAccountConfig accConfig;
	
	/**
	 * 公众号相关配置
	 * 微信API的Service:WxMpService
	 * WxMpConfigStorage:微信客户端配置存储
	 * WxMpInMemoryConfigStorage:基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
	 */
	@Bean
	public WxMpService wxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
	
	
	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorage mpConfigStorage = new WxMpInMemoryConfigStorage();
		mpConfigStorage.setAppId("wxd55cbe0463386cc7");
		mpConfigStorage.setSecret("5ebdf752c78ac294139b97cada7a4d93");
		return mpConfigStorage;
	}
	
	
}
