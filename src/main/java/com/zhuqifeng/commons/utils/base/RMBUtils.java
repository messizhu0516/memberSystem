package com.zhuqifeng.commons.utils.base;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金钱处理工具类
 * 
 * @Author:chenssy
 * @date:2014年8月7日
 */
public class RMBUtils {
	
	/**
	 * 汉语中数字大写
	 */
	 private static final String[] CN_UPPER_NUMBER = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖" };
	 
	 /**
	  * 汉语中货币单位大写
	  */
	 private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元","拾", "佰", "仟", "万", "拾", 
		 													  "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
		 													  "佰", "仟" };
	 /**
	  * 特殊字符：整
	  */
	 private static final String CN_FULL = "";
	 
	 /**
	  * 特殊字符：负
	  */
	 private static final String CN_NEGATIVE = "负";
	 /**
	  * 零元整
	  */
	 private static final String CN_ZEOR_FULL = "零元整";
	 
	 /**
	  * 金额的精度，默认值为2
	  */
	 private static final int MONEY_PRECISION = 2;
	 
	 /**
	  * 人民币转换为大写,格式为：x万x千x百x十x元x角x分
	  * 
	  * @autor:chenssy
	  * @date:2014年8月7日
	  *
	  * @param numberOfMoney 传入的金额
	  * @return
	  */
	 public static String number2CNMontray(String numberOfMoney) {
		 return number2CNMontray(new BigDecimal(numberOfMoney));
	 }
	 

	/**
	 * 人民币转换为大写,格式为：x万x千x百x十x元x角x分
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param numberOfMoney
	 * 					传入的金额
	 * @return
	 */
	public static String number2CNMontray(BigDecimal numberOfMoney) {
		StringBuffer sb = new StringBuffer();
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
	}
	
	/**
	 * 将人民币转换为会计格式金额(xxxx,xxxx,xxxx.xx),保留两位小数
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param money
	 * 				待转换的金额
	 * @return
	 */
	public static String accountantMoney(BigDecimal money){
		return accountantMoney(money, 2, 1);
	}
	
	/**
	 * 格式化金额，显示为xxx万元，xxx百万,xxx亿
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param money 
	 * 				待处理的金额
	 * @param scale  
	 * 				小数点后保留的位数
	 * @param divisor 
	 * 				格式化值（10:十元、100:百元,1000千元，10000万元......）
	 * @return
	 */
	public static String getFormatMoney(BigDecimal money,int scale,double divisor){
		return formatMoney(money, scale, divisor) + getCellFormat(divisor);
	}
	
	/**
	 * 获取会计格式的人民币(格式为:xxxx,xxxx,xxxx.xx)
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param money 
	 * 				待处理的金额
	 * @param scale 
	 * 				小数点后保留的位数
	 * @param divisor 
	 * 				格式化值（10:十元、100:百元,1000千元，10000万元......）
	 * @return
	 */
	public static String getAccountantMoney(BigDecimal money, int scale, double divisor){  
        return accountantMoney(money, scale, divisor) + getCellFormat(divisor);
    }  
	
	/**
	 * 将人民币转换为会计格式金额(xxxx,xxxx,xxxx.xx)
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param money 
	 * 				待处理的金额
	 * @param scale 
	 * 				小数点后保留的位数
	 * @param divisor 
	 * 				格式化值
	 * @return
	 */
	private static String accountantMoney(BigDecimal money,int scale,double divisor){
		String disposeMoneyStr = formatMoney(money, scale, divisor);  
        //小数点处理  
        int dotPosition = disposeMoneyStr.indexOf(".");  
        String exceptDotMoeny = null;//小数点之前的字符串  
        String dotMeony = null;//小数点之后的字符串  
        if(dotPosition > 0){  
            exceptDotMoeny = disposeMoneyStr.substring(0,dotPosition);  
            dotMeony = disposeMoneyStr.substring(dotPosition);  
        }else{  
            exceptDotMoeny = disposeMoneyStr;  
        }  
        //负数处理  
        int negativePosition = exceptDotMoeny.indexOf("-");  
        if(negativePosition == 0){  
            exceptDotMoeny = exceptDotMoeny.substring(1);  
        }  
        StringBuffer reverseExceptDotMoney = new StringBuffer(exceptDotMoeny);  
        reverseExceptDotMoney.reverse();//字符串倒转  
        char[] moneyChar = reverseExceptDotMoney.toString().toCharArray();  
        StringBuffer returnMeony = new StringBuffer();//返回值  
        for(int i = 0; i < moneyChar.length; i++){  
            if(i != 0 && i % 3 == 0){  
                returnMeony.append(",");//每隔3位加','  
            }  
            returnMeony.append(moneyChar[i]);  
        }  
        returnMeony.reverse();//字符串倒转  
        if(dotPosition > 0){  
            returnMeony.append(dotMeony);  
        }  
        if(negativePosition == 0){  
            return "-" + returnMeony.toString();  
        }else{  
            return returnMeony.toString();  
        }  
	}
	
	/**
	 * 格式化金额，显示为xxx万元，xxx百万,xxx亿
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param money 
	 * 				待处理的金额
	 * @param scale  
	 * 				小数点后保留的位数
	 * @param divisor 
	 * 				格式化值
	 * @return
	 */
	private static String formatMoney(BigDecimal money,int scale,double divisor){
		if (divisor == 0) {
			return "0.00";
		}
		if (scale < 0) {
			return "0.00";
		}
		BigDecimal divisorBD = new BigDecimal(divisor);
		return money.divide(divisorBD, scale, RoundingMode.HALF_UP).toString();
	}
	
	private static String getCellFormat(double divisor){
		String str = String.valueOf(divisor);
		int len = str.substring(0,str.indexOf(".")).length();
		String cell = "";
		switch(len){
			case 1:
				cell = "元";
				break;
			case 2:
				cell = "十元";
				break;
			case 3:
				cell = "百元";
				break;
			case 4:
				cell = "千元";
				break;
			case 5:
				cell = "万元";
				break;
			case 6:
				cell = "十万元";
				break;
			case 7:
				cell = "百万元";
				break;
			case 8:
				cell = "千万元";
				break;
			case 9:
				cell = "亿元";
				break;
			case 10:
				cell = "十亿元";
				break;
		}
		return cell;
	}
	
	public static BigDecimal getPriceByScale(String price, int scale) {
		if (StringUtils.isNotEmpty(price)) {
			BigDecimal bigDecimal = new BigDecimal(price);
			bigDecimal = bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
			return bigDecimal;
		}
		return new BigDecimal(0);
	}
	
	
	public float parserStatement(String statement) throws Exception {
		float result = 0;
		try {
			byte[] bytes = null;
			bytes = statement.getBytes("gb2312");
			for (int i = bytes.length - 1; i >= 0; i--) {
				String unit = queryUnit(bytes[i - 1], bytes[i]);
				// 无关键字结尾
				if (unit.equals("")) {
					if (bytes[i] > 0) {
						// 以阿拉伯数字结尾
						float num = numberYuan1(bytes, i + 1);
						result = num;
					} else {
						int num2 = queryNumber(bytes[i - 1], bytes[i]);
						if (num2 == -1) {
							i--;
							continue;
						}
						if (i < 3) {
							result = num2;
						} else {
							unit = queryUnit(bytes[i - 3], bytes[i - 2]);
							if (unit.equals("角")) {
								// 关键字"角"后跟汉字数字
								int num1 = queryNumber(bytes[i - 5], bytes[i - 4]);
								float num = numberYuan1(bytes, i - 6) + (float) (num1 * 0.1) + (float) (num2 * 0.01);
								result = num;
							} else if (unit.equals("元")) {
								// 关键字"元"后跟汉字数字
								float num = numberYuan1(bytes, i - 2) + (float) (num2 * 0.1);
								result = num;
							} else {
								// "零"后跟汉字数字
								float num = numberYuan1(bytes, i + 2);
								result = num;
							}
						}

					}
					break;
				} else if (unit.equals("分")) {
					// 关键字"分"结尾 关键字"分"前一般为一位汉字数字
					int num2 = queryNumber(bytes[i - 3], bytes[i - 2]);
					// 往前推 取"角""元"关键字前数字
					unit = queryUnit(bytes[i - 5], bytes[i - 4]);
					if (unit.equals("角")) {
						int num1 = queryNumber(bytes[i - 7], bytes[i - 6]);
						float num = numberYuan1(bytes, i - 8) + (float) (num1 * 0.1) + (float) (num2 * 0.01);
						result = num;
					} else {
						float num = numberYuan1(bytes, i - 6) + (float) (num2 * 0.01);
						result = num;
					}
					break;
				} else if (unit.equals("角")) {
					// 关键字"角"结尾 关键字"角"前为一位汉字数字
					int num1 = queryNumber(bytes[i - 3], bytes[i - 2]);
					// 取完"角"关键字前数字 往前推取"元"关键字前数字
					float num = numberYuan1(bytes, i - 4) + (float) (num1 * 0.1);
					result = num;
					break;
				} else if (unit.equals("元")) {
					// 关键字"元"结尾
					unit = queryUnit(bytes[i - 3], bytes[i - 2]);
					if (unit.equals("万")) {
						float num = numberYuan1(bytes, i - 2) * 10000;
						if (num < 0)
							num = numberYuan1(bytes, i - 2);
						result = num;
						break;
					} else {
						float num = numberYuan1(bytes, i);
						result = num;
						break;
					}
				}
				if (unit.equals("万")) {
					float num = numberYuan1(bytes, i) * 10000;
					if (num < 0)
						num = numberYuan1(bytes, i - 2);
					result = num;
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	// i值为汉字"元"的第二个字节的下标值
	// 关键字"元"前面的字符串有两种情况：1.一位汉字数字
	// 2.多位阿拉伯数字
	public float numberYuan(byte[] bytes, int i) {
		float num = 0;
		int index = 0;
		int flag = 0;
		if (bytes[i - 2] < 0) {
			// 为一位汉字数字情况： 从汉字数字转为阿拉伯数字
			num = queryNumber(bytes[i - 3], bytes[i - 2]);
			index = i - 3;
		} else {
			// 为多位阿拉伯数字情况： 从i-2开始向前推到出现汉字为止 中间截出阿拉伯数字字符串
			for (int j = i - 2; j >= 0; j--) {
				int number = queryLoopNumber(bytes[j]);
				if (number == -1) {
					byte[] byteNum = new byte[100];
					System.arraycopy(bytes, j + 1, byteNum, 0, i - 2 - j);
					String numStr = new String(byteNum);
					numStr = String.copyValueOf(numStr.toCharArray(), 0, bytes.length);
					num = Float.valueOf(numStr);
					index = j + 1;
					flag = 0;
					break;
				}
				if (j == 0) {
					byte[] byteNum = new byte[100];
					System.arraycopy(bytes, 0, byteNum, 0, i - 1);
					String numStr = new String(byteNum);
					numStr = String.copyValueOf(numStr.toCharArray(), 0, bytes.length);
					num = Float.valueOf(numStr);
					index = j;
					flag = 1;
					break;
				}
			}
		}
		// 截取数字前的字符串
		byte[] otherStrByte = new byte[index];
		System.arraycopy(bytes, 0, otherStrByte, 0, index);
		try {
			String otherString = new String(bytes, 0, index, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return num;
	}

	// i值为汉字"元"的第二个字节的下标值
	// 关键字"元"前面的字符串有两种情况：1.多位汉字数字
	// 2.多位阿拉伯数字
	public float numberYuan1(byte[] bytes, int i) {
		float num = 0;
		float num1 = 0;
		float num2 = 0;
		int index = 0;
		int flag = 0;
		int flag1 = 0;
		int flag2 = 0;
		int flag3 = 0;
		if (i - 2 < 0) {
			num = queryLoopNumber(bytes[i - 1]);
		} else if ((i > bytes.length) || (bytes[i - 2] < 0 && bytes[i - 1] < 0)) {
			// 为一位汉字数字情况：
			// 从汉字数字转为阿拉伯数字
			for (int j = i - 2; j >= 0; j = j - 2) {
				byte[] bytes1 = new byte[2];
				bytes1[0] = bytes[j - 1];
				bytes1[1] = bytes[j];
				String numStr = "";
				try {
					numStr = new String(bytes1, 0, bytes1.length, "gb2312");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (numStr.equals("零")) {
					flag1 = 1;
					flag2 = 1;
					flag3 = 1;
					continue;
				}
				if (numStr.equals("十") || numStr.equals("拾")) {
					if ((j - 3) < 0) {
						num = 10;
						if (num1 != 0)
							num = num + num1;
						index = 0;
						break;
					}
					bytes1[0] = bytes[j - 3];
					bytes1[1] = bytes[j - 2];
					flag1 = 1;
					numStr = "";
					try {
						numStr = new String(bytes1, 0, bytes1.length, "gb2312");
						if (!numStr.equals("一") && !numStr.equals("二") && !numStr.equals("三") && !numStr.equals("四") && !numStr.equals("五") && !numStr.equals("六") && !numStr.equals("七")
								&& !numStr.equals("八") && !numStr.equals("九")) {
							num = 10 + num1;
							index = j - 1;
							continue;
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (numStr.equals("百") || numStr.equals("佰")) {
						if (flag1 == 0)
							num1 = num1 * 10;
						num = 10 + num1;
						num1 = num;
						index = j - 3;
					} else {
						num = queryNumber(bytes[j - 3], bytes[j - 2]) * 10 + num1;
						num1 = num;
						index = j - 3;
						j = j - 2;
					}
				} else if (numStr.equals("百") || numStr.equals("佰")) {
					if (flag1 == 0)
						num1 = num1 * 10;
					num = queryNumber(bytes[j - 3], bytes[j - 2]) * 100 + num1;
					num1 = num;
					index = j - 3;
					j = j - 2;
					flag2 = 1;
				} else if (numStr.equals("千") || numStr.equals("仟")) {
					if (flag2 == 0)
						num1 = num1 * 100;
					num = queryNumber(bytes[j - 3], bytes[j - 2]) * 1000 + num1;
					num1 = num;
					index = j - 3;
					j = j - 2;
					flag3 = 1;
				} else if (numStr.equals("万")) {
					for (int k = j; k >= 0; k = k - 2) {
						bytes1[0] = bytes[k - 1];
						bytes1[1] = bytes[k];
						numStr = "";
						try {
							numStr = new String(bytes1, 0, bytes1.length, "gb2312");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						if (numStr.equals("零")) {
							flag1 = 1;
							flag2 = 1;
							flag3 = 1;
							continue;
						}
						if (numStr.equals("十") || numStr.equals("拾")) {
							bytes1[0] = bytes[k - 3];
							bytes1[1] = bytes[k - 2];
							numStr = "";
							try {
								numStr = new String(bytes1, 0, bytes1.length, "gb2312");
								if (!numStr.equals("一") && !numStr.equals("二") && !numStr.equals("三") && !numStr.equals("四") && !numStr.equals("五") && !numStr.equals("六") && !numStr.equals("七")
										&& !numStr.equals("八") && !numStr.equals("九")) {
									num = 10 + num1;
									index = j - 1;
									continue;
								}
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							if (numStr.equals("百") || numStr.equals("佰")) {
								num = 100000 + num1;
								num1 = num;
								index = k - 3;
								// j = j - 2;
							} else {
								num = queryNumber(bytes[k - 3], bytes[k - 2]) * 10 * 10000 + num1;
								num1 = num;
								index = k - 3;
								k = k - 2;
								j = k;
							}
						} else if (numStr.equals("百") || numStr.equals("佰")) {
							num = queryNumber(bytes[k - 3], bytes[k - 2]) * 100 * 10000 + num1;
							num1 = num;
							index = k - 3;
							k = k - 2;
							j = k;
						} else if (numStr.equals("千") || numStr.equals("仟")) {
							num = queryNumber(bytes[k - 3], bytes[k - 2]) * 1000 * 10000 + num1;
							num1 = num;
							index = k - 3;
							k = k - 2;
							j = k;
						} else {
							num2 = queryNumber(bytes[k - 3], bytes[k - 2]) * 10000;
							if (num2 < 0) {
								j = k;
								break;
							}
							if (flag3 == 0)
								num1 = num1 * 1000;
							num = num2 + num1;
							num1 = num2 + num1;
							index = k - 3;
							k = k - 2;
							j = k;
						}
					}
				} else {
					num1 = queryNumber(bytes[j - 1], bytes[j]);
					if (num1 < 0)
						break;
					num = num1;
					index = j - 1;
				}
			}
		} else {
			// 为多位阿拉伯数字情况： 从i-2开始向前推到出现汉字为止 中间截出阿拉伯数字字符串
			int k = 0;
			if (bytes[i - 1] > 0)
				i++;
			for (int j = i - 2; j >= 0; j--) {
				int number = queryLoopNumber(bytes[j]);
				k++;
				if (number == -1) {
					byte[] byteNum = new byte[100];
					System.arraycopy(bytes, j + 1, byteNum, 0, k - 1);
					String numStr = new String(byteNum);
					numStr = String.copyValueOf(numStr.toCharArray(), 0, bytes.length);
					num = Float.valueOf(numStr);
					index = j + 1;
					flag = 0;
					break;
				}
				// *********************
				if (j == 0) {
					byte[] byteNum = new byte[100];
					System.arraycopy(bytes, 0, byteNum, 0, k);
					String numStr = new String(byteNum);
					numStr = String.copyValueOf(numStr.toCharArray(), 0, bytes.length);
					num = Float.valueOf(numStr);
					index = j;
					flag = 1;
					break;
				}
			}
		}
		// 截取数字前的字符串
		byte[] otherStrByte = new byte[index];
		System.arraycopy(bytes, 0, otherStrByte, 0, index);
		try {
			String otherString = new String(bytes, 0, index, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return num;
	}

	private String queryUnit(byte first, byte second) {
		String unit = "";
		if (first == -73 && second == -42) {
			unit = "分";
		} else if (first == -67 && second == -57) {
			unit = "角";
		} else if (first == -61 && second == -85) {
			unit = "角";
		} else if (first == -44 && second == -86) {
			unit = "元";
		} else if (first == -65 && second == -23) {
			unit = "元";
		} else if (first == -44 && second == -78) {
			unit = "元";
		} else if (first == -51 && second == -14) {
			unit = "万";
		}
		return unit;

	}

	private int queryNumber(byte first, byte second) {
		int number = 0;
		if (second < 0) {
			// 第一个字节小于0 为汉字数字
			number = transNumber(first, second);
		} else {
			// 为阿拉伯数字
			number = second - 48;
		}
		return number;
	}

	private int queryLoopNumber(byte first) {
		int number = 0;
		if (first < 0) {
			return -1;
		} else {
			// 直接取数值
			number = first - 48;
			//
		}
		return number;
	}

	// 将汉字数字转为阿拉伯数字
	private int transNumber(byte first, byte second) {
		int number = 0;
		byte[] bytes = new byte[2];
		bytes[0] = first;
		bytes[1] = second;
		String numStr = "";
		try {
			numStr = new String(bytes, 0, bytes.length, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (numStr.equals("零")) {
			number = 0;
		} else if (numStr.equals("一") || numStr.equals("壹")) {
			number = 1;
		} else if (numStr.equals("二") || numStr.equals("贰") || numStr.equals("两")) {
			number = 2;
		} else if (numStr.equals("三") || numStr.equals("叁")) {
			number = 3;
		} else if (numStr.equals("四") || numStr.equals("肆")) {
			number = 4;
		} else if (numStr.equals("五") || numStr.equals("伍")) {
			number = 5;
		} else if (numStr.equals("六") || numStr.equals("陆")) {
			number = 6;
		} else if (numStr.equals("七") || numStr.equals("柒")) {
			number = 7;
		} else if (numStr.equals("八") || numStr.equals("捌")) {
			number = 8;
		} else if (numStr.equals("九") || numStr.equals("玖")) {
			number = 9;
		} else if (numStr.equals("十") || numStr.equals("拾")) {
			number = 10;
		} else if (numStr.equals("百") || numStr.equals("佰")) {
			number = 100;
		} else if (numStr.equals("千") || numStr.equals("仟")) {
			number = 1000;
		} else {
			number = -1;
		}
		return number;
	}
}
