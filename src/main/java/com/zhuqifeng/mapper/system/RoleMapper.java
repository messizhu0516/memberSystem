package com.zhuqifeng.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuqifeng.model.pojo.Resource;
import com.zhuqifeng.model.pojo.Role;

public interface RoleMapper extends BaseMapper<Role> {

	List<Long> selectResourceIdListByRoleId(@Param("id") Long id);

	List<Resource> selectResourceListByRoleIdList(@Param("list") List<Long> list, @Param("resource_type") Integer resource_type);

	List<Map<String, String>> selectResourceListByRoleId(@Param("id") Long id);

}