<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>HashSet与HashMap区别</h3>
<p>
HashMap实现了Map接口 
HashSet实现了Set接口
</p>
<p>
HashMap储存键值对 
HashSet仅仅存储对象
</p>
<p>
HashMap中使用键对象来计算hashcode值 
HashSet使用成员对象来计算hashcode值
</p>
<p>
HashMap比较快，因为是使用唯一的键来获取对象 
HashSet较HashMap来说比较慢
</p>
<h3>HashTable与HashMap的区别</h3>
<p>
Hashtable方法是同步的 
HashMap方法是非同步的
</p>
<p>
Hashtable基于Dictionary类 
HashMap基于AbstractMap，而AbstractMap基于Map接口的实现
</p>
<p>
Hashtable中key和value都不允许为null，遇到null，直接返回 NullPointerException 
HashMap中key和value都允许为null，遇到key为null的时候，调用putForNullKey方法进行处理，而对value没有处理
</p>
<p>
Hashtable中hash数组默认大小是11，扩充方式是old*2+1 
HashMap中hash数组的默认大小是16，而且一定是2的指数
</p>
<h3>什么是ArrayList</h3>
<p>
ArrayList可以理解为动态数组，它的容量能动态增长，该容量是指用来存储列表元素的数组的大小，随着向ArrayList中不断添加元素，其容量也自动增长 
</p>
<p>
ArrayList允许包括null在内的所有元素 
</p>
<p>
ArrayList是List接口的非同步实现 
</p>
<p>
ArrayList是有序的
</p>
定义如下：</br>
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{}</br>
ArrayList实现了List接口、底层使用数组保存所有元素，其操作基 本上是对数组的操作</br>
ArrayList继承了AbstractList抽象类，它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能</br>
ArrayList实现了RandmoAccess接口，即提供了随机访问功能，RandmoAccess是java中用来被List实现，为List提供快速访问功能的，我们可以通过元素的序号快速获取元素对象，这就是快速随机访问</br>
ArrayList实现了Cloneable接口，即覆盖了函数clone()，能被克隆 </br>
ArrayList实现了java.io.Serializable接口，意味着ArrayList支持序列化</br>
<h3>什么是LinkedList</h3>
LinkedList基于链表的List接口的非同步实现 </br>
LinkedList允许包括null在内的所有元素 </br>
LinkedList是有序的 </br>
LinkedList是fail-fast的</br>
<h3>LinkedList与ArrayList的区别</h3>
<p>
	LinkedList底层是双向链表 
	ArrayList底层是可变数组
</p>
<p>
	LinkedList不允许随机访问，即查询效率低 
	ArrayList允许随机访问，即查询效率高
</p>
<p>
	LinkedList插入和删除效率快 
	ArrayList插入和删除效率低</br>
	解释一下：
	对于随机访问的两个方法，get和set，ArrayList优于LinkedList，因为LinkedList要移动指针 
	对于新增和删除两个方法，add和remove，LinedList比较占优势，因为ArrayList要移动数据
</p>
<h3>HashMap运行原理</h3>
<p>
1：HashMap是基于hashing的原理，我们使用put(key, value)存储对象到HashMap中，使用get(key)从HashMap中获取对象。
</p>
<p>
2：当我们给put()方法传递键和值时，我们先对键调用hashCode()方法，返回的hashCode用于找到bucket位置来储存Entry对象。
</p>
<p>
3：当hashcode相同，bucket位置相同，‘碰撞’会发生。因为HashMap使用链表存储对象，这个Entry(包含有键值对的Map.Entry对象)会存储在链表中。”
</p>
<p>
4：当我们调用get()方法，HashMap会使用键对象的hashcode找到bucket位置，然后获取值对象。
</p>
<p>
5：如果有两个值对象储存在同一个bucket，将会遍历bucket中的链表，调用keys.equals()方法去找到链表中正确的节点，最终找到要找的值对象。
</p>
<p>
6：如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？HashMap默认的负载因子大小为0.75，也就是说，当一个map填满了75%的bucket时候，和其它集合类(如ArrayList等)一样，
</p>
<p>
将会创建原来HashMap大小的两倍的bucket数组，来重新调整map的大小，并将原来的对象放入新的bucket数组中。这个过程叫作rehashing，因为它调用hash方法找到新的bucket位置。
</p>
<p>
7：多线程的情况下，rehashing可能产生条件竞争(race condition)。当重新调整HashMap大小的时候，确实存在条件竞争，因为如果两个线程都发现HashMap需要重新调整大小了，它们会同时试着调整大小。</br>
在调整大小的过程中，存储在链表中的元素的次序会反过来，因为移动到新的bucket位置的时候，HashMap并不会将元素放在链表的尾部，而是放在头部，这是为了避免尾部遍历(tail traversing)。如果条件竞争发生了，那么就死循环了。
</p>
<p>
8：所以多线程的环境下不建议使用HashMap。
</p>
<h3>什么是ConcurrentHashMap</h3>
ConcurrentHashMap基于双数组和链表的Map接口的同步实现 </br>
ConcurrentHashMap中元素的key是唯一的、value值可重复</br> 
ConcurrentHashMap不允许使用null值和null键 </br>
ConcurrentHashMap是无序的</br>
<h3>ConcurrentHashMap和Hashtable的区别</h3>
<p>
它们都可以用于多线程的环境，但是当Hashtable的大小增加到一定的时候，性能会急剧下降，因为迭代时需要被锁定很长的时间。</br>
因为ConcurrentHashMap引入了分割(segmentation)，不论它变得多么大，仅仅需要锁定map的某个部分，而其它的线程不需要等到迭代完成才能访问map。</br>
简而言之，在迭代的过程中，ConcurrentHashMap仅仅锁定map的某个部分，而Hashtable则会锁定整个map。
</p>
<h3>为什么使用ConcurrentHashMap</h3>
	我们都知道HashMap是非线程安全的，当我们只有一个线程在使用HashMap的时候，自然不会有问题，但如果涉及到多个线程，并且有读有写的过程中，HashMap就会fail-fast。</br>
	要解决HashMap同步的问题，我们的解决方案有Hashtable，Collections.synchronizedMap(hashMap)
<p>
	这两种方式基本都是对整个hash表结构加上同步锁，这样在锁表的期间，别的线程就需要等待了，无疑性能不高，所以我们引入ConcurrentHashMap，既能同步又能多线程访问</br>
	ConcurrentHashMap的数据结构为一个Segment数组，Segment的数据结构为HashEntry的数组，而HashEntry存的是我们的键值对，可以构成链表。可以简单的理解为数组里装的是HashMap</br>
	从上面的结构我们可以了解到，ConcurrentHashMap定位一个元素的过程需要进行两次Hash操作，第一次Hash定位到Segment，第二次Hash定位到元素所在的链表的头部，</br>
	因此，这一种结构的带来的副作用是Hash的过程要比普通的HashMap要长，但是带来的好处是写操作的时候可以只对元素所在的Segment进行加锁即可，不会影响到其他的Segment。</br>
	正是因为其内部的结构以及机制，ConcurrentHashMap在并发访问的性能上要比Hashtable和同步包装之后的HashMap的性能提高很多。</br>
	在理想状态下，ConcurrentHashMap 可以支持 16 个线程执行并发写操作（如果并发级别设置为 16），及任意数量线程的读操作
</p>
</body>
</html>
