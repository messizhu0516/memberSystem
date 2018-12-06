package com.zhuqifeng.commons.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
public class FileUtils {

	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';

	/**
	 * @desc:判断指定路径是否存在，如果不存在，根据参数决定是否新建
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param filePath 指定的文件路径
	 * @param isNew    true：新建、false：不新建
	 * @return 存在返回TRUE，不存在返回FALSE
	 */
	public static boolean isExist(String filePath, boolean isNew) {
		File file = new File(filePath);
		if (!file.exists() && isNew) {
			return file.mkdirs(); // 新建文件路径
		}
		return false;
	}

	/**
	 * 获取指定文件的大小
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 *
	 * @author:chenssy
	 * @date : 2016年4月30日 下午9:10:12
	 */
	@SuppressWarnings("resource")
	public static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
		}
		return size;
	}

	/**
	 * 删除所有文件，包括文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:41:08
	 *
	 * @param dirpath
	 */
	public void deleteAll(String dirpath) {
		File path = new File(dirpath);
		try {
			if (!path.exists())
				return;// 目录不存在退出
			if (path.isFile()) // 如果是文件删除
			{
				path.delete();
				return;
			}
			File[] files = path.listFiles();// 如果目录中有文件递归删除文件
			for (int i = 0; i < files.length; i++) {
				deleteAll(files[i].getAbsolutePath());
			}
			path.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件或者文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:41:59
	 *
	 * @param inputFile   源文件
	 * @param outputFile  目的文件
	 * @param isOverWrite 是否覆盖文件
	 * @throws java.io.IOException
	 */
	public static void copy(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (!inputFile.exists()) {
			throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
		}
		copyPri(inputFile, outputFile, isOverWrite);
	}

	/**
	 * 复制文件或者文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:43:24
	 *
	 * @param inputFile   源文件
	 * @param outputFile  目的文件
	 * @param isOverWrite 是否覆盖文件
	 * @throws java.io.IOException
	 */
	private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (inputFile.isFile()) { // 文件
			copySimpleFile(inputFile, outputFile, isOverWrite);
		} else {
			if (!outputFile.exists()) { // 文件夹
				outputFile.mkdirs();
			}
			// 循环子文件夹
			for (File child : inputFile.listFiles()) {
				copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:44:07
	 *
	 * @param inputFile   源文件
	 * @param outputFile  目的文件
	 * @param isOverWrite 是否覆盖
	 * @throws java.io.IOException
	 */
	private static void copySimpleFile(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (outputFile.exists()) {
			if (isOverWrite) { // 可以覆盖
				if (!outputFile.delete()) {
					throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
				}
			} else {
				// 不允许覆盖
				return;
			}
		}
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}

	/**
	 * 获取文件的MD5
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:50:38
	 *
	 * @param file 文件
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	 * 获取文件的后缀
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:51:59
	 *
	 * @param file 文件
	 * @return
	 */
	public static String getFileSuffix(String file) {
		if (file == null) {
			return null;
		}
		int extIndex = file.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = file.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return file.substring(extIndex + 1);
	}

	/**
	 * 文件重命名
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:56:05
	 *
	 * @param oldPath 老文件
	 * @param newPath 新文件
	 */
	public boolean renameDir(String oldPath, String newPath) {
		File oldFile = new File(oldPath);// 文件或目录
		File newFile = new File(newPath);// 文件或目录

		return oldFile.renameTo(newFile);// 重命名
	}

	public static String getFileSize(long size) {
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			// 因为如果以MB为单位的话，要保留最后1位小数，
			// 因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
		}
	}

	/**
	 * @param response
	 * @param filePath //文件完整路径(包括文件名和扩展名)
	 * @param fileName //下载后看到的文件名
	 * @return 文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception {
		byte[] data = FileUtils.toByteArray2(filePath);
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		response.flushBuffer();
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName 目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName String 文件路径及名称 如c:/fqf.txt（删除文件）或路径（目录下所有文件及子目录下所有文件）
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.isDirectory()) {
				deleteDir(myDelFile);
			} else {
				myDelFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除文件夹及旗下所有子文件
	 * 
	 * @param dir
	 * @return
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 读取到字节数组0
	 * 
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {
		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String toByteArray4(String filePath) throws IOException {
		Scanner in = new Scanner(new File(filePath), "UTF-8");
		StringBuffer sb = new StringBuffer();
		// in.hasNextLine()//用于判断流中是否还有
		// 可读取数据，天生是给while语句设计的
		while (in.hasNextLine()) {
			sb.append(in.nextLine());
		}
		in.close();
		return sb.toString();
	}

	/**
	 * 获取指定目录下的所有文件名
	 * 
	 * @param path
	 * @param imgs
	 */
	public static List<String> getFile(String path, List<String> imgs) {
		File file = new File(path);
		File[] array = file.listFiles();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].isFile()) {
					imgs.add(path + "/" + array[i].getName());
				} else if (array[i].isDirectory()) {
					getFile(array[i].getPath(), imgs);
				}
			}
		}
		return imgs;
	}

	public static boolean createFile(String filename) throws IOException {
		File file = new File(filename);
		boolean b = false;
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			b = file.createNewFile();
		}
		return b;
	}

	public static boolean deleteFile(String filename) {
		return new File(filename).delete();
	}

	public static void deleteDirectory(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File[] delFile = f.listFiles();
				for (int index = 0; index < f.listFiles().length; index++) {
					if (delFile[index].isDirectory()) {
						deleteDirectory(delFile[index].getAbsolutePath());
					}
					delFile[index].delete();
				}
			}
			deleteDirectory(filepath);
		}
	}

	public static boolean writeFile(String file, String txt, boolean appent) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			createFile(file);
		}
		FileWriter writer = new FileWriter(f, appent);
		writer.write(txt);
		writer.close();
		return true;
	}

	public static boolean writeFile(String file, String txt, String encoding) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			createFile(file);
		}
		FileOutputStream fos = new FileOutputStream(file, true);
		OutputStreamWriter os = new OutputStreamWriter(fos, encoding == null ? "UTF-8" : encoding);
		os.write(txt);
		os.close();
		fos.close();
		return true;
	}

	@Deprecated
	public static void writeObjectToFile(Object obj, String filename) throws IOException {
		FileUtils.deleteFile(filename);
		final FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		fos.close();
		oos.close();
	}

	@Deprecated
	public static Object readObjectFromFile(String filename) throws IOException, ClassNotFoundException {
		final FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		fis.close();
		ois.close();
		return obj;
	}

	/**
	 * 获取文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int len = path.lastIndexOf(File.separator);
		return path.substring(len + 1);
	}

	/**
	 * 获取文件名 前缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		int len = fileName.lastIndexOf(".");
		return fileName.substring(0, len);
	}

	/**
	 * 文件复制 方法摘要：这里一句话描述方法的用途
	 * 
	 * @param
	 * @return void
	 */
	public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		byte[] buf = new byte[10240];
		try {
			while ((temp = fis.read(buf)) != -1) {
				fos.write(buf, 0, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void merge(File ordi, File newFile) {
		/**
		 * 进行文件合并
		 */
		FileOutputStream outputStream = null;
		FileInputStream temp = null;
		try {
			outputStream = new FileOutputStream(ordi, true);
			byte[] byt = new byte[10 * 1024 * 1024];
			int len;
			temp = new FileInputStream(newFile);
			// 文件追加写入
			while ((len = temp.read(byt)) != -1) {
				outputStream.write(byt, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (temp != null) {
				try {
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static List<File> listAllFiles(String path) {
		List<File> result = new ArrayList<File>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (null == files || files.length == 0) {
				return null;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						listAllFiles(file2.getAbsolutePath());
					} else {
						result.add(file2);
					}
				}
			}
		} else {
			return null;
		}
		return result;
	}

}
