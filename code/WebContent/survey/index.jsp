<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问卷管理</title>
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
			<table class="table table-hover">
				<tr>
					<td>问卷id</td>
					<td>问卷题目</td>
					<td>操作</td>
				</tr>
				<c:if test="${!empty requestScope.list }">
					<c:forEach var="value" items="${requestScope.list }">
						<tr>
							<td>${value.id }</td>
							<td>${value.title }</td>
							<td>
								<div class="btn-group">
  									<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    									操作 <span class="caret"></span>
  									</button>
  									<ul class="dropdown-menu" role="menu">
    									<li><a href="show.do?id=${value.id }">查看</a></li>
    									<li><a href="delete.do?id=${value.id}">删除</a></li>
  									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
</body>
</html>