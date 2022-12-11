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
    <title><fmt:message key="title.home"/></title>
</head>

<body>

<header>
    <jsp:include page="/view/fragment/nav.jsp"/>

    <p class="welcome"><fmt:message key="home.heading"/></p>
    <p class="title"><fmt:message key="home.description"/></p>

    <a href="#" class="bottom-linker">
        <p><fmt:message key="home.text"/></p>
        <img src="../../images/arrow.svg" alt="">
    </a>
</header>

</body>
</html>