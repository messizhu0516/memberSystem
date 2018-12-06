<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>一：事务的特性</h3>
<p>
⑴ 原子性（Atomicity）</br>
⑵ 一致性（Consistency）</br>
⑶ 隔离性（Isolation）</br>
⑷ 持久性（Durability）
</p>
<h3>二：现在来看看MySQL数据库为我们提供的四种隔离级别</h3>
　<p>

　　① Serializable (串行化)：可避免脏读、不可重复读、幻读的发生。</br>

　　② Repeatable read (可重复读)：可避免脏读、不可重复读的发生。</br>

　　③ Read committed (读已提交)：可避免脏读的发生。</br>

　　④ Read uncommitted (读未提交)：最低级别，任何情况都无法保证。
</p>
</body>
</html>
