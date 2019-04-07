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
				<legend>添加商品</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品名称</label>
					<div class="col-sm-4">
						<input class="form-control" name="productName" type="text"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品价格</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" name="productPrice"/>
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
							<input id ="productImg" class="form-control" name ="productImg" type ="file"  multiple>
						</div>
					</div>
				</div>
				<div class="form-group" data-flag="childImages">
					<label class="col-sm-2 control-label">商品子图</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="file" class="form-control" name="productImages"/>
							<input type="button" class="form-control" value="+" onclick="addImgFile()">
						</div>
					</div>
				</div>
			</fieldset>
			<div class="clearfix form-actions" style="text-align:center">
				<button class="btn btn-primary" type="button" onclick="addProduct();">
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
    /* 下拉列表框 */
    $(function(){
        initBrandTable();
    })

	//添加商品
	function addProduct() {
		location.href="addProduct.jhtml";
    }

    /*法二*/
    function addImgFile() {
        $("tr[data-flag='childImages']").last().after("<div class=\"form-group\" data-flag=\"childImages\">\n" +
            "\t\t\t\t\t<label class=\"col-sm-2 control-label\">商品子图</label>\n" +
            "\t\t\t\t\t<div class=\"col-sm-4\">\n" +
            "\t\t\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t\t\t<input type=\"file\" class=\"form-control\" name=\"productImages\"/>\n" +
            "\t\t\t\t\t\t\t<input type=\"button\" class=\"form-control\" value=\"-\" onclick=\"deleteImgFile()\">\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>");
    }

    function deleteImgFile(obj) {
        $(obj).closest("div").remove();
    }


	$("#productImg").fileinput({
		uploadUrl:"addProduct",
		uploadAsync : true, //默认异步上传
		showUpload : true, //是否显示上传按钮,跟随文本框的那个
		showRemove : false, //显示移除按钮,跟随文本框的那个
		showCaption : false,//是否显示标题,就是那个文本框
		showPreview : true, //是否显示预览,不写默认为true
		dropZoneEnabled : false,
		maxFileCount : 3,
		enctype : 'multipart/form-data',
		msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		language : 'zh',
		layoutTemplates: {
			actionUpload: '',   //取消上传按钮
			actionZoom: ''      //取消放大镜按钮
		}

	}).on("fileuploaded",function(event, data, previewId, index){
		if(data != null){
			alert(data.response.url);
		}
	});

</script>
</html>