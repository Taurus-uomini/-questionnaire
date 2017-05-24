<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse" role="navigation">
    <ul class="nav navbar-nav">
        <li class="active"><a href="/javaEEdzy/index.do">首页</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
    	<a href="" class="dropdown-toggle" data-toggle="dropdown">
    		<c:if test="${!empty requestScope.userInfo }">${requestScope.userInfo.username }</c:if>
    	    <span class="caret"></span>
        </a>
    	<ul class="dropdown-menu" role="menu">
    		<li><a href="/javaEEdzy/logout.do">注销</a></li>
    	</ul>
    </li>
</ul>
</nav>