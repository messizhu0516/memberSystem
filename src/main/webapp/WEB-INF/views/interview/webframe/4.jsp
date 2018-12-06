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
	<p>
		一、使用父类类型的引用指向子类的对象</br>
		二、该引用只能调用父类中定义的方法和变量</br>
		三、如果子类中重写了父类中的一个方法，那么在调用这个方法的时候，将会调用子类中的这个方法；（动态连接、动态调用）</br>
		四、变量不能被重写（覆盖），”重写“的概念只针对方法，如果在子类中”重写“了父类中的变量，那么在编译时会报错
	</p>
	<p>
		1、抽象类： </br>
		（1）、抽象类不可以被实例化； </br>
		（2）、抽象类中可以没有抽象方法；可以拥有非抽象方法或者属性； </br>
		（3）、接口与抽象类中的抽象方法不能具体实现； </br>
		（4）、抽象类可以有构造函数； </br>
		（5）、抽象类中的成员变量可以被不同的修饰符来修饰； 
	</p>
	<p>
		2、接口： </br>
		（1）、接口中不能有普通数据成员，只能够有静态的不能被修改的数据成员,final表示全局，static 表示不可修改，可以不用static final 修饰，会隐式的声明为static和final ； </br>
		（2）、接口中的方法一定是抽象方法，所以不用abstract修饰； </br>
		（3）、接口不能且它里面的方法只是一个声明必须用public来修饰没有具体实现的方法。
	</p>
</body>
</html>
