<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav nav-pills nav-stacked " role="tablist">
	<c:if test="${!empty requestScope.menu }">
		<c:forEach var="value" items="${requestScope.menu }">
			<c:if test="${requestScope.userInfo.type<=value.power }">
				<li role="presentation"<c:if test="${value.active==1 }">class="active"</c:if> ><a href="${value.uri }">${value.title }</a></li>
			</c:if>
		</c:forEach>
	</c:if>
</ul>