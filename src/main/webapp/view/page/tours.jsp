<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.controller.navigation.AttributeParameterKey" %>
<%@ page import="com.company.tourAgency.entity.enums.UserRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.tours"/></title>
</head>

<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="tours">
    <div class="tickets">
        <c:choose>
            <c:when test="${requestScope.tours_list!=null}">
                <c:forEach items="${requestScope.tours_list}" var="tour">
                    <div class="ticket">
                        <img src="${tour.imagePath}" alt="">
                        <p>${tour.name}</p>
                        <p>$ ${tour.price}</p>
                        <c:if test="${sessionScope.user.isLoyal()==true && tour.discount > 0}">
                            <p>For our loyal clients with ${tour.discount}% discount</p>
                            <p>Price after discount: ${tour.price * ((100 - tour.discount) / 100.0)}</p>
                        </c:if>
                        <p><fmt:formatDate value="${tour.date}" pattern="MMMM dd, yyyy  HH:mm"/></p>
                        <details>
                            <summary><fmt:message key="read.more"/></summary>
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
                        <form action="controller" class="delete-form">
                            <input type="hidden" name="command" value="${CommandType.DELETE_TOUR}">
                            <input type="hidden" name="${AttributeParameterKey.PARAMETER_TOUR_ID}" value="${tour.id}">
                            <button class="delete-btn"><img style="width: 42px" src="https://cdn-icons-png.flaticon.com/512/9068/9068885.png"></button>
                        </form>
                        </c:if>
                    </div>
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
