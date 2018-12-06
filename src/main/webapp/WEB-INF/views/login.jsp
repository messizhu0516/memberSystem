<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>用户登录</title>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css" />
<script src="static/encrypt/aes.js"></script>
<script src="static/encrypt/mode-ecb-min.js"></script>
<script type="text/javascript">
	//判断时候在Iframe框架内,在则刷新父页面
	if (self != top) {
		parent.location.reload(true);
		if (!!(window.attachEvent && !window.opera)) {
			document.execCommand("stop");
		} else {
			window.stop();
		}
	}
	
	function Encrypt(word, key) {
		var srcs = CryptoJS.enc.Utf8.parse(word);
		var encrypted = CryptoJS.AES.encrypt(srcs, key, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();
	}
	
	$(function() {
		// 验证码
		$("#captcha").click(function() {
			var $this = $(this);
			var url = $this.data("src") + new Date().getTime();
			$this.attr("src", url);
		});
		// 登录
		$('#loginform').form({
			url : basePath + '/login',
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				} else {
					var key = CryptoJS.enc.Utf8.parse('${pwdKey}');
					$('#password').val(Encrypt($('#password').val(), key));
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.code == "000") {
					window.location.href = basePath + '/index';
				} else {
					// 刷新验证码
					$("#captcha")[0].click();
					$("#password").val('');
					showMsg(result.msg);
				}
			}
		});
	});
	function submitForm() {
		$('#loginform').submit();
	}
	function clearForm() {
		$('#loginform').form('clear');
	}
	// 回车登录
	function enterlogin() {
		if (event.keyCode == 13) {
			event.returnValue = false;
			event.cancel = true;
			$('#loginform').submit();
		}
	}
</script>
</head>
<!-- style="background-image:url(static/loginbg.jpg)" -->
<body onkeydown="enterlogin();" style="background-image:url(static/loginbg.jpg)">
	<div style="background:#1e2722; margin:220px auto auto;border-radius: 2px;width:400px;text-align: center;">
	    <form method="post" id="loginform">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <div style="width:165px; height: 96px; position: absolute;"></div>
	        <P style="padding:30px 0px 10px; position:relative;">
	            <span class="u_logo"></span>
	            <input class="ipt" type="text" name="userName" placeholder="请输入登录名" value="admin"/>
	        </P>
	        <P style="position:relative;">
	            <span class="p_logo"></span>
	            <input class="ipt" id="password" type="password" name="password" placeholder="请输入密码" value="123456"/>
	        </P>
	        <P style="padding: 10px 0px 10px; position: relative;">
	            <input class="captcha" type="text" name="captcha" placeholder="请输入验证码"/>
	            <img id="captcha" alt="验证码" src="${path}/ignore/captcha.jpg" data-src="${path}/ignore/captcha.jpg?t=" style="vertical-align:middle;border-radius:4px;width:94.5px;height:35px;cursor:pointer;">
	        </P>
	        <P style="position: relative;text-align: left;color:wheat;">
	            <input class="rememberMe" type="checkbox" name="rememberMe" value="1" checked style="vertical-align:middle;margin-left:40px;height:20px;"/> 记住密码
	        </P>
	        <div style="height: 50px; line-height: 50px; margin-top: 10px;border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
	            <P style="margin: 0px 35px 20px 45px;">
	                <!-- <span style="float: left;">
	                    <a style="color: rgb(204, 204, 204);" href="javascript:;">忘记密码?</a>
	                </span> -->
	                <span style="float: right;">
	                    <!-- <a style="color: rgb(204, 204, 204); margin-right: 10px;" href="javascript:;">注册</a> -->
	                    <a style="background: #999999; padding: 7px 10px; border-radius: 4px; border: 1px solid #999999; border-image: none; color: rgb(255, 255, 255); font-weight: bold;" href="javascript:;" onclick="submitForm()">登录</a>
	                </span>
	            </P>
	        </div>
	    </form>
	</div>
	<div style="text-align:center;">
	    <p>
	        <a href="http://www.baidu.com" target="_blank"></a>
	        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256912241'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1256912241%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
	    </p>
	</div>
</body>
</html>
