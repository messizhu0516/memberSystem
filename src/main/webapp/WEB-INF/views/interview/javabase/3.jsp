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
		<h3>强引用：</h3>
		只要引用存在，垃圾回收器永远不会回收</br>
		Object obj = new Object();</br>
		//可直接通过obj取得对应的对象 如obj.equels(new Object());</br>
		而这样 obj对象对后面new Object的一个强引用，只有当obj这个引用被释放之后，对象才会被释放掉，这也是我们经常所用到的编码形式。
	</p>	
	<p>
		<h3>软引用：</h3>
		非必须引用，内存溢出之前进行回收，可以通过以下代码实现</br>
		<c:out value="Object obj = new Object();
		SoftReference<Object> sf = new SoftReference<Object>(obj);
		obj = null;
		sf.get();//有时候会返回null" escapeXml="true" /></br>
		这时候sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当然，当这个对象被标记为需要回收的对象时，则返回null；</br>
		软引用主要用户实现类似缓存的功能，在内存足够的情况下直接通过软引用取值，无需从繁忙的真实来源查询数据，提升速度；当内存不足时，自动删除这部分缓存数据，从真正的来源查询这些数据。
	</p>	
	<p>	 
		<h3>弱引用：</h3>
		第二次垃圾回收时回收，可以通过如下代码实现</br>
		<c:out value="Object obj = new Object();
		WeakReference<Object> wf = new WeakReference<Object>(obj);
		obj = null;
		wf.get();//有时候会返回null
		wf.isEnQueued();//返回是否被垃圾回收器标记为即将回收的垃圾" escapeXml="true" /></br>
		弱引用是在第二次垃圾回收时回收，短时间内通过弱引用取对应的数据，可以取到，当执行过第二次垃圾回收时，将返回null。</br>
		弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
	</p>	
	<p>	 
		<h3>虚引用：</h3>
		垃圾回收时回收，无法通过引用取到对象值，可以通过如下代码实现</br>
		<c:out value="Object obj = new Object();
			PhantomReference<Object> pf = new PhantomReference<Object>(obj);
			obj=null;
			pf.get();//永远返回null
			pf.isEnQueued();//返回是否从内存中已经删除" escapeXml="true" /></br>
		虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引用。</br>
		虚引用主要用于检测对象是否已经从内存中删除。
	</p>
</body>
</html>
