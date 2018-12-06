<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一：TCP与UDP区别总结：</h3>
<p>
1、TCP面向连接（如打电话要先拨号建立连接）;UDP是无连接的，即发送数据之前不需要建立连接
</p>
<p>
2、TCP提供可靠的服务。也就是说，通过TCP连接传送的数据，无差错，不丢失，不重复，且按序到达;UDP尽最大努力交付，即不保证可靠交付</br>
Tcp通过校验和，重传控制，序号标识，滑动窗口、确认应答实现可靠传输。如丢包时的重发控制，还可以对次序乱掉的分包进行顺序控制。
</p>
<p>
3、UDP具有较好的实时性，工作效率比TCP高，适用于对高速传输和实时性有较高的通信或广播通信。
</p>
<p>
4.每一条TCP连接只能是点到点的;UDP支持一对一，一对多，多对一和多对多的交互通信
</p>
<p>
5、TCP对系统资源要求较多，UDP对系统资源要求较少。
</p>

<h3>二：为什么UDP有时比TCP更有优势</h3>
<p>
UDP以其简单、传输快的优势，在越来越多场景下取代了TCP,如实时游戏。
</p>
<p>
（1）网速的提升给UDP的稳定性提供可靠网络保障，丢包率很低，如果使用应用层重传，能够确保传输的可靠性。
</p>
<p>
（2）TCP为了实现网络通信的可靠性，使用了复杂的拥塞控制算法，建立了繁琐的握手过程，由于TCP内置的系统协议栈中，极难对其进行改进。</br>
采用TCP，一旦发生丢包，TCP会将后续的包缓存起来，等前面的包重传并接收到后再继续发送，延时会越来越大，基于UDP对实时性要求较为严格的情况下，采用自定义重传机制，</br>
能够把丢包产生的延迟降到最低，尽量减少网络问题对游戏性造成影响。
</p>
</body>
</html>
