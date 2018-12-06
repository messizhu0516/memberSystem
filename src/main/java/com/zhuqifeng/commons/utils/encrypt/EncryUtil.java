package com.zhuqifeng.commons.utils.encrypt;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuqifeng.commons.utils.UtilBaseProperties;
import com.zhuqifeng.commons.utils.base.StringUtils;

public class EncryUtil extends UtilBaseProperties {
	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(TreeMap<String, Object> map, String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}

	/**
	 * 对易宝支付返回的结果进行验签
	 * 
	 * @param data
	 *            易宝支付返回的业务数据密文
	 * @param encrypt_key
	 *            易宝支付返回的对ybAesKey加密后的密文
	 * @param yibaoPublickKey
	 *            易宝支付提供的公钥
	 * @param merchantPrivateKey
	 *            商户自己的私钥
	 * @return 验签是否通过
	 * @throws Exception
	 */
	public static boolean checkDecryptAndSign(String realData, String yibaoPublickKey, String merchantPrivateKey) throws Exception {
		ObjectMapper om = new ObjectMapper();
		@SuppressWarnings("unchecked")
		TreeMap<String, String> map = om.readValue(realData, TreeMap.class);
		/** 3.取得data明文sign。 */
		String sign = StringUtils.trimToEmpty(map.get("sign"));
		/** 4.对map中的值进行验证 */
		StringBuffer signData = new StringBuffer();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			/** 把sign参数隔过去 */
			if (StringUtils.equals((String) entry.getKey(), "sign")) {
				continue;
			}
			signData.append(entry.getValue() == null ? "" : entry.getValue());
		}
		/** 5. result为true时表明验签通过 */
		boolean result = RSA.checkSign(signData.toString(), sign, yibaoPublickKey);
		return result;
	}

	/**
	 * 生成hmac
	 */
	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String hmacTemp = sbuffer.toString();

		String hmac = "";
		if (StringUtils.isNotEmpty(hmacKey)) {
			hmac = HMACSHA1.hmacSHASign(hmacTemp, hmacKey, UTF_8_STR);
		}
		return hmac;
	}
}
