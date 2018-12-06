package com.zhuqifeng.commons.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilBaseProperties {
	protected static Logger logger = LogManager.getLogger(UtilBaseProperties.class);

	// 字符集ISO-8859-1
	public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
	// 字符集GBK
	public static final Charset GBK = Charset.forName("GBK");
	// 字符集utf-8
	public static final Charset UTF_8 = StandardCharsets.UTF_8;
	public static final String UTF_8_STR = "UTF-8";

	public static final String SETTER_PREFIX = "set";
	public static final String GETTER_PREFIX = "get";
	public static final String CGLIB_CLASS_SEPARATOR = "$$";
}
