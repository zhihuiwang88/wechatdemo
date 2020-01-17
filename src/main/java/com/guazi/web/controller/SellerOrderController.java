package com.guazi.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Functions;
import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;
import com.guazi.web.service.impl.OrderMasterService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/seller/order")
@Log4j
public class SellerOrderController {

	@Autowired
	private OrderMasterService ordMasService;
	
	
	/**
	 * localhost:8080/sell/seller/order/list
	 * 卖家订单分页OrderMaster
	 * @param page，第几页
	 * @param size，每页几条数据
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView sellPage(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pageable = new PageRequest(page-1, size);
		Page<OrderMasterDto> list = ordMasService.findListPage(pageable);
		map.put("page", page);
		map.put("size", size);
		map.put("orderMasterPage", list);
		return new ModelAndView("order/list",map);
	}
	
	/**
	 * localhost:8080/sell/seller/order/cancel?orderId=
	 * 卖家端取消订单
	 * @param orderId,商品ID
	 * @return
	 */
	@GetMapping("/cancel")
	public ModelAndView cancelOrder(@RequestParam String orderId) {
		Map<String, Object> map = new HashMap<>();
		try {
			OrderMasterDto orderMasterDto = ordMasService.findOne(orderId);
			ordMasService.cancelOrder(orderMasterDto);
		}catch (SellException e) {
			log.error("【卖家端取消订单】发生异常:", e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error",map);
		}
		map.put("message", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success",map);
	}
	
	/**
	 * localhost:8080/sell/seller/order/detail?orderId=
	 * 卖家端订单详情
	 * @param orderId
	 * @return
	 */
	@GetMapping("/detail")
	public ModelAndView orderDetail(@RequestParam String orderId) {
		Map<String, Object> map = new HashMap<>();
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		try {
			orderMasterDto = ordMasService.findOne(orderId);
		} catch (SellException e) {
			log.error("【卖家端订单详情】错误",e);
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error",map);
		}
		map.put("orderMasterDto", orderMasterDto);
		return new ModelAndView("order/detail", map);
	}
	
	/**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId) {
    	Map<String, Object> map = new HashMap<>();
    	OrderMasterDto orderMasterDto = new OrderMasterDto();
    	try {
    		orderMasterDto = ordMasService.findOne(orderId);
			ordMasService.finishOrder(orderMasterDto);
		} catch (SellException e) {
			log.error("【卖家端完结订单】错误", e);
			map.put("message", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error",map);
		}
    	map.put("message", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success",map);
    }
	
}
