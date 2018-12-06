<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一：浅克隆</h3>
<p>
浅度克隆对于要克隆的对象，对于其基本数据类型的属性，复制一份给新产生的对象，对于非基本数据类型的属性，仅仅复制一份引用给新产生的对象，即新产生的对象和原始对象中的非基本数据类型的属性都指向的是同一个对象。</br>
浅克隆的步骤：</br>
1.实现java.lang.Cloneable接口</br>

   (1):实现Cloneable接口，不包含任何方法！仅仅是用来指示Object类中clone()方法可以用来合法的进行克隆。</br>

   (2)：如果在没有实现Cloneable接口的实例上调用Object的clone()方法，则会导致抛出CloneNotSupporteddException</br>

   (3):  实现此接口的类，应该使用public，重写Object的clone方法。Object类中的clone()是一个protected属性的方法，重写之后要把clone()方法的属性设置为public。</br>
   因为在Object类中的clone()方法是一个native方法，native方法的效率一般来说都是远高于java中的非native方法。</br>
   这也解释了为什么要用Object中clone()方法而不是先new一个类，然后把原始对象中的信息赋到新对象中，虽然这也实现了clone功能。
</p>
<h3>二：深克隆</h3>
<p>
 深克隆简单的说就是：除了克隆自身对象，还对其他非基本数据类型的引用的其他以外的所有对象，都克隆了一遍。</br>

深克隆的步骤：</br>

1.首先克隆的类，也必须实现Cloneable接口和重写Object的clone()的方法。</br>

2.在不引入第三方jar包的情况下，可以使用两种办法：</br>

(1)、先对对象进行序列化，紧接着马上反序列化出。</br>

(2)、先调用super.clone()方法克隆出一个新对象来，然后在子类的clone()方法中手动给克隆出来的非基本数据类型（引用类型）赋值，比如ArrayList的clone()方法：</br>
</p>
<h3>二：第三方jar包</h3>
<p>
对于克隆，java还提供了一些好用的第三方jar包，比如：</br>

（Apache BeanUtils、PropertyUtils,Spring BeanUtils,Cglib BeanCopier）都是相当于克隆中的浅克隆。</br>

1)spring包和Apache中的 BeanUtils     采用反射实现</br>
    Spring： void copyProperties(Object source, Object target,String[] ignoreProperties)</br>
    Apache：void  copyProperties(Object dest, Object orig)</br>
2)cglib包中的  Beancopier   采用动态字节码实现</br>
   cglib: BeanCopier create(Class source, Class target,boolean useConverter)</br>
   例如：</br>
          BeanCopier copier =BeanCopier.create(stuSource.getClass(), stuTarget.getClass(), false);</br>
          copier.copy(stuSource, stuTarget, null);</br>
 
以下是简单的性能分析：</br>
cglib   BeanCopier   15ms</br>
 
Spring  BeanUtil      4031ms</br>
 
apache BeanUtils      18514ms.</br>
</p>
</body>
</html>
