package com.guazi.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.guazi.web.entity.ProductCategory;


public interface ProductCategoryMapper {
	
	//新增
	@Insert("insert into product_category(category_name,category_type) "
			+ "VALUES(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
	int insertProductCategory(ProductCategory productCategory);
	
	/*
	 * 查询一个并返回信息
	 * 数据库返回的结果集和实体类字段不对应，我们就需要手动指定映射关系。
	 * @Result代表一个字段的映射关系，column指定数据库字段的名称，property指定实体类属性的名称，
	 * jdbcType数据库字段类型，@Result里的id值为true表明主键，默认false
	 */
	@Select("select category_id,category_name,category_type from product_category "
			+ "where category_type = #{categoryType}")
	@Results(
			{@Result(column = "category_id",property = "categoryId"),
			@Result(column = "category_name",property = "categoryName"),
			@Result(column = "category_type",property = "categoryType")})
	ProductCategory findByCategoryType(Integer categoryType);
	
	/*
	 * 根据categoryName查询
	 */
	@Select("select category_id,category_name,category_type from product_category where category_name=#{categoryName}")
	@Results({
		@Result(column = "category_id",property = "categoryId"),
		@Result(column = "category_name",property = "categoryName"),
		@Result(column = "category_type",property = "categoryType")
	})
	List<ProductCategory> findByCategoryName(String categoryName);

	/**
	 * 下面的是采用了XXXMapper.xml
	 */
	
	//根据查询categoryType查询
	ProductCategory selectCategoryType(Integer categoryType);
	
	
}
