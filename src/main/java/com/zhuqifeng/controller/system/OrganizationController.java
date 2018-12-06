package com.zhuqifeng.controller.system;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.Organization;
import com.zhuqifeng.service.system.OrganizationService;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

	@Autowired
	private OrganizationService organizationService;

	/**
	 * 部门管理主页
	 *
	 * @return
	 */
	@GetMapping(value = "/manager")
	public String manager() {
		return "system/organization/organization";
	}

	/**
	 * 部门资源树
	 *
	 * @return
	 */
	@PostMapping(value = "/tree")
	@ResponseBody
	public Object tree() {
		return organizationService.selectTree(super.getShiroUser());
	}

	/**
	 * 部门列表
	 *
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return organizationService.selectTreeGrid(super.getShiroUser());
	}

	/**
	 * 添加部门页
	 *
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		return "system/organization/organizationAdd";
	}

	/**
	 * 添加部门
	 *
	 * @param organization
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid Organization organization, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			organization.setCreateTime(new Date());
			organizationService.insertOrganization(organization);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}

	/**
	 * 编辑部门页
	 *
	 * @param request
	 * @param id
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		Organization organization = organizationService.selectOrganizationById(id);
		model.addAttribute("organization", organization);
		return "system/organization/organizationEdit";
	}

	/**
	 * 编辑部门
	 *
	 * @param organization
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid Organization organization, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			organizationService.updateOrganizationById(organization);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}

	/**
	 * 删除部门
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		if (id == null || id.longValue() == 0) {
			return super.ajaxFail("ID不能为空");
		} else {
			try {
				organizationService.deleteOrganization(id);
				return ajaxSucc();
			} catch (Exception e) {
				logger.error(e);
				return super.ajaxFail();
			}
		}
	}
}