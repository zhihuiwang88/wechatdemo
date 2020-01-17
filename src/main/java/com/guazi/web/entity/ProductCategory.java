package com.guazi.web.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;


/**
 *类目表
 * categoryName 类目名字
 * categoryType 类目编号
 *DynamicUpdate数据库的时间自动更新
 * GeneratedValue 主键自增
 * Data，set/get/toString方法，lombok依赖
 */


@Data
@Entity
@DynamicUpdate
public class ProductCategory implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8819628051174359161L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }

	
    
	
}
