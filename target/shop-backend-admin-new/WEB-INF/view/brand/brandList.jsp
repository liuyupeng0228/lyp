<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>品牌展示页面</title>
	<jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<input type="button" class="list-group-item active" value="返回主页面" onclick="javascript:; location.href='toProductList.jhtml'"/>
				</li>
			</ul>
		</div>
	</div>
</nav>

<div class="row">
	<div class="col-xs-6 col-md-11">
		<form class="form-horizontal" role="form">
			<fieldset>
				<legend>条件查询</legend>
				<div class="form-group" style="text-align: center">
					<label class="col-sm-1 control-label">品牌名称</label>
					<div class="col-sm-3">
						<input class="form-control" id="brandName" name="brandName" type="text"/>
					</div>
					<label class="col-sm-1 control-label">创建时间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="input-sm form-control" id="minCreateTime" onclick="WdatePicker()">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
							<input type="text" class="input-sm form-control" id="maxCreateTime" onclick="WdatePicker()">
						</div>
					</div>
					<label class="col-sm-1 control-label">修改时间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="input-sm form-control form_datetime" id="minUpdateTime"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
							<input type="text" class="input-sm form-control form_datetime" id="maxUpdateTime"/>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="clearfix form-actions" style="text-align:center">
				<button class="btn btn-primary" type="button" onclick="search();">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					查询
				</button>
				<button class="btn btn-default" type="reset">
					<i class="glyphicon glyphicon-refresh"></i>
					重置
				</button>
			</div>
		</form>
		<div style="background:#d8d8d8;text-align:right;">
			<button type="button" class="btn btn-info"  onclick="location.href='<%=basePath%>/toAddBrand.jhtml'">
				<span class="glyphicon glyphicon-plus"></span>
				添加品牌
			</button>

			<button type="button" class="btn btn-danger" onclick="deleteBatchBrand();">
				<span class="glyphicon glyphicon-remove"></span>
				批量删除
			</button>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading ">
				品牌列表
			</div>
			<div class="table-responsive">
				<table id="brandTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
					<tr>
						<th>多项选择</th>
						<th>品牌名称</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th>操作</th>
					</tr>
					</thead>
					<tfoot>
					<tr>
						<th>多项选择</th>
						<th>品牌名称</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th>操作</th>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    $(function(){
        //初始化品牌列表
        initBrandTable();
        initOnEvent();
    });
    /*表格展示*/
    var brandTable;
    function initBrandTable() {
        brandTable = $('#brandTable').DataTable({
            "autoWidth": true,//设置自适应宽度
            "info": true,//展示左下角信息
            "lengthChange": true,//是否允许改变每页条数
            "lengthMenu": [5, 10, 15, 30, 45],//设置每页条数
            "paging": true,//是否开启分页
            "processing": true,//页面加载慢的时候展示处理状态
            "serverSide": true,//时候开服务器模式
            "bFilter": false,
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [1, 3, 4]
                }
            ],
            "ajax": {
                "url": "queryBrand.jhtml",
                "type": "POST"
            },
            "drawCallback":function () {
                //获取当前表格所有行中的复选框的值
                $("#brandTable tbody tr input[type='checkbox']").each(function () {
                    var v_id = $(this).val();
                    //将当前的值与数组中的值进行对比，若一直，当前行要进行回填
                    if (isExist(v_id)){
                        //parents()可用，但是会消耗较多的资源，选用closest
                        /*$(this).parents("tr").css("background","#3D9CBD");*/
                        $(this).closest("tr").css("background","#3D9CBD");
                        this.checked=true;
                    }
                })
            },
            "columns": [
                {
                    "data": "id",
                    render: function (data, type, row, meta) {
                        return "<input type='checkbox' value='" + data + "' name='ids'>";
                    }
                },
                {"data": "brandName"},
                {
                    "data": "createTime",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                        }
                        return data;
                    }
                },
                {
                    "data": "updateTime",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                        }
                        return data;
                    }
                },
                {
                    "title": "操作",
                    render: function (data, type, row, meta) {
                        var buttons = '';
                        buttons += '<button type="button" class="btn btn-info" href="javascript:;" onclick="deleteBrand(\'' + row.id + '\')" ><span class="glyphicon glyphicon-trash"></span>\n' +
                            '                                    删除</button>';
                        buttons += '<button type="button" class="btn btn-danger" href="javascript:;" onclick="queryById(\'' + row.id + '\')" ><span class="glyphicon glyphicon-pencil"></span>\n' +
                            '                                    修改</button>';
                        return buttons;
                    }
                }
            ],
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            }
        });
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

    /* 条件查询 */
    function search(){
        var v_brandName = $("#brandName").val();
        var v_minCreateTime = $("#minCreateTime").val();
        var v_maxCreateTime = $("#maxCreateTime").val();
        var v_minUpdateTime = $("#minUpdateTime").val();
        var v_maxUpdateTime = $("#maxUpdateTime").val();

        var param = {};
        param.brandName = v_brandName;
        param.minCreateTime = v_minCreateTime;
        param.maxCreateTime = v_maxCreateTime;
        param.minUpdateTime = v_minUpdateTime;
        param.maxUpdateTime = v_maxUpdateTime;

        brandTable.settings()[0].ajax.data = param;
        brandTable.ajax.reload();
    }

    //创建一个空的数组，用于盛放被选中的input框的值
    var v_ids = [];
    //加载变色函数
    function initOnEvent() {
        $("#brandTable tbody").on("click","tr",function () {
            //若复选框处于选中状态，就清除选中状态，清除ids数组中的值，并使背景颜色消失
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
            if (v_checkbox.checked) {
                v_checkbox.checked = false;
                $(this).css("background","");
                deleteIds(v_ids);
            }
            else{
                //若复选框没被选中，就让复选框被选中，背景色改变，在ids中存入id值
                v_checkbox.checked = true;
                $(this).css("background","#3D9CBD");
                v_ids.push(v_checkbox.value);
            }
        })
    }

    //删除ids中的值，倒着删
    function deleteIds(id) {
        for (var i=v_ids.length;i>=0;i--){
            if (v_ids[i] == id){
                v_ids.splice(i,1);
                break;
            }
        }
    }

    //进行当前行的id与ids数组中的id进行对比
    function isExist(id) {
        for (var i=0;i<v_ids.length;i++){
            if (v_ids[i] == id){
                return true;
            }
        }
    }

    //修改
    function queryById(id) {
        location.href="queryByBrandId.jhtml?id="+id;
    }

    //时间格式
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
</script>
</html>