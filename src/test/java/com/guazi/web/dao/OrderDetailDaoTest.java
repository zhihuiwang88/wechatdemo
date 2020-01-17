package com.guazi.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.entity.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

	@Autowired
	private OrderDetailDao oDetailDao;
	
	
	@Test
	public void saveTest() {
		OrderDetail details = new OrderDetail();
		details.setDetailId("4");
		details.setOrderId("103");
		details.setProductId("0105");
		details.setProductName("华硕电脑");
		details.setProductPrice(new BigDecimal(55.6));
		details.setProductQuantity(1);
		details.setProductIcon("http://XXX.png");
		OrderDetail result = oDetailDao.save(details);
		Assert.assertNotNull(result);
	}
	
	// 买家OrderId为103的两件产品
	@Test
	public void findByOrderId() {
		List<OrderDetail> list = oDetailDao.findByOrderId("103");
		System.out.println(list);
		Assert.assertNotEquals(0, list.size());
	}
}
