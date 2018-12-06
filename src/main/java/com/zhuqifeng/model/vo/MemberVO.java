package com.zhuqifeng.model.vo;

import java.io.Serializable;

import com.zhuqifeng.model.pojo.MemberInfo;
import com.zhuqifeng.model.pojo.User;

public class MemberVO implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private MemberInfo memberInfo;
	private User user;

	public MemberVO() {
	}

	public MemberVO(MemberInfo memberInfo, User user) {
		this.memberInfo = memberInfo;
		this.user = user;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public User getUser() {
		return user;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public void setUser(User user) {
		this.user = user;
	}

}