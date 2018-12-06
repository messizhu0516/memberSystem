package com.zhuqifeng.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuqifeng.commons.enums.AdminParamEnum;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.Role;
import com.zhuqifeng.service.system.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 权限管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "system/role/role";
	}

	/**
	 * 权限列表
	 *
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(EasyuiPage<Role> easyuiPage) {
		roleService.selectDataGrid(easyuiPage, super.getShiroUser());
		return easyuiPage;
	}

	/**
	 * 权限树
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		return roleService.selectTree(super.getShiroUser());
	}

	/**
	 * 添加权限页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/role/roleAdd";
	}

	/**
	 * 添加权限
	 *
	 * @param role
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			Wrapper<Role> wrapper = new EntityWrapper<Role>();
			wrapper.eq("name", role.getName());
			wrapper.or().eq("code", role.getCode());
			Role selectOne = roleService.selectOne(wrapper);
			if (selectOne != null) {
				return super.ajaxFail("角色名称或编码已存在");
			}
			roleService.insertRole(role);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 删除权限
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		try {
			Subject subject = SecurityUtils.getSubject();
			Role role = this.roleService.selectRoleById(id);
			if (role != null) {
				String code = role.getCode();
				if (AdminParamEnum.ROLECODE.toString().equals(code)) {
					return super.ajaxFail("管理员角色，不能删除！");
				} else {
					this.roleService.deleteRole(id);
					if (subject.hasRole(code)) {
						subject.logout();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("hasRole", true);
						return super.ajaxSucc(map);
					} else {
						return super.ajaxSucc("删除成功！");
					}
				}
			} else {
				return super.ajaxFail("此角色已不存在");
			}
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 编辑权限页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, Long id) {
		Role role = roleService.selectRoleById(id);
		model.addAttribute("role", role);
		return "system/role/roleEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			Wrapper<Role> wrapper = new EntityWrapper<Role>();
			wrapper.eq("code", role.getCode()).or(" name ={0}", role.getName());
			wrapper.and().ne("id", role.getId());
			Role selectOne = roleService.selectOne(wrapper);
			if (selectOne != null) {
				return super.ajaxFail("角色名称或编码已存在");
			}
			roleService.updateRoleById(role);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 授权页面
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/grantPage")
	public String grantPage(Model model, Long id) {
		model.addAttribute("id", id);
		return "system/role/roleGrant";
	}

	/**
	 * 授权页面页面根据角色查询资源
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/findResourceIdListByRoleId")
	@ResponseBody
	public Object findResourceByRoleId(Long id) {
		return super.ajaxSucc(roleService.selectResourceIdListByRoleId(id));
	}

	/**
	 * 授权
	 *
	 * @param id
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Object grant(Long id, String resourceIds) {
		try {
			roleService.updateRoleResource(id, resourceIds);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

}
