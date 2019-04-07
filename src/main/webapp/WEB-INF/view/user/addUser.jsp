<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<div class="row">
    <div class="col-xs-12 col-md-12">
        <form class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>用户注册</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名称</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="username" id="username" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户密码</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="password" id="password" type="text"/>
                    </div>
                </div>
            </fieldset>
            <div class="clearfix form-actions" style="text-align:center">
                <button class="btn btn-primary" type="button" onclick="addUser();">
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
    function addUser() {
        var v_username = $("#username").val();
        var v_password = $("#password").val();
        if (v_username == null || v_username == "") {
            alert("用户名不可为空，请输入用户名！");
            return false;
        }
        if (v_password == null || v_password == "") {
            alert("密码不可为空，请输入密码！");
            return false;
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/addUser.jhtml",
            type: "post",
            datatype: "json",
            data: {
                "username": v_username,
                "password": v_password
            },
            success: function (result) {
                if (result.code == 300) {
                    alert(result.msg);
                    location.href = "<%=request.getContextPath()%>/toLogin.jhtml";
                }
                else {
                    alert(result.msg);
                }
            }
        })
    }
</script>
</html>
