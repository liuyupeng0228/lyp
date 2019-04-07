<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>商品列表</title>
    <link href="<%=basePath%>/commons/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>/commons/datatable/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>

</head>
<body>
    <table id="productInfo" class="table table-striped table-bordered" style="width:100%"></table>
    <script type="text/javascript" src="<%=basePath%>/commons/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>/commons/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/commons/datatable/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/commons/datatable/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/commons/dateFormat.js"></script>
    <script type="text/javascript" src="<%=basePath%>/commons/bootbox/bootbox.min.js"></script>
</body>
<script type="text/javascript">
    var productTable=$("#productInfo").dataTable({
        "autoWidth": true,//设置自适应宽度
        "info": true,//展示左下角信息
        "lengthChange": true,//是否允许改变每页条数
        "lengthMenu": [ 5, 10, 15, 20,25,30 ],//设置每页条数
        "paging": true,//是否开启分页
        "processing": true,//页面加载慢的时候展示处理状态
        "serverSide": true,//时候开服务器模式
        "ajax":{
            "url":"<%=request.getContextPath()%>/queryDataProduct.jhtml",
            "type":"post",
            "contentType": "application/json",
            "dataSrc":function(result){
                console.log(result.data)
                result.draw=result.draw;
                result.recordsFiltered = result.recordsFiltered;
                //总条数
                result.recordsTotal = result.recordsTotal;
                return result.data;
            }
        },
        "columns":[
            {"data":"id","title":"主键编号",
                render:function(data, type, row, meta){
                    return "<input type='checkbox' value='"+data+"' name='checkid'>";
                }
            },
            {"data":"productName","title":"商品名称"},
            {"data":"productPrice","title":"商品价格"},
            {"data":"createTime","title":"创建时间",
                render:function(data, type, row, meta){
                    if(data!=null){
                        return (new Date(data)).Format("yyyy-MM-dd");
                    }
                    return data;
                }
            },
            {"data":"updateTime","title":"修改时间",
                render:function(data, type, row, meta){
                    if(data!=null){
                        return (new Date(data)).Format("yyyy-MM-dd");
                    }
                    return data;
                }
            },
            {"data":"brand.brandName","title":"商品品牌"},
            {"data":"imgurl","title":"商品图片",
                render:function (data,type,row,meta) {
                    return "<img src='<%=request.getContextPath()%>/"+data+"' width='50px'/>"
                }
            },
            {"title":"操作",
                render: function(data, type, row, meta) {
                    var buttons='';
                    buttons+='<a type="button"  href="javascript:;" onclick="deleteProduct(\''+row.id+'\')" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                    buttons+='<a type="button"  href="javascript:;" onclick="updateProduct(\''+row.id+'\')" >修改</a>';
                    return buttons;
                }
            }
        ],
        "language":{
            "url":"<%=request.getContextPath()%>/commons/datatable/Chinese.json"
        }
    })
</script>
</html>
