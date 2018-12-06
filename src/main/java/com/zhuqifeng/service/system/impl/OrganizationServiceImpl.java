package com.zhuqifeng.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.enums.SystemParamEnum;
import com.zhuqifeng.commons.result.Tree;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.mapper.system.OrganizationMapper;
import com.zhuqifeng.mapper.system.UserMapper;
import com.zhuqifeng.model.pojo.Organization;
import com.zhuqifeng.service.system.OrganizationService;

@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Tree> selectTree(ShiroUser user) {
		List<Organization> organizationList = this.selectTreeGrid(user);
		List<Tree> trees = new ArrayList<Tree>();
		if (organizationList != null) {
			for (Organization organization : organizationList) {
				Tree tree = new Tree();
				tree.setId(organization.getId());
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIcon());
				tree.setPid(organization.getPid());
				trees.add(tree);
			}
		}
		return trees;
	}
	
	@Override
	public List<Organization> selectTreeGrid(ShiroUser user) {
		String userType = user.getUserType();
		EntityWrapper<Organization> wrapper = new EntityWrapper<Organization>();
		// 企业账号登录时
		if (SystemParamEnum.AC_COMPANY.toString().equals(userType)) {
			wrapper.eq("id", user.getOrganizationId());
		}
		wrapper.orderBy("sort");
		List<Organization> organizationList = organizationMapper.selectList(wrapper);
		return organizationList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteOrganization(Long id) {
		if (id != null && id.intValue() > 0) {
			recursionDel(id);
			// 删除当前分类
			super.deleteById(id);
			// 清除用户中有此部门信息的人的部门数据
			this.userMapper.clearUserOrganization(id);
		} else {
			throw new NullPointerException();
		}
	}

	private void recursionDel(Long id) {
		List<Organization> children = super.selectList(new EntityWrapper<Organization>().eq("pid", id));
		if (children != null && children.size() > 0) {
			for (Organization organization : children) {
				Long id2 = organization.getId();
				recursionDel(id2);
				// 删除当前分类
				super.deleteById(id2);
				// 清除用户中有此部门信息的人的部门数据
				this.userMapper.clearUserOrganization(id2);
			}
		}
	}

	@Override
	public Organization selectOrganizationById(Long id) {
		return super.selectById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertOrganization(Organization organization) {
		super.insert(organization);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOrganizationById(Organization organization) {
		super.updateById(organization);
	}

}