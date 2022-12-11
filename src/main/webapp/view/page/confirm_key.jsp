<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="title.confirm.key"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="sign-in">
    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_CONFIRM_KEY}">
            <div class="input-container">
                <input style="margin-top: 20px" type="text" name="${AttributeParameterKey.PARAMETER_RECEIVED_KEY}"/>
                <label><fmt:message key="key.was.sent.to"/> ${sessionScope.email}</label>
                <c:if test="${requestScope.confirm_key_invalid!=null}">
                    <div class="text-danger">
                        <fmt:message key="message.invalid.confirm.key"/>
                        <br>
                    </div>
                </c:if>
                <br> <br>
            </div>
            <button type="submit"><fmt:message key="button.submit"/></button>
        </form>
    </div>

</div>
</body>
</html>
