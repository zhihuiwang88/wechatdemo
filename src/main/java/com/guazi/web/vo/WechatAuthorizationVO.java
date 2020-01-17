package com.guazi.web.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class WechatAuthorizationVO implements Serializable {

	private static final long serialVersionUID = 8935376640989688718L;
	/**
	 * access_token,网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * expires_in,access_token接口调用凭证超时时间，单位（秒） refresh_token,用户刷新access_token
	 * scope,用户授权的作用域，使用逗号（,）分隔
	 * openid,用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String access_token;
	private Integer expires_in;
	private String refresh_token;
	private String scope;
	private String openid;
	
	
	public WechatAuthorizationVO(Integer id, String access_token, Integer expires_in, String refresh_token, String scope,
			String openid) {
		super();
		this.id = id;
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.scope = scope;
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "WeChatAuthorization [ id=" + id + ",access_token=" + access_token + ", expires_in=" + expires_in
				+ ", refresh_token=" + refresh_token + ", scope=" + scope + ", openid=" + openid + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}


	
}
