<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>日志展示页面</title>
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
                    <label class="col-sm-1 control-label">用户名称</label>
                    <div class="col-sm-3">
                        <input class="form-control" id="userName" name="userName" type="text"/>
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
        <div class="panel panel-default">
            <div class="panel-heading ">
                日志列表
            </div>
            <div class="table-responsive">
                <table id="logTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>用户名称</th>
                        <th>日志内容</th>
                        <th>创建时间</th>
                        <th>操作用时</th>
                        <th>操作状态</th>
                        <th>具体操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>选择</th>
                        <th>用户名称</th>
                        <th>日志内容</th>
                        <th>创建时间</th>
                        <th>操作用时</th>
                        <th>操作状态</th>
                        <th>具体操作</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        //初始化品牌列表
        initLogTable();
        initOnEvent();
    });
    /*表格展示*/
    var logTable;
    function initLogTable() {
        logTable = $('#logTable').DataTable({
            "autoWidth": true,//设置自适应宽度
            "info": true,//展示左下角信息
            "lengthChange": true,//是否允许改变每页条数
            "lengthMenu": [5, 10, 15, 30, 45],//设置每页条数
            "paging": true,//是否开启分页
            "processing": true,//页面加载慢的时候展示处理状态
            "serverSide": true,//时候开服务器模式
            "bFilter": false,
            "ajax": {
                "url": "queryLog.jhtml",
                "type": "POST"
            },
            "drawCallback":function () {
                //获取当前表格所有行中的复选框的值
                $("#logTable tbody tr input[type='checkbox']").each(function () {
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
                {"data": "userName"},
                {"data": "logInfo"},
                {
                    "data": "createTime",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                        }
                        return data;
                    }
                },
                {"data": "excuteTime"},
                {
                    "data": "status",
                    render:function(data, type, row, meta){
                        if(data==0){
                            return "失败";
                        }else{
                            return "成功";
                        }
                    }
                },
                {"data": "content"}
            ],
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            }
        });
    }


    /* 条件查询 */
    function search(){
        var v_userName = $("#userName").val();
        var v_minCreateTime = $("#minCreateTime").val();
        var v_maxCreateTime = $("#maxCreateTime").val();

        var param = {};
        param.userName = v_userName;
        param.minCreateTime = v_minCreateTime;
        param.maxCreateTime = v_maxCreateTime;

        logTable.settings()[0].ajax.data = param;
        logTable.ajax.reload();
    }

    //创建一个空的数组，用于盛放被选中的input框的值
    var v_ids = [];
    //加载变色函数
    function initOnEvent() {
        $("#logTable tbody").on("click","tr",function () {
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
</script>
</html>
