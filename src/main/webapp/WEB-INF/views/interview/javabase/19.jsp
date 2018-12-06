<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一、类加载器</h3> 
<p>
1、父亲委托机制(Parent Delegation) </br>
类加载器用来把类加载到Java虚拟机中。从JDK1.2版本开始，类的加载过程采用父亲委托机制，这种机制能够更好的保证java平台的安全，在此委托机制中，除了Java虚拟机自带的根类加载器外，其余的类加载器都有且只有一个父类加载器。</br>
当java程序请求加载器loader加载Sample类时，loader首先委托自己的父加载器去加载Sample类，若加载器能加载，则由父加载器完成加载任务，则由父加载器完成加载任务，否则，才有加载器loader本身加载Sample类。 </br>
父加载器不是继承关系。也就是说子加载器不一定是继承了父加载器。 
</p>
<p>
2、java虚拟机自带的几种加载器 </br>
（1） 根(Bootstrap)类加载器：该类加载器没有父加载器，他负责加载虚拟机的核心类库，如java.lang.*等。根类加载器从系统属性sun.boot.class.path所指定的目录中加载类库。</br>
根类加载器的实现依赖于底层操作系统，属于虚拟机的实现的一部分，他并没有继承java.lang.ClassLoader类。 </br>
（2）扩展（Extension）类加载器：它的父类加载器为根类加载器。他从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录的jre\lib\ext子目录（扩展目录）下加载类库，</br>
如果把用户创建的JAR文件放在这个目录下，也会自动有扩展类加载器加载。扩展类加载器是纯java类，是java.lang.ClassLoader类的子类。 </br>
（3） 系统(System)类加载器：也称为应用加载器，他的父类加载器为扩展类加载器。他从环境变量classpath或者系统属性java.class.path所指定的目录中加载类。他是用户自定义的类加载器的默认父加载器。</br>
系统类加载器是纯java类，是java.lang.ClassLoader子类。
</p>
<h3>二、类的卸载 </h3>
<p>
当Sample类被加载、连接和初始化后，他的生命周期就开始了。当代表Sample类的Class对象不再被引用，即不可触及时，Class对象就会结束生命周期，当Samle类在方法区内的数据也会被卸载，从而结束Sample类的生命周期。</br>
由此可见，一个类何时结束生命周期，取决于代表它的Class对象何时结束生命周期。 由Java虚拟机自带的类加载器所加载的类，自虚拟机的生命周期中，始终不会被卸载。</br>
Java虚拟机自带的类加载器包括根类加载器，扩展类加载器和系统类加载器。Java虚拟机本身会始终引用这些类加载器，而这些类加载器则会使用他们所加载的类的Class对象，因此这些Class对象始终是可触及的。 </br>
由用户自定义的类加载器所加载的类是可以被卸载的。 </br>
一个类的实例总是引用代表着这个类的Class对象。在Object中定义了getClass()方法，这个方法返回代表所属泪的Class对象的引用。此外，所有的Java对象都有一个静态属性class，他引用代表这个类的Class对象。
</p>
</body>
</html>
