<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<p>
PS: https就是http和TCP之间有一层SSL层，这一层的实际作用是防止钓鱼和加密。防止钓鱼通过网站的证书，网站必须有CA证书，证书类似于一个解密的签名。另外是加密，加密需要一个密钥交换算法，双方通过交换后的密钥加解密。
</p>
<h3>
      HTTPS和HTTP的区别：
</h3>
<p>
      1：http的全称是Hypertext Transfer Protocol Vertion （超文本传输协议），说通俗点就是用网络链接传输文本信息的协议，我们现在所看的各类网页就是这个东东。</br>
      HTTPS的全称是Secure Hypertext Transfer Protocol（安全超文本传输协议），是在http协议基础上增加了使用SSL加密传送信息的协议。
</p>
<p>
      2：https协议需要到ca申请证书，一般免费证书很少，需要交费。
</p>
<p>
      3：http是超文本传输协议，信息是明文传输，https 则是具有安全性的ssl加密传输协议。
</p>
<p>
      4：http和https使用的是完全不同的连接方式用的端口也不一样，前者是80，后者是443。
</p>
<p>
      5：http的连接很简单，是无状态的。
</p>
<p>
      6：HTTPS协议是由SSL+HTTP协议构建的可进行加密传输、身份认证的网络协议，要比http协议安全。
</p>

      <h3>
      HTTPS解决的问题：
      </h3>
<p>
      1 . 信任主机的问题. 采用https 的server 必须从CA 申请一个用于证明服务器用途类型的证书. 改证书只有用于对应的server 的时候，客户度才信任次主机。
</p>
<p>
      2 . 通讯过程中的数据的泄密和被窜改
</p>
<p>
      1）一般意义上的https, 就是 server 有一个证书.
</p>
<p>
      a) 主要目的是保证server 就是他声称的server. 这个跟第一点一样.
</p>
<p>
      b) 服务端和客户端之间的所有通讯，都是加密的.
</p>
<p>
      i. 具体讲，是客户端产生一个对称的密钥，通过server 的证书来交换密钥，一般意义上的握手过程。
</p>
<p>
      ii. 加下来所有的信息往来就都是加密的，第三方即使截获，也没有任何意义，因为他没有密钥，当然窜改也就没有什么意义了。
</p>
<p>
      2）少许对客户端有要求的情况下，会要求客户端也必须有一个证书。
</p>
<p>
      a) 这里客户端证书，其实就类似表示个人信息的时候，除了用户名/密码， 还有一个CA 认证过的身份，个人证书一般来说上别人无法模拟的，所有这样能够更深的确认自己的身份。
</p>
<p>
      b) 目前少数个人银行的专业版是这种做法，具体证书可能是拿U盘作为一个备份的载体。
</p>
</body>
</html>
