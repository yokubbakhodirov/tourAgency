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
    <title><fmt:message key="title.aboutUs"/></title>
</head>
<body>

<jsp:include page="/view/fragment/nav.jsp"/>

<div class="about">
    <div class="about-description">
        <h3><fmt:message key="aboutUs.first.heading"/></h3>
        <h6><fmt:message key="aboutUs.second.heading"/></h6>
        <p>
            <fmt:message key="aboutUs.description"/>
        </p>

    </div>
    <div class="about-image">
        <img src="https://images.unsplash.com/photo-1668795493928-94e465816ee9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
             alt="">
    </div>
</div>
</body>
</html>