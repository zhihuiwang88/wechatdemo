package com.guazi.web.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.entity.OrderMaster;

public class OrdMas2OrdMasDTOConverter {

	public static OrderMasterDto converter(OrderMaster orderMaster) {
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		BeanUtils.copyProperties(orderMaster, orderMasterDto);
		return orderMasterDto;
	}

	public static List<OrderMasterDto> converter(List<OrderMaster> orderMasterList) {
		List<OrderMasterDto> orderMasterStream = orderMasterList.stream().map(e -> converter(e))
				.collect(Collectors.toList());
		return orderMasterStream;
	}

}
