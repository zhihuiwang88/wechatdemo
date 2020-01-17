package com.guazi.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.enums.ResultEnum;
import com.guazi.web.exception.SellException;
import com.guazi.web.utils.JsonUtil;
import com.guazi.web.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Service
public class PayServiceImpl implements PayService{

	@Autowired
	private BestPayServiceImpl bestPayServiceImpl;
	private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);
	private static final String ORDER_NAME = "微信点餐";
	
	@Autowired
	private OrderMasterService ordMasService;
	//微信统一下单参数设置 
	@Override
	public PayResponse createOrder(OrderMasterDto orderMasterDto) {
		PayRequest payRequest = new PayRequest();
		 payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		 payRequest.setOrderId(orderMasterDto.getOrderId());
		 payRequest.setOrderName(ORDER_NAME);
		 payRequest.setOrderAmount(orderMasterDto.getOrderAmount().doubleValue());
		 payRequest.setOpenid(orderMasterDto.getBuyerOpenid());
		 log.info("【微信支付】支付人，payRequest={}",JsonUtil.toJson(payRequest));
		 PayResponse payResponse = bestPayServiceImpl.pay(payRequest);
		 //错误没有解决：【微信统一支付】发起支付, returnCode != SUCCESS, returnMsg = 签名错误
		//AppId与AppSecret不对应
		 log.info("【微信支付】，payResponse={}",JsonUtil.toJson(payResponse));
		 return payResponse;
		 
	}

	@Override
	public PayResponse  notify(String notifyData) {
		PayResponse payResponse = bestPayServiceImpl.asyncNotify(notifyData);
		// 判断订单
		OrderMasterDto orderMasterDto = ordMasService.findOne(payResponse.getOrderId());
		if(orderMasterDto == null) {
			 log.error("【微信支付】异步通知, 订单不存在, orderId={}", payResponse.getOrderId());
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		//判断金额是否一致
		if(!MathUtil.equal(payResponse.getOrderAmount(), orderMasterDto.getOrderAmount().doubleValue())) {
			 log.error("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}",
	                    payResponse.getOrderId(),
	                    payResponse.getOrderAmount(),
	                    orderMasterDto.getOrderAmount());
	            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
		}
		// 修改订单支付状态
		ordMasService.payOrder(orderMasterDto);
		return payResponse;
		
	}

	//订单退款
	public RefundResponse refundOrder(OrderMasterDto orderMasterDto) {
		RefundRequest refundRequest = new RefundRequest();
		refundRequest.setOrderAmount(orderMasterDto.getOrderAmount().doubleValue());
		refundRequest.setOrderId(orderMasterDto.getOrderId());
		refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		
		RefundResponse refundResponse = bestPayServiceImpl.refund(refundRequest);
		return refundResponse;
	}
	
	
	
}
