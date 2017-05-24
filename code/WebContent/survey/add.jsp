<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问卷设计</title>
<link rel="stylesheet" type="text/css" href="../css/surveyadd.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../js/icheck/skins/all.css" rel="stylesheet">
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script lang="javascript" type="text/javascript" src="../js/surveyadd.js"></script>
<script lang="javascript" type="text/javascript" src="../js/icheck/icheck.min.js"></script>
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
		<form action="add.do" method="post" id="form">
		<div class=center>
			<div class="page-header">
  				<h1><input type="text" name="title" id="title" /></h1>
			</div>
		</div>
		<div class="end">
			<div class="btn-group">
  				<button type="button" class="btn btn-primary" id="addquestion">添加问题</button>
  				<button type="button" class="btn btn-primary" id="addcheckbox">添加复选框</button>
  				<button type="button" class="btn btn-primary" id="addradio">添加单选框</button>
  				<div class="btn-group dropup">
  				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">改变选框样式 <span class="caret"></span></button>
				<ul class="dropdown-menu" role="menu">
    				<li><a onclick="changeichicktype('flat');">flat</a></li>
    				<li><a onclick="changeichicktype('minimal');">minimal</a></li>
    				<li><a onclick="changeichicktype('square');">square</a></li>
  				</ul>
  				</div>
  				<div class="btn-group dropup">
  				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">改变选框颜色 <span class="caret"></span></button>
				<ul class="dropdown-menu" role="menu">
    				<li><a onclick="changeichickcolor('green');">green</a></li>
    				<li><a onclick="changeichickcolor('orange');">orange</a></li>
    				<li><a onclick="changeichickcolor('pink');">pink</a></li>
    				<li><a onclick="changeichickcolor('blue');">blue</a></li>
    				<li><a onclick="changeichickcolor('aero');">aero</a></li>
    				<li><a onclick="changeichickcolor('red');">red</a></li>
  				</ul>
  				</div>
  				<div class="btn-group">
  					<button type="button" class="btn btn-primary" id="save">保存</button>
  				</div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>