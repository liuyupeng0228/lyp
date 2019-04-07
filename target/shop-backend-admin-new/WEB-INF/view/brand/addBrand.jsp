<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加页面</title>
	<jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<div class="row">
	<div class="col-xs-12 col-md-12">
		<form class="form-horizontal" role="form" method="post" id="productForm" enctype="multipart/form-data">
			<fieldset>
				<legend>添加品牌</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">品牌名称</label>
					<div class="col-sm-4">
						<input class="form-control" name="brandName" type="text"/>
					</div>
				</div>
			</fieldset>
			<div class="clearfix form-actions" style="text-align:center">
				<button class="btn btn-primary" type="button" onclick="addBrand();">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					添加
				</button>
				<button class="btn btn-default" type="reset">
					<i class="glyphicon glyphicon-refresh"></i>
					重置
				</button>
			</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
    //添加商品
    function addBrand() {
        location.href="addBrand.jhtml";
    }
</script>
</html>