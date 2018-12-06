package com.zhuqifeng.commons.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩、解压工具类。文件压缩格式为zip
 *
 * @Author:chenssy
 * @date:2016年5月24日 下午9:16:01
 */
public class ZipUitls {
	/** 文件后缀名 */
	private static final String ZIP_FILE_SUFFIX = ".zip";
	private static byte[] ZIP_HEADER_1 = new byte[] { 80, 75, 3, 4 };
	private static byte[] ZIP_HEADER_2 = new byte[] { 80, 75, 5, 6 };

	/**
	 * 压缩文件
	 *
	 * @author:chenssy
	 * @date : 2016年5月24日 下午9:56:36
	 *
	 * @param resourcePath 源文件
	 * @param targetPath   目的文件,保存文件路径
	 */
	public static void zipFile(String resourcePath, String targetPath) {
		File resourcesFile = new File(resourcePath);
		File targetFile = new File(targetPath);

		// 目的文件不存在，则新建
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 文件名
		String targetName = resourcesFile.getName() + ZIP_FILE_SUFFIX;

		ZipOutputStream out = null;
		try {
			FileOutputStream outputStream = new FileOutputStream(targetPath + "//" + targetName);
			out = new ZipOutputStream(new BufferedOutputStream(outputStream));

			compressedFile(out, resourcesFile, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 *
	 * @author:chenssy
	 * @date : 2016年5月24日 下午10:00:22
	 *
	 * @param out
	 * @param resourcesFile
	 * @param dir
	 */
	private static void compressedFile(ZipOutputStream out, File file, String dir) {
		FileInputStream fis = null;
		try {
			if (file.isDirectory()) { // 文件夹
				// 得到文件列表信息
				File[] files = file.listFiles();
				// 将文件夹添加到下一级打包目录
				out.putNextEntry(new ZipEntry(dir + "/"));

				dir = dir.length() == 0 ? "" : dir + "/";

				// 循环将文件夹中的文件打包
				for (int i = 0; i < files.length; i++) {
					compressedFile(out, files[i], dir + files[i].getName()); // 递归处理
				}
			} else { // 如果是文件则打包处理
				fis = new FileInputStream(file);

				out.putNextEntry(new ZipEntry(dir));
				// 进行写操作
				int j = 0;
				byte[] buffer = new byte[1024];
				while ((j = fis.read(buffer)) > 0) {
					out.write(buffer, 0, j);
				}
				// 关闭输入流
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param inputFileName 你要压缩的文件夹(整个完整路径)
	 * @param zipFileName   压缩后的文件(整个完整路径)
	 */
	public static void zip(String inputFileName, String zipFileName) throws Exception {
		zip(zipFileName, new File(inputFileName));
	}

	private static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		out.flush();
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir) throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
	}

	public static File zipMultiFiles(File[] files, String outputFile) throws IOException {
		// 如果files长度为0，zout.close()方法会抛异常： ZIP file must have at least one entry
		if (files.length == 0) {
			return null;
		}
		FileOutputStream out = null;
		BufferedOutputStream buffOut = null;
		ZipOutputStream zout = null;
		try {
			out = new FileOutputStream(outputFile);
			buffOut = new BufferedOutputStream(out);
			zout = new ZipOutputStream(buffOut);
			for (int i = 0; i < files.length; i++) {
				InputStream in = null;
				BufferedInputStream buffIn = null;
				try {
					in = new FileInputStream(files[i]);
					buffIn = new BufferedInputStream(in, 2048);
					ZipEntry zipEntry = new ZipEntry(files[i].getName());
					zout.putNextEntry(zipEntry);
					int len = 0;
					byte data[] = new byte[2048];
					while ((len = buffIn.read(data)) != -1) {
						zout.write(data, 0, len);
					}
				} finally {
					try {
						zout.closeEntry();
					} catch (IOException e) {
					}
					buffIn.close();
					in.close();
				}
			}
			return new File(outputFile);
		} finally {
			zout.close();
			buffOut.close();
			out.close();
		}
	}

	/**
	 * 判断文件是否为一个压缩文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isArchiveFile(File file) {
		if (file == null) {
			return false;
		}
		if (file.isDirectory()) {
			return false;
		}
		boolean isArchive = false;
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			byte[] buffer = new byte[4];
			int length = input.read(buffer, 0, 4);
			if (length == 4) {
				isArchive = (Arrays.equals(ZIP_HEADER_1, buffer)) || (Arrays.equals(ZIP_HEADER_2, buffer));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return isArchive;
	}
}
