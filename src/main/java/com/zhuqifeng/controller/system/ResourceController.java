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
import com.zhuqifeng.model.pojo.Resource;
import com.zhuqifeng.service.system.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 菜单树
	 *
	 * @return
	 */
	@PostMapping("/leftMenuTree")
	@ResponseBody
	public Object leftMenuTree() {
		return resourceService.leftMenuTree(getShiroUser());
	}

	/**
	 * 资源管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "system/resource/resource";
	}

	/**
	 * 资源管理列表
	 *
	 * @return
	 */
	@PostMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return resourceService.selectAll();
	}

	/**
	 * 添加资源页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/resource/resourceAdd";
	}

	/**
	 * 添加资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid Resource resource, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			resource.setCreateTime(new Date());
			// 选择菜单时将openMode设置为null
			Integer type = resource.getResourceType();
			if (null != type && type == 0) {
				resource.setOpenMode(null);
			}
			resourceService.insertResource(resource);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 查询所有的菜单
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public Object allMenu() {
		return resourceService.selectAllMenu(getShiroUser());
	}

	/**
	 * 查询所有的资源tree
	 */
	@RequestMapping("/treeForGrant")
	@ResponseBody
	public Object allTree() {
		return resourceService.treeForGrant(super.getShiroUser());
	}

	/**
	 * 编辑资源页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, Long id) {
		Resource resource = resourceService.selectResource(id);
		model.addAttribute("resource", resource);
		return "system/resource/resourceEdit";
	}

	/**
	 * 编辑资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid Resource resource, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		try {
			resourceService.updateResourceById(resource);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 删除资源
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		try {
			resourceService.deleteResource(id);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

}
