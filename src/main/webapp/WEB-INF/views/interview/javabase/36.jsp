<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h4>一、Java面相对象的思想的理解</h4>
	<p>1：类具有三个基本特征：封装、继承、多态。</p>
	<p>2：对于多态，可以总结以下几点：</p>
	<p>一、使用父类类型的引用指向子类的对象； </p>
	<p>二、该引用只能调用父类中定义的方法和变量； </p>
	<p>三、如果子类中重写了父类中的一个方法，那么在调用这个方法的时候，将会调用子类中的这个方法；（动态连接、动态调用） </p>
	<p>四、变量不能被重写（覆盖），”重写“的概念只针对方法，如果在子类中”重写“了父类中的变量，那么在编译时会报错</p>
</body>
</html>
