package com.zhuqifeng.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhuqifeng.model.dto.UserDTO;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.vo.UserVO;

public interface UserMapper extends BaseMapper<User> {

	UserVO selectUserVoById(@Param("id") Long id);

	List<UserDTO> selectUserPage(Pagination page, UserVO<UserDTO> userVO);
	
	List<UserDTO> selectUserPageCompany(Pagination page, UserVO<UserDTO> userVO);

	void clearUserOrganization(Long organization_id);

}