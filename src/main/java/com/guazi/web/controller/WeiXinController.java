package com.guazi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.guazi.web.vo.WechatAuthorizationVO;



@RestController
@RequestMapping("/weixin")
public class WeiXinController {
	
	private static final Logger log = LoggerFactory.getLogger(WeiXinController.class);
	
	/**
	 * 7-3视频
	 * 手动获取微信CODE和openID
	 * localhost:8080/sell/weixin/auth
	 */
	
	@GetMapping("/auth")
	public void author(@RequestParam("code") String code) {
		log.info("进入author...");
		// 获取code
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd55cbe0463386cc7&secret=5ebdf752c78ac294139b97cada7a4d93&"
				+ "code=" + code + "&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();
		// 获取openID
		String response = restTemplate.getForObject(url, String.class);
		//json字符串转对象，WeChatAuthorization
		Gson gson = new Gson();
		WechatAuthorizationVO authorization = gson.fromJson(response, WechatAuthorizationVO.class);
		String openId = authorization.getOpenid();
		log.info("------openId={}",openId);
		log.info("【获取code】，code={}", code);
		log.info("response={}", response); 
	}
	
	/**
	 * 微信扫码登录,待定
	 * https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html
	 * localhost:8080/sell/weixin/wxEwm
	 * @param,appid,应用唯一标识
	 * @param,redirectUri,请使用urlEncode对链接进行处理
	 * @param,scope,网页应用目前仅填写snsapi_login即
	 * @return,用户允许授权后，将会重定向到redirect_uri的网址上，并且带上code和state参数,
	 * redirect_uri?code=CODE&state=STATE
	 */
	@GetMapping("/wxEwm")
	public void wxSaomaLogin() {
		String appid = "wxd55cbe0463386cc7";
		String redirectUri = "http%3a%2f%2ftool.chinaz.com%2ftools%2furlencode.aspx";
		String scope = "snsapi_login";
		String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + appid +
				"&redirect_uri=" + redirectUri + 
				"&response_type=code&scope="+ scope +"&state=STATE#wechat_redirect";
		
		
	}

}
