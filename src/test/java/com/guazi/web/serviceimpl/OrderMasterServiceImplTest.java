package com.guazi.web.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guazi.web.dto.OrderMasterDto;
import com.guazi.web.entity.OrderDetail;
import com.guazi.web.entity.OrderMaster;
import com.guazi.web.service.impl.OrderMasterServiceImpl;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderMasterServiceImplTest {

	@Autowired
	private OrderMasterServiceImpl omServiceImpl;
	private static final Logger log = LoggerFactory.getLogger(OrderMasterServiceImplTest.class);
	private static String BUYER_OPENID = "110120";
	private static String ORDER_ID = "201912061575597902137";

	// 减库存
	@Test
	public void inventoryReduction() {
		// 订单人信息
		OrderMasterDto omDTO = new OrderMasterDto();
		omDTO.setBuyerName("王五");
		omDTO.setBuyerPhone("18812341022");
		omDTO.setBuyerAddress("上海嘉定政府");
		omDTO.setBuyerOpenid(BUYER_OPENID);
		// 购物车信息
		List<OrderDetail> orderDetailsList = new ArrayList<>();

		OrderDetail orderDetails = new OrderDetail();
		orderDetails.setProductId("1230");
		orderDetails.setProductQuantity(5);
		orderDetailsList.add(orderDetails);
		
		/*OrderDetail orderDetails11 = new OrderDetail();
		orderDetails11.setProductId("12340");
		orderDetails11.setProductQuantity(3);
		orderDetailsList.add(orderDetails11);*/

		omDTO.setOrderDetail(orderDetailsList);
		omServiceImpl.createNewOrder(omDTO);
	}

	// 查询一个订单
	@Test
	public void findOne() {
		OrderMaster orderMaster = new OrderMaster();
		String orderId = "201912041739421575452382544";
		OrderMasterDto dTOId = omServiceImpl.findOne(orderId);
		System.out.println(dTOId);
		Assert.assertNotNull(dTOId);
	}

	@Test
	public void findList() {
		String buyerOpenid = "110120";
		PageRequest pageable = new PageRequest(0, 1);
		Page<OrderMasterDto> page = omServiceImpl.findList(buyerOpenid, pageable);
		Assert.assertNotEquals(0, page.getTotalElements());
	}

	// 增库存
	@Test
	public void inventoryIncrease() {
		OrderMasterDto orderMasterDto = omServiceImpl.findOne("201912061575597902137");
		OrderMasterDto result = omServiceImpl.cancelOrder(orderMasterDto);
		Assert.assertEquals(orderMasterDto.getOrderStatus(), result.getOrderStatus());
	}

	@Test
	public void finishOrder() {
		OrderMasterDto orderMasterDto = omServiceImpl.findOne(ORDER_ID);
		OrderMasterDto fisishOrder = omServiceImpl.finishOrder(orderMasterDto);
		Assert.assertEquals(orderMasterDto.getOrderStatus(), fisishOrder.getOrderStatus());
	}

	// 支付订单
	@Test
	public void payOrder() {
		OrderMasterDto pay = omServiceImpl.findOne(ORDER_ID);
		OrderMasterDto resutl = omServiceImpl.payOrder(pay);
		Assert.assertEquals(pay.getPayStatus(), resutl.getPayStatus());
	}

	// 订单分页
	@Test
	public void orderPage() {
		PageRequest pageRequest = new PageRequest(0, 3);
		Page<OrderMasterDto> result = omServiceImpl.findListPage(pageRequest);
		log.info("【卖家订单分页】，result={}", result);
		Assert.assertNotEquals(0, result.getTotalElements());
	}

}
