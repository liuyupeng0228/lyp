<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>/commons/jquery-1.7.2.js"></script>
<title>跳转查询页面</title>
</head>
<body>
<center>
	<input type="button" value="查询商品" onclick="location.href='toProductList.jhtml'"/>
	<input type="button" value="查询品牌" onclick="location.href='queryBrand.jhtml'"/>
	<input type="button" value="查询品牌2" onclick="location.href='toBrand.jhtml'"/>
	<input type="button" value="日志查询" onclick="location.href='queryLog.jhtml'"/>
	<input type="button" value="用户界面" onclick="location.href='queryUserList.jhtml'"/>
</center>
</body>
</html>