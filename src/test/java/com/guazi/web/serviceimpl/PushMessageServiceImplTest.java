package com.guazi.web.serviceimpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.service.impl.OrderMasterService;
import com.guazi.web.service.impl.PushMessageServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {

	@Autowired
	private OrderMasterService orderMasterService;
	@Autowired
	private PushMessageServiceImpl pushMessageServiceImpl;
	
	
	@Test
	public void orderPushMsg() {
		String orderId = "201912061575622112057";
		OrderMasterDto  orderMasterDto  = orderMasterService.findOne(orderId);
		pushMessageServiceImpl.orderPush(orderMasterDto);
		
	}
	
	
}
