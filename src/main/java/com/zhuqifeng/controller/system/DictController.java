package com.zhuqifeng.controller.system;

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

import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.Dict;
import com.zhuqifeng.service.system.DictService;

/**
 * ClassName: DictController <br/>
 * Function: 数据字典控制器 <br/>
 * Reason: TODO <br/>
 * date: 2018年5月30日 上午11:11:48 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	@GetMapping("/manager")
	public String manager() {
		return "system/dict/dict";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(EasyuiPage<Dict> easyuiPage) {
		this.dictService.selectDataGrid(easyuiPage);
		return easyuiPage;
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid Dict dict, BindingResult result) {
		if (result.hasErrors()) {
			return ajaxFail(result);
		}
		try {
			this.dictService.insert(dict);
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
				this.dictService.deleteById(id);
			} catch (Exception e) {
				logger.error(e);
				return ajaxFail();
			}
			return ajaxSucc();
		} else {
			return ajaxFail("ID不能为空");
		}
	}

	@RequestMapping("/dialog/{dictEdit}")
	public String editPage(Model model, Long id, @PathVariable String dictEdit) {
		if (id != null && id.longValue() > 0) {
			Dict dict = this.dictService.selectById(id);
			model.addAttribute("dict", dict);
		}
		return "system/dict/" + dictEdit;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid Dict dict, BindingResult result) {
		if (result.hasErrors()) {
			return super.ajaxFail(result);
		}
		try {
			this.dictService.updateById(dict);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}
	
	static char a;
	public static void main(String[] args) {
		System.out.println(a);
		System.out.println(123);
	}

}
