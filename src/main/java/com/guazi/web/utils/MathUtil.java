package com.guazi.web.utils;

public class MathUtil {

	/**
	 * 判断支付订单的金额与下单的金额
	 * @param dou1,支付订单的金额
	 * @param dou2,下单的金额
	 */
	private static final Double MONEY_RANGE = 0.01;
	
	public static boolean equal(Double dou1,Double dou2) {
		Double dou = Math.abs(dou1 - dou2);
		if(dou < MONEY_RANGE) {
			return true;
		}else {
			return false;
		}
		
		
	}
}
