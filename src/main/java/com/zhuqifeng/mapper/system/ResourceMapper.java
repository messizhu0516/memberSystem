package com.zhuqifeng.mapper.system;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuqifeng.model.pojo.Resource;

public interface ResourceMapper extends BaseMapper<Resource> {
	List<String> selectAllUseingResources();
}