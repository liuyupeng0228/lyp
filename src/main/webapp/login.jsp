<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
    <link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css">
    <link href="<%=request.getContextPath()%>/js/datatable/css/dataTables.bootstrap.min.css"
          rel="stylesheet" type="text/css">
<style type="text/css">
body {
  background: radial-gradient(ellipse at bottom, #1b2735 0%, #090a0f 100%);
  height: 100vh;
  overflow: hidden;
  display: flex;
  font-family: 'Anton', sans-serif;
  justify-content: center;
  align-items: center;
}

.night {
  position: relative;
  width: 100%;
  height: 100%;
  -webkit-transform: rotateZ(45deg);
          transform: rotateZ(45deg);
}

.shooting_star {
  position: absolute;
  left: 50%;
  top: 50%;
  height: 2px;
  background: linear-gradient(-45deg, #5f91ff, rgba(0, 0, 255, 0));
  border-radius: 999px;
  -webkit-filter: drop-shadow(0 0 6px #699bff);
          filter: drop-shadow(0 0 6px #699bff);
  -webkit-animation: tail 3000ms ease-in-out infinite, shooting 3000ms ease-in-out infinite;
          animation: tail 3000ms ease-in-out infinite, shooting 3000ms ease-in-out infinite;
}
.shooting_star::before, .shooting_star::after {
  content: '';
  position: absolute;
  top: calc(50% - 1px);
  right: 0;
  height: 2px;
  background: linear-gradient(-45deg, rgba(0, 0, 255, 0), #5f91ff, rgba(0, 0, 255, 0));
  -webkit-transform: translateX(50%) rotateZ(45deg);
          transform: translateX(50%) rotateZ(45deg);
  border-radius: 100%;
  -webkit-animation: shining 3000ms ease-in-out infinite;
          animation: shining 3000ms ease-in-out infinite;
}
.shooting_star::after {
  -webkit-transform: translateX(50%) rotateZ(-45deg);
          transform: translateX(50%) rotateZ(-45deg);
}
.shooting_star:nth-child(1) {
  top: calc(50% - 200px);
  left: calc(50% - 105px);
  -webkit-animation-delay: 9592ms;
          animation-delay: 9592ms;
}
.shooting_star:nth-child(1)::before, .shooting_star:nth-child(1)::after, .shooting_star:nth-child(1)::after {
  -webkit-animation-delay: 9592ms;
          animation-delay: 9592ms;
}
.shooting_star:nth-child(2) {
  top: calc(50% - -18px);
  left: calc(50% - 87px);
  -webkit-animation-delay: 5908ms;
          animation-delay: 5908ms;
}
.shooting_star:nth-child(2)::before, .shooting_star:nth-child(2)::after, .shooting_star:nth-child(2)::after {
  -webkit-animation-delay: 5908ms;
          animation-delay: 5908ms;
}
.shooting_star:nth-child(3) {
  top: calc(50% - 131px);
  left: calc(50% - 81px);
  -webkit-animation-delay: 1191ms;
          animation-delay: 1191ms;
}
.shooting_star:nth-child(3)::before, .shooting_star:nth-child(3)::after, .shooting_star:nth-child(3)::after {
  -webkit-animation-delay: 1191ms;
          animation-delay: 1191ms;
}
.shooting_star:nth-child(4) {
  top: calc(50% - 178px);
  left: calc(50% - 252px);
  -webkit-animation-delay: 9638ms;
          animation-delay: 9638ms;
}
.shooting_star:nth-child(4)::before, .shooting_star:nth-child(4)::after, .shooting_star:nth-child(4)::after {
  -webkit-animation-delay: 9638ms;
          animation-delay: 9638ms;
}
.shooting_star:nth-child(5) {
  top: calc(50% - 54px);
  left: calc(50% - 207px);
  -webkit-animation-delay: 2554ms;
          animation-delay: 2554ms;
}
.shooting_star:nth-child(5)::before, .shooting_star:nth-child(5)::after, .shooting_star:nth-child(5)::after {
  -webkit-animation-delay: 2554ms;
          animation-delay: 2554ms;
}
.shooting_star:nth-child(6) {
  top: calc(50% - 170px);
  left: calc(50% - 257px);
  -webkit-animation-delay: 2165ms;
          animation-delay: 2165ms;
}
.shooting_star:nth-child(6)::before, .shooting_star:nth-child(6)::after, .shooting_star:nth-child(6)::after {
  -webkit-animation-delay: 2165ms;
          animation-delay: 2165ms;
}
.shooting_star:nth-child(7) {
  top: calc(50% - -93px);
  left: calc(50% - 69px);
  -webkit-animation-delay: 9997ms;
          animation-delay: 9997ms;
}
.shooting_star:nth-child(7)::before, .shooting_star:nth-child(7)::after, .shooting_star:nth-child(7)::after {
  -webkit-animation-delay: 9997ms;
          animation-delay: 9997ms;
}
.shooting_star:nth-child(8) {
  top: calc(50% - 106px);
  left: calc(50% - 99px);
  -webkit-animation-delay: 422ms;
          animation-delay: 422ms;
}
.shooting_star:nth-child(8)::before, .shooting_star:nth-child(8)::after, .shooting_star:nth-child(8)::after {
  -webkit-animation-delay: 422ms;
          animation-delay: 422ms;
}
.shooting_star:nth-child(9) {
  top: calc(50% - -17px);
  left: calc(50% - 35px);
  -webkit-animation-delay: 2714ms;
          animation-delay: 2714ms;
}
.shooting_star:nth-child(9)::before, .shooting_star:nth-child(9)::after, .shooting_star:nth-child(9)::after {
  -webkit-animation-delay: 2714ms;
          animation-delay: 2714ms;
}
.shooting_star:nth-child(10) {
  top: calc(50% - -114px);
  left: calc(50% - 232px);
  -webkit-animation-delay: 6136ms;
          animation-delay: 6136ms;
}
.shooting_star:nth-child(10)::before, .shooting_star:nth-child(10)::after, .shooting_star:nth-child(10)::after {
  -webkit-animation-delay: 6136ms;
          animation-delay: 6136ms;
}
.shooting_star:nth-child(11) {
  top: calc(50% - 175px);
  left: calc(50% - 73px);
  -webkit-animation-delay: 9617ms;
          animation-delay: 9617ms;
}
.shooting_star:nth-child(11)::before, .shooting_star:nth-child(11)::after, .shooting_star:nth-child(11)::after {
  -webkit-animation-delay: 9617ms;
          animation-delay: 9617ms;
}
.shooting_star:nth-child(12) {
  top: calc(50% - -137px);
  left: calc(50% - 300px);
  -webkit-animation-delay: 8175ms;
          animation-delay: 8175ms;
}
.shooting_star:nth-child(12)::before, .shooting_star:nth-child(12)::after, .shooting_star:nth-child(12)::after {
  -webkit-animation-delay: 8175ms;
          animation-delay: 8175ms;
}
.shooting_star:nth-child(13) {
  top: calc(50% - 64px);
  left: calc(50% - 125px);
  -webkit-animation-delay: 2715ms;
          animation-delay: 2715ms;
}
.shooting_star:nth-child(13)::before, .shooting_star:nth-child(13)::after, .shooting_star:nth-child(13)::after {
  -webkit-animation-delay: 2715ms;
          animation-delay: 2715ms;
}
.shooting_star:nth-child(14) {
  top: calc(50% - 144px);
  left: calc(50% - 266px);
  -webkit-animation-delay: 9024ms;
          animation-delay: 9024ms;
}
.shooting_star:nth-child(14)::before, .shooting_star:nth-child(14)::after, .shooting_star:nth-child(14)::after {
  -webkit-animation-delay: 9024ms;
          animation-delay: 9024ms;
}
.shooting_star:nth-child(15) {
  top: calc(50% - -70px);
  left: calc(50% - 111px);
  -webkit-animation-delay: 4994ms;
          animation-delay: 4994ms;
}
.shooting_star:nth-child(15)::before, .shooting_star:nth-child(15)::after, .shooting_star:nth-child(15)::after {
  -webkit-animation-delay: 4994ms;
          animation-delay: 4994ms;
}
.shooting_star:nth-child(16) {
  top: calc(50% - -147px);
  left: calc(50% - 196px);
  -webkit-animation-delay: 6546ms;
          animation-delay: 6546ms;
}
.shooting_star:nth-child(16)::before, .shooting_star:nth-child(16)::after, .shooting_star:nth-child(16)::after {
  -webkit-animation-delay: 6546ms;
          animation-delay: 6546ms;
}
.shooting_star:nth-child(17) {
  top: calc(50% - -49px);
  left: calc(50% - 240px);
  -webkit-animation-delay: 895ms;
          animation-delay: 895ms;
}
.shooting_star:nth-child(17)::before, .shooting_star:nth-child(17)::after, .shooting_star:nth-child(17)::after {
  -webkit-animation-delay: 895ms;
          animation-delay: 895ms;
}
.shooting_star:nth-child(18) {
  top: calc(50% - 171px);
  left: calc(50% - 3px);
  -webkit-animation-delay: 4390ms;
          animation-delay: 4390ms;
}
.shooting_star:nth-child(18)::before, .shooting_star:nth-child(18)::after, .shooting_star:nth-child(18)::after {
  -webkit-animation-delay: 4390ms;
          animation-delay: 4390ms;
}
.shooting_star:nth-child(19) {
  top: calc(50% - -57px);
  left: calc(50% - 58px);
  -webkit-animation-delay: 5414ms;
          animation-delay: 5414ms;
}
.shooting_star:nth-child(19)::before, .shooting_star:nth-child(19)::after, .shooting_star:nth-child(19)::after {
  -webkit-animation-delay: 5414ms;
          animation-delay: 5414ms;
}
.shooting_star:nth-child(20) {
  top: calc(50% - 109px);
  left: calc(50% - 174px);
  -webkit-animation-delay: 9384ms;
          animation-delay: 9384ms;
}
.shooting_star:nth-child(20)::before, .shooting_star:nth-child(20)::after, .shooting_star:nth-child(20)::after {
  -webkit-animation-delay: 9384ms;
          animation-delay: 9384ms;
}
@-webkit-keyframes tail {
  0% {
    width: 0;
  }
  30% {
    width: 100px;
  }
  100% {
    width: 0;
  }
}
@keyframes tail {
  0% {
    width: 0;
  }
  30% {
    width: 100px;
  }
  100% {
    width: 0;
  }
}
@-webkit-keyframes shining {
  0% {
    width: 0;
  }
  50% {
    width: 30px;
  }
  100% {
    width: 0;
  }
}
@keyframes shining {
  0% {
    width: 0;
  }
  50% {
    width: 30px;
  }
  100% {
    width: 0;
  }
}
@-webkit-keyframes shooting {
  0% {
    -webkit-transform: translateX(0);
            transform: translateX(0);
  }
  100% {
    -webkit-transform: translateX(300px);
            transform: translateX(300px);
  }
}
@keyframes shooting {
  0% {
    -webkit-transform: translateX(0);
            transform: translateX(0);
  }
  100% {
    -webkit-transform: translateX(300px);
            transform: translateX(300px);
  }
}
@-webkit-keyframes sky {
  0% {
    -webkit-transform: rotate(45deg);
            transform: rotate(45deg);
  }
  100% {
    -webkit-transform: rotate(405deg);
            transform: rotate(405deg);
  }
}
@keyframes sky {
  0% {
    -webkit-transform: rotate(45deg);
            transform: rotate(45deg);
  }
  100% {
    -webkit-transform: rotate(405deg);
            transform: rotate(405deg);
  }
}
/* 按钮样式 */
.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
.button4 {border-radius: 12px;}
/* 文本框 */
.input_control{
  width:260px;
  margin:20px auto;
}
input[type="text"],#btn1,#btn2{
  box-sizing: border-box;
  text-align:center;
  font-size:1.4em;
  height:2.7em;
  border-radius:4px;
  border:1px solid #c8cccf;
  color:#6a6f77;
  -web-kit-appearance:none;
  -moz-appearance: none;
  display:block;
  outline:0;
  padding:0 1em;
  text-decoration:none;
  width:100%;
}
input[type="text"]:focus{
  border:1px solid #ff7496;
}
/* 密码框 */
input[type="password"],#btn1,#btn2{
  box-sizing: border-box;
  text-align:center;
  font-size:1.4em;
  height:2.7em;
  border-radius:4px;
  border:1px solid #c8cccf;
  color:#6a6f77;
  -web-kit-appearance:none;
  -moz-appearance: none;
  display:block;
  outline:0;
  padding:0 1em;
  text-decoration:none;
  width:100%;
}
input[type="text"]:focus{
  border:1px solid #ff7496;
}
/* 加深input框颜色 */
::-moz-placeholder { /* Mozilla Firefox 4 to 18 */
  color: #6a6f77;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
  color: #6a6f77;
}
input::-webkit-input-placeholder{
  color: #6a6f77;
}
</style>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/js/datatable/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootbox/bootbox.locales.min.js"></script>
    <center>
            <div class="night">
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
              <div class="shooting_star"></div>
            </div>

            <form>
                <input type="text" class="form_input" id="username" placeholder="账号"/><br>
                <input type="password" class="form_input" id="password" placeholder="密码"/><br>
                <input type="text" class="form_input" id="img" placeholder="验证码"/><br>
                <img src="<%=request.getContextPath()%>/imgcode" id="refash">
                <input type="button" class="button button4" value="刷新" onclick="refresh();"><br>
                <input type="button" class="button button4" value="登录" onclick="login();"/>
                <input type="reset" class="button button4" value="重置"/><br>
                <input type="button" class="button button4" value="注册" onclick="toAddUser();"/>
            </form>

</center>
</div>
<script type="text/javascript" src="<%=basePath%>/commons/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/commons/md5.js"></script>
<script type="text/javascript">
  function refresh(){
     $("#refash").attr("src","<%=request.getContextPath()%>/imgcode?"+Math.random());
  }


function login(){
	var v_username = $("#username").val();
	var v_password = hex_md5($("#password").val());
	var v_imgcode = $("#img").val();
	if (v_username == null || v_username == "") {
		alert("用户名不可为空，请输入用户名！");
		return false;
	}
	if (v_password == null || v_password == "") {
		alert("密码不可为空，请输入密码！");
		return false;
	}
	if (v_imgcode == null || v_imgcode == ""){
	    alert("验证码不可为空，请输入验证码！")
    }
	
	$.ajax({
		url:"<%=basePath%>/loginController/login.jhtml",
		type:"post",
		datatype:"json",
		data:{
			"username":v_username,
			"password":v_password,
            "imgcode":v_imgcode
		},
		success:function(result){
			if(result.code==200){
				bootbox.alert(result.msg);
				location.href="toProductList.jhtml";
			}
			else{
				bootbox.alert(result.msg);
			}
		}
	})
}

    function toAddUser(){
      location.href="<%=request.getContextPath()%>/toAddUser.jhtml";
    }
</script>
</body>
</html>