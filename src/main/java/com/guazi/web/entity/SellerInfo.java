package com.guazi.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SellerInfo implements Serializable{

	private static final long serialVersionUID = -8474904534457472018L;
	
	/**
	 * 登录信息
	 */
	@Id
	private String sellerId;
	private String username;
	private String password;
	private String openid;
	private Date createTime;
	private Date updateTime;
	
	
}
