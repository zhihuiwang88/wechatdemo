package com.guazi.web.form;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import com.guazi.web.entity.ProductInfo;

import lombok.Data;

@Data
public class ProductForm implements Serializable{

    /**
	 * 商品列表表单修改
	 */
	private static final long serialVersionUID = -9069568916490693880L;

	private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    @DecimalMin(value = "0.001")
    private BigDecimal productPrice;

    /** 库存. */
    @Min(value = 0)
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;

	
	
}
