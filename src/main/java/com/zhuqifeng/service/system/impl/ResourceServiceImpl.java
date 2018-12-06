package com.zhuqifeng.service.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.enums.AdminParamEnum;
import com.zhuqifeng.commons.result.Tree;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.mapper.system.ResourceMapper;
import com.zhuqifeng.mapper.system.RoleMapper;
import com.zhuqifeng.mapper.system.RoleResourceMapper;
import com.zhuqifeng.mapper.system.UserRoleMapper;
import com.zhuqifeng.model.pojo.Resource;
import com.zhuqifeng.service.system.ResourceService;

/**
 * ClassName: ResourceServiceImpl <br/>
 * Function: 资源菜单处理 <br/>
 * Reason: TODO <br/>
 * date: 2018年4月28日 下午5:08:34 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public List<Resource> selectAll() {
		EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
		wrapper.orderBy("sort");
		return resourceMapper.selectList(wrapper);
	}

	@Override
	public List<Tree> treeForGrant(ShiroUser shiroUser) {
		return this.generateResourceTree(shiroUser, "open", null);
	}

	@Override
	public List<Tree> selectAllMenu(ShiroUser shiroUser) {
		return this.generateResourceTree(shiroUser, "open", 0);
	}

	@Override
	public List<Tree> leftMenuTree(ShiroUser shiroUser) {
		return this.generateResourceTree(shiroUser, "open", 0);
	}

	/**
	 * 根据相应条件生成资源树 ， treeOpen：提供给前端ztree用的树的打开与关闭状态（open：打开，closed：关闭），
	 * resource_type：资源类型（0：菜单导航，1：按钮）
	 */
	private List<Tree> generateResourceTree(ShiroUser shiroUser, String treeOpen, Integer resource_type) {
		List<Tree> trees = new ArrayList<Tree>();
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		if (roles == null) {
			return trees;
		}
		List<Resource> resourceList = null;
		// 如果有超级管理员权限
		if (roles.contains(AdminParamEnum.ROLECODE.toString())) {
			resourceList = this.findResourceByType(resource_type);
		} else {
			// 普通用户
			List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
			if (roleIdList == null) {
				return trees;
			}
			resourceList = roleMapper.selectResourceListByRoleIdList(roleIdList, resource_type);
		}
		if (resourceList == null) {
			return trees;
		}
		for (Resource resource : resourceList) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setOpenMode(resource.getOpenMode());
			if (StringUtils.isNotBlank(treeOpen)) {
				tree.setState(treeOpen);
			} else {
				tree.setState(resource.getOpened());
			}
			trees.add(tree);
		}
		return trees;
	}

	private List<Resource> findResourceByType(Integer resource_type) {
		EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
		Resource resource = new Resource();
		wrapper.setEntity(resource);
		if (resource_type != null) {
			wrapper.addFilter("resource_type = {0}", resource_type);
		}
		wrapper.orderBy("sort");
		return resourceMapper.selectList(wrapper);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean deleteById(Serializable resourceId) {
		roleResourceMapper.deleteByResourceId(resourceId);
		return super.deleteById(resourceId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteResource(Long id) {
		if (id != null && id.intValue() > 0) {
			recursionDel(id);
			// 删除当前分类
			super.deleteById(id);
		} else {
			throw new NullPointerException();
		}
	}

	private void recursionDel(Long id) {
		List<Resource> children = super.selectList(new EntityWrapper<Resource>().eq("pid", id));
		if (children != null && children.size() > 0) {
			for (Resource resource : children) {
				Long id2 = resource.getId();
				recursionDel(id2);
				// 删除当前分类
				super.deleteById(id2);
			}
		}
	}

	@Override
	public Resource selectResource(Long id) {
		return super.selectById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertResource(Resource resource) {
		super.insert(resource);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateResourceById(Resource resource) {
		super.updateById(resource);
	}

	@Override
	public List<String> selectAllUseingResources() {
		return this.resourceMapper.selectAllUseingResources();
	}

}