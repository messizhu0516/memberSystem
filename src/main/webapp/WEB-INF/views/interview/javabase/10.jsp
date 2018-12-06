<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一、线程的四种状态</h3>
<p>
新建（new）：处于该状态的时间很短暂。已被分配了必须的系统资源，并执行了初始化。表示有资格获得CPU时间。调度器可以把该线程变为runnable或者blocked状态
</p>
<p>
就绪（Runnable）：这种状态下只要调度器把时间片分配给线程，线程就能运行。处在这种状态就是可运行可不运行的状态
</p>
<p>
阻塞（Bolocked）：线程能够运行，但有个条件阻止它的运行。当线程处于阻塞状态时，调度器将会忽略线程，不会分配给线程任何CPU时间（例如sleep）。只有重新进入了就绪状态，才有可能执行操作。
</p>
<p>
死亡（Dead）：处于死亡状态的线程讲不再是可调度的，并且再也不会得到CPU时间。任务死亡的通常方式是从run()方法返回。
</p>
一个任务进入阻塞状态，可能有如下原因： 
<p>
1.sleep 
</p>
<p>
2.wait()，知道线程得到了notify()或者notifyAll()消息，线程才会进入就绪状态。 
</p>
<p>
3.任务在等待某个输入/输出完成 
</p>
<p>
4.线程在试图在某个对象上调用其同步控制方法，但是对象锁不可用，因为另一个任务已经获取了这个锁。
</p>
<h3>二、wait和sleep的区别</h3>
<p>
1.wait和notify方法定义在Object类中，因此会被所有的类所继承。 这些方法都是final的，即它们都是不能被重写的，不能通过子类覆写去改变它们的行为。 而sleep方法是在Thread类中是由native修饰的，本地方法。</br>
    public static native void sleep(long l) throws InterruptedException;
</p>
<p>
2.当线程调用了wait()方法时，它会释放掉对象的锁。 </br>
另一个会导致线程暂停的方法：Thread.sleep()，它会导致线程睡眠指定的毫秒数，但线程在睡眠的过程中是不会释放掉对象的锁的。
</p>
<p>
3.正因为wait方法会释放锁，所以调用该方法时，当前的线程必须拥有当前对象的monitor，也即lock，就是锁。要确保调用wait()方法的时候拥有锁，即，wait()方法的调用必须放在synchronized方法或synchronized块中。</br>
顺便说说notify()，notify()方法会唤醒一个等待当前对象的锁的线程。 如果多个线程在等待，它们中的一个将会选择被唤醒。这种选择是随意的，和具体实现有关。（线程等待一个对象的锁是由于调用了wait方法中的一个）。</br>
被唤醒的线程是不能被执行的，需要等到当前线程放弃这个对象的锁。 </br>
被唤醒的线程将和其他线程以通常的方式进行竞争，来获得对象的锁。也就是说，被唤醒的线程并没有什么优先权，也没有什么劣势，对象的下一个线程还是需要通过一般性的竞争。 </br>
且notify方法和wait一样，是需要放在synchronized方法或synchronized块中。
</p>
</body>
</html>
