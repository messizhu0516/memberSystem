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
 * ClassName: FileNameEnum <br/>
 * Function: 系统配置文件名称枚举类，方便在config类获取配置参数时通过文件名查找配置参数 <br/>
 * Reason: TODO <br/>
 * date: 2018年7月3日 上午10:00:47 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public enum FileNameEnum {
	/**
	 * 系统配置
	 */
	APPLICATION_PROP("application.properties"),
	/**
	 * 微信配置
	 */
	WEIXIN_PROP("weixin.properties");
	private String value;

	private FileNameEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
