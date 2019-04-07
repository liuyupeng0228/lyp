<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌展示页面</title>
</head>
<body>
<input type="button" value="返回主页面" onclick="javascript:; location.href='toProductList.jhtml'"/>
<center>
	<form>
		品牌名称：<input type="text" id="brandName"/><br>
		录入时间：<input type="text" id="minCreateTime" onclick="WdatePicker()"/>--<input type="text" id="maxCreateTime" onclick="WdatePicker()"/><br>
		修改时间：<input type="text" id="minUpdateTime" onclick="WdatePicker()"/>--<input type="text" id="maxUpdateTime" onclick="WdatePicker()"/><br><br>
		<input type="button" value="查询" onclick="search(1);"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置数据"/>
	</form><br><br>

	<input type="button" value="批量删除" onclick="deleteBatchBrand();"/><br><br>
</center>
<center>
<div id="brandTable">
	<jsp:include page="/WEB-INF/view/brand2/brandPage.jsp"></jsp:include>
</div>
<input type="text" id="add_brandName"/><input type="button" value="增加" onclick="addBrand2();"/>
</center>

<script type="text/javascript" src="<%=basePath%>/commons/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/commons/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	
	function addBrand2(){
		var v_brandName = $("#add_brandName").val();
		$.ajax({
			url:"<%=basePath%>/addBrand2.jhtml",
			type:"post",
			data:{
				"brandName":v_brandName
			},
			success:function(res){
				if (res.code == 200) {
					search();
				}
			}
		})
	}
	
	function editBrand(id){
		//获取自定义属性data-id为id的td中的自定义属性为data-info的值
		var v_brandName = $("td[data-id='"+id+"']").attr("data-info");
		// \"用于转义，下列代码是转义为字符串
		$("td[data-id='"+id+"']").html("<input type='text' value='"+v_brandName+"'/> <input type='button' value='取消' onclick='resetInfo(\""+id+"\",\""+v_brandName+"\");'/> <input type='button' value='保存' onclick='updateToBrand(\""+id+"\");'/>");
	}
	
	function resetInfo(id,v_brandName){
		//点击取消时，有id找到对应的td，并将v_brandName赋给td
		$("td[data-id='"+id+"']").html(v_brandName);
	}
	
	function updateToBrand(id){
		var v_brandName = $("td[data-id='"+id+"']").find("input[type=text]").val();
		$.ajax({
			url:"<%=basePath%>/updateToBrand.jhtml",
			type:"post",
			data:{
				"id":id,
				"brandName":v_brandName
			},
			success:function(res){
				if (res.code==200) {
					alert(res.msg);
					search();
				}
			}
		})
	}

	
	
	/* 括号内为形参，可自己定义，一般就为要传递的参数 */
	function deleteBrand(id){
		if (confirm("确定删除吗？")) {
			$.ajax({
				url:"<%=basePath%>/deleteBrand.jhtml",
				type:"post",
				data:{
					"id":id
				},
				success:function(result){
					if (result.code==200) {
						alert(result.message);
						search(1);
					}
				},
				error:function(){
					alert("删除失败");
				}
			})
		}
	}
	
	//批量删除
	function deleteBatchBrand(){
		var v_ids = "";
		$("input[name='ids']:checkbox:checked").each(function(){
			/* this为js对象，下面为js的对象属性方法的调用 */
			v_ids += ","+this.value;
		})
		if (v_ids.length>0) {
			v_ids = v_ids.substring(1);
			if (confirm("确定删除吗")) {
				$.ajax({
					url:"<%=basePath%>/deleteBatchBrand.jhtml",
					type:"post",
					data:{
						"ids":v_ids
					},
					success:function(res){
						if (res.code==200) {
							alert(res.message);
							search(1);
						}
					},
					error:function(){
						alert("删除失败");
					}
				})
			}
		}
		else{
			alert("请至少选择一条数据");
		}
	}
	
	
	/*全选 */
	function ckeckAll(obj){
		var ids = document.getElementsByName("ids");
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = obj;
		}
	}
	/*反选*/
	function reverseCkeck(obj){
		var ids = document.getElementsByName("ids");
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = !ids[i].checked;
		}
	}
	
	/* 条件查询 */
	function search(pageIndex){
		var v_brandName = $("#brandName").val();
		var v_minCreateTime = $("#minCreateTime").val();
		var v_maxCreateTime = $("#maxCreateTime").val();
		var v_minUpdateTime = $("#minUpdateTime").val();
		var v_maxUpdateTime = $("#maxUpdateTime").val();
		var v_pageSize = $("#pageSizeId").val();
		$.ajax({
			url:"<%=basePath%>/toBrand.jhtml",
			type:"post",
			data:{
				"brandName":v_brandName,
				"minCreateTime":v_minCreateTime,
				"maxCreateTime":v_maxCreateTime,
				"minUpdateTime":v_minUpdateTime,
				"maxUpdateTime":v_maxUpdateTime,
				"pageIndex":pageIndex,
				"pageSize":v_pageSize,
				"flag":1
				
			},
			success:function(result){
				$("#brandTable").html(result);
			}
		})
	}
	
</script>
</body>
</html>