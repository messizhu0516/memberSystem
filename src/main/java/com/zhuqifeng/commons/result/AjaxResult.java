package com.zhuqifeng.commons.result;

import java.io.Serializable;

import com.zhuqifeng.commons.utils.base.StringUtils;

/**
 * @author ZhuQiFeng
 * @addDate 2017年6月26日下午2:21:56
 * @description AJAX结果集
 */
public class AjaxResult implements Serializable {

	private static final long serialVersionUID = 5576237395711742681L;

	/**
	 * 結果状态，默认true成功
	 */
	private boolean success = Boolean.TRUE;

	/**
	 * 结果消息描述
	 */
	private String msg = "成功";
	/**
	 * 结果对象
	 */
	private Object obj = null;

	public AjaxResult() {
		super();
	}

	public AjaxResult(String msg) {
		super();
		this.msg = StringUtils.isNotEmpty(msg) ? msg : this.msg;
	}

	public AjaxResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = StringUtils.isNotEmpty(msg) ? msg : this.msg;
	}

	public AjaxResult(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = StringUtils.isNotEmpty(msg) ? msg : this.msg;
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
