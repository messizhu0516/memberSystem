package com.zhuqifeng.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuqifeng.commons.shiro.PasswordHash;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.utils.sequence.SequenceUtil;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.dto.UserDTO;
import com.zhuqifeng.model.pojo.Role;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.vo.UserVO;
import com.zhuqifeng.service.system.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordHash passwordHash;

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "system/user/user";
	}

	/**
	 * 用户管理列表
	 *
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(UserVO<UserDTO> userVO) {
		this.userService.selectDataGrid(userVO, getShiroUser());
		return userVO;
	}

	/**
	 * 添加用户页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/user/userAdd";
	}

	/**
	 * 添加用户
	 *
	 * @param userVo
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid UserVO userVo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		String loginName = userVo.getLoginName();
		String password = userVo.getPassword();
		Wrapper<User> wrapper = new EntityWrapper<User>();
		wrapper.eq("login_name", loginName);
		User user = userService.selectUserOne(wrapper);
		if (user != null) {
			return ajaxFail("登录名已存在！");
		}
		try {
			String salt = SequenceUtil.getUUID();
			String pwd = passwordHash.toHex(password, salt);
			userVo.setSalt(salt);
			userVo.setPassword(pwd);
			userService.insertByVo(userVo);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	/**
	 * 编辑用户页
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		UserVO userVo = userService.selectVoById(id);
		List<Role> rolesList = userVo.getRolesList();
		List<Long> ids = new ArrayList<Long>();
		for (Role role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "system/user/userEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid UserVO userVo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.ajaxFail(bindingResult);
		}
		String loginName = userVo.getLoginName();
		Long id = userVo.getId();
		User user = this.userService.getUserForEdit(id, loginName);
		if (user != null) {
			return ajaxFail("登录名已存在!");
		}
		// 更新密码
		try {
			String password = userVo.getPassword();
			if (StringUtils.isNotBlank(password)) {
				User dbUser = userService.selectUserById(userVo.getId());
				String salt = dbUser.getSalt();
				String pwd = passwordHash.toHex(password, salt);
				userVo.setPassword(pwd);
			}
			userService.updateByVo(userVo);
			return ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return ajaxFail();
		}
	}

	/**
	 * 修改密码页
	 *
	 * @return
	 */
	@GetMapping("/editPwdPage")
	public String editPwdPage() {
		return "system/user/userEditPwd";
	}

	/**
	 * 修改密码
	 *
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	@PostMapping("/editUserPwd")
	@ResponseBody
	public Object editUserPwd(String oldPwd, String pwd) {
		User user = userService.selectUserById(super.getLoginUserId());
		String salt = user.getSalt();
		if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
			return super.ajaxFail("原密码不正确!");
		}
		try {
			userService.updatePwdByUserId(super.getLoginUserId(), passwordHash.toHex(pwd, salt));
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

	@PostMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		Long currentUserId = super.getLoginUserId();
		if (id == currentUserId) {
			return super.ajaxFail("不可以删除自己！");
		}
		try {
			userService.deleteUserById(id);
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}
}