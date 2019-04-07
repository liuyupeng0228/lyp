<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>地区展示页面</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>

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
                地区管理
                <button type="button" class="btn btn-primary" onclick="showAddDlg();"><span
                        class="glyphicon glyphicon-plus"></span>新增
                </button>
                <button type="button" class="btn btn-info" onclick="showEditDlg();"><span
                        class="glyphicon glyphicon-pencil"></span>修改
                </button>
                <button type="button" class="btn btn-danger" onclick="deleteArea();"><span
                        class="glyphicon glyphicon-trash"></span>删除
                </button>
            </div>
            <div class="panel-body">
                <ul id="areaZtree" class="ztree"></ul>
            </div>
        </div>
    </div>

    <div class="col-sm-9">
    </div>
</div>

<%--树添加弹出框--%>
<div  id="addAreaForm" style="display:none;">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">地区名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">地区简介</label>
            <div class="col-sm-3">
                <textarea class="form-control" id="remark"></textarea>
            </div>
        </div>
        <input type="text" id="pId">
    </form>
</div>

<%--树修改弹出框--%>
<div  id="updateAreaForm" style="display:none;">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">地区名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="edit_name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">地区简介</label>
            <div class="col-sm-3">
                <textarea class="form-control" id="edit_remark"></textarea>
            </div>
        </div>
        <input type="text" id="edit_id">
    </form>
</div>


</body>
<script type="text/javascript">
    $(function () {
        initAreazTree();
    });


    //加载树
    function initAreazTree() {
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
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/area/queryAreaList.jhtml",
            success: function (result) {
                $.fn.zTree.init($("#areaZtree"), setting, result.data).expandAll(true);
            }
        })
    }



    //老大的地区添加
    function showAddDlg(){
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("areaZtree");
        var v_selectedNodes = treeObj.getSelectedNodes();

        if (v_selectedNodes.length == 1) {
            var v_pid = v_selectedNodes[0].id;
            $("#pId").attr("value",v_pid);
            var v_addAreaInfo = bootbox.dialog({
                title: "添加地区",
                message: $("#addAreaForm").html(),
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
                            //获取地区名
                            var v_deptName = $("#name", v_addAreaInfo).val();
                            //获取文本域内容
                            var v_remark = $("#remark", v_addAreaInfo).val();
                            //获取父级节点
                            var v_pId = $("#pId", v_addAreaInfo).val();
                            //创建一个空数组，用以盛放要传递的数据
                            var v_param = {};
                            //将数据放入数组中
                            v_param.name = v_deptName;
                            v_param.remark = v_remark;
                            v_param.pId = v_pId;
                            $.ajax({
                                url: "/area/addAreaInfo.jhtml",
                                type: "post",
                                data:v_param,
                                dataType:"json",
                                success:function (res) {
                                    if (res.code == 200) {
                                        //获取选中的节点
                                        var treeObj = $.fn.zTree.getZTreeObj("areaZtree");
                                        //空数组用于盛放节点信息
                                        var node = {};
                                        node.name = v_deptName;
                                        node.remark = v_remark;
                                        //获取后台添加数据后返回的新数据的id
                                        node.id = res.data;
                                        //进行节点的创建
                                        treeObj.addNodes(v_selectedNodes[0], node);
                                    }else {
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

    //修改地区
    function showEditDlg() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("areaZtree");
        var v_selectedNodes = treeObj.getSelectedNodes();

        if (v_selectedNodes.length == 1) {
            //获取选中节点的数据
            var v_nodes = v_selectedNodes[0];
            //将节点中的数据赋给隐藏框进行回显
            $("#edit_id").attr("value",v_nodes.id);
            $("#edit_name").attr("value",v_nodes.name);
            //用html也可
            $("#edit_remark").html(v_nodes.remark);

            var v_updateAreaInfo = bootbox.dialog({
                title: "修改地区",
                message: $("#updateAreaForm").html(),
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
                            //获取地区名
                            var v_editDeptName = $("#edit_name", v_updateAreaInfo).val();
                            //获取文本域内容
                            var v_editRemark = $("#edit_remark", v_updateAreaInfo).val();
                            //获取节点
                            var v_editId = $("#edit_id", v_updateAreaInfo).val();
                            //创建一个空数组，用以盛放要传递的数据
                            var v_param = {};
                            //将数据放入数组中，向后台传递参数
                            v_param.name = v_editDeptName;
                            v_param.remark = v_editRemark;
                            v_param.id = v_editId;
                            $.ajax({
                                url: "/area/updateAreaInfo.jhtml",
                                type: "post",
                                data:v_param,
                                dataType:"json",
                                success:function (res) {
                                    if (res.code == 200) {
                                        bootbox.alert("修改成功！");
                                        //获取选中的节点
                                        var treeObj = $.fn.zTree.getZTreeObj("areaZtree");
                                        //回显选中节点的节点信息
                                        v_nodes.name = v_editDeptName;
                                        v_nodes.remark = v_editRemark;
                                        //进行节点的修改
                                        treeObj.updateNode(v_nodes);
                                    }else {
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

    //删除地区
    function deleteArea() {
        //获取当前选中的节点的集合
        var treeObj = $.fn.zTree.getZTreeObj("areaZtree");
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
                                url: "/area/deleteArea.jhtml",
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

</script>
</html>
