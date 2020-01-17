package com.guazi.web.dao;

import java.math.BigDecimal;

import org.apache.catalina.authenticator.SavedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.entity.OrderMaster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
	
	@Autowired
	private OrderMasterDao  oMasterDao;
	
	private static  final String OPENID = "001";
	
	@Test
	public void saveTest() {
		OrderMaster master = new OrderMaster();
		master.setOrderId("103");
		master.setBuyerName("刘先生");
		master.setBuyerPhone("18812344444");
		master.setBuyerAddress("北京昌平政府");
		master.setBuyerOpenid(OPENID);
		master.setOrderAmount(new BigDecimal(101));
		// 0默认为新订单
		master.setOrderStatus(0);
		// 0默认未支付
		master.setPayStatus(0);
		OrderMaster one = oMasterDao.save(master);
		Assert.assertNotNull(one);
	}
	
	
	@Test
	public void findByOpenId() {
		PageRequest pageable = new PageRequest(0, 2);
		Page<OrderMaster> orderMaster = oMasterDao.findByBuyerOpenid(OPENID, pageable);
		Assert.assertNotEquals(0, orderMaster.getTotalElements());
	}
	

}
