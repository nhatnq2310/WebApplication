<%-- 
    Document   : CreateAccount
    Created on : Mar 17, 2025, 4:07:51 PM
    Author     : AMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}" />
            Username* <input type="text" name="txtUserName" value="${param.txtUserName}" />(6-30 char)</br>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6-30 char)</br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm*  <input type="password" name="txtConfirm" value="" />(6-30 char)</br>
            <c:if test="${not empty errors.confirmNotMatchedErr}">
                <font color="red">
                ${errors.confirmNotMatchedErr}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(6-30 char)</br>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction"/>
            <input type="reset" value="Reset"/>
        </form>

    </body>
</html>
