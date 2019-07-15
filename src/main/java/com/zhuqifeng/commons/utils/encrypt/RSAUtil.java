/**  
 * Project Name:hqb-sop  
 * File Name:RSAUtil.java  
 * Package Name:com.hqblicai.security  
 * Date:2019年7月3日  下午4:30:07  
 * Copyright (c) 2019, zz621@126.com All Rights Reserved.   
 */

package com.zhuqifeng.commons.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Date;


/**  
 * ClassName:RSAUtil </br>
 * Function: TODO ADD FUNCTION. </br>
 * Reason:   TODO ADD REASON.  </br>
 * Date:     2019年7月3日  下午4:30:07 
 *
 * @author   James Zhu  
 * @version  3.8
 * @since    JDK 1.7  
 * @see       
 */
public class RSAUtil {
	public static void main(String[] args) throws Exception {
		String content = "啊书法大是大非";

		KeyPair keyPair = getKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("公钥：" + encodePublicKey(publicKey));
		System.out.println("私钥：" + encodePrivateKey(privateKey));

//		String sha1Sign = getSha1Sign(content, privateKey);
//		System.out.println("签名：" + sha1Sign);
//
//		// 验签
//		boolean sha1Verifty = verifyWhenSha1Sign(content, sha1Sign, publicKey);
//		System.out.println("验签：" + sha1Verifty);
		

	}

	public static String encodePublicKey(PublicKey publicKey) {
		byte[] encoded = publicKey.getEncoded();
		byte[] encode = Base64.getEncoder().encode(encoded);
		String string = null;
		try {
			string = new String(encode, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String encodePrivateKey(PrivateKey privateKey) {
		byte[] encoded = privateKey.getEncoded();
		byte[] encode = Base64.getEncoder().encode(encoded);
		String string = null;
		try {
			string = new String(encode, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	//生成密钥对
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048); //可以理解为：加密后的密文长度，实际原文要小些 越大 加密解密越慢
		KeyPair keyPair = keyGen.generateKeyPair();
		return keyPair;
	}

	//用md5生成内容摘要，再用RSA的私钥加密，进而生成数字签名
	public static String getMd5Sign(String content, PrivateKey privateKey) throws Exception {
		byte[] contentBytes = content.getBytes("utf-8");
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(contentBytes);
		byte[] signs = signature.sign();
		return Base64.getEncoder().encodeToString(signs);
	}

	//对用md5和RSA私钥生成的数字签名进行验证
	public static boolean verifyWhenMd5Sign(String content, String sign, PublicKey publicKey) throws Exception {
		byte[] contentBytes = content.getBytes("utf-8");
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicKey);
		signature.update(contentBytes);
		return signature.verify(Base64.getDecoder().decode(sign));
	}

	//用sha1生成内容摘要，再用RSA的私钥加密，进而生成数字签名
	public static String getSha1Sign(String content, PrivateKey privateKey) throws Exception {
		byte[] contentBytes = content.getBytes("utf-8");
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privateKey);
		signature.update(contentBytes);
		byte[] signs = signature.sign();
		return Base64.getEncoder().encodeToString(signs);
	}

	//对用md5和RSA私钥生成的数字签名进行验证
	public static boolean verifyWhenSha1Sign(String content, String sign, PublicKey publicKey) throws Exception {
		byte[] contentBytes = content.getBytes("utf-8");
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(publicKey);
		signature.update(contentBytes);
		return signature.verify(Base64.getDecoder().decode(sign));
	}
}
