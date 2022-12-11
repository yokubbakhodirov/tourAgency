<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html lang="${sessionScope.localization}">
<head>
    <title><fmt:message key="error.404.title"/></title>
</head>
<body>
<h1><fmt:message key="error.404.text"/></h1>
<h1><a href="${pageContext.request.contextPath}/controller?command=${CommandType.HOME}">
    <fmt:message key="error.link"/>
</a></h1>
</body>
</html>
