<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>商品展示页面</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
    <style>
        body {
            padding-top: 55px;
        }

        .show-grid [class ^="col-"] {
            padding-top: 10px;
            padding-bottom: 10px;
            background-color: #eee;
            border: 1px solid #ddd;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

        #wrapper {
            padding-left: 0;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
        }

        #wrapper.toggled {
            padding-left: 250px;
        }

        #sidebar-wrapper {
            z-index: 1000;
            position: fixed;
            left: 250px;
            width: 0;
            height: 100%;
            margin-left: -250px;
            overflow-y: auto;
            background: #F5F5F5;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
        }

        #wrapper.toggled #sidebar-wrapper {
            width: 250px;
        }

        #page-content-wrapper {
            width: 100%;
            position: absolute;
            padding: 15px;
        }

        #wrapper.toggled #page-content-wrapper {
            position: absolute;
            margin-right: -250px;
        }

        /* Sidebar Styles */
        .sidebar-nav {
            position: absolute;
            top: 0;
            width: 250px;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .sidebar-nav li {
            text-indent: 20px;
            line-height: 40px;
        }

        .sidebar-nav li a {
            display: block;
            text-decoration: none;
            color: #999999;
        }

        .sidebar-nav li a:hover {
            text-decoration: none;
            color: #fff;
            background: rgba(255, 255, 255, 0.2);
        }

        .sidebar-nav li a:active,
        .sidebar-nav li a:focus {
            text-decoration: none;
        }

        .sidebar-nav > .sidebar-brand {
            height: 65px;
            font-size: 18px;
            line-height: 60px;
        }

        .sidebar-nav > .sidebar-brand a {
            color: #999999;
        }

        .sidebar-nav > .sidebar-brand a:hover {
            color: #fff;
            background: none;
        }

        @media (min-width: 768px) {
            #wrapper {
                padding-left: 250px;
            }

            #wrapper.toggled {
                padding-left: 0;
            }

            #sidebar-wrapper {
                width: 250px;
            }

            #wrapper.toggled #sidebar-wrapper {
                width: 0;
            }

            #page-content-wrapper {
                padding: 20px;
                position: relative;
            }

            #wrapper.toggled #page-content-wrapper {
                position: relative;
                margin-right: 0;
            }
        }
    </style>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<%--用于引入下拉列表数据的查询--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/brand/brand.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
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

        <!-- Collect the nav links, forms, and other content for toggling -->
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
            </ul>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-xs-6 col-md-2">
        <div class="list-group">
            <a href="#" class="list-group-item active">用户管理</a>
            <a href="#" class="list-group-item">增加用户</a>
            <a href="#" class="list-group-item">修改用户</a>
            <a href="#" class="list-group-item">删除用户</a>
            <a href="#" class="list-group-item">用户列表</a>
        </div>

        <div class="list-group">
            <a href="#" class="list-group-item active">
                产品管理
            </a>
            <a href="#" class="list-group-item">增加产品</a>
            <a href="#" class="list-group-item">修改产品</a>
            <a href="#" class="list-group-item">删除产品</a>
            <a href="#" class="list-group-item">产品列表</a>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item active">
                品牌管理
            </a>
            <a href="#" class="list-group-item">增加品牌</a>
            <a href="#" class="list-group-item">修改品牌</a>
            <a href="#" class="list-group-item">删除品牌</a>
            <a href="#" class="list-group-item">品牌列表</a>
        </div>

        <div class="list-group">
            <a href="#" class="list-group-item active">
                日志管理
            </a>
            <a href="#" class="list-group-item">增加日志</a>
            <a href="#" class="list-group-item">修改日志</a>
            <a href="#" class="list-group-item">删除日志</a>
            <a href="#" class="list-group-item">日志列表</a>
        </div>
    </div>

    <div class="col-xs-6 col-md-10">
        <form class="form-horizontal" role="form">
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
								<i class="glyphicon glyphicon-calendar"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxPrice" name="maxPrice"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">创建时间</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control" id="minCreateTime" onclick="WdatePicker()" name="minCreateTime"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxCreateTime" onclick="WdatePicker()" name="maxCreateTime"/>
                        </div>
                    </div>
                    <label class="col-sm-2 control-label">修改时间</label>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input type="text" class="input-sm form-control" id="minUpdateTime" onclick="WdatePicker()" name="minUpdateTime"/>
                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
                            </span>
                            <input type="text" class="input-sm form-control" id="maxUpdateTime" onclick="WdatePicker()" name="maxUpdateTime"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品品牌</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="selectid" onchange="findBrand();" name="brand.id">
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
            <button type="button" class="btn btn-info">
                <span class="glyphicon glyphicon-plus"></span>
                添加产品
            </button>

            <button type="button" class="btn btn-danger">
                <span class="glyphicon glyphicon-remove"></span>
                批量删除
            </button>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading ">
                产品列表
            </div>
            <div class="table-responsive">
                <table id="example" class="table table-striped table-bordered" style="width:100%">
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

    $(document).ready(function () {
        $('#example').DataTable({
            "autoWidth": true,//设置自适应宽度
            "info": true,//展示左下角信息
            "lengthChange": true,//是否允许改变每页条数
            "lengthMenu": [ 5, 10, 15, 30, 45],//设置每页条数
            "paging": true,//是否开启分页
            "processing": true,//页面加载慢的时候展示处理状态
            "serverSide": true,//时候开服务器模式
            "bFilter": false,
            "ajax": {
                "url": "queryProduct.jhtml",
                "type": "POST",
                "data":function (a) {
                    a.productName = $("#productName").val();
                    a.minPrice = $("#minPrice").val();
                    a.maxPrice = $("#maxPrice").val();
                    a.minCreateTime = $("#minCreateTime").val();
                    a.maxCreateTime = $("#maxCreateTime").val();
                    a.minUpdateTime = $("#minUpdateTime").val();
                    a.maxUpdateTime = $("#maxUpdateTime").val();
                    a.brandid = $("#selectid").val();
                }
            },
            "columns": [
                {
                    "data": "id",
                    render: function (data, type, row, meta) {
                        return "<input type='checkbox' value='" + data + "' name='checkid' id='productTable"+data.id+"'>";
                    }
                },
                {"data": "productName"},
                {"data": "productPrice"},
                {
                    "data": "createTime",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd");
                        }
                        return data;
                    }
                },
                {
                    "data": "updateTime",
                    render: function (data, type, row, meta) {
                        if (data != null) {
                            return (new Date(data)).Format("yyyy-MM-dd");
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
                        buttons += '<button type="button" class="btn btn-danger" href="javascript:;" onclick="updateProduct(\'' + row.id + '\')" ><span class="glyphicon glyphicon-pencil"></span>\n' +
                            '                                    修改</button>';
                        return buttons;
                    }
                }
            ],
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            }
        });
    });


    /* 下拉列表框 */
    $(function(){
        initBrandTable();
    })

    /* 条件查询 */
    function search(){
        /*不再进行表格的重新重绘*/
        $('#example').dataTable().fnDraw(false);
    }

    function findBrand(){
        search();
    }
</script>
</html>
