package com.guazi.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.guazi.web.entity.OrderDetail;
import com.guazi.web.enums.OrderStatusEnum;
import com.guazi.web.enums.PayStatusEnum;
import com.guazi.web.utils.Date2LongSerializer;
import com.guazi.web.utils.EnumUtil;

import lombok.Data;

@Data
public class OrderMasterDto implements Serializable{

	private static final long serialVersionUID = -6233791366507197918L;

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
	    private Integer orderStatus;

	    /** 支付状态, 默认为0未支付. */
	    private Integer payStatus;

	    /** 创建时间. */
	    @JsonSerialize(using = Date2LongSerializer.class)
	    private Date createTime;

	    /** 更新时间.
	     * JsonSerialize,
	     *  Date类型的变量转换成Long类型
	     *  */
	    @JsonSerialize(using = Date2LongSerializer.class)
	    private Date updateTime;
	
	    private List<OrderDetail> orderDetail;

        
	    /*JsonIgnore,此注解是忽略不想返回给前端的接口数据
	     * 商品
	     */
	    @JsonIgnore
		public  OrderStatusEnum getOrderStatusEnum() {
			return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
		}
		//支付
	    @JsonIgnore
		public  PayStatusEnum getPayStatus() {
			return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
		}
	    
}
