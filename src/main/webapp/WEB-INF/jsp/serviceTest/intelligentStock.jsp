<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示智能选股的全部股票</title>
</head>
<body>
<c:if test="${!empty stockList}">
    <c:forEach var="oneStock" items="${stockList}">
        单个股票编号：${oneStock.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>