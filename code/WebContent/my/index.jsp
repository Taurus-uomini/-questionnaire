<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="../css/myindex.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script lang="javascript" type="text/javascript" src="../js/myindex.js"></script>
<script lang="javascript" type="text/javascript" src="../js/ajaxfileupload.js"></script>
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
    				<h3 class="panel-title">我的信息</h3>
  				</div>			
				<div class="panel-body">
					<div class="top">
						<img src="../user/getphoto.do?id=${requestScope.userInfo.id }" width="50px" />
    					<p>用户id：${requestScope.userInfo.id }</p>
    					<p>用户名：${requestScope.userInfo.username }</p>
    					<p>用户e-mail：${requestScope.userInfo.email }</p>
					</div>
					<div class="b_right">
						<p><c:if test="${!empty requestScope.userInfo.psignature }">${requestScope.userInfo.psignature }</c:if><c:if test="${empty requestScope.userInfo.psignature }">这人很懒，什么也没留下：（</c:if></p>
					</div>
					<div class="footer">
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editpass">修改密码</button>
  							<button type="button" class="btn btn-primary" id="addsurvey">设计问卷</button>
  							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editinfo">编辑信息</button>
						</div>
					</div>
					<div class="modal fade" id="editpass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  						<div class="modal-dialog">
    						<div class="modal-content">
      						<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
        							<h4 class="modal-title" id="myModalLabel">修改密码</h4>
      						</div>
      						<div class="modal-body">
        							<input type="password" class="form-control editpassinput" id="oldpassword" placeholder="旧密码"/>
        							<input type="password" class="form-control editpassinput" id="newpassword" placeholder="新密码"/>
        							<input type="password" class="form-control editpassinput" id="renewpassword" placeholder="重复新密码"/>
      						</div>
      						<div class="modal-footer">
        							<button type="button" class="btn btn-default" data-dismiss="modal" id="editpassclose">关闭</button>
        							<button type="button" class="btn btn-primary" id="editpasssave">保存密码</button>
      						</div>
    						</div>
  						</div>
					</div>
					<div class="modal fade" id="editinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  						<div class="modal-dialog">
    						<div class="modal-content">
      						<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
        							<h4 class="modal-title" id="myModalLabel">编辑信息</h4>
      						</div>
      						<div class="modal-body">
      							<p>用户头像：<img src="../user/getphoto.do?id=${requestScope.userInfo.id }" width="50px" id="userphoto" /></p>
    								<input type="file" name="photo" id="inputfile" />
    								<input type="hidden" name="hasfile" value="0" id="hasfile" />
        							<textarea class="form-control" rows="3" id="psignature"><c:if test="${!empty requestScope.userInfo.psignature }">${requestScope.userInfo.psignature }</c:if><c:if test="${empty requestScope.userInfo.psignature }">这人很懒，什么也没留下：（</c:if></textarea>
      						</div>
      						<div class="modal-footer">
        							<button type="button" class="btn btn-default" data-dismiss="modal" id="editinfoclose">关闭</button>
        							<button type="button" class="btn btn-primary" id="editinfosave">保存信息</button>
      						</div>
    						</div>
  						</div>
					</div>
  				</div>
			</div>
		</div>
	</div>
</body>
</html>