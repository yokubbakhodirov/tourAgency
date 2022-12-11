<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ page import="com.company.tourAgency.entity.enums.UserRole" %>
<%@ page import="com.company.tourAgency.entity.enums.TourType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="title.shopping"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="tours">
    <div class="tickets">
        <c:choose>
            <c:when test="${requestScope.tours_list!=null}">
                <c:forEach items="${requestScope.tours_list}" var="tour">
                    <c:if test="${tour.type==TourType.SHOPPING}">
                        <div class="ticket">
                            <img src="${tour.imagePath}" alt="">
                            <p>${tour.name}</p>
                            <p>$ ${tour.price}</p>
                            <p><fmt:formatDate value="${tour.date}" pattern="MMMM dd, yyyy  HH:mm"/></p>
                            <details>
                                <summary> <fmt:message key="read.more"/></summary>
                                <p>${tour.description}</p>
                            </details>
                            <c:if test="${sessionScope.user.role==UserRole.USER}">
                                <form name="controller" method="get">
                                    <input type="hidden" name="command" value="${CommandType.BUY}"/>
                                    <input type="hidden" name="${AttributeParameterKey.PARAMETER_ATTRIBUTE_TOUR_ID}"
                                           value="${tour.id}"/>
                                    <input type="number" name="${AttributeParameterKey.PARAMETER_ATTRIBUTE_TOUR_AMOUNT}"/>
                                    <label><fmt:message key="label.number"/></label>
                                    <button class="btn"><fmt:message key="button.buy"/></button>
                                </form>
                            </c:if>

                            <c:if test="${sessionScope.user.role==UserRole.ADMIN}">
                                <form class="delete-form">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_TOUR}">
                                    <input type="hidden" name="${AttributeParameterKey.PARAMETER_TOUR_ID}" value="${tour.id}">
                                    <button class="delete-btn"><img style="width: 42px" src="https://cdn-icons-png.flaticon.com/512/9068/9068885.png"></button>
                                </form>
                            </c:if>
                        </div>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="welcome"><fmt:message key="no.tours"/></p>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>
