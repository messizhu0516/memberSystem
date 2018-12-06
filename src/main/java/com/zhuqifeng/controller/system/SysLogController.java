package com.zhuqifeng.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.SysLog;
import com.zhuqifeng.model.vo.SysLogVO;
import com.zhuqifeng.service.system.SysLogService;

@Controller
@RequestMapping("/sysLog")
public class SysLogController extends BaseController {
	@Autowired
	private SysLogService sysLogService;

	@GetMapping("/manager")
	public String manager() {
		return "system/oplog/oplog";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(SysLogVO<SysLog> sysLogVO) {
		this.sysLogService.selectDataGrid(sysLogVO);
		return sysLogVO;
	}
}
