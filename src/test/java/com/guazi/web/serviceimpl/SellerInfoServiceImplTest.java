package com.guazi.web.serviceimpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.guazi.web.entity.SellerInfo;
import com.guazi.web.service.impl.SellerInfoServiceImpl;
import com.guazi.web.utils.KeyUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {

	@Autowired
	private SellerInfoServiceImpl sellInfoSerImpl;
	
	@Test
	public void findByOpenId() {
		SellerInfo sellerInfo = sellInfoSerImpl.findSellerInfoByOpenid("122");
		Assert.assertNotEquals(null, sellerInfo);
	}
	
	@Test
	public void saveData() {
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtil.getUniqueKey());
		sellerInfo.setUsername("赵六");;
		sellerInfo.setPassword("zhaoliu");
		sellerInfo.setOpenid("132");
		SellerInfo resutl = sellInfoSerImpl.save(sellerInfo);
		Assert.assertNotEquals(null, resutl);
	}
	
	
		
	
	
	
	
	
}
