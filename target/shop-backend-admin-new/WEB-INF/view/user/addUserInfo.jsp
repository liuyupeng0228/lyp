<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <legend>用户信息添加</legend>
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
                <div class="form-group">
                    <label class="col-sm-2 control-label">真实姓名</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="realname" id="realname" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户性别</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="sex" id="sex" type="radio" value="0"/>女
                        <input class="form-control" name="sex" id="sex" type="radio" value="1"/>男
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户生日</label>
                    <div class="col-sm-4">
                        <input class="input-sm form-control form_datetime" name="birthday" id="birthday" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户薪资</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="salary" id="salary" type="text"/>
                    </div>
                </div>
            </fieldset>
            <div class="clearfix form-actions" style="text-align:center">
                <button class="btn btn-primary" type="button" onclick="addUserInfo();">
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
    function addUserInfo(){
        var v_username = $("#username").val();
        var v_password = $("#password").val();
        var v_realname = $("#realname").val();
        var v_birthday = $("#birthday").val();
        var v_sex = $("#sex").val();
        var v_salary = $("#salary").val();
        if (v_username == null || v_username == "") {
            alert("用户名不可为空，请输入用户名！");
            return false;
        }
        if (v_password == null || v_password == "") {
            alert("密码不可为空，请输入密码！");
            return false;
        }
        if (v_realname == null || v_realname == "") {
            alert("真实姓名不可为空，请输入真实姓名！");
            return false;
        }
        if (v_birthday == null || v_birthday == "") {
            alert("出生日期不可为空，请输入出生日期！");
            return false;
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/addUserInfo.jhtml",
            type: "post",
            datatype: "json",
            data: {
                "username": v_username,
                "password": v_password,
                "realname":v_realname,
                "sex":v_sex,
                "birthday":v_birthday,
                "salary":v_salary
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

    //时间格式
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd', language: 'zh-CN', showClear: true});
</script>
</html>
