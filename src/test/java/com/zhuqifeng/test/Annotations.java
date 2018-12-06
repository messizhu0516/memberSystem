/**  
 * Project Name:memberSystem  
 * File Name:a.java  
 * Package Name:com.zhuqifeng.test  
 * Date:2018年7月23日下午3:14:52  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @ClassName:Annotations
 * @Description:新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
 * @author diandian.zhang
 * @date 2017年3月31日下午4:39:57
 */
public class Annotations {
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
	public @interface NonEmpty {
	}

	public static class Holder<@NonEmpty T> extends @NonEmpty Object {
		public void method() throws @NonEmpty Exception {
		}
	}

	public static void main(String[] args) {
		final Holder<String> holder = new @NonEmpty Holder<String>();
		@NonEmpty
		Collection<@NonEmpty String> strings = new ArrayList<>();
	}
}
