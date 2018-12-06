package com.zhuqifeng.commons.scan;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.model.pojo.SysLog;
import com.zhuqifeng.service.system.SysLogService;

/**
 * ClassName: SysLogAspect <br/>  
 * Function: aop日志 <br/>  
 * Reason: TODO <br/>  
 * date: 2018年7月5日 下午2:34:52 <br/>  
 *  
 * @author QiFeng.Zhu  
 * @version 1.0  
 * @since JDK 1.8
 */
@Aspect
@Component
@Order
public class SysLogAspect {
	private static final Logger LOGGER = LogManager.getLogger(SysLogAspect.class);

	@Autowired
	private SysLogService sysLogService;

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void cutController() {
	}

	@Around("cutController()")
	public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
		String strMethodName = point.getSignature().getName();
		String strClassName = point.getTarget().getClass().getName();
		Object[] params = point.getArgs();
		StringBuffer bfParams = new StringBuffer();
		Enumeration<String> paraNames = null;
		HttpServletRequest request = null;
		if (params != null && params.length > 0) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			paraNames = request.getParameterNames();
			String key;
			String value;
			while (paraNames.hasMoreElements()) {
				key = paraNames.nextElement();
				value = request.getParameter(key);
				bfParams.append(key).append("=").append(value).append("&");
			}
			if (StringUtils.isBlank(bfParams)) {
				bfParams.append(request.getQueryString());
			}
		}

		String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams.toString());
		LOGGER.info(strMessage);
		if (isWriteLog(strMethodName)) {
			try {
				Subject currentUser = SecurityUtils.getSubject();
				PrincipalCollection collection = currentUser.getPrincipals();
				if (null != collection) {
					String loginName = collection.getPrimaryPrincipal().toString();
					SysLog sysLog = new SysLog();
					sysLog.setLoginName(loginName);
					sysLog.setRoleName(loginName);
					sysLog.setOptContent(strMessage);
					sysLog.setCreateTime(new Date());
					if (request != null) {
						sysLog.setClientIp(request.getRemoteAddr());
					}
					LOGGER.info(sysLog.toString());
					sysLogService.insert(sysLog);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return point.proceed();
	}

	private boolean isWriteLog(String method) {
		String[] pattern = { "login", "logout", "save", "update", "delete", "grant" };
		for (String s : pattern) {
			if (method.indexOf(s) > -1) {
				return true;
			}
		}
		return false;
	}
}
