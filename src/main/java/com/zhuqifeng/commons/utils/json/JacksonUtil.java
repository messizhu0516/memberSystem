/**  
 * Project Name:memberSystem  
 * File Name:JacksonUtil.java  
 * Package Name:com.zhuqifeng.commons.utils.json  
 * Date:2018年7月5日下午2:22:54  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.utils.json;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ClassName:JacksonUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年7月5日 下午2:22:54 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public class JacksonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String BeanToJson(Object obj) {
		StringWriter sw = new StringWriter();
		JsonGenerator gen = null;
		try {
			gen = new JsonFactory().createGenerator(sw);
			mapper.writeValue(gen, obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (gen != null) {
					gen.close();
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return sw.toString();
	}

	public static <T> T JsonToBean(String jsonStr, Class<T> objClass) {
		T t;
		try {
			t = mapper.readValue(jsonStr, objClass);
		} catch (IOException e) {
			e.printStackTrace();
			t = null;
		}
		return t;
	}

}
