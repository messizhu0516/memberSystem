<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一、Comparable接口</h3>
<p>
1.什么是Comparable接口</br>

此接口强行对实现它的每个类的对象进行整体排序。此排序被称为该类的自然排序 ，类的 compareTo方法被称为它的自然比较方法 。</br>
实现此接口的对象列表（和数组）可以通过 Collections.sort（和 Arrays.sort ）进行自动排序。实现此接口的对象可以用作有序映射表中的键或有序集合中的元素，无需指定比较器。 
</p>
<p>
2.实现什么方法</br>

int compareTo(T o)</br>

比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。</br>

参数： o - 要比较的对象。</br>

返回：负整数、零或正整数，根据此对象是小于、等于还是大于指定对象。</br>

抛出：ClassCastException - 如果指定对象的类型不允许它与此对象进行比较。</br>
</p>

<h3>二、Comparator接口</h3>
<p>
与上面的Comparable接口不同的是：</br>

①、Comparator位于包java.util下，而Comparable位于包java.lang下。</br>

②、Comparable接口将比较代码嵌入需要进行比较的类的自身代码中，而Comparator接口在一个独立的类中实现比较。</br>

③、如果前期类的设计没有考虑到类的Compare问题而没有实现Comparable接口，后期可以通过Comparator接口来实现比较算法进行排序，并且为了使用不同的排序标准做准备，比如：升序、降序。</br>

④、Comparable接口强制进行自然排序，而Comparator接口不强制进行自然排序，可以指定排序顺序。</br>
</p>
</body>
</html>
