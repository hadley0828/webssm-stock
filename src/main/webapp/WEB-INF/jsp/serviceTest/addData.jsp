<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>添加全部的分数信息</title>
</head>
<body>

<%--<c:if test="${!empty data}">--%>
<%--<c:forEach var="date" items="${data}">--%>
<%--date: ${date};--%>
<%--</c:forEach>--%>
<%--</c:if>--%>
<c:if test="${result!=null}">
    添加是否完成：${result)} &nbsp;&nbsp;

    <br>
</c:if>
</body>
</html>