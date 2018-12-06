<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<p>1、过滤器是基于函数回调，而拦截器是基于java的反射机制；</p>

<p>2、过滤器是servlet规范规定的，只能用于web程序中，而拦截器是在spring容器中，它不依赖servlet容器</p>

<p>3、过滤器可以拦截几乎所有的请求(包含对静态资源的请求)，而拦截器只拦截action请求(不拦截静态资源请求)</p>

<p>4、过滤器不能访问action上下文及值栈里的对象，而拦截器都是可以的。</p>

<p>5、拦截器可以获取spring容器里的对象，而过滤器是不行的</p>

<p>6、拦截器在action的生命周期内是可以多次调用，而过滤器只在容器初始化时被调用一次。</p>

<p>7、拦截器是被包裹在过滤器之中。</p>
</body>
</html>
