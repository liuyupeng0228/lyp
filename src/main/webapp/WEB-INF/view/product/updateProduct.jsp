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
	<title>修改页面</title>
	<jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>

<div class="row">
	<div class="col-xs-12 col-md-12">
		<form class="form-horizontal" role="form" id="updateForm" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>修改商品</legend>
					<input type="hidden" name="id" value="${product.id}"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品名称</label>
					<div class="col-sm-4">
						<input class="form-control" name="productName" type="text" value="${product.productName}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品价格</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" name="productPrice" value="${product.productPrice}"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品品牌</label>
					<div class="col-sm-4">
						<select class="form-control" id="selectid">
							<option value="-1">==请选择==</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品主图</label>
					<div class="col-sm-4">
						<div class="input-group">
							<img width="50px" src="<%=request.getContextPath()%>${product.imgurl}">
							<input type="file" class="form-control" name="productImg"/>
							<input type="hidden" class="form-control" name="imgurl" value="${product.imgurl}"/>
						</div>
					</div>
				</div>
				<div class="form-group" id="imagesFile">
					<label class="col-sm-2 control-label">展示商品子图</label>
					<div class="col-sm-4">
						<div class="input-group">
							<c:forEach items="${imagesList}" var="images">
								<div style="float: left;margin: 5px" data-flag="image">
									<div>
										<img src="<%=request.getContextPath()%>${images.imagePath}" width="100px">
									</div>
									<div style="text-align: center">
										<input type="button" class="form-control" value="删除" onclick="deleteImages(this,'${images.id}')">
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group"  data-flag="childImages">
						<label class="col-sm-2 control-label">添加商品子图</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="file" class="form-control" name="productChildImages"/>
								<input type="button" class="form-control" value="+" onclick="addImgFile()">
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="clearfix form-actions" style="text-align:center">
				<button class="btn btn-primary" type="button" onclick="updateProduct();">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					修改
				</button>
				<button class="btn btn-default" type="reset">
					<i class="glyphicon glyphicon-refresh"></i>
					重置
				</button>
			</div>
			<input type="hidden" name="ids" id="ids">
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
    /* 下拉列表框 */
    $(function(){
        initBrandTable('${product.brand.id}');
    })

    //修改
    function updateProduct() {
        location.href = "updateProduct.jhtml";
    }

    /*对input框用this标记，传过来个obj表示被选中的input框*/
    function deleteImages(obj,id){
        $(obj).parents("div[data-flag='image']").remove();
        //记录要删除的数据
        $("#ids").val($("#ids").val()+","+id);
    }


    function addImgFile() {
        $("div[data-flag='childImages']").last().after('<div data-flag="childImages">\n' +
            '\t\t\t\t<td>商品子图添加：</td>\n' +
            '\t\t\t\t<td>\n' +
            '\t\t\t\t\t<input type="file" name="productChildImages"/>\n' +
            '\t\t\t\t\t<input type="button" value="-" onclick="deleteImgFile(this)">\n' +
            '\t\t\t\t</td>\n' +
            '\t\t\t<tr>');
    }

    function deleteImgFile(obj) {
        $(obj).parents("tr").remove();

    }
</script>
</html>