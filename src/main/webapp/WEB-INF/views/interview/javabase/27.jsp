<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一、 同步方法 </h3>
<h3>二、同步代码块 </h3>
<h3>三、使用特殊域变量修饰符volatile实现线程同步 </h3>
<p>
（1）、volatile关键字为域变量的访问提供了一种免锁机制 </br>
（2）、使用volatile修饰域变量相当于告诉虚拟机可能会被其他线程更新，因此每次使用该域变量都要同步到内存，从内存中读取，而不是直接使用寄存器中的值。 </br>
（3）、volatile不会提供原子操作，他不能用来修饰final类型的变量。 </br>
（4）、volatile只对域变量起作用，并不能保证线程安全。
</p>
<h3>四、使用重入锁实现线程同步 </h3>
<h3>五、使用ThreadLocal管理局部变量实现线程同步</h3>
<p>
ThreadLocal管理变量，则每一个使用该变量的线程都获得一个该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的副本，而不会对其他线程产生影响。 </br>
ThreadLocal类常用的方法： </br>
1、get()：返回该线程局部变量的当前线程副本中的值。 </br>
2、initialValue()：返回此线程局部变量的当前线程的”初始值“。 </br>
3、remove()：移除此线程局部变量当前线程的值。 </br>
4、set(T value)：将此线程局部便利啊ing的当前线程副本中的值设置为指定值value。
</p>
<p>避免死锁：</p>
<p>
加锁顺序（线程按照一定的顺序加锁）</br>
加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）</br>
死锁检测
</p>
</body>
</html>
