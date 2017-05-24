<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问卷</title>
<link rel="stylesheet" type="text/css" href="../css/surveyadd.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../js/icheck/skins/all.css" rel="stylesheet">
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script lang="javascript" type="text/javascript" src="../js/surveyadd.js"></script>
<script lang="javascript" type="text/javascript" src="../js/icheck/icheck.min.js"></script>
<script>
	$(document).ready(function()
	{
		$('input').iCheck({
	  		checkboxClass: 'icheckbox_${requestScope.sm.ichecktype }-${requestScope.sm.icheckcolor }',
	  		radioClass: 'iradio_${requestScope.sm.ichecktype }-${requestScope.sm.icheckcolor }',
	  		increaseArea: '20%' // optional
	  	});
	});
 </script>
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
			<div class="page-header">
  				<h1>${requestScope.sm.title }</h1>
			</div>
			<c:forEach var="vq" items="${requestScope.sm.sqm }"  varStatus="v">
			<div class="panel panel-primary">
				<div id="questiond1">
					<div class="panel-heading">
						<h3>${vq.question }</h3>
					</div>
					<div class="panel-body">
						<c:forEach var="vi" items="${vq.sim }">
						<div class="chose">
							<c:if test="${vi.type==1 }">
								<input type="checkbox" name="q1checkbox${v.index }" vlaue="1">
							</c:if>
							<c:if test="${vi.type==2 }">
								<input type="radio" name="q1radio${v.index }" vlaue="1">
							</c:if>
							<p class="icheckinput">${vi.content }</p>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>