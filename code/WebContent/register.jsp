<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setBundle basename="com.city.javaEEdzy.language.messages" var="mes"/>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title><fmt:message key="com.city.javaEEdzy.register.title" bundle="${mes}"/></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <script src="js/jquery-3.1.1.min.js"></script>
        <script lang="javascript" type="text/javascript" src="js/register.js"></script>
    </head>
    <body onload="lookerr();">
        <div id="form_div">
            <form action="register.do" method="POST" onsubmit="return check(this);" enctype="multipart/form-data">
                <input type="text" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="username" placeholder="<fmt:message key="com.city.javaEEdzy.register.inputusername" bundle="${mes}"/>" />
                <br />
                <input type="password" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="pas" placeholder="<fmt:message key="com.city.javaEEdzy.register.inputpassword" bundle="${mes}"/>" />
                <br />
                <input type="password" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="pasconfirm" placeholder="<fmt:message key="com.city.javaEEdzy.register.inputpasswordconfirm" bundle="${mes}"/>" />
                <br />
                <input type="text" class="input_text" onfocus="input_text_onfocus(this);" onblur="input_text_onblur(this);" name="email" placeholder="<fmt:message key="com.city.javaEEdzy.register.inputemail" bundle="${mes}"/>" />
                <br />
                <label for="photo" id="pho">
                	<fmt:message key="com.city.javaEEdzy.register.photo" bundle="${mes}"/>
                </label>
                <input id="photo" type="file" name="photo" />
                <br />
                <input type="hidden" name="hasfile" value="0" id="hasfile" />
                <input type="submit" class="input_button" value="<fmt:message key="com.city.javaEEdzy.register.submit" bundle="${mes}"/>" />    
                <br />
                <a href="login.jsp"><fmt:message key="com.city.javaEEdzy.register.login" bundle="${mes}"/></a> 
            </form>
        </div>
    </body>
</html>