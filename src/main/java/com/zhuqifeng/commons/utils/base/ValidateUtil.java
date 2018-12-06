package com.zhuqifeng.commons.utils.base;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断对象、字符串、集合是否为空、不为空
 * 
 * @Author:chenssy
 * @date:2014年8月4日
 */
public final class ValidateUtil {

	private static Pattern numeric_Pattern = Pattern.compile("^[0-9\\-]+$");
	private static Pattern floatNumeric_Pattern = Pattern.compile("^[0-9\\-\\.]+$");
	private static Pattern abc_Pattern = Pattern.compile("^[a-z|A-Z]+$");
	public static final String EMAIL_REG = "^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$";
	public static final String MOBILE_REG = "^((13[0-9])|(14[0-9])|(15[0|1|2|3|5|6|7|8|9]|17[6|8])|(18[0-9]))\\d{8}$";

	/**
	 * @author ZhuQiFeng
	 * @addDate 2015-7-9上午10:19:19
	 * @description 判断访问来源是手机还是PC
	 * @return true(手机)
	 */
	public static boolean isMobileConnect(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
				"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
				"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech",
				"gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew",
				"cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-",
				"moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
				"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		String header = request.getHeader("User-Agent");
		if (StringUtils.isNotEmpty(header)) {
			for (String mobileAgent : mobileAgents) {
				if (header.toLowerCase().indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}

	/**
	 * 判断字符串是否是一个IP地址
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr) {
		if (isEmpty(addr))
			return false;
		String[] ips = addr.split(".");
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 判断是不是一个合法的手机号码
	 */
	public static boolean isMobile(String s) {
		s = StringUtils.trim(s, null, 0);
		if (isEmpty(s))
			return false;
		Pattern p = Pattern.compile(MOBILE_REG);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 校验邮箱是否合法
	 * 
	 * @param strEmail
	 * @return
	 */
	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REG);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 判断是否浮点数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isFloatNumeric(String src) {
		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = floatNumeric_Pattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * 判断是否纯字母组合
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否纯字母组合的标志
	 */
	public static boolean isABC(String src) {
		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = abc_Pattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * 判断是否数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumeric(String src) {
		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = numeric_Pattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @author chenssy
	 * @date Dec 23, 2013
	 * @param array
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private static <T> boolean isEmptyArray(T[] array) {
		if (array == null || array.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断数组是否不为空
	 * 
	 * @author chenssy
	 * @date Dec 23, 2013
	 * @param array
	 * @return boolean
	 */
	public static <T> boolean isNotEmptyArray(T[] array) {
		if (array != null && array.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @author chenssy
	 * @date Dec 23, 2013
	 * @param string
	 * @return boolean
	 */
	public static boolean isEmptyString(String string) {
		if (string == null || string.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @author chenssy
	 * @date Dec 23, 2013
	 * @param string
	 * @return boolean
	 */
	public static boolean isNotEmptyString(String string) {
		if (string != null && string.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @author chenssy
	 * @date Dec 26, 2013
	 * @param collection
	 * @return boolean
	 */
	public static boolean isEmptyCollection(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @author chenssy
	 * @date Dec 26, 2013
	 * @param collection
	 * @return boolean
	 */
	public static boolean isNotEmptyCollection(Collection<?> collection) {
		if (collection != null && !collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断map集合是否不为空
	 * 
	 * @author chenssy
	 * @date Dec 26, 2013
	 * @param map
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmptyMap(Map map) {
		if (map != null && !map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断map集合是否为空
	 * 
	 * @author ming.chen
	 * @date Dec 26, 2013
	 * @param map
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyMap(Map map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检验对象是否为空,String 中只有空格在对象中也算空.
	 * 
	 * @param object
	 * @return 为空返回true,否则false.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
		if (null == object)
			return true;
		else if (object instanceof String)
			return "".equals(object.toString().trim());
		else if (object instanceof Iterable)
			return !((Iterable) object).iterator().hasNext();
		else if (object.getClass().isArray())
			return Array.getLength(object) == 0;
		else if (object instanceof Map)
			return ((Map) object).size() == 0;
		else if (Number.class.isAssignableFrom(object.getClass()))
			return false;
		else if (Date.class.isAssignableFrom(object.getClass()))
			return false;
		else
			return false;
	}

	/**
	 * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	 * 
	 * @param obj
	 *            被验证的对象
	 * @param message
	 *            异常信息
	 */
	@SuppressWarnings("rawtypes")
	public static void objNotEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 * 
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 *            传入的字符串
	 * @return
	 */
	public static boolean isChinese(String value) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(value).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 *            传入的字符串
	 * @return
	 */
	public static boolean isDouble(String value) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(value).matches();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 *            传入的字符串
	 * @return
	 */
	public static boolean isInteger(String value) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(value).matches();
	}
}
