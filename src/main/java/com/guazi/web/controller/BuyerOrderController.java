package com.guazi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guazi.web.converter.OrdForm2OrdMasDTOConverter;
import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;
import com.guazi.web.form.OrderForm;
import com.guazi.web.service.impl.BuyerService;
import com.guazi.web.service.impl.OrderMasterService;
import com.guazi.web.utils.ResultVOUtil;
import com.guazi.web.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	
	@Autowired
	private OrderMasterService ordMasService;
	//日志 
	private static final Logger log = LoggerFactory.getLogger(BuyerOrderController.class);
    
	@Autowired
	private BuyerService buyerService;
	
	
	
	/**
	 * 创建订单
	 * Valid，验证变量并返回错误信息：手机号必填
	 * @param orderForm 参数信息
	 * @param bindingResult
	 * localhost:8080/sell/buyer/order/create
	 * @return
	 */
	@PostMapping("/create")
	public ResultVO<Object> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {
		// 判断orderForm中参数
		if (bindingResult.hasErrors()) {
			log.error("【创建订单】订单参数不正确，orderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		// 判断购物车
		OrderMasterDto ordMasDTO = OrdForm2OrdMasDTOConverter.converter(orderForm);
		if (CollectionUtils.isEmpty(ordMasDTO.getOrderDetail())) {
			log.error("【创建订单】购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}

		OrderMasterDto createResult = ordMasService.createNewOrder(ordMasDTO);
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", createResult.getOrderId());
		return ResultVOUtil.success(map);
	}
	
	/**
	 * 查询订单列表
	 * @param openid,买家微信openid
	 * @param page，0页开始
	 * @param size，每页10条数据
	 * localhost:8080/sell/buyer/order/list
	 * @return
	 */
	@GetMapping("/list")
	public ResultVO<List<OrderMasterDto>> findList(@RequestParam("openid") String openid,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		
		if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
		PageRequest pageable = new PageRequest(page, size);
		Page<OrderMasterDto> listPage = ordMasService.findList(openid, pageable);
		return ResultVOUtil.success(listPage.getContent());
	}
	
	//订单详情
	@GetMapping("/detail")
	public ResultVO<OrderMasterDto> findDetailAndOrder(@RequestParam("openid") String openid,
			@RequestParam("orderId") String orderId) {
		OrderMasterDto orderOne = buyerService.findOrderOne(openid, orderId);
		return ResultVOUtil.success(orderOne);
	}
	
	//取消订单
	@PostMapping("/cancel")
	public ResultVO cancelOrder(@RequestParam("openid") String openid,
			@RequestParam("orderId") String orderId) {
		buyerService.cancelOrder(openid, orderId);
		return ResultVOUtil.success();
	}
	
	

}
