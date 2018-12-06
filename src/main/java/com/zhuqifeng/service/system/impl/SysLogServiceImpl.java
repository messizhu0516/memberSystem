package com.zhuqifeng.service.system.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.mapper.system.SysLogMapper;
import com.zhuqifeng.model.pojo.SysLog;
import com.zhuqifeng.model.vo.SysLogVO;
import com.zhuqifeng.service.system.SysLogService;

@Service
@Transactional(readOnly = true)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	@Override
	public void selectDataGrid(SysLogVO<SysLog> sysLogVO) {
		Page<SysLog> page = new Page<SysLog>(sysLogVO.getNowpage(), sysLogVO.getPagesize());
		SysLog sysLog = new SysLog();
		String loginName = sysLogVO.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			sysLog.setLoginName(loginName);
		}
		EntityWrapper<SysLog> wrapper = new EntityWrapper<SysLog>(sysLog);
		wrapper.orderBy(sysLogVO.getSortName(), sysLogVO.getOrder().equalsIgnoreCase("ASC"));
		Date startDate = sysLogVO.getStartDate();
		Date endDate = sysLogVO.getEndDate();
		if (startDate != null) {
			wrapper.where("create_time >= {0}", startDate);
		}
		if (endDate != null) {
			wrapper.where("create_time <= {0}", endDate);
		}
		selectPage(page, wrapper);
		sysLogVO.setRows(page.getRecords());
		sysLogVO.setTotal(page.getTotal());
	}

}