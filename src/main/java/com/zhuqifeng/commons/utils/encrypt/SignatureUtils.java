package com.zhuqifeng.commons.utils.encrypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Created by BF100400 on 2017/4/17.
 */
public class SignatureUtils {

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	/**
	 * @encryptStr 摘要
	 * @signature  签名
	 * @pubCerPath 公钥路径
	 * 验签
	 */
	public static boolean verifySignature(String pubCerPath, String encryptStr, String sign) throws Exception {
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(encryptStr.getBytes("UTF-8"));
		return signature.verify(Base64.getDecoder().decode(sign));
	}

	/**
	 *
	 * 用私钥对信息生成数字签名
	 * @param data 已加密数据
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, byte[] keyBytes) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.getEncoder().encodeToString(signature.sign());
	}
	
	
	public static void main(String[] args) throws Exception {
//		PrivateKey privateKeyFromFile = RsaReadUtil.getPrivateKeyFromFile("C:\\Users\\Administrator\\Desktop\\ke\\platform_private_cert.pem");
		// {"requestNo":"15434718641000","user_register_date":"20180909","timestamp":1543471864,"pageSize":50,"pageNum":1}
//		Map<String,String> map = new HashMap<>();
//		map.put("requestNo", "15434718641000");
//		map.put("user_register_date", "20180909pp");
//		map.put("timestamp", "1543471864");
//		map.put("pageSize", "50");
//		map.put("pageNum", "1");
//		String jsonString = JSON.toJSONString(map);
//		String sign = sign(jsonString.getBytes(), privateKeyFromFile.getEncoded());
//		System.out.println(sign);
//		
//		boolean verifySignature = verifySignature("C:\\Users\\Administrator\\Desktop\\ke\\platform_public_cert.key", jsonString, sign);
//		System.out.println(verifySignature);
		
		Date date = new Date();
		System.out.println(date.getTime());
		
	}

}
