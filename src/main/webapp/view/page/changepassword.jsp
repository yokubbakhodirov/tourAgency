<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="title.changePassword"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="sign-in">
    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_CHANGE_PASSWORD}">
            <div class="input-container">
                <label><fmt:message key="label.password"/></label>
                <input style="margin-top: 20px" type="password" class="form-control"
                       name="${AttributeParameterKey.PARAMETER_PASSWORD}">
                <div style="top: 5px" class="text-danger top">
                    <c:if test="${requestScope.form_invalid.password!=null}">
                        <fmt:message key="message.invalid.user.password"/>
                    </c:if>
                </div>
            </div>

            <div class="input-container">
                <label><fmt:message key="label.repeat.password"/></label>
                <input style="margin-top: 20px" type="password" class="form-control"
                       name="${AttributeParameterKey.PARAMETER_PASSWORD_REPEAT}">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.password_repeat!=null}">
                        <fmt:message key="message.invalid.user.password.repeat"/>
                    </c:if>
                </div>
            </div>
            <button type="submit" class="btn btn-custom-fill"><fmt:message key="button.password.change"/></button>
        </form>
    </div>
</div>

</body>
</html>
