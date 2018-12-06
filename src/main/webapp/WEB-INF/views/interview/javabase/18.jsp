<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一、java虚拟机与程序的生命周期 </h3>
在如下几种情况之下，java虚拟机将结束生命周期： 
<p>
1、执行了System.exit()方法 
</p>
<p>
2、程序正常执行结束 
</p>
<p>
3、程序在执行过程中遇到了异常或者错误而异常终止 
</p>
<p>
4、由于操作系统用出现错误而导致java虚拟机进程终止 
</p>
<h3>二、类的加载，链接，初始化 </h3>
<p>
1、加载：查找并加载类的二进制数据 
</p>
<p>
2、连接： </br>
（1）验证：确保被加载的类的正确性（验证字节码）  </br>
（2）准备：为类的静态变量分配内存，并将其初始化为默认值  </br>
（3）解析：把类中的符号引用转换为直接引用  </br>
</p>
<p>
3、初始化：为类的静态成员变量赋予正确的初始值变量，那么在编译时会报错</p>
</p>
</body>
</html>
