<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType" %>
<%@ page import="com.company.tourAgency.entity.enums.UserRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
<fmt:setBundle basename="localization/localizedtext"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
          rel="stylesheet">
</head>
<nav>
    <ul>
        <a href="${pageContext.request.contextPath}/controller?command=${CommandType.HOME}">
            <img src="https://logo.templateo.com/app/data/gy6/gy60k0prpy-29idz3oqb8.jpg" alt="logo">
        </a>
        <li class="tour">
            <img src="https://cdn-icons-png.flaticon.com/512/3019/3019021.png">
            <ul class="tour-types">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.CHANGE_LOCALIZATION}&localization=en">
                        <fmt:message key="header.en"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.CHANGE_LOCALIZATION}&localization=ru">
                        <fmt:message key="header.ru"/>
                    </a>
                </li>
            </ul>
        </li>
    </ul>

    <ul>
        <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.HOME}">
            <fmt:message key="header.HOME"/></a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.ABOUT_US}">
            <fmt:message key="header.aboutUs"/></a></li>

        <c:if test="${sessionScope.user.role == UserRole.USER || sessionScope.user.role == UserRole.ADMIN}">
            <li class="tour"><a href="${pageContext.request.contextPath}/controller?command=${CommandType.TOURS}">
                <fmt:message key="header.tours"/></a>
                <ul class="tour-types">
                    <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.TOURS}">
                        <fmt:message key="header.all"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.EXCURSION}">
                        <fmt:message key="header.excursion"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.REST}">
                        <fmt:message key="header.rest"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.SHOPPING}">
                        <fmt:message key="header.shopping"/>
                    </a></li>
                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.user.role==UserRole.USER}">
            <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.MY_TOURS}">
                <fmt:message key="header.myTours"/></a></li>
        </c:if>
        <c:if test="${sessionScope.user.role == UserRole.ADMIN}">
            <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_TOURS}">
                <fmt:message key="header.addTours"/>
            </a></li>
        </c:if>
    </ul>
    <ul>
        <c:if test="${sessionScope.user.role == UserRole.GUEST}">
            <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.SIGN_IN}">
                <fmt:message key="header.signIn"/></a></li>
            <li><a class="" href="${pageContext.request.contextPath}/controller?command=${CommandType.SIGN_UP}">
                <fmt:message key="header.signUp"/></a></li>
        </c:if>

        <c:if test="${sessionScope.user.role != UserRole.GUEST}">
            <li><a href="${pageContext.request.contextPath}/controller?command=${CommandType.LOGOUT}">
                <fmt:message key="header.log.out"/>
            </a></li>
        </c:if>

        <c:if test="${sessionScope.user.role==UserRole.USER}">
            <li class="user"><img src="https://cdn-icons-png.flaticon.com/512/8648/8648573.png" alt=""><a href="#">
                <fmt:message key="header.user"/></a></li>
        </c:if>

        <c:if test="${sessionScope.user.role==UserRole.ADMIN}">
            <li class="user"><img src="https://cdn-icons-png.flaticon.com/512/8648/8648573.png" alt=""><a href="#">
                <fmt:message key="header.admin"/></a></li>
        </c:if>

    </ul>
</nav>
</html>
