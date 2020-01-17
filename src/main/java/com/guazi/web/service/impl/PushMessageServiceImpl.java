package com.guazi.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guazi.web.config.WechatAccountConfig;
import com.guazi.web.dto.OrderMasterDto;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WechatAccountConfig accountConfig;

	/**
	 * 微信公众平台官网
	 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html#5
	 * 微信订单推送消息
	 * @param touser,接收者openid
	 * @param template_id,模板ID
	 * @param data,模板数据
	 * @throws WxErrorException
	 */
	@Override
	public void orderPush(OrderMasterDto orderMasterDto) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(orderMasterDto.getBuyerOpenid());
		templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderPushStatus"));
		templateMessage.setTemplateId("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY");
		List<WxMpTemplateData> templateData = Arrays.asList(
				new WxMpTemplateData("first", "恭喜你购买成功！"),
				new WxMpTemplateData("keyword1", "微信点餐"), 
				new WxMpTemplateData("keyword2", "18866662345"),
				new WxMpTemplateData("keyword3", orderMasterDto.getOrderId()),
				new WxMpTemplateData("keyword4", orderMasterDto.getOrderStatusEnum().getMessage()),
				new WxMpTemplateData("keyword5", "￥" + orderMasterDto.getOrderAmount()),
				new WxMpTemplateData("remark", "欢迎再次光临！"));
		templateMessage.setData(templateData);
		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		} catch (WxErrorException e) {
			log.error("【微信模版消息】发送失败, {}", e);
		}

	}

}
