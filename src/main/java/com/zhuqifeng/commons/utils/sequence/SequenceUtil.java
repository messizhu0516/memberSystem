package com.zhuqifeng.commons.utils.sequence;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成编号类
 * 
 * @author 杜涛 2015年12月8日 下午3:54:14
 */
public class SequenceUtil {

	/**
	 * 所用文件
	 */
	private static Date date = new Date();
	private static StringBuilder buf = new StringBuilder();
	private static int seq = 0;
	private static final int ROTATION = 99999;

	private static String randomStub = "00";
	private static int lastInt = 0;
	private static long lastTimeMills = 0l;

	public static String getId() {
		initWithStationNo(1);
		return createFixedSeqId(15);
	}

	/**
	 * 
	 * @Title initWithStationNo
	 * @Description 分布环境下，传入ID生成服务器的台号，防止序列号重复
	 * @param @param
	 *            stationNo 最大支持99台服务器
	 * @return void
	 * @throws @author
	 *             macsunny
	 * @date 2016年2月3日 下午3:01:59
	 */
	public static void initWithStationNo(int stationNo) {
		randomStub = String.format("%02d", stationNo % 100);
		lastTimeMills = System.currentTimeMillis();
	}

	private synchronized static int nextInt(long currentTimeMills) {
		if (lastTimeMills != currentTimeMills) {
			lastInt = 0;
		} else
			lastInt = ++lastInt % 10000;
		return lastInt;
	}

	/**
	 * 
	 * @Title createFixedSeqId
	 * @Description 根据27位以上指定长度生成纯数字的随机数（时间递增相关)
	 * @param @param
	 *            length
	 * @param @return
	 * @return String
	 * @throws @author
	 *             macsunny
	 * @date 2016年2月19日 下午6:06:32
	 */
	public static String createFixedSeqId(int length) {
		if (length < 15)
			return "-1";
		StringBuilder sb = new StringBuilder();
		long currentTimeMills = System.currentTimeMillis();
		sb.append(currentTimeMills);
		sb.append(randomStub);
		int currentInt = nextInt(currentTimeMills);
		int restLen = length - sb.length() - 3;
		if (restLen > 0) {
			String format = "%0" + restLen + "d";
			int bound = (int) Math.pow(10, restLen);
			sb.append(String.format(format, currentInt % bound));
		}
		sb.append(String.format("%02d", new Random().nextInt(100)));
		return sb.toString();
	}

	/**
	 * length位随机字母+数字
	 */
	public static String createId(int length) {
		StringBuffer id = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				id.append((char) (random.nextInt(26) + temp));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				id.append(random.nextInt(10));
			}
		}
		return id.toString();
	}

	/**
	 * length位数字
	 */
	public static String createInviteCode(int length) {
		int offset = 4;
		if (length < offset) {
			offset = length;
		}
		if (offset < 0) {
			offset = 0;
		}
		StringBuffer inviteCode = new StringBuffer();
		Random random = new Random();
		String timeStr = String.valueOf(System.currentTimeMillis());
		int timeStrLen = timeStr.length();
		inviteCode.append(timeStr.substring(timeStrLen - offset, timeStrLen));
		for (int i = 0; i < length - offset; i++) {
			inviteCode.append(random.nextInt(10));
		}
		return inviteCode.toString();
	}

	/**
	 * 获取一个去掉-的UUID字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		String string = UUID.randomUUID().toString();
		return string.replaceAll("-", "");
	}

	final private static char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static String generateVariableCode(int charCnt) {
		if (charCnt <= 0)
			return "";
		char[] ret = new char[charCnt];
		for (int i = 0; i < charCnt; i++) {
			int letterPos = (int) (Math.random() * 10000) % (chars.length);
			ret[i] = chars[letterPos];
		}
		String code = String.valueOf(ret);
		return code;
	}

	public static String generateInviteCode() {
		int charCnt = 5;
		char[] ret = new char[charCnt];
		for (int i = 0; i < charCnt; i++) {
			int letterPos = (int) (Math.random() * 10000) % (chars.length);
			ret[i] = chars[letterPos];
		}
		String code = String.valueOf(ret);
		return code;
	}

	public static String generateExchangeCode() {
		int charCnt = 12;
		char[] ret = new char[charCnt];
		for (int i = 0; i < charCnt; i++) {
			int letterPos = (int) (Math.random() * 10000) % (10);
			ret[i] = chars[letterPos];
		}
		String code = String.valueOf(ret);
		return code;
	}

	public static String generatePassword() {
		int charCnt = 6;
		char[] ret = new char[charCnt];
		for (int i = 0; i < charCnt; i++) {
			int letterPos = (int) (Math.random() * 10000) % (10);
			ret[i] = chars[letterPos];
		}
		String code = String.valueOf(ret);
		return code;
	}

	/**
	 * 生成18位订单编号
	 * 
	 * @return 订单编号
	 */
	public static synchronized String generateOrderSn() {
		int random_one = (int) ((Math.random() * 9 + 1) * 100);
		int random_two = (int) ((Math.random() * 9 + 1) * 1000);
		String sn = next() + "" + random_one + "" + random_two;
		return sn;
	}

	/**
	 * 生成14位货源编号103************
	 * 
	 * @return
	 */
	public static synchronized String generateGoodsSourceSn() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return "103" + str.substring(12) + (int) ((Math.random() * 9 + 1) * 1000);
	}

	/**
	 * 生成14位运单号309************
	 * 
	 * @return
	 */
	public static synchronized String generateTansportNumberSn() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return "309" + str.substring(12) + (int) ((Math.random() * 9 + 1) * 1000);
	}

	/**
	 * 生成14位车源编号 102************
	 * 
	 * @return
	 */
	public static synchronized String generateCarSourceSn() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return "102" + str.substring(12) + (int) ((Math.random() * 9 + 1) * 1000);
	}

	/**
	 * 生成14位交易编号 502************
	 * 
	 * @return
	 */
	public static synchronized String generateTrackingNumber() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return "502" + str.substring(12) + (int) ((Math.random() * 9 + 1) * 1000);
	}

	/**
	 * 生成交易流水号
	 * 
	 * @return
	 */
	public static synchronized String generateSerialsNumber() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return str;
	}

	/**
	 * 生成6位回单密码
	 * 
	 * @return
	 */
	public static String generateReceiptPass() {
		int password = (int) ((Math.random() * 9 + 1) * 100000);
		return String.valueOf(password);
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	private static synchronized long next() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		str = str.substring(8);
		return Long.parseLong(str);
	}

}
