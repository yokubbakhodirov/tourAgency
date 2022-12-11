<%@ page contentType="text/html" language="java" %>
<%@ page import="com.company.tourAgency.command.CommandType"%>

<html>
<head>
    <title>Main page</title>
</head>
<body>

<jsp:forward page="controller?command=${CommandType.HOME}"/>

</body>
</html>