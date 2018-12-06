package com.zhuqifeng.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.Constants;
import com.zhuqifeng.commons.enums.AdminParamEnum;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.commons.result.Tree;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.mapper.system.RoleMapper;
import com.zhuqifeng.mapper.system.RoleResourceMapper;
import com.zhuqifeng.mapper.system.UserRoleMapper;
import com.zhuqifeng.model.pojo.Role;
import com.zhuqifeng.model.pojo.RoleResource;
import com.zhuqifeng.service.system.RoleService;

/**
 * ClassName: RoleServiceImpl <br/>
 * Function: 角色服务实现类 <br/>
 * Reason: TODO <br/>
 * date: 2018年5月31日 下午4:53:23 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	public List<Role> selectAll(ShiroUser user) {
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		// 非管理员登录时，角色信息需要屏蔽管理员角色
		String loginName = user.getLoginName();
		if (!AdminParamEnum.LOGINNAME.toString().equals(loginName)) {
			wrapper.where(" code != {0}", AdminParamEnum.ROLECODE.toString());
		}
		wrapper.orderBy("sort");
		return roleMapper.selectList(wrapper);
	}

	@Override
	public void selectDataGrid(EasyuiPage<Role> easyuiPage, ShiroUser user) {
		Page<Role> page = new Page<Role>(easyuiPage.getNowpage(), easyuiPage.getPagesize());
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.orderBy(easyuiPage.getSortName(), easyuiPage.getOrder().equalsIgnoreCase("ASC"));
		// 非管理员登录时，角色信息需要屏蔽管理员角色
		String loginName = user.getLoginName();
		if (!AdminParamEnum.LOGINNAME.toString().equals(loginName)) {
			wrapper.where(" code != {0}", AdminParamEnum.ROLECODE.toString());
		}
		super.selectPage(page, wrapper);
		easyuiPage.setRows(page.getRecords());
		easyuiPage.setTotal(page.getTotal());
	}

	@Override
	public Object selectTree(ShiroUser user) {
		List<Tree> trees = new ArrayList<Tree>();
		List<Role> roles = this.selectAll(user);
		for (Role role : roles) {
			Tree tree = new Tree();
			tree.setId(role.getId());
			tree.setText(role.getName());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public void updateRoleResource(Long roleId, String resourceIds) {
		// 先删除后添加,有点爆力
		RoleResource roleResource = new RoleResource();
		roleResource.setRoleId(roleId);
		roleResourceMapper.delete(new EntityWrapper<RoleResource>(roleResource));

		// 如果资源id为空，判断为清空角色资源
		if (StringUtils.isBlank(resourceIds)) {
			return;
		}

		StringTokenizer st = new StringTokenizer(resourceIds, Constants.SEPARATOR_COMMA);
		while (st.hasMoreElements()) {
			String resourceId = st.nextToken();
			roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(Long.parseLong(resourceId));
			roleResourceMapper.insert(roleResource);
		}
	}

	@Override
	public List<Long> selectResourceIdListByRoleId(Long id) {
		return roleMapper.selectResourceIdListByRoleId(id);
	}

	@Override
	public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
		Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(userId);
		Set<String> urlSet = new HashSet<String>();
		Set<String> roles = new HashSet<String>();
		for (Long roleId : roleIdList) {
			List<Map<String, String>> resourceList = roleMapper.selectResourceListByRoleId(roleId);
			if (resourceList != null && !resourceList.isEmpty()) {
				for (Map<String, String> map : resourceList) {
					if (map != null && StringUtils.isNotBlank(map.get("url"))) {
						urlSet.add(map.get("url"));
					}
				}
			}
			Role role = roleMapper.selectById(roleId);
			if (role != null) {
				roles.add(role.getCode());
			}
		}
		resourceMap.put("urls", urlSet);
		resourceMap.put("roles", roles);
		return resourceMap;
	}

	@Override
	public void deleteRole(Long id) {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("role_id", id);
		// 解除用户与角色的关系
		this.userRoleMapper.deleteByMap(columnMap);
		// 解除角色与资源的关系
		this.roleResourceMapper.deleteByMap(columnMap);
		// 删除角色本身
		this.roleMapper.deleteById(id);
	}

	@Override
	public Role selectRoleById(Long id) {
		return super.selectById(id);
	}

	@Override
	public void updateRoleById(Role role) {
		super.updateById(role);
	}

	@Override
	public void insertRole(Role role) {
		super.insert(role);
	}

	@Override
	public List<Role> selectUserRoles(Long userId) {
		return this.userRoleMapper.selectUserRoles(userId);
	}

}