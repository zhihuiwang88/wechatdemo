package com.guazi.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guazi.web.enums.ProductStatusEnum;
import com.guazi.web.utils.EnumUtil;

import lombok.Data;


/**
 * 
 * 商品表
 * @author Dell
 *
 */

@Data
@Entity
@DynamicUpdate
public class ProductInfo implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = -3068595719500378092L;

		@Id
	    private String productId;

	    /** 名字. */
	    private String productName;

	    /** 单价. */
	    private BigDecimal productPrice;

	    /** 库存. */
	    private Integer productStock;

	    /** 描述. */
	    private String productDescription;

	    /** 小图. */
	    private String productIcon;

	    /** 状态, 0正常1下架. */
	    private Integer productStatus = ProductStatusEnum.PRODUCT_UP.getCode() ;

	    /** 类目编号. */
	    private Integer categoryType;

	    private Date createTime;

	    private Date updateTime;
        @JsonIgnore
		public ProductStatusEnum getProductStatusEnum() {
			return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
		}
	    
	    
}
