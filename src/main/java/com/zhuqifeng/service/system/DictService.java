package com.zhuqifeng.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.commons.result.EasyuiPage;
import com.zhuqifeng.model.pojo.Dict;

public interface DictService extends IService<Dict> {
	void selectDataGrid(EasyuiPage<Dict> easyuiPage);
}