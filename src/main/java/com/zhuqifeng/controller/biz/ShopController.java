package com.zhuqifeng.controller.biz;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.Shop;
import com.zhuqifeng.service.biz.ShopService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author QiFeng.Zhu
 * @since 2018-06-20
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;

	@GetMapping("/manager")
	public String manager() {
		return "biz/shop/shopList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(EasyuiPage<Shop> easyuiPage) {
		Page<Shop> page = new Page<Shop>(easyuiPage.getNowpage(), easyuiPage.getPagesize());
		EntityWrapper<Shop> wrapper = new EntityWrapper<Shop>();
		wrapper.orderBy(easyuiPage.getSortName(), easyuiPage.getOrder().equalsIgnoreCase("ASC"));
		shopService.selectPage(page, wrapper);
		easyuiPage.setRows(page.getRecords());
		easyuiPage.setTotal(page.getTotal());
		return easyuiPage;
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid Shop shop, BindingResult result) {
		if (result.hasErrors()) {
			return ajaxFail(result);
		}
		try {
			this.shopService.insert(shop);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		if (id != null && id.longValue() > 0) {
			try {
				this.shopService.deleteById(id);
			} catch (Exception e) {
				logger.error(e);
				return ajaxFail();
			}
			return ajaxSucc();
		} else {
			return ajaxFail("ID不能为空");
		}
	}

	@RequestMapping("/dialog/{shopEdit}")
	public String editPage(Model model, Long id, @PathVariable String shopEdit) {
		if (id != null && id.longValue() > 0) {
			Shop shop = this.shopService.selectById(id);
			model.addAttribute("shop", shop);
		}
		return "biz/shop/" + shopEdit;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid Shop shop, BindingResult result) {
		if (result.hasErrors()) {
			return super.ajaxFail(result);
		}
		try {
			this.shopService.updateById(shop);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}

}
