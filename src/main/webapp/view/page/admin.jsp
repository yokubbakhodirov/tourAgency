<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="title.admin"/></title>
</head>
<body>
<div class="admin">
    <jsp:include page="/view/fragment/nav.jsp"/>

    <div style="display: flex; gap: 12px;">
        <form action="controller">
            <input type="hidden" name="command" value="${CommandType.ADMIN}">
            <input type="hidden" name="${AttributeParameterKey.REQUEST_ATTRIBUTE_ADMIN_DATA}"
                   value="${AttributeParameterKey.REQUEST_ATTRIBUTE_PARAMETER_USERS}">
            <button style=" border: none; color: rgb(0, 56, 238);">
                <fmt:message key="button.users"/>
            </button>
        </form>
        <form action="controller">
            <input type="hidden" name="command" value="${CommandType.ADMIN}">
            <input type="hidden" name="${AttributeParameterKey.REQUEST_ATTRIBUTE_ADMIN_DATA}" value="${AttributeParameterKey.REQUEST_ATTRIBUTE_PARAMETER_ORDERS}">
            <button style="border: none; color: rgb(0, 56, 238);" type="">
                <fmt:message key="button.orders"/>
            </button>
        </form>
    </div>

    <div class="users-orders">
        <c:if test="${!requestScope.users.isEmpty()}">
            <c:forEach items="${requestScope.users}" var="user">
                <div class="card">
                    <div>
                        <p><fmt:message key="label.user"/></p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_ID}</p>
                        <p>${user.id}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_EMAIL}</p>
                        <p>${user.email}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_NAME}</p>
                        <p>${user.name}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_LASTNAME}</p>
                        <p>${user.lastname}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_PHONE}</p>
                        <p>${user.phone}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_USER_ROLE}</p>
                        <p>${user.role}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_CARD_ID}</p>
                        <p>${user.cardId}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_IS_LOYAL}</p>
                        <c:if test="${user.isLoyal()!=null}">
                            <p>${user.isLoyal()}</p>
                        </c:if>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_CREATED_AT}</p>
                        <p>${user.createdAt}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_IS_DELETED}</p>
                        <p>${user.isDeleted()}</p>
                    </div>
                    <c:if test="${user.role==UserRole.USER}">
                    <form action="controller" class="delete-form2">
                        <input type="hidden" name="command" value="${CommandType.DELETE_USER}">
                        <input type="hidden" name="${AttributeParameterKey.PARAMETER_ID}" value="${user.id}">
                        <button class="delete-btn2"><img src="https://cdn-icons-png.flaticon.com/512/9068/9068885.png"></button>
                    </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.users.isEmpty()}">
            <p class="welcome"><fmt:message key="no.users"/></p>
        </c:if>

        <c:if test="${!requestScope.orders.isEmpty()}">
            <c:forEach items="${requestScope.orders}" var="order">
                <div class="card">
                    <div>
                        <p><fmt:message key="label.order"/></p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_ID}</p>
                        <p>${order.id}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_USER_ID}</p>
                        <p>${order.userId}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_TOUR_ID}</p>
                        <p>${order.tourId}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_AMOUNT}</p>
                        <p>${order.amount}</p>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_ORDER_DATE}</p>
                        <fmt:formatDate value="${order.orderDate}" pattern="MMMM dd, yyyy"/>
                    </div>
                    <div>
                        <p>${AttributeParameterKey.PARAMETER_IS_DELETED}</p>
                        <p>${order.isDeleted()}</p>
                    </div>
                    <form action="controller" class="delete-form2">
                        <input type="hidden" name="command" value="${CommandType.DELETE_ORDER}">
                        <input type="hidden" name="${AttributeParameterKey.PARAMETER_ID}" value="${order.id}">
                        <button class="delete-btn2"><img src="https://cdn-icons-png.flaticon.com/512/9068/9068885.png"></button>
                    </form>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.orders.isEmpty()}">
            <p class="welcome"><fmt:message key="no.orders"/></p>
        </c:if>
    </div>
</div>
</body>
</html>
