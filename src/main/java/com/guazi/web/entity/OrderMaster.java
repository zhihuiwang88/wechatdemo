package com.guazi.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;

import com.guazi.web.enums.OrderStatusEnum;
import com.guazi.web.enums.PayStatusEnum;

import lombok.Data;

@Entity
@DynamicUpdate
@Data
public class OrderMaster implements Serializable{

	/**
	 * 订单表
	 */
	
	private static final long serialVersionUID = -5781058090787036510L;

	/** 订单id. */
    @Id
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW_ORDER.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.PAYMENT_WAITING.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;




	
}
