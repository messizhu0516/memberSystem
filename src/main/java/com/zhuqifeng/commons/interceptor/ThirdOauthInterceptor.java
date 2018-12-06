package com.zhuqifeng.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhuqifeng.commons.utils.base.Config;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.weixin.util.SnsAccessTokenApi;
import com.zhuqifeng.commons.weixin.util.WeixinUtil;

/**
 * ClassName: ThirdOauthInterceptor <br/>
 * Function:
 * 需要进行拦截的第三方鉴权处理拦截器（例如需要微信授权登录的鉴权等页面都需要经过此拦截器拦截并获取相应的第三方鉴权结果后才能进行后续操作） <br/>
 * Reason: TODO <br/>
 * date: 2018年7月5日 下午2:36:24 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public class ThirdOauthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String userAgent = request.getHeader("User-Agent");
		String agent = userAgent.toLowerCase();
		String myParameters = request.getParameter("myParameters");
		// 生成回调URL
		StringBuffer sb = new StringBuffer(request.getScheme());
		sb.append("://").append(request.getServerName()).append(":").append(request.getServerPort());
		sb.append(request.getContextPath()).append("/").append(WeixinUtil.WXCB_URL).append("?myParameters=").append(myParameters);
		String requestURL = "";
		if (agent.contains("micromessenger")) {
			String appId = Config.getConfig(WeixinUtil.WX_FILENAME, "appId");
			requestURL = SnsAccessTokenApi.getAuthorizeURL(appId, sb.toString(), "", false);
		}
		// 支付宝生活号
		else if (agent.contains("alipayclient")) {
		}
		// 重定向URL获取code，第三方再通过redirect_url跳转回相应URL做后续处理
		response.sendRedirect(StringUtils.isNotEmpty(requestURL) ? requestURL : WeixinUtil.WXCB_URL);
		return false;
	}

}
