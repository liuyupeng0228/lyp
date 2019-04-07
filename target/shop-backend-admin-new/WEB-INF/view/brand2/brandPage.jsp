<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty brandList}">
	<table width="90%" border="1px" cellpadding="0px" cellspacing="0px">
	<thead>
		<tr>
			<td>选择
				<input type="button" value="全选" onclick="ckeckAll(this);"/>
				<input type="button" value="反选" onclick="reverseCkeck(this);"/>
			</td>
			<td>ID号</td>
			<td>品牌名称</td>
			<td>录入时间</td>
			<td>修改时间</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${brandList}" var="brand">
			<tr>
				<td><input type="checkbox" name="ids" value="${brand.id}"/></td>
				<td>${brand.id}</td>
				<td data-id="${brand.id}" data-info="${brand.brandName}">${brand.brandName}</td>
				<td>
					<fmt:formatDate value="${brand.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${brand.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<input type="button" value="删除" onclick="deleteBrand('${brand.id}');"/>
					<input type="button" value="修改" onclick="editBrand('${brand.id}')"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</c:if>

<c:if test="${empty brandList}">
	<h1><font color="red">对不起，没有符合条件的数据！</font></h1><br>
	<input type="button" value="返回列表" onclick="location.href='toBrand.jhtml'"/>
</c:if>