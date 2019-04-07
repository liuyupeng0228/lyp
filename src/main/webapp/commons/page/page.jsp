<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
td {
      text-align:center; /*设置水平居中*/
      vertical-align:middle;/*设置垂直居中*/
}
</style>
</head>
<body>

<table border="1px" cellpadding="0px" cellspacing="0px" width="600px">
	<tr>
		<c:if test="${page.pageNow !=1 }">
			<td>
				<a href="javascript:;" onclick="toPageList(1)">首页 </a>
			</td>
			<td>		
				<a href="javascript:;" onclick="toPageList(${page.pageNow-1})">上一页  </a>
			</td>
		</c:if>
		<c:if test="${page.pageNow ==1 && page.pageNum !=0}">
			<td>首页</td>
			<td>上一页</td> 
		</c:if>		
			
			<c:if test="${page.pageNow !=page.pageNum && page.pageNum !=0}">
				<td>
					<a href="javascript:;" onclick="toPageList(${page.pageNow+1})">下一页 </a>
				</td>
				<td>
					<a href="javascript:;" onclick="toPageList(${page.pageNum})">尾页 </a>
				</td>
			</c:if>
			<c:if test="${page.pageNow == page.pageNum}">
				<td>下一页</td>
				<td>尾页</td>
			</c:if>
			<td>
				 当前第${page.pageNow}页 
			</td>
			<td>		
				 共${page.totalCount}条
			</td>
			<td>		 
				 共${page.pageNum}页
			</td>
	 </tr> 
</table>		
</body>
</html>