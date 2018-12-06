package com.zhuqifeng.service.system;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.commons.shiro.ShiroUser;
import com.zhuqifeng.model.dto.UserDTO;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.vo.UserVO;

public interface UserService extends IService<User> {

	User selectByLoginName(String loginName);

	void insertByVo(UserVO userVo);

	UserVO selectVoById(Long id);

	void updateByVo(UserVO userVo);

	void updatePwdByUserId(Long userId, String md5Hex);

	void selectDataGrid(UserVO<UserDTO> userVO, ShiroUser user);

	void deleteUserById(Long id);

	User getUserForEdit(Long id, String loginName);

	User selectUserById(Long id);

	User selectUserOne(Wrapper<User> wrapper);
}