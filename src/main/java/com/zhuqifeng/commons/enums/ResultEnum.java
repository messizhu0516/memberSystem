/**  
 * Project Name:FFJRCommonInterface  
 * File Name:ResultEnum.java  
 * Package Name:com.ffjr.commons.enums  
 * Date:2018年5月22日下午2:08:18  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.enums;

/**
 * ClassName:ResultEnum <br/>
 * Function: HTTP请求返回json结果对象时，用于描述结果的字段枚举类 <br/>
 * Date: 2018年5月22日 下午2:08:18 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public enum ResultEnum {
	/**
	 * 返回码：成功
	 */
	CODE_SUCC("000"), DESC_SUCC("操作成功"),
	/**
	 * 返回码：失败，一般指系统发生的运行期错误如抛出exception，或者解密，验签失败，或者字段校验失败，业务逻辑抛出exception等等
	 */
	CODE_FAIL("999"), DESC_FAIL("系统错误"),
	/**
	 * 返回码key
	 */
	KEY_CODE("code"),
	/**
	 * 返回描述key
	 */
	KEY_MSG("msg"),
	/**
	 * 返回对象key
	 */
	KEY_OBJ("obj");

	private String value;

	private ResultEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
