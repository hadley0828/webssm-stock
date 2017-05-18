<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示市场温度计信息</title>
</head>
<body>

<%--<c:if test="${!empty data}">--%>
<%--<c:forEach var="date" items="${data}">--%>
<%--date: ${date};--%>
<%--</c:forEach>--%>
<%--</c:if>--%>
<c:if test="${marketdto!=null}">
    市场信息：${marketdto.toString()} &nbsp;&nbsp;

    <br>
</c:if>
</body>
</html>