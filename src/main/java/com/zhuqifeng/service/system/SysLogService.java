package com.zhuqifeng.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.model.pojo.SysLog;
import com.zhuqifeng.model.vo.SysLogVO;

public interface SysLogService extends IService<SysLog> {

	void selectDataGrid(SysLogVO<SysLog> sysLog);

}