<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户展示页面</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" id="menu-toggle">
                金科教育
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">校园风采 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">师资团队</a></li>
                <li><a href="#">明星学员</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">java课程 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">云计算</a></li>
                        <li><a href="#">云应用</a></li>
                        <li><a href="#">云服务</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">大数据</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">数据挖掘</a></li>
                    </ul>
                </li>
                <li>
                    <input type="button" class="list-group-item active" value="返回主页面"
                           onclick="javascript:; location.href='toProductList.jhtml'"/>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-sm-3" style=";height: 600px">
        <div class="panel panel-info">
            <div class="panel-heading">
                部门管理
                <button type="button" class="btn btn-primary" onclick="showAddDlg();"><span
                        class="glyphicon glyphicon-plus"></span>新增
                </button>
                <button type="button" class="btn btn-info" onclick="showEditDlg();"><span
                        class="glyphicon glyphicon-pencil"></span>修改
                </button>
                <button type="button" class="btn btn-danger" onclick="deleteDpt();"><span
                        class="glyphicon glyphicon-trash"></span>删除
                </button>
            </div>
            <div class="panel-body">
                <ul id="deptzTree" class="ztree"></ul>
            </div>
        </div>
    </div>

    <div class="col-sm-9">
        <form class="form-horizontal" role="form" id="userSearchFrom" method="post">
            <fieldset>
                <legend>条件查询</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名称</label>
                    <div class="col-sm-4">
                        <input class="form-control" id="realname" name="realname" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户生日</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control form_datetime" id="minBirthday"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input type="text" class="input-sm form-control form_datetime" id="maxBirthday"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户薪资</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control" id="minSalary"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxSalary"/>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="clearfix form-actions" style="text-align:center">
                <button class="btn btn-primary" type="button" onclick="search();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    查询
                </button>
                <button class="btn btn-default" type="reset" onclick="clearTree();">
                    <i class="glyphicon glyphicon-refresh"></i>
                    重置
                </button>
            </div>
            <input type="text" id="deptIds">
        </form>
        <div style="background:#d8d8d8;text-align:left;">
            <button type="button" class="btn btn-primary" onclick="addUserInfo();">
                <span class="glyphicon glyphicon-plus"></span>
                添加用户
            </button>

            <button type="button" class="btn btn-info" onclick="updateUserInfo();">
                <span class="glyphicon glyphicon-pencil"></span>
                修改用户
            </button>

            <button type="button" class="btn btn-danger" onclick="deleteUserInfo();">
                <span class="glyphicon glyphicon-trash"></span>
                删除用户
            </button>

            <button type="button" class="btn btn-danger" onclick="updateUserDept();">
                <span class="glyphicon glyphicon-retweet"></span>
                批量修改用户部门
            </button>

            <button type="button" class="btn btn-primary" onclick="exportExcelByDept();">
                <span class="glyphicon glyphicon-arrow-down"></span>
                部门信息导出导出
            </button>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading ">
                用户列表
            </div>
            <div class="table-responsive">
                <table id="userTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>用户名称</th>
                        <th>真实姓名</th>
                        <th>用户性别</th>
                        <th>用户生日</th>
                        <th>用户薪资</th>
                        <th>所在部门</th>
                        <th>用户状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>选择</th>
                        <th>用户名称</th>
                        <th>真实姓名</th>
                        <th>用户性别</th>
                        <th>用户生日</th>
                        <th>用户薪资</th>
                        <th>所在部门</th>
                        <th>用户状态</th>
                        <th>操作</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<%--树添加弹出框--%>
<div id="addDeptForm" style="display:none;">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">部门简介</label>
            <div class="col-sm-3">
                <textarea class="form-control" id="remark"></textarea>
            </div>
        </div>
        <input type="text" id="pId">
    </form>
</div>

<%--树修改弹出框--%>
<div id="updateDeptForm" style="display:none;">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="edit_name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">部门简介</label>
            <div class="col-sm-3">
                <textarea class="form-control" id="edit_remark"></textarea>
            </div>
        </div>
        <input type="text" id="edit_id">
    </form>
</div>

