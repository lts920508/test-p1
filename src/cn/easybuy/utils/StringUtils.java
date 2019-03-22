package cn.easybuy.utils;

import java.util.UUID;

public class StringUtils {
	public static String randomUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
}
