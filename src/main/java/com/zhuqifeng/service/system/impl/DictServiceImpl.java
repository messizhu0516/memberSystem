package com.zhuqifeng.service.system.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.mapper.system.DictMapper;
import com.zhuqifeng.model.pojo.Dict;
import com.zhuqifeng.service.system.DictService;

@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	@Override
	public void selectDataGrid(EasyuiPage<Dict> easyuiPage) {
		Page<Dict> page = new Page<Dict>(easyuiPage.getNowpage(), easyuiPage.getPagesize());
		EntityWrapper<Dict> wrapper = new EntityWrapper<Dict>();
		wrapper.orderBy(easyuiPage.getSortName(), easyuiPage.getOrder().equalsIgnoreCase("ASC"));
		super.selectPage(page, wrapper);
		easyuiPage.setRows(page.getRecords());
		easyuiPage.setTotal(page.getTotal());
	}

}