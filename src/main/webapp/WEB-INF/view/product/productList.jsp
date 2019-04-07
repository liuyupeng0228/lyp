<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>商品展示页面</title>
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
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active">
                    <input type="button" class="list-group-item active" value="用户管理"
                           onclick="location.href='toUserList.jhtml'"/>
                </li>
                <li>
                    <input type="button" class="list-group-item active" value="品牌管理"
                           onclick="location.href='queryBrandList.jhtml'"/>
                </li>
                <li>
                    <input type="button" class="list-group-item active" value="品牌2"
                           onclick="location.href='toBrand.jhtml'"/>
                </li>
                <li>
                    <input type="button" class="list-group-item active" value="日志管理"
                           onclick="location.href='toLogList.jhtml'"/>
                </li>
                <li>
                    <input type="button" class="list-group-item active" value="地区管理"
                           onclick="location.href='/area/toAreaList.jhtml'"/>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="row">
    <h3 style="text-align: center">
        尊敬的${dataUser.username}欢迎进入，您上次登录时间是<fmt:formatDate value="${sessionScope.dataUser.lastLoginTime}"
                                                        pattern="yyyy年MM月dd日 HH时mm分ss秒"/>，今天登陆了${sessionScope.dataUser.loginCount}次
    </h3>
</div>

