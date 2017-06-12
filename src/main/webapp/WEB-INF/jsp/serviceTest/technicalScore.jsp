<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示一个股票的技术面分数</title>
</head>
<body>

<%--<c:if test="${!empty data}">--%>
<%--<c:forEach var="date" items="${data}">--%>
<%--date: ${date};--%>
<%--</c:forEach>--%>
<%--</c:if>--%>
<c:if test="${technicalDTO!=null}">
    技术面分数：${technicalDTO.toString()} &nbsp;&nbsp;

    <br>
</c:if>
</body>
</html>