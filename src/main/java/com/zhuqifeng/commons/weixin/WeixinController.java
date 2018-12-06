package com.zhuqifeng.commons.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuqifeng.commons.enums.FileNameEnum;
import com.zhuqifeng.commons.enums.SystemParamEnum;
import com.zhuqifeng.commons.utils.base.Config;
import com.zhuqifeng.commons.utils.base.EmojiFilter;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.weixin.util.WeixinUtil;
import com.zhuqifeng.commons.weixin.util.WeixinUtil.TextMessage;
import com.zhuqifeng.controller.common.BaseController;
import com.zhuqifeng.model.pojo.MemberInfo;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.vo.MemberVO;
import com.zhuqifeng.service.biz.MemberInfoService;
import com.zhuqifeng.service.system.UserService;

/**
 * ClassName: WeixinMsgController <br/>
 * Function: 微信公众号相关处理入口 <br/>
 * Reason: TODO <br/>
 * date: 2018年7月5日 上午10:02:17 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends BaseController {
	private Logger logger = LogManager.getLogger(WeixinController.class);
	private final String wx_filename = FileNameEnum.WEIXIN_PROP.toString();

	@Autowired
	private UserService userService;
	@Autowired
	private MemberInfoService memberInfoService;

	@RequestMapping(value = "/msg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8"); // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
		response.setCharacterEncoding("UTF-8"); // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		PrintWriter out = response.getWriter();
		try {
			if (isGet) {
				// 微信加密签名
				String signature = request.getParameter("signature");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 微信token
				String token = Config.getConfig(wx_filename, "token");
				String[] str = { token, timestamp, nonce };
				Arrays.sort(str); // 字典序排序
				String bigStr = str[0] + str[1] + str[2];
				// SHA1加密
				SimpleHash simpleHash = new SimpleHash("SHA-1", bigStr);
				String digest = simpleHash.toHex();
				if (digest.equals(signature)) {
					response.getWriter().print(echostr);
				}
			}
			// 用户向公众号发送消息
			else {
				String respMessage = "异常消息！";
				try {
					respMessage = this.processUserMsg(request);
					out.write(respMessage);
				} catch (Exception e) {
					logger.error("Failed to convert the message from weixin!");
				}
			}
		} catch (Exception e) {
			logger.error("Connect the weixin server is error.");
		} finally {
			out.close();
		}
	}

	/**
	 * processUserMsg:处理用户发送的请求 <br/>
	 * 
	 * @param request
	 * @return
	 * @author QiFeng.Zhu
	 */
	public String processUserMsg(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = WeixinUtil.xmlToMap(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");
			logger.debug("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);
			// 文本消息
			if (msgType.equals(WeixinUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 这里根据关键字执行相应的逻辑，只有你想不到的，没有做不到的
				if (content.equals("xxx")) {

				}
				// 自动回复
				TextMessage text = new TextMessage();
				text.setContent("亲爱哒说：" + content);
				text.setToUserName(fromUserName);
				text.setFromUserName(toUserName);
				text.setCreateTime(new Date().getTime() + "");
				text.setMsgType(msgType);
				respMessage = WeixinUtil.textMessageToXml(text);
			}
			// 事件推送
			else if (msgType.equals(WeixinUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");// 事件类型
				// 订阅
				if (eventType.equals(WeixinUtil.EVENT_TYPE_SUBSCRIBE)) {
					TextMessage text = new TextMessage();
					text.setContent("欢迎关注，徐敏燕猪大头");
					text.setToUserName(fromUserName);
					text.setFromUserName(toUserName);
					text.setCreateTime(new Date().getTime() + "");
					text.setMsgType(WeixinUtil.RESP_MESSAGE_TYPE_TEXT);
					respMessage = WeixinUtil.textMessageToXml(text);
				}
				// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				else if (eventType.equals(WeixinUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后相关操作
				}
				// 自定义菜单点击事件
				else if (eventType.equals(WeixinUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					if (eventKey.equals("customer_telephone")) {
						TextMessage text = new TextMessage();
						text.setContent("0755-86671980");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(WeixinUtil.RESP_MESSAGE_TYPE_TEXT);
						respMessage = WeixinUtil.textMessageToXml(text);
					}
				}
			}
		} catch (Exception e) {
			logger.error("处理用户消息时出错：" + e.getMessage(), e);
		}
		return respMessage;
	}

	@RequestMapping(value = "/doOauth")
	@ResponseBody
	public Object doOauth(HttpServletRequest request, HttpServletResponse resp) {
		String myParameters = request.getParameter("myParameters");
		try {
			// 关注公众号，扫描二维码时注册用户
			Map<String, Object> map = WeixinUtil.getUserOpenId(request, false);
			String openId = (String) map.get("openId");
			String nickname = (String) map.get("nickname");
			if (StringUtils.isNotEmpty(openId)) {
				Wrapper<User> wrapper = new EntityWrapper<User>();
				wrapper.eq("open_id", openId);
				User user = this.userService.selectOne(wrapper);
				if (user == null) {
					// 自动注册用户
					user = new User();
					user.setUserType(SystemParamEnum.AC_MEMBER.toString());
					user.setOpenId(openId);
					// 过滤特殊字符
					nickname = EmojiFilter.filterEmoji(nickname);
					MemberInfo mi = new MemberInfo();
					mi.setNikeName(nickname);
					MemberVO vo = new MemberVO(mi, user);
					// 注册会员用户
					this.memberInfoService.registerMember(vo);
				}
				return super.ajaxSucc();
			} else {
				return super.ajaxFail();
			}
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return super.ajaxFail();
		}
	}

}
