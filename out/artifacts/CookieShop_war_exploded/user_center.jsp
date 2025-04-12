<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>个人中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/simpleCart.min.js"></script>
	<script type="text/javascript">
		function preview(obj) {
			var img = document.getElementById("userAvatar");
			img.src = window.URL.createObjectURL(obj.files[0]);
		}
	</script>
</head>
<body>
<jsp:include page="/header.jsp">
	<jsp:param value="4" name="flag"/>
</jsp:include>
<c:if test="${empty user}"><%response.sendRedirect("/index");%></c:if>
<!--account-->
<div class="account">
	<div class="container">
		<div class="register">
			<c:if test="${!empty msg }">
				<div class="alert alert-success">${msg }</div>
			</c:if>
			<c:if test="${!empty failMsg }">
				<div class="alert alert-danger">${failMsg }</div>
			</c:if>

			<div class="register-top-grid">
				<h3>个人中心</h3>
				<h4>用户头像</h4>
				<c:if test="${not empty currentAvatarPath}">
					<img id="userAvatar" src="${pageContext.request.contextPath}/${currentAvatarPath}" alt="User Avatar" width="100" height="100">
				</c:if>
				<c:if test="${empty currentAvatarPath}">
					<img id="userAvatar" src="/picture/touxiang.jpg" alt="User Avatar" width="100" height="100">
				</c:if>
				<form action="userAvatar" method="post" enctype="multipart/form-data">
					<input type="file" name="avatar" onChange="preview(this)">
					<div class="register-but text-center">
						<input type="submit" value="Upload Avatar">
					</div>
				</form>
				<script>
					$(document).ready(function () {
						console.log("当前头像路径: ", "${currentAvatarPath}");
						if ("${currentAvatarPath}" === "") {
							console.log("头像路径为空，将显示默认头像");
						} else {
							console.log("头像路径非空，将尝试显示此路径对应的头像");
						}
					});
				</script>
				<form action="/user_changeaddress" method="post">
					<!-- 收货信息 start -->
					<h4>收货信息</h4>
					<div class="input">
						<span>收货人<label></label></span>
						<input type="text" name="name" value="${user.name }" placeholder="请输入收货">
					</div>
					<div class="input">
						<span>收货电话</span>
						<input type="text" name="phone" value="${user.phone }" placeholder="请输入收货电话">
					</div>
					<div class="input">
						<span>收货地址</span>
						<input type="text" name="address" value="${user.address }" placeholder="请输入收货地址">
					</div>
					<div class="register-but text-center">
						<input type="submit" value="提交">
					</div>
					<!-- 收货信息 end -->
				</form>
				<hr>
				<form action="/user_changepwd" method="post">
					<h4>安全信息</h4>
					<div class="input">
						<span>原密码</span>
						<input type="text" name="password" placeholder="请输入原密码");
					</div>
					<div class="input">
						<span>新密码</span>
						<input type="text" name="newPassword" placeholder="请输入新密码");
					</div>
					<div class="clearfix"> </div>
					<div class="register-but text-center">
						<input type="submit" value="提交");
					</div>
				</form>
			</div>

			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--//account-->




<jsp:include page="/footer.jsp"></jsp:include>


</body>
</html>