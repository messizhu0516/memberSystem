package com.zhuqifeng.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.commons.result.Tree;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.model.pojo.Organization;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface OrganizationService extends IService<Organization> {

	List<Tree> selectTree(ShiroUser user);

	List<Organization> selectTreeGrid(ShiroUser user);

	void deleteOrganization(Long id);

	Organization selectOrganizationById(Long id);

	void insertOrganization(Organization organization);

	void updateOrganizationById(Organization organization);
}