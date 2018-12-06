package com.zhuqifeng.controller.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuqifeng.commons.base.StringEscapeEditor;
import com.zhuqifeng.commons.enums.ResultEnum;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.commons.utils.base.StringUtils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * ClassName: BaseController <br/>
 * Function:
 * springmvc，controller基类，一些公共方法和常用操作可以放在此类中，其他controller可以继承此类以便调用父类方法 <br/>
 * Reason: TODO <br/>
 * date: 2018年5月25日 上午11:38:19 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseController {
	// 控制器本来就是单例，这样似乎更加合理
	protected Logger logger = LogManager.getLogger(getClass());

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor());
	}

	/**
	 * 获取当前登录用户对象
	 * 
	 * @return {ShiroUser}
	 */
	public ShiroUser getShiroUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取当前登录用户id
	 * 
	 * @return {Long}
	 */
	public Long getLoginUserId() {
		return this.getShiroUser().getId();
	}

	/**
	 * 获取当前登录用户名
	 * 
	 * @return {String}
	 */
	public String getLoginName() {
		return this.getShiroUser().getName();
	}

	protected String redirect(String url) {
		return new StringBuilder("redirect:").append(url).toString();
	}

	public String ajaxSucc() {
		return ajaxSucc(null);
	}

	public String ajaxSucc(Object obj) {
		return this.generateResultJSON(ResultEnum.CODE_SUCC.toString(), ResultEnum.DESC_SUCC.toString(), obj);
	}

	public String ajaxFail() {
		return ajaxFail(null, null);
	}

	public String ajaxFail(String msg) {
		return ajaxFail(msg, null);
	}

	public String ajaxFail(String msg, Object obj) {
		msg = StringUtils.isEmpty(msg) ? ResultEnum.DESC_FAIL.toString() : msg;
		return this.generateResultJSON(ResultEnum.CODE_FAIL.toString(), msg, obj);
	}

	/**
	 * bean validation异常
	 * 
	 * 此处只是粗略的构造了错误信息，只处理了第一条错误。
	 * 
	 * 如果要做的完美，需要将所有的错误信息展示于页面。
	 * 
	 * @param result
	 * @return
	 */
	public String ajaxFail(BindingResult result) {
		FieldError error = result.getFieldError();
		StringBuilder errorMsg = new StringBuilder();
		errorMsg.append("$(form).find(\"[name=\\\"");
		errorMsg.append(error.getField());
		errorMsg.append("\\\"]\").closest(\"td\").prev().text() + \"，");
		errorMsg.append(error.getDefaultMessage());
		errorMsg.append("\"");
		return this.generateResultJSON(ResultEnum.CODE_FAIL.toString(), errorMsg.toString(), null);
	}

	private String generateResultJSON(String code, String msg, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put(ResultEnum.KEY_CODE.toString(), code);
		map.put(ResultEnum.KEY_MSG.toString(), msg);
		map.put(ResultEnum.KEY_OBJ.toString(), obj == null ? StringUtils.EMPTY : obj);
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> void exportExcel(ExportParams excelParams, Class<T> entity, List<T> dataList, HttpServletResponse response) {
		Workbook workbook = ExcelExportUtil.exportExcel(excelParams, entity, dataList);
		String fileName = String.valueOf(System.currentTimeMillis()) + ".xls";
		response.setContentType("application/x-download;charset=gb2312");
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error("Excel通用导出处理失败", e);
		}
	}
	
}
