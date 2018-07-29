<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 07/05/2018
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="users-list">
    <c:choose>
        <c:when test="${empty users}">
            <p>Non esistono utenti</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${users}" var="user">
                <div>
                    <c:out value="{user.name}"/> <c:out value="{user.surname}"/>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>
