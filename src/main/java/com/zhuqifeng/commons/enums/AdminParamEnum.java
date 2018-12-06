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
 * ClassName:AdminParamEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年5月2日 下午3:34:21 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public enum AdminParamEnum {
	LOGINNAME("admin"), ROLECODE("admin");
	private String value;

	private AdminParamEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
