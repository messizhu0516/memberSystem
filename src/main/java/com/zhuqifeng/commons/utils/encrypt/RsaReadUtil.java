package com.zhuqifeng.commons.utils.encrypt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <b>公私钥读取工具</b><br>
 * <br>
 * 
 * @author 行者
 * @version 4.1.0
 */
public final class RsaReadUtil {

	/**
	 * 根据Cer文件读取公钥
	 * 
	 * @param pubCerPath
	 * @return
	 */
	public static PublicKey getPublicKeyFromFile(String pubCerPath) {
		FileInputStream pubKeyStream = null;
		BufferedReader br = null;
		try {
			pubKeyStream = new FileInputStream(pubCerPath);
			byte[] reads = new byte[pubKeyStream.available()];
			pubKeyStream.read(reads);

			br = new BufferedReader(new StringReader(new String(reads)));
			String line = null;
			StringBuilder keyBuffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("-")) {
					keyBuffer.append(line);
				}
			}
			byte[] pubbyte = base64decode(keyBuffer.toString().trim());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubbyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");
			RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);
			return rsaPubKey;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (pubKeyStream != null) {
					pubKeyStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据私钥路径读取私钥
	 * 
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	
	
	
	
	public static PrivateKey getPrivateKeyFromFile(String pfxPath) {
		FileInputStream pubKeyStream = null;
		BufferedReader br = null;
		try {
			pubKeyStream = new FileInputStream(pfxPath);
			byte[] reads = new byte[pubKeyStream.available()];
			pubKeyStream.read(reads);

			br = new BufferedReader(new StringReader(new String(reads)));
			String line = null;
			StringBuilder keyBuffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("-")) {
					keyBuffer.append(line);
				}
			}
			byte[] pubbyte = base64decode(keyBuffer.toString().trim());
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pubbyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) fac.generatePrivate(keySpec);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (pubKeyStream != null) {
					pubKeyStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("restriction")
	private static byte[] base64decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}

}
