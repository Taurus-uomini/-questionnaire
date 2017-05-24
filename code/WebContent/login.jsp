<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setBundle basename="com.city.javaEEdzy.language.messages" var="mes"/>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title><fmt:message key="com.city.javaEEdzy.login.title" bundle="${mes}"/></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <script lang="javascript" type="text/javascript" src="js/login.js"></script>
    </head>
    <body onload="lookerr();">
        <div id="form_div">
            <form action="login.do" method="POST" onsubmit="return check(this);">
                <input type="text" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="username" placeholder="<fmt:message key="com.city.javaEEdzy.login.inputusername" bundle="${mes}"/>" />
                <br />
                <input type="password" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="pas" placeholder="<fmt:message key="com.city.javaEEdzy.login.inputpassword" bundle="${mes}"/>" />
                <br />
                <label for="saveme">
                    <fmt:message key="com.city.javaEEdzy.login.saveme" bundle="${mes}"/>
                </label>
                <input type="checkbox" id="saveme" name="saveme" value="1">
                <br />
                <input type="submit" class="input_button" value="<fmt:message key="com.city.javaEEdzy.login.submit" bundle="${mes}"/>" />   
                <br />
                <a href="register.jsp"><fmt:message key="com.city.javaEEdzy.login.register" bundle="${mes}"/></a> 
            </form>
        </div>
    </body>
</html>