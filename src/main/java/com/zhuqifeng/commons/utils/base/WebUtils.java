package com.zhuqifeng.commons.utils.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zhuqifeng.commons.utils.date.DateUtil;
import com.zhuqifeng.commons.utils.file.FileUtils;

/**
 * Miscellaneous utilities for web applications.
 * 
 * @author L.cm
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

	/**
	 * 判断是否ajax请求 spring ajax 返回含有 ResponseBody 或者 RestController注解
	 * 
	 * @param handlerMethod
	 *            HandlerMethod
	 * @return 是否ajax请求
	 */
	public static boolean isAjax(HandlerMethod handlerMethod) {
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (null != responseBody) {
			return true;
		}
		RestController restAnnotation = handlerMethod.getBeanType().getAnnotation(RestController.class);
		if (null != restAnnotation) {
			return true;
		}
		return false;
	}

	/**
	 * 读取cookie
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 清除 某个指定的cookie
	 * 
	 * @param response
	 * @param key
	 */
	public static void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	public static final String TMP_IMAGE_PATH = "/imgUpload/tmp/";
	public static final String REAL_IMAGE_PATH = "/imgUpload/real";
	public static final String TMP_PATH_SEPARATOR = "/tmp";

	/**
	 * @author ZhuQiFeng
	 * @addDate 2016年3月3日上午10:28:41
	 * @description 上传图片到临时目录
	 * @param TODO
	 * @return TODO
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static String uploadImageToTmp(HttpServletRequest request) throws Exception {
		ServletContext servletContext = request.getSession().getServletContext();
		// 解析器解析request的上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
		String tmpPath = "";
		// 先判断request中是否包涵multipart类型的数据，
		if (multipartResolver.isMultipart(request)) {
			// 再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					// 重命名图片名字
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmsss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);
					fileName = newFileName + StringUtils.generateWord() + extension;
					String formatDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
					String rootPath = servletContext.getRealPath("/");
					tmpPath = TMP_IMAGE_PATH + formatDate + "/" + fileName;
					String savePath = rootPath + tmpPath;
					File tempFile = new File(savePath);
					if (!tempFile.exists()) {
						tempFile.mkdirs();
					}
					file.transferTo(tempFile);
				}
			}
		}
		return tmpPath;
	}

	/**
	 * @author ZhuQiFeng
	 * @addDate 2016年3月3日上午9:44:24
	 * @description 图片从临时目录拷贝到真实目录
	 * @param TODO
	 * @return TODO
	 */
	public static String copyTmpImageToReal(HttpServletRequest request, String imgPath) {
		String finalPath = "";
		if (StringUtils.isNotEmpty(imgPath)) {
			StringTokenizer tokenizer = new StringTokenizer(imgPath, ",");
			while (tokenizer.hasMoreElements()) {
				String currPath = tokenizer.nextToken();
				String dbPath = "";
				if (currPath.contains(TMP_PATH_SEPARATOR)) {
					// 临时文件路径
					String projectPath = request.getSession().getServletContext().getRealPath("/");
					File inputFile = new File(projectPath + currPath);
					String inPath = inputFile.getAbsolutePath();
					String realPath = currPath.split(TMP_PATH_SEPARATOR)[1];
					// 路径分隔符转换
					projectPath = projectPath.replaceAll("\\\\", "/");
					String dataRoot = projectPath.split("/")[0];
					dbPath = REAL_IMAGE_PATH + realPath;
					// 要拷贝的文件路径
					File outputFile = new File(dataRoot + dbPath);
					String ab_outPath = outputFile.getAbsolutePath();
					String substring = "";
					if (ab_outPath.lastIndexOf("/") > 0) {
						substring = ab_outPath.substring(0, ab_outPath.lastIndexOf("/"));
					} else if (ab_outPath.lastIndexOf("\\") > 0) {
						substring = ab_outPath.substring(0, ab_outPath.lastIndexOf("\\"));
					}
					File createFile = new File(substring);
					if (!createFile.exists()) {
						createFile.mkdirs();
					}
					try {
						FileUtils.copyFile(inPath, ab_outPath);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						finalPath = "";
					}
				} else {
					dbPath = currPath;
				}
				if (StringUtils.isEmpty(finalPath)) {
					finalPath = dbPath;
				} else {
					finalPath += "," + dbPath;
				}
			}
		}
		return finalPath;
	}

}
