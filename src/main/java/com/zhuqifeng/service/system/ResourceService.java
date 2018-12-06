package com.zhuqifeng.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.commons.result.Tree;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.model.pojo.Resource;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface ResourceService extends IService<Resource> {

	List<Resource> selectAll();

	List<Tree> selectAllMenu(ShiroUser shiroUser);

	List<Tree> treeForGrant(ShiroUser shiroUser);

	List<Tree> leftMenuTree(ShiroUser shiroUser);

	void deleteResource(Long id);

	Resource selectResource(Long id);

	void insertResource(Resource resource);

	void updateResourceById(Resource resource);

	List<String> selectAllUseingResources();

}