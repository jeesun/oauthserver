package com.simon.common.utils;

import java.util.UUID;

/**
 * @Description: TODO(说明)
 * @author HWJ
 * @date 2018年3月24日
 */
public class CodeUtil {
	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String get6Code() {
		int code = (int) ((Math.random() * 9 + 1) * 100000);
		return code + "";
	}

	/**
	 * 生成4位随机数
	 * 
	 * @return
	 */
	public static String get4Code() {
		int code = (int) ((Math.random() * 9 + 1) * 1000);
		return code + "";
	}

	/**
	 * 生成表单token
	 * 
	 * @return
	 */
	public static String getFormToken() {
		return MD5Util.sign(getUUID());
	}

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}


	public static void main(String[] args) {
		System.out.println(getFormToken());
	}

}