<div class="row">
    <div class="col-xs-6 col-md-12">
        <form class="form-horizontal" role="form" id="productForm">
            <fieldset>
                <legend>条件查询</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品名称</label>
                    <div class="col-sm-4">
                        <input class="form-control" id="productName" name="productName" type="text"/>
                    </div>
                    <label class="col-sm-2 control-label">商品价格</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control" id="minPrice" name="minPrice"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-yen"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxPrice" name="maxPrice"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">创建时间</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control" id="minCreateTime" onclick="WdatePicker()">
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxCreateTime" onclick="WdatePicker()">
                        </div>
                    </div>
                    <label class="col-sm-2 control-label">修改时间</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control form_datetime" id="minUpdateTime"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input type="text" class="input-sm form-control form_datetime" id="maxUpdateTime"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品品牌</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="selectid" onchange="findBrand();">
                            <option value="-1">==请选择==</option>
                        </select>
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
            <button type="button" class="btn btn-info" onclick="toAdd()">
                <span class="glyphicon glyphicon-plus"></span>
                添加商品
            </button>

            <button type="button" class="btn btn-danger" onclick="deleteBatchProduct();">
                <span class="glyphicon glyphicon-remove"></span>
                批量删除
            </button>

            <button type="button" class="btn btn-primary" onclick="exportExcel();">
                <span class="glyphicon glyphicon-arrow-down"></span>
                Excel有样式导出
            </button>

            <button type="button" class="btn btn-primary" onclick="exportExcelByBrand();">
                <span class="glyphicon glyphicon-arrow-down"></span>
                按品牌导出Excel
            </button>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading ">
                商品列表
            </div>
            <div class="table-responsive">
                <table id="productTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>多项选择</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>商品品牌</th>
                        <th>商品图片</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>多项选择</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>商品品牌</th>
                        <th>商品图片</th>
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

    $(function () {
        //初始化商品列表
        initProductTable()
        //初始化绑定事件
        initOnEvent();
        //初始化下拉框
        initBrandTable();
    })

    //创建一个空的数组，用于盛放被选中的input框的值
    var v_ids = [];

    //加载变色函数
    function initOnEvent() {
        $("#productTable tbody").on("click", "tr", function () {
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

    /*品牌条件查询*/
    function findBrand() {
        search();
    }

    /*条件查询*/
    function search() {
        var v_productName = $("#productName").val();
        var v_minPrice = $("#minPrice").val();
        var v_maxPrice = $("#maxPrice").val();
        var v_minCreateTime = $("#minCreateTime").val();
        var v_maxCreateTime = $("#maxCreateTime").val();
        var v_minUpdateTime = $("#minUpdateTime").val();
        var v_maxUpdateTime = $("#maxUpdateTime").val();
        var v_selectid = $("#selectid").val();

        var param = {};
        param.productName = v_productName;
        param.minPrice = v_minPrice;
        param.maxPrice = v_maxPrice;
        param.minCreateTime = v_minCreateTime;
        param.maxCreateTime = v_maxCreateTime;
        param.minUpdateTime = v_minUpdateTime;
        param.maxUpdateTime = v_maxUpdateTime;
        param.brandid = v_selectid;

        productTable.settings()[0].ajax.data = param;
        productTable.ajax.reload();
    }

    /*表格展示*/
    var productTable;

    function initProductTable() {
        productTable = $('#productTable').DataTable({
            "autoWidth": true,//设置自适应宽度
            "info": true,//展示左下角信息
            "lengthChange": true,//是否允许改变每页条数
            "lengthMenu": [5, 10, 15, 30, 45],//设置每页条数
            "paging": true,//是否开启分页
            "processing": true,//页面加载慢的时候展示处理状态
            "serverSide": true,//时候开服务器模式
            "bFilter": false,
            "order": [[0, "desc"]],
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [1, 5, 6, 7]
                }
            ],
            "ajax": {
                "url": "queryProduct.jhtml",
                "type": "POST",
                "dataSrc": function (result) {
                    if(result.code == 200){
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
                $("#productTable tbody tr input[type='checkbox']").each(function () {
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
                {"data": "productName"},
                {"data": "productPrice"},
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
                {"data": "brand.brandName"},
                {
                    "data": "imgurl",
                    render: function (data, type, row, meta) {
                        return "<img src='" + data + "' width='50px'/>"
                    }
                },
                {
                    "title": "操作",
                    render: function (data, type, row, meta) {
                        var buttons = '';
                        buttons += '<button type="button" class="btn btn-info" href="javascript:;" onclick="deleteProduct(\'' + row.id + '\')" ><span class="glyphicon glyphicon-trash"></span>\n' +
                            '                                    删除</button>';
                        buttons += '<button type="button" class="btn btn-danger" href="javascript:;" onclick="queryById(\'' + row.id + '\')" ><span class="glyphicon glyphicon-pencil"></span>\n' +
                            '                                    修改</button>';
                        buttons += '<button type="button" class="btn btn-info" href="javascript:;" onclick="findProductImages(\'' + row.id + '\')" ><span class="glyphicon glyphicon-search"></span>\n' +
                            '                                    子图查询</button>';
                        return buttons;
                    }
                }
            ],
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            }
        });
    }

    //添加商品
    function toAdd() {
        location.href = "toAdd.jhtml";
    }

    //删除商品
    /* 括号内为形参，可自己定义，一般就为要传递的参数 */
    function deleteProduct(id) {
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
            callback: function () {
                $.ajax({
                    url: "<%=request.getContextPath()%>/deleteProduct.jhtml",
                    type: "post",
                    data: {
                        "id": id
                    },
                    success: function (result) {
                        console.log(result);
                        if (result.code == 200) {
                            bootbox.alert(result.mas);
                            location.href = "<%=request.getContextPath()%>/toProductList.jhtml";
                        }
                        else {
                            bootbox.alert("删除失败");
                        }
                    }
                })
            }
        })
    }

    //批量删除
    function deleteBatchProduct() {
        var v_ids = "";
        $("input[name='ids']:checkbox:checked").each(function () {
            /* this为js对象，下面为js的对象属性方法的调用 */
            v_ids += "," + this.value;
        })
        if (v_ids.length > 0) {
            v_ids = v_ids.substring(1);
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
                callback: function () {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/deleteBatchProduct.jhtml",
                        type: "post",
                        data: {
                            "ids": v_ids
                        },
                        success: function (res) {
                            if (res.code == 200) {
                                bootbox.alert(res.message);
                                location.href = "<%=request.getContextPath()%>/queryProduct.jhtml";
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
            })
        }
        else {
            bootbox.alert("请至少选择一条数据");
        }
    }

    //修改
    function queryById(id) {
        location.href = "queryById.jhtml?id=" + id;
    }

    /*excel导出*/
    function exportExcel() {
        //用js动态提交form表单，将前台参数传递到后台
        var productForm = document.getElementById("productForm");
        productForm.action = "<%=request.getContextPath()%>/exportExcel.jhtml";
        productForm.method = "post";
        productForm.submit();
    }

    /*按品牌excel导出*/
    function exportExcelByBrand() {
        //用js动态提交form表单，将前台参数传递到后台
        var productForm = document.getElementById("productForm");
        productForm.action = "<%=request.getContextPath()%>/exportExcelByBrand.jhtml";
        productForm.method = "post";
        productForm.submit();
    }

    /*子图查询*/
    function findProductImages(productId) {
        location.href = "<%=request.getContextPath()%>/images/findProductImages.jhtml?id=" + productId;
    }

    //时间格式
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss', language: 'zh-CN', showClear: true});

</script>
</html>
