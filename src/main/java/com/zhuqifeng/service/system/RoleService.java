package com.zhuqifeng.service.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.model.pojo.Role;

public interface RoleService extends IService<Role> {

	void selectDataGrid(EasyuiPage<Role> easyuiPage, ShiroUser user);

	Object selectTree(ShiroUser user);

	List<Long> selectResourceIdListByRoleId(Long id);

	void updateRoleResource(Long id, String resourceIds);

	Map<String, Set<String>> selectResourceMapByUserId(Long userId);

	void deleteRole(Long id);

	Role selectRoleById(Long id);

	void updateRoleById(Role role);

	void insertRole(Role role);

	List<Role> selectUserRoles(Long userId);

}