<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
	<p>1：子类继承父类的方法重写时，子类抛出异常不能多于父类抛出的异常。子类抛出的异常需要更具体。也就是父类同名方法子异常。</p>
	<p>
		2：常见的RuntimeExpection有：</br> 
		NullPointerException - 空指针引用异常 </br>
		ClassCastException - 类型强制转换异常。 </br>
		IllegalArgumentException - 传递非法参数异常。 </br>
		ArithmeticException - 算术运算异常 </br>
		ArrayStoreException - 向数组中存放与声明类型不兼容对象异常 </br>
		IndexOutOfBoundsException - 下标越界异常 </br>
		NegativeArraySizeException - 创建一个大小为负数的数组错误异常 </br>
		NumberFormatException - 数字格式异常 </br>
		SecurityException - 安全异常 </br>
		UnsupportedOperationException - 不支持的操作异常</br>
	</p>
	<p>
		3：新版本处理捕获多个异常; </br>
		try() { </br>
		</br>
		} catch (XXExpection | XXXXExpection e) { </br>
		</br>
		} 
	</p>
</body>
</html>
