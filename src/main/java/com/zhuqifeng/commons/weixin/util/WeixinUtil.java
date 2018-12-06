/**  
 * Project Name:memberSystem  
 * File Name:a.java  
 * Package Name:com.zhuqifeng.commons.weixin.util  
 * Date:2018年7月3日下午3:44:15  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.zhuqifeng.commons.enums.FileNameEnum;
import com.zhuqifeng.commons.utils.base.Config;
import com.zhuqifeng.commons.utils.base.StringUtils;
import com.zhuqifeng.commons.utils.net.HttpClientUtil;
import com.zhuqifeng.commons.weixin.WeixinController;

/**
 * ClassName:a <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年7月3日 下午3:44:15 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public class WeixinUtil {
	private static Logger logger = LogManager.getLogger(WeixinController.class);
	// 微信公众号相关配置的properties文件名
	public static final String WX_FILENAME = FileNameEnum.WEIXIN_PROP.toString();
	public static final String getUserInfo = "https://api.weixin.qq.com/sns/userinfo";
	// 微信鉴权获取code后的回调URL
	public static final String WXCB_URL = "/weixin/doOauth";
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * xml转换为map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = reader.read(ins);
			Element root = doc.getRootElement();

			List<Element> list = root.elements();

			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}

			return map;
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} finally {
			ins.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getUserOpenId(HttpServletRequest request, boolean getUserInfo) {
		String code = request.getParameter("code");
		Map<String, Object> map = new HashMap<String, Object>();
		String openId = null;
		// 获取微信公众号用户openid
		if (code != null) {
			String appId = Config.getConfig(WX_FILENAME, "appId");
			String appSecret = Config.getConfig(WX_FILENAME, "appSecret");
			// 通过code换取网页授权access_token
			SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, appSecret, code);
			String token = snsAccessToken.getAccessToken();
			openId = snsAccessToken.getOpenid();
			// 获取用户信息
			if (getUserInfo) {
				String apiResult = WeixinUtil.getUserInfo(token, openId);
				ObjectMapper om = new ObjectMapper();
				Map<String, Object> jsonObject;
				try {
					jsonObject = om.readValue(apiResult, Map.class);
					// String nickName = jsonObject.getString("nickname");
					// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
					// int sex = jsonObject.getIntValue("sex");
					// String city = jsonObject.getString("city");// 城市
					// String province = jsonObject.getString("province");// 省份
					// String country = jsonObject.getString("country");// 国家
					// String headimgurl = jsonObject.getString("headimgurl");
					// String unionid = jsonObject.getString("unionid");
					map.put("userInfo", jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			map.put("openId", openId);
		} else {
			logger.error("获取微信公众号openid时缺少code参数");
			return null;
		}
		return map;
	}

	/**
	 * 获取用户个人信
	 * 
	 * @param accessToken
	 *            调用凭证access_token
	 * @param openId
	 *            普通用户的标识，对当前开发者帐号唯一
	 * @return ApiResult
	 */
	public static String getUserInfo(String accessToken, String openId) {
		StringBuffer sb = new StringBuffer(getUserInfo);
		sb.append("?access_token=").append(accessToken).append("&openid=").append(openId).append("&lang=zh_CN");
		return HttpClientUtil.sendHttpGet(sb.toString());
	}

	/**
	 * 组装签名的字段
	 * 
	 * @param params
	 *            参数
	 * @param urlEncoder
	 *            是否urlEncoder
	 * @return String
	 */
	public static String packageSign(Map<String, String> params, boolean urlEncoder) {
		// 先将参数以其参数名的字典序升序进行排序
		TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Entry<String, String> param : sortedParams.entrySet()) {
			String value = param.getValue();
			if (StringUtils.isBlank(value)) {
				continue;
			}
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			sb.append(param.getKey()).append("=");
			if (urlEncoder) {
				try {
					value = urlEncode(value);
				} catch (UnsupportedEncodingException e) {
				}
			}
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * urlEncode
	 * 
	 * @param src
	 *            微信参数
	 * @return String
	 * @throws UnsupportedEncodingException
	 *             编码错误
	 */
	public static String urlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
	}

	public static class TextMessage {
		private String ToUserName;
		private String FromUserName;
		private String CreateTime;
		private String MsgType;
		private String Content;
		private String MsgId;

		public String getToUserName() {
			return ToUserName;
		}

		public String getFromUserName() {
			return FromUserName;
		}

		public String getCreateTime() {
			return CreateTime;
		}

		public String getMsgType() {
			return MsgType;
		}

		public String getContent() {
			return Content;
		}

		public String getMsgId() {
			return MsgId;
		}

		public void setToUserName(String toUserName) {
			ToUserName = toUserName;
		}

		public void setFromUserName(String fromUserName) {
			FromUserName = fromUserName;
		}

		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}

		public void setMsgType(String msgType) {
			MsgType = msgType;
		}

		public void setContent(String content) {
			Content = content;
		}

		public void setMsgId(String msgId) {
			MsgId = msgId;
		}

	}
}
