package com.guazi.web.controller;

import java.net.URLEncoder;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guazi.web.config.ProjectUrlConfig;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private  ProjectUrlConfig projectUrlConfig;
	@Autowired
    private WxMpService wxOpenService;
	
	private static final Logger log = LoggerFactory.getLogger(WechatController.class);
	
	/**
	 * 
	 * 使用SDK获取openID
	 * MP_OAuth2网页授权
	 * https://github.com/chanjarster/weixin-java-tools/wiki/MP_OAuth2%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83
	 * returnUrl,用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
	 * @param returnUrl,http://xxx.com/abc
	 * @return ,http://xxx.com/abc?openid=oZxSYw
	 */
	
	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl) {
		/*配置
		 *应用方法
		 * 首先构造网页授权url，然后构成超链接让用户点击
		 * localhost:8080/sell/wechat/authorize?returnUrl=http://sell.com/#/
		 * http://zgpayment.natapp1.cc/sell/wechat/authorize?returnUrl=http%3a%2f%2fsell.com%2f%23%2f
		 */
		String url = "http://zgpayment.natapp1.cc/sell/wechat/userInfo";
		String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
				WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
		return "redirect:" + redirectUrl;
	}
	
	
	//当用户同意授权后，会回调所设置的url并把authorization code传过来，然后用这个code获得access token
	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl ) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
		try {
		wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
		String openId = wxMpOAuth2AccessToken.getOpenId();
		return "redirect:" + returnUrl + "?openid=" + openId;
	}
	
	
	/**
	 * localhost:8080/sell/wechat/qrauthorize?returnUrl=http://www.baidu.com
	 * 微信扫码
	 * @param returnUrl
	 * @return
	 */
	@GetMapping("/qrauthorize")
	public String qrauthorize(@RequestParam("returnUrl") String returnUrl) {
		String url = "http://zgpayment.natapp1.cc/sell/wechat/qruserInfo";
		String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN,
				URLEncoder.encode(returnUrl));
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * localhost:8080/sell/wechat/qruserInfo?code=&returnUrl=
	 * @param code
	 * @param returnUrl
	 * @return
	 */
	@GetMapping("/qruserInfo")
	public String qruserInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
		try {
			wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			log.error("【微信网页授权】{}", e);
			throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
		}
/*
 * localhost:8080/sell/wechat/qrauthorize?returnUrl=http://www.baidu.com
 * 转发到
 * https://open.weixin.qq.com/connect/qrconnect?appid=?&redirect_uri=?&response_type=code&scope=snsapi_login&state=?#wechat_redirect
 * 转发到
 * localhost:8080/http://www.baidu.com?openid=
 * 
 */
		 String openId = wxMpOAuth2AccessToken.getOpenId();
	     return "redirect:" + returnUrl + "?openid=" + openId;
	}
	
}
