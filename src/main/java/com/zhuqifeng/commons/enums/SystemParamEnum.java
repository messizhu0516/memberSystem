/**  
 * Project Name:FFJRManagement  
 * File Name:UserEnum.java  
 * Package Name:com.ffjr.commons.enums  
 * Date:2018年5月2日下午3:34:21  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.enums;

/**
 * ClassName: SystemParamEnum <br/>
 * Function: 系统相关的属性枚举 类<br/>
 * Reason: TODO <br/>
 * date: 2018年6月19日 下午1:39:40 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public enum SystemParamEnum {
	/**
	 * 系统账户
	 */
	AC_SYSTEM("s"),
	/**
	 * 会员账号
	 */
	AC_MEMBER("c"),
	/**
	 * 企业账户
	 */
	AC_COMPANY("c");
	private String value;

	private SystemParamEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
