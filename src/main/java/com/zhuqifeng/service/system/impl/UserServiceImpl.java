package com.zhuqifeng.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.enums.AdminParamEnum;
import com.zhuqifeng.commons.enums.SystemParamEnum;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.utils.beanclone.BeanUtils;
import com.zhuqifeng.mapper.system.UserMapper;
import com.zhuqifeng.mapper.system.UserRoleMapper;
import com.zhuqifeng.model.dto.UserDTO;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.pojo.UserRole;
import com.zhuqifeng.model.vo.UserVO;
import com.zhuqifeng.service.system.UserService;

@Service
@Transactional
public class UserServiceImpl<T> extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User selectByLoginName(String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		/*
		 * Subject subject = SecurityUtils.getSubject(); ShiroUser loginer = (ShiroUser)
		 * subject.getPrincipal(); if (loginer != null) { Long id = loginer.getId();
		 * wrapper.where("id != {0}", id); }
		 */
		return this.selectOne(wrapper);
	}

	@Override
	public void insertByVo(UserVO userVo) {
		User user = BeanUtils.copy(userVo, User.class);
		user.setCreateTime(new Date());
		this.insert(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public UserVO selectVoById(Long id) {
		return userMapper.selectUserVoById(id);
	}

	@Override
	public void updateByVo(UserVO userVo) {
		User user = BeanUtils.copy(userVo, User.class);
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		this.updateById(user);

		Long id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.selectByUserId(id);
		if (userRoles != null && !userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updatePwdByUserId(Long userId, String md5Hex) {
		User user = new User();
		user.setId(userId);
		user.setPassword(md5Hex);
		this.updateById(user);
	}

	@Override
	public void selectDataGrid(UserVO<UserDTO> userVO, ShiroUser user) {
		Integer nowpage = userVO.getNowpage();
		Integer pagesize = userVO.getPagesize();
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(nowpage, pagesize);
		String userType = user.getUserType();
		// 企业账号登录时
		if (SystemParamEnum.AC_COMPANY.toString().equals(userType)) {
			userVO.setOrganizationId(user.getOrganizationId());
			userVO.setUserType(user.getUserType());
		}
		// 系统账号登录时
		else {
			// 非管理员登录时，用户信息需要屏蔽管理员
			String loginName = user.getLoginName();
			String admin = AdminParamEnum.LOGINNAME.toString();
			if (!admin.equals(loginName)) {
				userVO.setLoginName(admin);
			}
		}
		List<UserDTO> list = userMapper.selectUserPage(page, userVO);
		userVO.setRows(list);
		userVO.setTotal(page.getTotal());
	}

	@Override
	public void deleteUserById(Long id) {
		this.deleteById(id);
		userRoleMapper.deleteByUserId(id);
	}

	@Override
	public User getUserForEdit(Long id, String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		wrapper.where("id != {0}", id);
		return this.selectOne(wrapper);
	}

	@Override
	public User selectUserById(Long id) {
		return super.selectById(id);
	}

	@Override
	public User selectUserOne(Wrapper<User> wrapper) {
		return super.selectOne(wrapper);
	}

}