<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="title.forgot.password"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="sign-in">
    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_SEND_KEY}"/>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.email!=null}">
                        <fmt:message key="message.invalid.user.email"/>
                    </c:if>
                </div>
                <div class="input-container">
                    <input type="text" name="${AttributeParameterKey.PARAMETER_EMAIL}" required=""/>
                    <label><fmt:message key="label.email"/></label>
                </div>
            </div>
            <button class="btn send"><fmt:message key="button.send.key"/></button>
        </form>
    </div>
</div>
</body>
</html>
