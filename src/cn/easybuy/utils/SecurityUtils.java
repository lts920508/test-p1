package cn.easybuy.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {
	/**
	 * md5加密
	 */
	
	public static String mt5Hex(String value) {
		return DigestUtils.md5Hex(value);
	}
	
	/**
	 * 3次
	 */
	public static String mt5Hex3(String value) {
		for (int i = 0; i < 3; i++) {
			value=DigestUtils.md5Hex(value);
		}
		return value;
	}
	
}
