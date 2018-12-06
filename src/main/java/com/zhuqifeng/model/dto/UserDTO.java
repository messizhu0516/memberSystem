package com.zhuqifeng.model.dto;

import com.zhuqifeng.model.pojo.User;

public class UserDTO extends User {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	private String organizationName;
	private String rolesList;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRolesList() {
		return rolesList;
	}

	public void setRolesList(String rolesList) {
		this.rolesList = rolesList;
	}

}
