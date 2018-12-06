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
(1) 在编译阶段就能够确定的字符串常量，完全没有必要创建String或StringBuffer对象。直接使用字符串常量的”+”连接操作效率最高。 
</p>
<p>
(2) StringBuffer对象的append效率要高于String对象的”+”连接操作。 
</p>
<p>
(3) 不停的创建对象是程序低效的一个重要原因。那么相同的字符串值能否在堆中只创建一个String对象那。</br>
显然拘留字符串能够做到这一点，除了程序中的字符串常量会被JVM自动创建拘留字符串之外，调用String的intern()方法也能做到这一点。</br>
当调用intern()时，如果常量池中已经有了当前String的值，那么返回这个常量指向拘留对象的地址。如果没有，则将String值加入常量池中，并创建一个新的拘留字符串对象。
</p>
</body>
</html>
