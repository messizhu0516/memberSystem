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
生产者消费者问题是研究多线程程序时绕不开的经典问题之一，它描述是有一块缓冲区作为仓库，生产者可以将产品放入仓库，消费者则可以从仓库中取走产品。解决生产者/消费者问题的方法可分为两类： </br>
（1）采用某种机制保护生产者和消费者之间的同步； </br>
（2）在生产者和消费者之间建立一个管道。 
</p>
<p>
第一种方式有较高的效率，并且易于实现，代码的可控制性较好，属于常用的模式。第二种管道缓冲区不易控制，被传输数据对象不易于封装等，实用性不强。因此本文只介绍同步机制实现的生产者/消费者问题。 </br>
同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。Java语言在多线程编程上实现了完全对象化，提供了对同步机制的良好支持。</br>
在Java中一共有四种方法支持同步，其中前三个是同步方法，一个是管道方法。 </br>
（1）wait() / notify()方法（notifyAll()） </br>
（2）await() / signal()方法（signalAll()） </br>
（3）BlockingQueue阻塞队列方法 </br>
（4）PipedInputStream / PipedOutputStream 
</p>
</body>
</html>
