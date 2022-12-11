<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.myTours"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="tours">
    <div class="tickets">
        <c:choose>
            <c:when test="${!requestScope.my_tours_list.isEmpty()}">
                <c:forEach items="${requestScope.my_tours_list}" var="tour">
                    <div class="ticket">
                        <img src="${tour.key.imagePath}" alt="">
                        <p>${tour.key.name}</p>
                        <p>$ ${tour.key.price}</p>
                        <p><fmt:formatDate value="${tour.key.date}" pattern="MMMM dd, yyyy  HH:mm"/></p>
                        <details>
                            <summary><fmt:message key="read.more"/></summary>
                            <p>${tour.key.description}</p>
                        </details>
                        <p><fmt:message key="label.number"/>: ${tour.value}</p>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="welcome"><fmt:message key="no.purchase"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>