package com.guazi.web.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.guazi.web.converter.OrdMas2OrdMasDTOConverter;
import com.guazi.web.dao.OrderDetailDao;
import com.guazi.web.dao.OrderMasterDao;
import com.guazi.web.dto.CartDto;
import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.entity.OrderDetail;
import com.guazi.web.entity.OrderMaster;
import com.guazi.web.entity.ProductInfo;
import com.guazi.web.enums.OrderStatusEnum;
import com.guazi.web.enums.PayStatusEnum;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.ResponseBankException;
import com.guazi.web.exception.SellException;
import com.guazi.web.utils.KeyUtil;



@Service
public class OrderMasterServiceImpl implements OrderMasterService {
	@Autowired
	private OrderMasterDao orderMasterDao;
	
	@Autowired
	private ProductInfoService pinfoService;
	@Autowired
	private OrderDetailDao odetailDao;
	@Autowired
	private PayService payService;
	@Autowired
	private  MyWebSocket webSocket;
	@Autowired
	private PushMessageService pushMessageService;
	
	Logger log = LoggerFactory.getLogger(OrderMasterServiceImpl.class);
	
	/*
	 * 创建新订单
	 * (non-Javadoc)
	 */
	@Override
	@Transactional
	public OrderMasterDto createNewOrder(OrderMasterDto orderMasterDto) {

		// 订单总价初始为零
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		String orderId = KeyUtil.getUniqueKey();
		
		//查询商品（数量，价格）
		// 获取订单详情
		List<OrderDetail> orderDetailList = orderMasterDto.getOrderDetail();
		for(OrderDetail oDetail : orderDetailList) {
			Integer integerId = Integer.parseInt(oDetail.getProductId());
			ProductInfo proInfo = pinfoService.findByProductId(integerId);
			if(proInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//				throw new ResponseBankException();
			}
			//计算订单总价格,单价 * 数量
			orderAmount = proInfo.getProductPrice().multiply(new BigDecimal(oDetail.getProductQuantity()))
					.add(orderAmount);
			//订单详情入库,detail_id,order_id
			oDetail.setOrderId(orderId);
			oDetail.setDetailId(KeyUtil.getUniqueKey());
			BeanUtils.copyProperties(proInfo, oDetail);
			odetailDao.save(oDetail);
		}
		//写入订单数据库OrderMaster
		OrderMaster orderMaster = new OrderMaster();
		orderMasterDto.setOrderId(orderId);
		BeanUtils.copyProperties(orderMasterDto, orderMaster);
		//订单总价
		orderMaster.setOrderAmount(orderAmount);
		//订单状态
		orderMaster.setOrderStatus(OrderStatusEnum.NEW_ORDER.getCode());
		//订单支付状态
		orderMaster.setPayStatus(PayStatusEnum.PAYMENT_WAITING.getCode());
		orderMasterDao.save(orderMaster);
		 //扣库存
		List<CartDto> cartDtoList = orderDetailList.stream()
				.map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
				.collect(Collectors.toList());
		pinfoService.inventoryReduction(cartDtoList);
		//有新订单进行消息推送
		webSocket.sendMsg("有新订单");
		return orderMasterDto;
	}

	// 查询一个订单
	@Override
	public OrderMasterDto findOne(String orderId) {
		// 判断商品表中的orderId不为空
		OrderMaster orderMaster = orderMasterDao.findOne(orderId);
		if(StringUtils.isEmpty(orderMaster)) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		// 判断订单详情表的order_id不为空
		List<OrderDetail> orderDetailList = odetailDao.findByOrderId(orderId);
		if(orderDetailList.size() == 0) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		//买家信息COPY到OrderMasterDto
		BeanUtils.copyProperties(orderMaster,orderMasterDto);
		// 买家信息和买家买的商品组合一起
		BeanUtils.copyProperties(orderMaster, orderDetailList);
		orderMasterDto.setOrderDetail(orderDetailList);
		return orderMasterDto;
	}

