<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.signUp"/></title>
</head>

<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="sign-in">

    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_SIGN_UP}">
            <span class="text-center"><fmt:message key="title.signUp"/></span>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.email!=null}">
                        <fmt:message key="message.invalid.user.email"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_EMAIL}" required=""/><br><br>
                <label><fmt:message key="label.email"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.password!=null}">
                        <fmt:message key="message.invalid.user.password"/>
                    </c:if>
                </div>
                <input type="password" name="${AttributeParameterKey.PARAMETER_PASSWORD}" required=""/><br><br>
                <label><fmt:message key="label.password"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.password_repeat!=null}">
                        <fmt:message key="message.invalid.user.password.repeat"/>
                    </c:if>
                </div>
                <input type="password" name="${AttributeParameterKey.PARAMETER_PASSWORD_REPEAT}" required=""/><br><br>
                <label><fmt:message key="label.repeat.password"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.name!=null}">
                        <fmt:message key="message.invalid.user.name"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_NAME}" required=""/><br><br>
                <label><fmt:message key="label.name"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.lastname!=null}">
                        <fmt:message key="message.invalid.user.lastname"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_LASTNAME}" required=""/><br><br>
                <label><fmt:message key="label.lastname"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.phone!=null}">
                        <fmt:message key="message.invalid.user.phone"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_PHONE}" required=""/><br><br>
                <label><fmt:message key="label.phone"/></label>
            </div>
            <div class="text-danger">
                <c:if test="${requestScope.user_exists!=null}">
                    <fmt:message key="message.invalid.user.exists.signUp"/>
                </c:if>
            </div> <br>
            <button class="btn"><fmt:message key="button.signUp"/></button>
        </form>
        <a href="${pageContext.request.contextPath}/controller?command=${CommandType.SIGN_UP}">
            <fmt:message key="already.have.account"/></a>
    </div>
</div>
</body>
</html>