<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script lang="javascript" type="text/javascript" src="../js/index.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<div class="header">
		<%@ include file="../include/header.jsp" %>
	</div>
	<div class="main">
		<div class="left">
			<%@ include file="../include/left.jsp" %>
		</div>
		<div class=center>
			<div class="panel panel-info">
				<div class="panel-heading">
    				<h3 class="panel-title">用户信息</h3>
  				</div>
				<c:if test="${!empty requestScope.user }">
					<div class="panel-body">
    					<p>用户id：${requestScope.user.id }</p>
    					<p>用户名：${requestScope.user.username }</p>
    					<p>用户e-mail：${requestScope.user.email }</p>
    					<p>个性签名：<c:if test="${!empty requestScope.user.psignature }">${requestScope.user.psignature }</c:if><c:if test="${empty requestScope.user.psignature }">这人很懒，什么也没留下：（</c:if></p>
    					<p>用户头像：<img src="getphoto.do?id=${requestScope.user.id }" width="50px" /></p>
  					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>