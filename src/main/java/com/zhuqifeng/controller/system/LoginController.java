package com.zhuqifeng.controller.system;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuqifeng.commons.shiro.captcha.FlySkyCaptcha;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.utils.encrypt.AES;
import com.zhuqifeng.commons.utils.encrypt.Base64;
import com.zhuqifeng.commons.utils.net.IPUtil;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.SysLog;
import com.zhuqifeng.model.vo.LoginVO;
import com.zhuqifeng.service.system.SysLogService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private FlySkyCaptcha flySkyCaptcha;
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/index";
	}

	/**
	 * 首页
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	/**
	 * GET 登录
	 * 
	 * @return {String}
	 */
	@GetMapping("/login")
	// @CsrfToken(create = true)
	public String login(HttpServletRequest request) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		String pwdKey = UUID.randomUUID().toString();
		pwdKey = pwdKey.replaceAll("-", pwdKey).substring(0, 16);
		request.setAttribute("pwdKey", pwdKey);
		request.getSession().setAttribute("pwdKey", pwdKey);
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Object loginPost(HttpServletRequest request, HttpServletResponse response, LoginVO loginVO, @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
		String userName = loginVO.getUserName();
		String password = loginVO.getPassword();
		String captcha = loginVO.getCaptcha();
		if (StringUtils.isBlank(userName)) {
			return super.ajaxFail("用户名不能为空");
		} else if (StringUtils.isBlank(password)) {
			return super.ajaxFail("密码不能为空");
		} else if (StringUtils.isBlank(captcha)) {
			return super.ajaxFail("验证码不能为空");
		} else if (!flySkyCaptcha.validate(request, response, captcha)) {
			return super.ajaxFail("验证码错误");
		} else {
			try {
				Subject user = SecurityUtils.getSubject();
				// 解密
				HttpSession session = request.getSession(true);
				String pwdKey = (String) session.getAttribute("pwdKey");
				if (StringUtils.isBlank(pwdKey)) {
					return super.ajaxFail("pwdKey不存在，请重新刷新登录页面尝试再次登录");
				} else {
					byte[] decode = Base64.decode(password.getBytes());
					byte[] decrypt = AES.decrypt(decode, pwdKey.getBytes());
					password = new String(decrypt, "utf-8");
					UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
					// 设置记住密码
					token.setRememberMe(1 == rememberMe);
					user.login(token);
					session.setAttribute("pwdKey", null);
					this.saveLoginLog(request, userName);
				}
			} catch (UnknownAccountException e) {
				return super.ajaxFail("账号不存在！");
			} catch (DisabledAccountException e) {
				return super.ajaxFail("账号未启用！");
			} catch (IncorrectCredentialsException e) {
				return super.ajaxFail("用户名或密码错误！");
			} catch (Throwable e) {
				logger.error(e);
				return super.ajaxFail(e.getMessage());
			}
		}
		return super.ajaxSucc();
	}

	private void saveLoginLog(HttpServletRequest request, String loginName) {
		SysLog sysLog = new SysLog();
		sysLog.setLoginName(loginName);
		sysLog.setOptContent("用户登录");
		sysLog.setCreateTime(new Date());
		sysLog.setClientIp(IPUtil.getIpAddr(request));
		sysLogService.insert(sysLog);
	}

	/**
	 * 未授权
	 * 
	 * @return {String}
	 */
	@GetMapping("/unauth")
	public String unauth() {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/login";
		}
		return "unauth";
	}

	/**
	 * 退出
	 * 
	 * @return {Result}
	 */
	@PostMapping("/logout")
	@ResponseBody
	public Object logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return super.ajaxSucc();
		} catch (Exception e) {
			logger.error(e);
			return super.ajaxFail();
		}
	}

}
