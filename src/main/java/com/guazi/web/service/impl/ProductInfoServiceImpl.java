package com.guazi.web.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.guazi.web.dao.ProductInfoDao;
import com.guazi.web.dto.CartDto;
import com.guazi.web.entity.ProductInfo;
import com.guazi.web.enums.ProductStatusEnum;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoDao piDao;
	
	@Override
	@Cacheable(cacheNames="productInfo",key="1234")
	public ProductInfo findByProductId(Integer productId) {
		String strId ="" + productId;
		return piDao.findOne(strId);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return piDao.findByProductStatus(ProductStatusEnum.PRODUCT_UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return piDao.findAll(pageable);
	}

	@Override
	@CachePut(cacheNames="productInfo",key="1234")
	public ProductInfo save(ProductInfo productInfo) {
		return piDao.save(productInfo);
	}

	// 减库存，下单
	@Override
	@Transactional
	public void inventoryReduction(List<CartDto> cartDtoList) {
		for(CartDto cartDto : cartDtoList) {
			ProductInfo productInfo = piDao.findOne(cartDto.getProductId());
			if(StringUtils.isEmpty(productInfo)) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			// 比较product_stock与ProductQuantity
			Integer  numberStock = productInfo.getProductStock() - cartDto.getProductQuantity();
			if(numberStock < 0) {
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(numberStock);
			piDao.save(productInfo);
		}
	}
	
	// 增库存，取消订单
	@Override
	@Transactional
	public void inventoryIncrease(List<CartDto> cartDtoList) {
		for (CartDto cartList : cartDtoList) {
			// 判断productId
			ProductInfo productInfo = piDao.findOne(cartList.getProductId());
			if (StringUtils.isEmpty(productInfo)) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			// 增加库存
			Integer productStock = productInfo.getProductStock() + cartList.getProductQuantity();
			productInfo.setProductStock(productStock);
			piDao.save(productInfo);
		}
		
	}
	//商品下架
	@Override
	public ProductInfo soldOut(String productId) {
		//判断商品存在不和商品状态（上架/下架）
		ProductInfo productInfo = piDao.findOne(productId);
		if(productInfo == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if(productInfo.getProductStatusEnum().equals(ProductStatusEnum.PRODUCT_DOWN)) {
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		//修改商品状态
		productInfo.setProductStatus(ProductStatusEnum.PRODUCT_DOWN.getCode());
		return piDao.save(productInfo);
	}
	//商品上架
	@Override
	public ProductInfo putAway(String productId) {
        //判断商品存在不和商品状态（上架/下架）
		ProductInfo productInfo = piDao.findOne(productId);
		if(productInfo == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if(productInfo.getProductStatusEnum().equals(ProductStatusEnum.PRODUCT_UP)) {
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		//修改商品状态
		productInfo.setProductStatus(ProductStatusEnum.PRODUCT_UP.getCode());
		return  piDao.save(productInfo);
	}

}
