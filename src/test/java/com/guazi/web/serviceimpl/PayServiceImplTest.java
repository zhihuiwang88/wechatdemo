package com.guazi.web.serviceimpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.service.impl.OrderMasterService;
import com.guazi.web.service.impl.PayService;
import com.guazi.web.service.impl.PayServiceImpl;

@SpringBootTest
@RunWith(value = SpringRunner.class)
public class PayServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(PayServiceImplTest.class);
	
	private static final String ORDER_ID = "201912041739421575452382544";
	
	@Autowired
	private PayService payService;
	@Autowired
	private OrderMasterService ordMasService;
	
@Test
public void createOrder() {
	
	OrderMasterDto ordMasDTO = ordMasService.findOne(ORDER_ID);
	payService.createOrder(ordMasDTO);
	
}
	




}
