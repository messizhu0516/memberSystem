/**  
 * Project Name:hqb-loan-data  
 * File Name:A.java  
 * Package Name:com.hqblicai.base.util  
 * Date:2019年6月19日  上午10:13:08  
 * Copyright (c) 2019, zz621@126.com All Rights Reserved.   
 */

package com.zhuqifeng.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**  
 * ClassName:SpringBeanUtil </br>
 * Function: 
 * <p>SpringBeanUtil，获取spring上下文以及bean的工具类</p>
 * Reason:   TODO ADD REASON.  </br>
 * Date:     2019年6月19日  上午10:13:08 
 *
 * @author   zhuqifeng  
 * @version  
 * @since    JDK 1.8  
 * @see       
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clz) throws BeansException {
		return (T) applicationContext.getBean(clz);
	}
}
