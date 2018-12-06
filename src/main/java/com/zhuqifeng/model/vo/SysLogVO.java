package com.zhuqifeng.model.vo;

import java.util.Date;

import com.zhuqifeng.commons.result.EasyuiPage;

public class SysLogVO<T> extends EasyuiPage<T> {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	/** 登陆名 */
	private String loginName;
	private Date startDate;
	private Date endDate;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}