<%--添加用户弹出框--%>
<div id="addUserForm" style="display: none">
    <form class="form-horizontal" role="form" method="post" id="add_user_form" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-2 control-label">用户名称</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_username" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户密码</label>
            <div class="col-sm-4">
                <input class="form-control" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">确认密码</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_password" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_realname" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户头像</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_headPortrait" name="headImage" type="file" multiple>
                <input class="form-control" id="headPortrait" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">用户性别</label>
            <div class="col-sm-4">
                <input name="add_sex" type="radio" value="0"/>女
                <input name="add_sex" type="radio" value="1"/>男
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户生日</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_birthday" type="text" onclick="WdatePicker()"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户薪资</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_salary" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所在部门</label>
            <div class="col-sm-4">
                <input class="form-control" id="add_deptName" type="text"/>
                <input class="form-control" id="add_deptId" type="text"/>
            </div>
            <div>
                <button class="btn btn-success" type="button" onclick="addDept();">
                    选择部门
                </button>
            </div>
        </div>
    </form>
</div>
<%--添加时树的显示--%>
<div id="treeDialog" style="display: none">
    <ul id="addDeptzTree" class="ztree"></ul>
</div>

<%--修改弹出框--%>
<div id="updateUserForm" style="display: none">
    <form class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
        <input type="text" id="update_id">
        <div class="form-group">
            <label class="col-sm-2 control-label">用户名称</label>
            <div class="col-sm-4">
                <input class="form-control" id="update_username" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-4">
                <input class="form-control" id="update_realname" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户性别</label>
            <div class="col-sm-4">
                <input name="update_sex" type="radio" value="0"/>女
                <input name="update_sex" type="radio" value="1"/>男
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户生日</label>
            <div class="col-sm-4">
                <input class="form-control" id="update_birthday" type="text" onclick="WdatePicker()"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户薪资</label>
            <div class="col-sm-4">
                <input class="form-control" id="update_salary" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所在部门</label>
            <div class="col-sm-4">
                <input class="form-control" id="update_deptName" type="text"/>
                <input class="form-control" id="update_deptId" type="text"/>
            </div>
            <div>
                <button class="btn btn-success" type="button" onclick="addDept();">
                    选择
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    $(function () {
        //时间插件
        initTime();
        //初始化品牌列表
        initUserTable();
        initOnEvent();
        initDeptzTree();
    });

    //时间格式
    function initTime() {
        $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd', locale: 'zh-CN', showClear: true});
    }

    /*表格展示*/
    var userTable;

    function initUserTable() {
        userTable = $('#userTable').DataTable({
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
                    "bSortable": false, "aTargets": [1, 2, 3, 6, 7, 8, 9]
                }
            ],
            "ajax": {
                "url": "queryUserList.jhtml",
                "type": "POST",
                "dataSrc": function (result) {
                    if (result.code == 200) {
                        //因为前台传递过来的数据（包含draw，recordsTotal，recordsFiltered，data）
                        //都放在了result.data中，与前台的数据显示结构不符，所以要进行结构的重新构建赋值
                        result.draw = result.data.draw;
                        result.recordsTotal = result.data.recordsTotal;
                        result.recordsFiltered = result.data.recordsFiltered;
                        return result.data.data;
                    }
                }
            },
            "drawCallback": function () {
                //获取当前表格所有行中的复选框的值
                $("#userTable tbody tr input[type='checkbox']").each(function () {
                    var v_id = $(this).val();
                    //将当前的值与数组中的值进行对比，若一直，当前行要进行回填
                    if (isExist(v_id)) {
                        //parents()可用，但是会消耗较多的资源，选用closest
                        /*$(this).parents("tr").css("background","#3D9CBD");*/
                        $(this).closest("tr").css("background", "#3D9CBD");
                        this.checked = true;
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
                {"data": "username"},
                {"data": "realname"},
                {
                    "data": "sex",
                    render: function (data, type, row, meta) {
                        if (data == 0) {
                            return "女生";
                        } else {
                            return "男生";
                        }
                    }
                },
                {
                    "data": "birthday",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd");
                        }
                        return data;
                    }
                },
                {"data": "salary"},
                {"data": "name"},
                {
                    "data": "userType",
                    render: function (data, type, row, meta) {
                        if (data == 0) {
                            return "未锁定";
                        } else {
                            return "已锁定";
                        }
                    }
                },
                {
                    "title": "操作",
                    render: function (data, type, row, meta) {
                        var buttons = '';
                        buttons += '<button type="button" class="btn btn-info" href="javascript:;" onclick="updateUserType(\'' + row.id + '\')" ><span class="glyphicon glyphicon-pencil"></span>\n' +
                            '                                    解锁</button>';
                        return buttons;
                    }
                }
            ],
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            }
        });
    }

    //创建一个空的数组，用于盛放被选中的input框的值
    var v_ids = [];

    //加载变色函数
    function initOnEvent() {
        $("#userTable tbody").on("click", "tr", function () {
            //若复选框处于选中状态，就清除选中状态，清除ids数组中的值，并使背景颜色消失
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
            if (v_checkbox.checked) {
                v_checkbox.checked = false;
                $(this).css("background", "");
                deleteIds(v_ids);
            }
            else {
                //若复选框没被选中，就让复选框被选中，背景色改变，在ids中存入id值
                v_checkbox.checked = true;
                $(this).css("background", "#3D9CBD");
                v_ids.push(v_checkbox.value);
            }
        })
    }

    //删除ids中的值，倒着删
    function deleteIds(id) {
        for (var i = v_ids.length; i >= 0; i--) {
            if (v_ids[i] == id) {
                v_ids.splice(i, 1);
                break;
            }
        }
    }

    //进行当前行的id与ids数组中的id进行对比
    function isExist(id) {
        for (var i = 0; i < v_ids.length; i++) {
            if (v_ids[i] == id) {
                return true;
            }
        }
    }

    /*条件查询*/
    function search() {
        var v_realname = $("#realname").val();
        var v_minBirthday = $("#minBirthday").val();
        var v_maxBirthday = $("#maxBirthday").val();
        var v_minSalary = $("#minSalary").val();
        var v_maxSalary = $("#maxSalary").val();
        var v_deptIds = $("#deptIds").val();

        var param = {};
        param.realname = v_realname;
        param.minBirthday = v_minBirthday;
        param.maxBirthday = v_maxBirthday;
        param.minSalary = v_minSalary;
        param.maxSalary = v_maxSalary;
        param.deptIds = v_deptIds;

        userTable.settings()[0].ajax.data = param;
        userTable.ajax.reload();
    }

    //树的节点查询,由加载树中的setting中的callback: {onClick: zTreeOnClick}所来
    //创建一个空数组，用以盛放所有被选择的节点
    var v_idArr = [];

    function zTreeOnClick() {
        //将存放有树节点的input框数据清空，为了使子子孙孙节点不重复
        v_idArr = [];
        $("#deptIds").val("");
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var v_selectedNodes = treeObj.getSelectedNodes();
        //将所选节点以及节点下的子节点的所有信息取出并将数据转换为一个数组
        var nodeArr = treeObj.transformToArray(v_selectedNodes);
        //将数据循环放入数组中
        for (var i = 0; i < nodeArr.length; i++) {
            //数组中没有此id的重复值时才存入
            if (!isHave(nodeArr[i].id)) {
                v_idArr.push(nodeArr[i].id);
            }
        }
        $("#deptIds").val(v_idArr.join(","));
        search();

    }

    //判断数组中是否有与要存入的数据重复的id值
    function isHave(id) {
        for (var i = 0; i < v_idArr.length; i++) {
            if (v_idArr[i].id == id) {
                return true;
            }
        }
    }

    //将树的选中状态解除
    function clearTree() {
        //清空弄数组
        v_idArr = [];
        $("#deptIds").val("");
        //清空选中的节点
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        treeObj.cancelSelectedNode();
        //刷新
        search();
    }

    /*解锁*/
    function updateUserType(id) {
        location.href = "<%=request.getContextPath()%>/updateUserType.jhtml?id=" + id;
    }


    //树的选择
    var v_deptName;
    var v_deptId;

    function addDept() {
        var v_source = $("#treeDialog").html();
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/dept/queryDeptList.jhtml",
            success: function (result) {
                //树的样式
                var setting = {
                    view: {
                        selectedMulti: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };
                if (result.code == 200) {
                    //渲染zTree
                    $.fn.zTree.init($("#addDeptzTree"), setting, result.data).expandAll(true);
                    //弹出框
                    bootbox.dialog({
                        title: "添加部门",
                        message: $("#addDeptzTree"),
                        size: "large",
                        buttons: {
                            confirm: {
                                label: "确定",
                                className: 'btn-info',
                                callback: function () {
                                    //获取当前选中的节点的集合
                                    var treeObj = $.fn.zTree.getZTreeObj("addDeptzTree");
                                    var v_selectedNodes = treeObj.getSelectedNodes();
                                    if (v_selectedNodes.length == 1) {
                                        //获取节点的数据
                                        v_deptName = v_selectedNodes[0].name;
                                        v_deptId = v_selectedNodes[0].id;
                                        //将值回填到对应的框中
                                        $("#add_deptName", v_addUserInfo).val(v_deptName);
                                        $("#add_deptId", v_addUserInfo).val(v_deptId);
                                        $("#update_deptName", v_updateUserInfo).val(v_deptName);
                                        $("#update_deptId", v_updateUserInfo).val(v_deptId);
                                    }
                                    else {
                                        bootbox.alert("只能选取一部门！")
                                    }

                                }
                            },
                            cancel: {
                                label: "取消",
                                className: 'btn-danger'
                            }
                        }
                    })
                    //回填树
                    $("#treeDialog").html(v_source);
                }
                else {
                    bootbox.alert("查询失败");
                }
            }
        })
    }

    //添加用户
    var v_addUserInfo;

    function addUserInfo() {
        //获取原始内容
        var v_source = $("#addUserForm").html();
        //加载用户添加弹出框
        v_addUserInfo = bootbox.dialog({
            title: "添加用户",
            message: $("#add_user_form"),
            size: "large",
            buttons: {
                confirm: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function () {
                        var v_username = $("#add_username", v_addUserInfo).val();
                        var v_realname = $("#add_realname", v_addUserInfo).val();
                        var v_password = $("#add_password", v_addUserInfo).val();
                        var v_sex = $("input[name='add_sex']:checked").val();
                        var v_birthday = $("#add_birthday", v_addUserInfo).val();
                        var v_salary = $("#add_salary", v_addUserInfo).val();
                        var v_deptId = $("#add_deptId", v_addUserInfo).val();
                        var v_headPortrait = $("#headPortrait", v_addUserInfo).val();
                        $.ajax({
                            url: "/addUserInfo.jhtml",
                            type: "post",
                            data: {
                                "username": v_username,
                                "realname": v_realname,
                                "password": v_password,
                                "sex": v_sex,
                                "birthday": v_birthday,
                                "salary": v_salary,
                                "deptId": v_deptId,
                                "headPortrait": v_headPortrait
                            },
                            dataType: "json",
                            success: function (res) {
                                if (res.code == 200) {
                                    bootbox.alert("添加成功！");
                                    location.href = "<%=request.getContextPath()%>/toUserList.jhtml";
                                } else {
                                    bootbox.alert("添加失败！");
                                }
                            }
                        })
                    }
                },
                cancel: {
                    label: "取消",
                    className: 'btn-danger'
                }
            }
        })
        //在添加框取消后，再次加载时间插件，同时将隐藏框的样式还回去
        $("#addUserForm").html(v_source);
    }

    //修改用户
    var v_updateUserInfo;

    function updateUserInfo() {
        var v_updateForm = $("#updateUserForm").html();
        if (v_ids.length == 1) {
            var v_id = v_ids[0];
            console.log(v_id);
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/findUserById.jhtml",
                data: {"id": v_id},
                success: function (result) {
                    if (result.code == 200) {
                        //回显的弹出框赋值
                        $("#update_id").val(result.data.id);
                        $("#update_username").val(result.data.username);
                        $("#update_realname").val(result.data.realname);
                        $("input[name='update_sex'][value=" + result.data.sex + "]").attr("checked", true);
                        var birthday = new Date(result.data.birthday).Format("yyyy-MM-dd");
                        $("#update_birthday").val(birthday);
                        $("#update_salary").val(result.data.salary);
                        $("#update_deptName").val(result.data.name);
                        $("#update_deptId").val(result.data.deptId);

                        v_updateUserInfo = bootbox.dialog({
                            titl: "修改用户信息",
                            message: $("#updateUserForm form"),
                            size: "large",
                            buttons: {
                                confirm: {
                                    label: "提交",
                                    className: "btn-success",
                                    callback: function () {
                                        var update_id = $("#update_id", v_updateUserInfo).val();
                                        var update_username = $("#update_username", v_updateUserInfo).val();
                                        var update_realname = $("#update_realname", v_updateUserInfo).val();
                                        var update_sex = $("input[name='update_sex']:checked", v_updateUserInfo).val();
                                        var update_birthday = $("#update_birthday", v_updateUserInfo).val();
                                        var update_salary = $("#update_salary", v_updateUserInfo).val();
                                        var update_deptId = $("#update_deptId", v_updateUserInfo).val();
                                        var param = {};
                                        param.id = update_id;
                                        param.username = update_username;
                                        param.realname = update_realname;
                                        param.sex = update_sex;
                                        param.birthday = update_birthday;
                                        param.salary = update_salary;
                                        param.deptId = update_deptId;

                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/updateUser.jhtml",
                                            type: "post",
                                            data: param,
                                            success: function (result) {
                                                if (result.code == 200) {
                                                    bootbox.alert("修改成功");
                                                    location.href = "<%=request.getContextPath()%>/toUserList.jhtml";
                                                }
                                            },
                                            error: function () {
                                                bootbox.alert("修改失败");
                                            }
                                        })
                                    }
                                },
                                cancel: {
                                    label: "取消",
                                    className: "btn-danger"
                                }
                            }
                        });
                        $("#updateUserForm").html(v_updateForm);
                        initTime();
                    }
                }
            })
        }
        else {
            bootbox.alert("请选择一位用户");
        }
    }

    //删除用户
    function deleteUserInfo() {
        if (v_ids.length > 0) {
            bootbox.dialog({
                message: "是否删除?",
                size: "large",
                buttons: {
                    confirm: {
                        label: '确定',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '取消',
                        className: 'btn-danger'
                    }
                },
                message: '确认要删除吗？',
                callback: function (result) {
                    alert(result);
                    if (result) {
                        var idArr = [];
                        for (var i = 0; i < v_ids.length; i++) {
                            idArr.push(v_ids[i].id);
                        }
                        $.ajax({
                            url: "<%=request.getContextPath()%>/deleteUser.jhtml",
                            type: "post",
                            data: {
                                "ids": idArr
                            },
                            success: function (res) {
                                if (res.code == 200) {
                                    bootbox.alert(res.message);
                                    location.href = "<%=request.getContextPath()%>/toUserList.jhtml";
                                }
                                else {
                                    bootbox.alert(res.message);
                                }
                            },
                            error: function () {
                                bootbox.alert("删除失败");
                            }
                        })
                    }
                }
            })
        }
        else {
            bootbox.alert("请选取一个用户");
        }
    }

    //批量修改用户部门
    function updateUserDept() {
        if (v_ids.length > 0) {
            var v_source = $("#treeDialog").html();
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/dept/queryDeptList.jhtml",
                success: function (result) {
                    //树的样式
                    var setting = {
                        view: {
                            selectedMulti: true
                        },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        }
                    };
                    if (result.code == 200) {
                        //渲染zTree
                        $.fn.zTree.init($("#addDeptzTree"), setting, result.data).expandAll(true);
                        //弹出框
                        bootbox.dialog({
                            title: "添加部门",
                            message: $("#addDeptzTree"),
                            size: "large",
                            buttons: {
                                confirm: {
                                    label: "确定",
                                    className: 'btn-info',
                                    callback: function () {
                                        //获取当前选中的节点的集合
                                        var treeObj = $.fn.zTree.getZTreeObj("addDeptzTree");
                                        var v_selectedNode = treeObj.getSelectedNodes();
                                        if (v_selectedNode.length == 1) {
                                            var deptId = v_selectedNode[0].id;
                                            $.ajax({
                                                type: "post",
                                                url: "<%=request.getContextPath()%>/updateUserDeptId.jhtml",
                                                data: {
                                                    "ids": v_ids,
                                                    "deptId": deptId
                                                },
                                                success: function (result) {
                                                    if (result.code == 200) {
                                                        bootbox.alert("修改成功");
                                                        location.href = "<%=request.getContextPath()%>/toUserList.jhtml";
                                                    }
                                                },
                                                error: function () {
                                                    bootbox.alert("修改失败");
                                                }
                                            })

                                        }
                                        else {
                                            bootbox.alert("请选取一部门！")
                                        }

                                    }
                                },
                                cancel: {
                                    label: "取消",
                                    className: 'btn-danger'
                                }
                            }
                        })
                        //回填树
                        $("#treeDialog").html(v_source);
                    }
                    else {
                        bootbox.alert("查询失败");
                    }
                }
            })
        }
        else {
            bootbox.alert("请选择一个用户！");
        }
    }

    //加载树
    function initDeptzTree() {
        //树的样式
        var setting = {
            callback: {
                onClick: zTreeOnClick
            },
            view: {
                selectedMulti: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/dept/queryDeptList.jhtml",
            success: function (result) {
                $.fn.zTree.init($("#deptzTree"), setting, result.data).expandAll(true);
            }
        })
    }

    //添加部门
    /*function showAddDlg() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var selectedNodes = treeObj.getSelectedNodes();

        if (selectedNodes.length == 1) {
            var v_pid = selectedNodes[0].id;
            bootbox.dialog({
                title: "添加部门",
                message: $("#addDeptForm").html(),
                size: "large",
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger'
                    },
                    ok: {
                        label: "确定",
                        className: 'btn-info',
                        callback: function(){
                            var v_deptName = $("#name").val();
                            alert(v_deptName);
                            $.ajax({
                                url: "/dept/addDeptInfo.jhtml",
                                type: "post",
                                data:{
                                    "pId":v_pid,
                                    "name":v_deptName
                                },
                                dataType:"json",
                                success:function (res) {
                                    if (res.code == 200) {
                                        bootbox.alert("添加成功！");
                                        location.href = "/toUserList.jhtml";
                                    }else {
                                        bootbox.alert(res.message);
                                    }
                                }

                            })
                        }
                    }
                }
            })
        }
        else {
            bootbox.alert("请选取一个节点");
        }

    }*/

    //老大的部门添加
    function showAddDlg() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var v_selectedNodes = treeObj.getSelectedNodes();

        if (v_selectedNodes.length == 1) {
            var v_pid = v_selectedNodes[0].id;
            $("#pId").attr("value", v_pid);
            var v_updateDeptInfo = bootbox.dialog({
                title: "添加部门",
                message: $("#addDeptForm").html(),
                size: "large",
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger'
                    },
                    ok: {
                        label: "确定",
                        className: 'btn-info',
                        callback: function () {
                            //获取部门名
                            var v_deptName = $("#name", v_updateDeptInfo).val();
                            //获取文本域内容
                            var v_remark = $("#remark", v_updateDeptInfo).val();
                            //获取父级节点
                            var v_pId = $("#pId", v_updateDeptInfo).val();
                            //创建一个空数组，用以盛放要传递的数据
                            var v_param = {};
                            //将数据放入数组中
                            v_param.name = v_deptName;
                            v_param.remark = v_remark;
                            v_param.pId = v_pId;
                            $.ajax({
                                url: "/dept/addDeptInfo.jhtml",
                                type: "post",
                                data: v_param,
                                dataType: "json",
                                success: function (res) {
                                    if (res.code == 200) {
                                        //获取选中的节点
                                        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
                                        //空数组用于盛放节点信息
                                        var node = {};
                                        node.name = v_deptName;
                                        node.remark = v_remark;
                                        //获取后台添加数据后返回的新数据的id
                                        node.id = res.data;
                                        //进行节点的创建
                                        treeObj.addNodes(v_selectedNodes[0], node);
                                    } else {
                                        bootbox.alert("添加失败！");
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        else {
            bootbox.alert("请选取一个节点");
        }
    }

    //修改部门
    function showEditDlg() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var v_selectedNodes = treeObj.getSelectedNodes();

        if (v_selectedNodes.length == 1) {
            //获取选中节点的数据
            var v_nodes = v_selectedNodes[0];
            //将节点中的数据赋给隐藏框进行回显
            $("#edit_id").attr("value", v_nodes.id);
            $("#edit_name").attr("value", v_nodes.name);
            //用html也可
            $("#edit_remark").html(v_nodes.remark);
            //$("#edit_remark").text(v_nodes.remark);

            var v_updateDeptInfo = bootbox.dialog({
                title: "修改部门",
                message: $("#updateDeptForm").html(),
                size: "large",
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger'
                    },
                    ok: {
                        label: "确定",
                        className: 'btn-info',
                        callback: function () {
                            //获取部门名
                            var v_editDeptName = $("#edit_name", v_updateDeptInfo).val();
                            //获取文本域内容
                            var v_editRemark = $("#edit_remark", v_updateDeptInfo).val();
                            //获取节点
                            var v_editId = $("#edit_id", v_updateDeptInfo).val();
                            //创建一个空数组，用以盛放要传递的数据
                            var v_param = {};
                            //将数据放入数组中，向后台传递参数
                            v_param.name = v_editDeptName;
                            v_param.remark = v_editRemark;
                            v_param.id = v_editId;
                            $.ajax({
                                url: "/dept/updateDeptInfo.jhtml",
                                type: "post",
                                data: v_param,
                                dataType: "json",
                                success: function (res) {
                                    if (res.code == 200) {
                                        bootbox.alert("修改成功！");
                                        //获取选中的节点
                                        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
                                        //回显选中节点的节点信息
                                        v_nodes.name = v_editDeptName;
                                        v_nodes.remark = v_editRemark;
                                        //进行节点的修改
                                        treeObj.updateNode(v_nodes);
                                    } else {
                                        bootbox.alert("修改失败！");
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        else {
            bootbox.alert("请选取一个节点");
        }
    }

    //删除部门
    function deleteDpt() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var selectedNodes = treeObj.getSelectedNodes();
        if (selectedNodes.length > 0) {
            bootbox.dialog({
                message: "是否删除?",
                size: "large",
                buttons: {
                    confirm: {
                        label: '确定',
                        className: 'btn-success',
                        callback: function () {
                            //将所选节点以及节点下的子节点的所有信息取出并将数据转换为一个数组
                            var nodeArr = treeObj.transformToArray(selectedNodes);
                            var idArr = [];
                            for (var i = 0; i < nodeArr.length; i++) {
                                idArr.push(nodeArr[i].id);
                            }
                            $.ajax({
                                url: "/dept/deleteDpt.jhtml",
                                type: "post",
                                data: {
                                    "ids": idArr
                                },
                                dataType: "json",
                                success: function (res) {

                                    if (res.code == 200) {
                                        bootbox.alert("删除成功！");
                                        //对前台的树进行js动态删除
                                        for (var i = 0; i < nodeArr.length; i++) {
                                            treeObj.removeNode(nodeArr[i]);
                                        }

                                    } else {
                                        bootbox.alert("删除失败！");
                                    }
                                }
                            })
                        }
                    },
                    cancel: {
                        label: '取消',
                        className: 'btn-danger'
                    }
                }
            })
        }
        else {
            bootbox.alert("请选取要删除的节点");
        }
    }

    //部门信息导出
    var v_childrenNodesArr = [];

    function exportExcelByDept() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("deptzTree");
        var v_selectedNodes = treeObj.getSelectedNodes();
        if (v_selectedNodes.length > 0) {
            var v_childrenNode = v_selectedNodes[0].children;
            for (var i = 0; i < v_childrenNode.length; i++) {
                v_childrenNodesArr.push(v_childrenNode[i].id);
            }
            console.log(v_childrenNodesArr);
            //用js动态提交form表单，将前台参数传递到后台
            var userSearchFrom = document.getElementById("userSearchFrom");
            userSearchFrom.action = "<%=request.getContextPath()%>/exportExcelByDept.jhtml?childNodes=" + v_childrenNodesArr;
            userSearchFrom.method = "post";
            userSearchFrom.submit();
            v_childrenNodesArr = [];
        }
        else {
            bootbox.alert("请选择一个部门！");
        }
    }

    $("#add_headPortrait").fileinput({
        uploadUrl: "fileInput/addHeadPortrait.jhtml",
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮,跟随文本框的那个
        showRemove: false, //显示移除按钮,跟随文本框的那个
        showCaption: false,//是否显示标题,就是那个文本框
        showPreview: true, //是否显示预览,不写默认为true
        dropZoneEnabled: false,
        maxFileCount: 3,
        enctype: 'multipart/form-data',
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        language: 'zh',
        layoutTemplates: {
            actionUpload: '',   //取消上传按钮
            actionZoom: ''      //取消放大镜按钮
        }

    }).on("fileuploaded", function (event, data, previewId, index) {
        var result = data.response;
        if (result.code == 200) {
            $("#headPortrait").val(result.data);
            bootbox.alert("图片已完成上传！");
        }
    });
</script>
</html>
