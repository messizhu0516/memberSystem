package com.zhuqifeng.commons.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

public class APPUtil {

	private static final float RADIX_MULTS[] = { 0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F };
	private static final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in", "mm", "", "" };
	private static final String FRACTION_UNITS[] = { "%", "%p", "", "", "", "", "", "" };

	/**
	 * 解压IPA文件，只获取IPA文件的Info.plist文件存储指定位置
	 * 
	 * @param file
	 *            zip文件
	 * @param unzipDirectory
	 *            解压到的目录
	 * @throws Exception
	 */
	private static File getZipInfo(File file, String unzipDirectory) throws Exception {
		// 定义输入输出流对象
		InputStream input = null;
		OutputStream output = null;
		File result = null;
		File unzipFile = null;
		ZipFile zipFile = null;
		try {
			// 创建zip文件对象
			zipFile = new ZipFile(file);
			// 创建本zip文件解压目录
			String name = file.getName().substring(0, file.getName().lastIndexOf("."));
			unzipFile = new File(unzipDirectory + "/" + name);
			if (unzipFile.exists()) {
				unzipFile.delete();
			}
			unzipFile.mkdir();
			// 得到zip文件条目枚举对象
			Enumeration<? extends ZipEntry> zipEnum = zipFile.entries();
			// 定义对象
			ZipEntry entry = null;
			String entryName = null;
			String names[] = null;
			int length;
			// 循环读取条目
			while (zipEnum.hasMoreElements()) {
				// 得到当前条目
				entry = zipEnum.nextElement();
				entryName = new String(entry.getName());
				// 用/分隔条目名称
				names = entryName.split("\\/");
				length = names.length;
				for (int v = 0; v < length; v++) {
					if (entryName.endsWith(".app/Info.plist")) { // 为Info.plist文件,则输出到文件
						input = zipFile.getInputStream(entry);
						result = new File(unzipFile.getAbsolutePath() + "/Info.plist");
						output = new FileOutputStream(result);
						byte[] buffer = new byte[1024 * 8];
						int readLen = 0;
						while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1) {
							output.write(buffer, 0, readLen);
						}
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.flush();
				output.close();
			}
			// 必须关流，否则文件无法删除
			if (zipFile != null) {
				zipFile.close();
			}
		}
		// 如果有必要删除多余的文件
		if (file.exists()) {
			file.delete();
		}
		return result;
	}

	/**
	 * 通过IPA文件获取Info信息
	 */
	public static Map<String, String> readIPA(String filePath) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		File ipa = new File(filePath);
		try {
			int byteread = 0;
			String filename = ipa.getAbsolutePath().replaceAll(".ipa", ".zip");
			File newfile = new File(filename);
			if (ipa.exists()) {
				// 创建一个Zip文件
				InputStream inStream = new FileInputStream(ipa);
				FileOutputStream fs = new FileOutputStream(newfile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				if (inStream != null) {
					inStream.close();
				}
				if (fs != null) {
					fs.close();
				}
				// 解析Zip文件
				File zipInfo = getZipInfo(newfile, newfile.getParent());
				// 需要第三方jar包dd-plist
				NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(zipInfo);
				// 应用包名
				NSString parameters = (NSString) rootDict.objectForKey("CFBundleIdentifier");
				map.put("CFBundleIdentifier", parameters.toString());
				// 应用名称
				parameters = (NSString) rootDict.objectForKey("CFBundleName");
				map.put("CFBundleName", parameters.toString());
				// 应用版本
				parameters = (NSString) rootDict.objectForKey("CFBundleVersion");
				map.put("CFBundleVersion", parameters.toString());
				// 应用展示的名称
				parameters = (NSString) rootDict.objectForKey("CFBundleDisplayName");
				map.put("CFBundleDisplayName", parameters == null ? "" : parameters.toString());
				// 应用所需IOS最低版本
				parameters = (NSString) rootDict.objectForKey("MinimumOSVersion");
				map.put("MinimumOSVersion", parameters.toString());
				// 如果有必要，应该删除解压的结果文件
				zipInfo.delete();
				zipInfo.getParentFile().delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 读取apk
	 * 
	 * @param apkUrl
	 * @return
	 */
	public static Map<String, Object> readAPK(String apkUrl) {
		ZipFile zipFile;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			zipFile = new ZipFile(apkUrl);
			Enumeration<?> enumeration = zipFile.entries();
			ZipEntry zipEntry = null;
			while (enumeration.hasMoreElements()) {
				zipEntry = (ZipEntry) enumeration.nextElement();
				if (zipEntry.isDirectory()) {

				} else {
					if ("androidmanifest.xml".equals(zipEntry.getName().toLowerCase())) {
						AXmlResourceParser parser = new AXmlResourceParser();
						parser.open(zipFile.getInputStream(zipEntry));
						while (true) {
							int type = parser.next();
							if (type == XmlPullParser.END_DOCUMENT) {
								break;
							}
							String name = parser.getName();
							if (null != name && name.toLowerCase().equals("manifest")) {
								for (int i = 0; i != parser.getAttributeCount(); i++) {
									String attributeName = parser.getAttributeName(i);
									String value = getAttributeValue(parser, i);
									map.put(attributeName, value);
								}
								break;
							}
						}
					}

				}
			}
			zipFile.close();
		} catch (Exception e) {
			map.put("code", "fail");
			map.put("error", "读取apk失败");
		}
		return map;
	}

	private static String getAttributeValue(AXmlResourceParser parser, int index) {
		int type = parser.getAttributeValueType(index);
		int data = parser.getAttributeValueData(index);
		if (type == TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type == TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type == TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X", data);
		}
		if (type == TypedValue.TYPE_INT_BOOLEAN) {
			return data != 0 ? "true" : "false";
		}
		if (type == TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type == TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X", data);
		}
		if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>", data, type);
	}

	private static String getPackage(int id) {
		if (id >>> 24 == 1) {
			return "android:";
		}
		return "";
	}

	private static float complexToFloat(int complex) {
		return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> readIPA = readIPA("C:\\Users\\Administrator\\Desktop\\1.0.ipa");
		System.out.println("IOS信息：" + readIPA);
		Map<String, Object> readAPK = readAPK("C:\\\\Users\\\\Administrator\\\\Desktop\\sougoushurufa_700.apk");
		System.out.println("Android信息：" + readAPK);

		Map<String, Object> appInfo = APPUtil.readAPK("C:\\Users\\Administrator\\Desktop\\1\\sougoushurufa_700.apk");
		// extractFileFromApk(apkpath, apkInfo.getApplicationIcon(),
		// "C:\\Users\\Administrator\\Desktop\\1\\a.png");

	}

	/**
	 * 从指定的apk文件里获取指定file的流
	 * 
	 * @param apkpath
	 * @param fileName
	 * @return
	 */
	public static InputStream extractFileFromApk(String apkpath, String fileName) {
		try {
			ZipFile zFile = new ZipFile(apkpath);
			if (zFile != null) {
				ZipEntry entry = zFile.getEntry(fileName);
				entry.getComment();
				entry.getCompressedSize();
				entry.getCrc();
				entry.isDirectory();
				entry.getSize();
				entry.getMethod();
				InputStream stream = zFile.getInputStream(entry);
				return stream;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void extractFileFromApk(String apkpath, String fileName, String outputPath) throws Exception {
		InputStream is = extractFileFromApk(apkpath, fileName);

		File file = new File(outputPath);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 1024);
		byte[] b = new byte[1024];
		BufferedInputStream bis = new BufferedInputStream(is, 1024);
		while (bis.read(b) != -1) {
			bos.write(b);
		}
		bos.flush();
		is.close();
		bis.close();
		bos.close();
	}
}
