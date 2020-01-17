package com.guazi.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetail implements Serializable{

	/**
	 * 订单详情表
	 */
	private static final long serialVersionUID = -5137339980236955500L;

	    @Id
	    private String detailId;

	    /** 订单id. */
	    private String orderId;

	    /** 商品id. */
	    private String productId;

	    /** 商品名称. */
	    private String productName;

	    /** 商品单价. */
	    private BigDecimal productPrice;

	    /** 商品数量. */
	    private Integer productQuantity;

	    /** 商品小图. */
	    private String productIcon;

		@Override
		public String toString() {
			return "OrderDetail [detailId=" + detailId + ", orderId=" + orderId + ", productId=" + productId
					+ ", productName=" + productName + ", productPrice=" + productPrice + ", productQuantity="
					+ productQuantity + ", productIcon=" + productIcon + "]";
		}

		public String getDetailId() {
			return detailId;
		}

		public void setDetailId(String detailId) {
			this.detailId = detailId;
		}

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
		}

		public Integer getProductQuantity() {
			return productQuantity;
		}

		public void setProductQuantity(Integer productQuantity) {
			this.productQuantity = productQuantity;
		}

		public String getProductIcon() {
			return productIcon;
		}

		public void setProductIcon(String productIcon) {
			this.productIcon = productIcon;
		}

	
}
