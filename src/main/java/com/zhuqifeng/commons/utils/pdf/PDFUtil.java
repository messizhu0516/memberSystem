/**  
 * Project Name:oceanus  
 * File Name:PDFUtil.java  
 * Package Name:com.zhuqifeng.commons.utils.pdf  
 * Date:2018年5月24日上午11:13:28  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.utils.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.zhuqifeng.commons.utils.date.DateUtil;

/**
 * ClassName:PDFUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年5月24日 上午11:13:28 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public class PDFUtil {
	public static String addQRCodeToPDFFile(HttpServletRequest request, String srcPDFPath, String qrCodeImagePath, String subProjectNum) {
		ServletContext servletContext = request.getSession().getServletContext();
		String rootPath = servletContext.getRealPath("/");
		// 生成的文件路径
		String d = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
		String targetPath = rootPath + "/htPDFImg/" + d + "/" + subProjectNum + ".pdf";
		File tempfile = new File(targetPath);
		if (!tempfile.exists()) {
			tempfile.mkdirs();
		}
		try {
			PdfReader reader = new PdfReader(srcPDFPath, "PDF".getBytes());
			if (tempfile.exists()) {
				tempfile.delete();
			}
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(targetPath));
			Image img = Image.getInstance(qrCodeImagePath);// 插入水印
			img.scaleToFit(1000, 40);// 大小
			// 左边距、底边距
			img.setAbsolutePosition(520, 768);
			PdfContentByte under = stamp.getUnderContent(1);
			under.addImage(img);
			stamp.close();// 关闭
			return targetPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
