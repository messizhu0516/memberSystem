package com.zhuqifeng.commons.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhuqifeng.commons.enums.AdminParamEnum;
import com.zhuqifeng.model.pojo.Role;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.service.system.ResourceService;
import com.zhuqifeng.service.system.RoleService;
import com.zhuqifeng.service.system.UserService;

/**
 * ClassName: ShiroDbRealm <br/>
 * Function: shiro鉴权 <br/>
 * Reason: TODO <br/>
 * date: 2018年5月25日 上午11:52:10 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
		super(cacheManager, matcher);
	}

	/**
	 * Shiro登录认证(原理：用户提交 用户名和密码 --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro
	 * 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		User user = userService.selectByLoginName(username);
		// 账号不存在
		if (user == null || user.getStatus() == 1) {
			return null;
		}
		// 读取用户的权限（URI）和角色
		Long userId = user.getId();
		List<Role> userRoles = this.roleService.selectUserRoles(userId);
		if (userRoles == null || userRoles.size() == 0) {
			return null;
		}
		Set<String> roleSet = new HashSet<String>();
		for (Role role : userRoles) {
			roleSet.add(role.getCode());
		}
		Set<String> urls = new HashSet<String>();
		Set<String> roles = new HashSet<String>();
		// 当登录者拥有超级管理员角色时
		if (roleSet.contains(AdminParamEnum.ROLECODE.toString())) {
			List<String> allResources = this.resourceService.selectAllUseingResources();
			for (String url : allResources) {
				urls.add(url);
			}
			roles = roleSet;
		} else {
			Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
			urls = resourceMap.get("urls");
			roles = resourceMap.get("roles");
		}
		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), urls);
		shiroUser.setRoles(roles);
		shiroUser.setUserType(user.getUserType());
		shiroUser.setOrganizationId(user.getOrganizationId());
		// 认证缓存信息
		return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), ShiroByteSource.of(user.getSalt()), getName());
	}

	/**
	 * Shiro权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(shiroUser.getRoles());
		info.addStringPermissions(shiroUser.getUrlSet());
		return info;
	}

	@Override
	protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
		return shiroUser.toString();
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
		return shiroUser.toString();
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param shiroUser
	 */
	public void removeUserCache(ShiroUser shiroUser) {
		removeUserCache(shiroUser.getLoginName());
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param loginName
	 */
	public void removeUserCache(String loginName) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(loginName, super.getName());
		super.clearCachedAuthenticationInfo(principals);
	}
}
