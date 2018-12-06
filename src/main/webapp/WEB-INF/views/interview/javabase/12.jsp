<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
<style type="text/css">
	.sj20 {padding-left:20px;}
	.sj40 {padding-left:40px;}
	.sj80 {padding-left:80px;}
	.gh1 {margin-bottom:20px;}
	.java {color:#de1c1c;}
	.zjsj20 {padding-left:40px;color:#34b90b;}
	.hh {word-break:break-all;word-wrap:break-word;}
</style>
</head>
<body>
	<p>在java中，其实java中实现 多线程有三种方法，一种是继承Thread类；第二种是实现Runnable接口；第三种是实现Callable接口。</p>
	<p>
		那么为啥非要使用start();方法启动多线程呢？</br>
       	 源码找到Thread中的start()方法的定义，可以发现此方法中使用了private native void start0();其中native关键字表示可以调用操作系统的底层函数，那么这样的技术成为JNI技术（java Native Interface）
	</p>
	<h3>继承和实现方式的区别和联系：</h3>
    1,避免点继承的局限，一个类可以继承多个接口。 
	<p>
    2,适合于资源的共享
	</p>
	<p>
    3,public class Thread extends Object implements Runnable，发现Thread类也是Runnable接口的子类。
	</p>
	<h3>Callable 和 Runnable 的使用方法大同小异， 区别在于： </h3>
	<p>
                1.Callable 使用 call（） 方法， Runnable 使用 run() 方法 
	</p>
	<p>
                2.call() 可以返回值， 而 run()方法不能返回。 
	</p>
	<p>
                3.call() 可以抛出受检查的异常，比如ClassNotFoundException， 而run()不能抛出受检查的异常。
	</p>
	<p>
        Callable示例如下：
	</p>
	<p>
		Java代码 </br>
		class TaskWithResult implements Callable<c:out value="<String>" escapeXml="true" /> {  </br>
		   <span class="sj20"/> private int id;  </br>
		
		    <span class="sj20"/>public TaskWithResult(int id) {  </br>
		        <span class="sj40"/>this.id = id;  </br>
		   <span class="sj20"/> }  
		
		    <span class="sj20"/>@Override  </br>
		    <span class="sj20"/>public String call() throws Exception {  </br>
		        <span class="sj40"/>return "result of TaskWithResult " + id;  </br>
		    <span class="sj20"/>}  </br>
		}  
	</p>
	<p>
		public class CallableTest {  </br>
		    <span class="sj20"/>public static void main(String[] args) throws InterruptedException,  </br>
		        <span class="sj40"/>ExecutionException {  </br>
		        <span class="sj40"/>ExecutorService exec = Executors.newCachedThreadPool();  </br>
		         <span class="zjsj20">//Future 相当于是用来存放Executor执行的结果的一种容器  </span></br>
		        <span class="sj40"/>ArrayList<c:out value="<Future<String>" escapeXml="true" /> results = new ArrayList<c:out value="<Future<String>" escapeXml="true" />(); </br>  
		        <span class="sj40"/>for (int i = 0; i < 10; i++) {  </br>
		            <span class="sj80"/>results.add(exec.submit(new TaskWithResult(i)));  </br>
		        <span class="sj40"/>}</br>
		        <span class="sj40"/>for (Future<c:out value="<String>" escapeXml="true" /> fs : results) {  </br>
		            <span class="sj80"/>if (fs.isDone()) {  </br>
		                <span class="sj80"/>System.out.println(fs.get());  </br>
		            <span class="sj80"/>} else {  </br>
		                <span class="sj80"/>System.out.println("Future result is not yet complete");  </br>
		            <span class="sj80"/>}  </br>
		        <span class="sj40"/>}  </br>
		        <span class="sj40"/>exec.shutdown();</br>
		    <span class="sj20"/>}  </br>
		}  </br> 
	</p>
</body>
</html>
