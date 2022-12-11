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
    <title><fmt:message key="title.addTours"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="sign-in">

    <div class="box">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandType.FINISH_ADD_TOURS}">
            <span class="text-center"><fmt:message key="title.addTours"/></span>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.name!=null}">
                        <fmt:message key="message.invalid.tour.name"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_NAME}" required=""/>
                <label><fmt:message key="label.name"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.type!=null}">
                        <fmt:message key="message.invalid.tour.type"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_TYPE}" required=""/>
                <label><fmt:message key="label.type"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.description!=null}">
                        <fmt:message key="message.invalid.tour.description"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_DESCRIPTION}" required=""/>
                <label><fmt:message key="label.description"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.discount!=null}">
                        <fmt:message key="message.invalid.tour.discount"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_DISCOUNT}" required=""/>
                <label><fmt:message key="label.discount"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.date!=null}">
                        <fmt:message key="message.invalid.tour.date"/>
                    </c:if>
                </div>
                <br>
                <input type="datetime-local" name="${AttributeParameterKey.PARAMETER_DATE}" required=""/>
                <label><fmt:message key="label.date"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.price!=null}">
                        <fmt:message key="message.invalid.tour.price"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_PRICE}" required=""/>
                <label><fmt:message key="label.price"/></label>
            </div>
            <div class="input-container">
                <div class="text-danger">
                    <c:if test="${requestScope.form_invalid.image_path!=null}">
                        <fmt:message key="message.invalid.tour.image.path"/>
                    </c:if>
                </div>
                <input type="text" name="${AttributeParameterKey.PARAMETER_IMAGE_PATH}" required=""/>
                <label><fmt:message key="label.image.path"/></label>
            </div>

            <div class="text-success">
                <c:if test="${requestScope.add_tour_success!=null}">
                    <fmt:message key="message.success.add.tour"/>
                </c:if>
            </div>
            <div class="text-danger">
                <c:if test="${requestScope.tour_exists!=null}">
                    <fmt:message key="message.invalid.tour.exists.addTour"/>
                </c:if>
            </div>
            <br>
            <button class="btn"><fmt:message key="button.addTour"/></button>
        </form>
    </div>
</div>
</body>
</html>
