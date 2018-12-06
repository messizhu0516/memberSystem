package com.zhuqifeng.commons.utils.beanclone;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

/**
 * ClassName: BeanUtils <br/>
 * Function: 基于CGlib 实体工具类<br/>
 * Reason: TODO <br/>
 * date: 2018年5月24日 上午11:44:58 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public final class BeanUtils extends org.springframework.beans.BeanUtils {
	private BeanUtils() {
	}

	/**
	 * Bean转换为Map
	 *
	 * @param object
	 * @return String-Object的HashMap
	 *
	 * @author chenssy
	 * @date 2016-09-25
	 * @since v1.0.0
	 */
	public static Map<String, Object> bean2MapObject(Object object) {
		if (object == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(object);

					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * Map转换为Java Bean
	 *
	 * @param map
	 *            待转换的Map
	 * @param object
	 *            Java Bean
	 * @return java.lang.Object
	 *
	 * @author chenssy
	 * @date 2016-09-25
	 * @since v1.0.0
	 */
	public static Object map2Bean(Map map, Object object) {
		if (map == null || object == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(object, value);
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 实例化对象
	 * 
	 * @param clazz
	 *            类
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		return (T) instantiate(clazz);
	}

	/**
	 * 实例化对象
	 * 
	 * @param clazzStr
	 *            类名
	 * @return 对象
	 */
	public static <T> T newInstance(String clazzStr) {
		try {
			Class<?> clazz = Class.forName(clazzStr);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.getProperties().list(System.out);
	}

	/**
	 * 获取Bean的属性
	 * 
	 * @param bean
	 *            bean
	 * @param propertyName
	 *            属性名
	 * @return 属性值
	 */
	public static Object getProperty(Object bean, String propertyName) {
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
		if (pd == null) {
			throw new RuntimeException("Could not read property '" + propertyName + "' from bean PropertyDescriptor is null");
		}
		Method readMethod = pd.getReadMethod();
		if (readMethod == null) {
			throw new RuntimeException("Could not read property '" + propertyName + "' from bean readMethod is null");
		}
		if (!readMethod.isAccessible()) {
			readMethod.setAccessible(true);
		}
		try {
			return readMethod.invoke(bean);
		} catch (Throwable ex) {
			throw new RuntimeException("Could not read property '" + propertyName + "' from bean", ex);
		}
	}

	/**
	 * 设置Bean属性
	 * 
	 * @param bean
	 *            bean
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
		if (pd == null) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean PropertyDescriptor is null");
		}
		Method writeMethod = pd.getWriteMethod();
		if (writeMethod == null) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean writeMethod is null");
		}
		if (!writeMethod.isAccessible()) {
			writeMethod.setAccessible(true);
		}
		try {
			writeMethod.invoke(bean, value);
		} catch (Throwable ex) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean", ex);
		}
	}

	/**
	 * copy 对象属性到另一个对象，默认不使用Convert 目前copy不支持map、list
	 * 
	 * @param src
	 * @param clazz
	 *            类名
	 * @return T
	 */
	public static <T> T copy(Object src, Class<T> clazz) {
		BeanCopier copier = BeanCopier.create(src.getClass(), clazz, false);

		T to = newInstance(clazz);
		copier.copy(src, to, null);
		return to;
	}

	/**
	 * 拷贝对象 目前copy不支持map、list
	 * 
	 * @param src
	 *            源对象
	 * @param dist
	 *            需要赋值的对象
	 */
	public static void copy(Object src, Object dist) {
		BeanCopier copier = BeanCopier.create(src.getClass(), dist.getClass(), false);
		copier.copy(src, dist, null);
	}

	/**
	 * 将对象装成map形式
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map toMap(Object src) {
		return BeanMap.create(src);
	}

	/**
	 * 将map 转为 bean
	 */
	public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
		T bean = BeanUtils.newInstance(valueType);
		PropertyDescriptor[] beanPds = getPropertyDescriptors(valueType);
		for (PropertyDescriptor propDescriptor : beanPds) {
			String propName = propDescriptor.getName();
			// 过滤class属性
			if (propName.equals("class")) {
				continue;
			}
			if (beanMap.containsKey(propName)) {
				Method writeMethod = propDescriptor.getWriteMethod();
				if (null == writeMethod) {
					continue;
				}
				Object value = beanMap.get(propName);
				if (!writeMethod.isAccessible()) {
					writeMethod.setAccessible(true);
				}
				try {
					writeMethod.invoke(bean, value);
				} catch (Throwable e) {
					throw new RuntimeException("Could not set property '" + propName + "' to bean", e);
				}
			}
		}
		return bean;
	}

}
