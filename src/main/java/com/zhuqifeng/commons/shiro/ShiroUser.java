/**
 *
 */
package com.zhuqifeng.commons.shiro;

import java.io.Serializable;
import java.util.Set;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1373760761780840081L;

	private Long id;
	private Long organizationId;
	private final String loginName;
	private String name;
	private String userType;
	private Set<String> urlSet;
	private Set<String> roles;

	public ShiroUser(String loginName) {
		this.loginName = loginName;
	}

	public ShiroUser(Long id, String loginName, String name, Set<String> urlSet) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.urlSet = urlSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getUrlSet() {
		return urlSet;
	}

	public void setUrlSet(Set<String> urlSet) {
		this.urlSet = urlSet;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}