package com.zhuqifeng.commons.utils.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.zhuqifeng.commons.utils.UtilBaseProperties;

public class Config extends UtilBaseProperties {

	private static Map<String, Map<String, String>> propertiesMap = null;

	public static String getConfig(String fileName, String key) {
		if (StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(key)) {
			if (propertiesMap != null) {
				Map<String, String> map = propertiesMap.get(fileName);
				if (map != null) {
					return map.get(key);
				} else {
					putConfigsTOpropertiesMapFromFile(fileName);
				}
			} else {
				putConfigsTOpropertiesMapFromFile(fileName);
			}
			Map<String, String> map = propertiesMap.get(fileName);
			return map.get(key);
		}
		return null;
	}

	private static Map<String, String> putConfigsTOpropertiesMapFromFile(String fileName) {
		Properties props = new Properties();
		try {
			InputStream in = Config.class.getClassLoader().getResourceAsStream(fileName);
			props.load(new InputStreamReader(in, UTF_8_STR));
			Set<Object> keySet = props.keySet();
			Iterator<Object> iterator = keySet.iterator();
			Map<String, String> map = new HashMap<String, String>();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				map.put(key, props.getProperty(key));
			}
			if (propertiesMap == null) {
				propertiesMap = new HashMap<String, Map<String, String>>();
			}
			propertiesMap.put(fileName, map);
		} catch (IOException e) {
			logger.info(e);
		}
		return null;
	}

}
