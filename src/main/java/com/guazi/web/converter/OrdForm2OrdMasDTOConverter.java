package com.guazi.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guazi.web.controller.BuyerOrderController;
import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.entity.OrderDetail;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;
import com.guazi.web.form.OrderForm;

public class OrdForm2OrdMasDTOConverter {

	private static final Logger log = LoggerFactory.getLogger(OrdForm2OrdMasDTOConverter.class);
	
	public static OrderMasterDto converter(OrderForm ordForm) {
		Gson gson = new Gson();
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		orderMasterDto.setBuyerName(ordForm.getName());
		orderMasterDto.setBuyerPhone(ordForm.getPhone());
		orderMasterDto.setBuyerAddress(ordForm.getAddress());
		orderMasterDto.setBuyerOpenid(ordForm.getOpenid());
		List<OrderDetail> orderDetailList = new ArrayList<>();
		//json对象转Java对象列表
		try {
			orderDetailList = gson.fromJson(ordForm.getItems(), 
					new TypeToken<List<OrderDetail>>(){}.getType());
		} catch (Exception e) {
			 log.error("【对象转换】错误, string={}", ordForm.getItems());
	            throw new SellException(ResultEnum.PARAM_ERROR);
		}
		orderMasterDto.setOrderDetail(orderDetailList);
		return orderMasterDto;
	}

}
