<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="title.buyTours"/></title>
</head>
<body>
<jsp:include page="/view/fragment/nav.jsp"/>
<div class="sign-in">
    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_BUY}"/>
            <div class="input-container">
                <div class="text-info">
                    <c:if test="${sessionScope.card_number!=null}">
                        <fmt:message key="your.card.number"/>
                        <c:out value="${sessionScope.card_number}"/>
                    </c:if>
                </div>

                <input type="text" name="${AttributeParameterKey.PARAMETER_CARD_NUMBER}" required=""/>
                <label><fmt:message key="label.card"/></label>
                <div class="text-success">
                    <c:if test="${requestScope.purchase_success!=null}">
                        <fmt:message key="message.success.purchase"/>
                    </c:if>
                </div>
                <div class="text-danger">
                    <c:if test="${requestScope.card_exists!=null}">
                        <fmt:message key="message.invalid.card.exists.buy"/>
                    </c:if>
                </div>
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.card_number!=null}">
                        <fmt:message key="message.invalid.card"/>
                    </c:if>
                </div>
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.tour_amount!=null}">
                        <fmt:message key="message.invalid.tour.amount"/>
                    </c:if>
                </div>
            </div>
            <button class="btn"><fmt:message key="button.buy"/></button>
        </form>
    </div>
</div>
</body>
</html>