	//根据买家buyerOpenid，查新信息并分页
	@Override
	public Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable) {
 
		Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderMasterDto>  content = OrdMas2OrdMasDTOConverter.converter(orderMasterPage.getContent());
		PageImpl<OrderMasterDto> pageImpl = new PageImpl<OrderMasterDto>(content, pageable, orderMasterPage.getTotalElements());
		return pageImpl;
	}

	
	// 取消订单
	@Override
	@Transactional
	public OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto) {

		OrderMaster orderMaster = new OrderMaster();
		// 查询订单的状态
		if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
			log.error("【取消订单】订单状态不正确，orderId={}, orderStatus={}", orderMasterDto.getOrderId(),
					orderMasterDto.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// 修改订单状态
		// 1代表订单取消
		orderMasterDto.setOrderStatus(OrderStatusEnum.CANNCEL_ORDER.getCode());
		BeanUtils.copyProperties(orderMasterDto, orderMaster);
		OrderMaster updateStatus = orderMasterDao.save(orderMaster);
		if (updateStatus == null) {
			log.error("【取消订单】更新失败,orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		// 判断商品详情
		if (StringUtils.isEmpty(orderMasterDto.getOrderDetail())) {
			log.error("【取消订单】订单中无商品详情,orderMasterDto={}", orderMasterDto);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		// 库存增加
		List<CartDto> cartDtoList = orderMasterDto.getOrderDetail().stream()
				.map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		pinfoService.inventoryIncrease(cartDtoList);
		// 如果支付，则退钱
		if(orderMasterDto.getPayStatus().equals(PayStatusEnum.PAYMENT_SUCCESS.getCode())) {
			payService.refundOrder(orderMasterDto);
		}
		return orderMasterDto;
	}

	@Override
	public OrderMasterDto finishOrder(OrderMasterDto orderMasterDto) {

		// 判断订单状态,2=0
		if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
			log.error("【完结订单】订单状态不正确，orderId={}, orderStatus={}", orderMasterDto.getOrderId(),
					orderMasterDto.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// 修改订单状态，2代表订单完结
		orderMasterDto.setOrderStatus(OrderStatusEnum.FINISH_ORDER.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderMasterDto, orderMaster);
		// 更新订单状态
		OrderMaster udateResult = orderMasterDao.save(orderMaster);
		if (udateResult == null) {
			log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		// 推送微信模版消息
		pushMessageService.orderPush(orderMasterDto);
		return orderMasterDto;
	}
	
	//支付订单
	@Override
	@Transactional
	public OrderMasterDto payOrder(OrderMasterDto orderMasterDto) {
		// 判断订单状态
		if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
			log.error("【支付订单完成】订单状态不正确,orderId={}, orderStatus={}", orderMasterDto.getOrderId(),
					orderMasterDto.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// 判断支付状态
		if (!orderMasterDto.getPayStatus().equals(PayStatusEnum.PAYMENT_WAITING.getCode())) {
			log.error("【支付订单完成】支付状态不正确,orderId={}, payStatus={}", orderMasterDto.getOrderId(),
					orderMasterDto.getPayStatus());
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		// 修改支付状态
		OrderMaster orderMaster = new OrderMaster();
		orderMasterDto.setPayStatus(PayStatusEnum.PAYMENT_SUCCESS.getCode());
		BeanUtils.copyProperties(orderMasterDto, orderMaster);
		OrderMaster updateResult = orderMasterDao.save(orderMaster);
		if (updateResult == null) {
			log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderMasterDto;
	}
                                    
	//卖家订单分页
	@Override
	public Page<OrderMasterDto> findListPage(Pageable pageable) {
		Page<OrderMaster>  result = orderMasterDao.findAll(pageable);
		List<OrderMasterDto>  content = OrdMas2OrdMasDTOConverter.converter(result.getContent());
		PageImpl<OrderMasterDto> pageImpl = new PageImpl<>(content, pageable, result.getTotalElements());
		return pageImpl;   
	}

	
	
	
	
}
