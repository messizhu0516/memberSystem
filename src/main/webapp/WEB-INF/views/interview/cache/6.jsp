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
		noeviction：当内存使用达到阈值的时候，所有引起申请内存的命令会报错。
</p>
 
<p>
·         allkeys-lru：在主键空间中，优先移除最近未使用的key。
</p>
 
<p>
·         volatile-lru：在设置了过期时间的键空间中，优先移除最近未使用的key。
</p>
 
<p>
·         allkeys-random：在主键空间中，随机移除某个key。
</p>
 
<p>
·         volatile-random：在设置了过期时间的键空间中，随机移除某个key。
</p>
 
<p>
·         volatile-ttl：在设置了过期时间的键空间中，具有更早过期时间的key优先移除。
	</p>
</body>
</html>
