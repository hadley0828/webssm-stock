<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示一个板块所有的股票</title>
</head>
<body>
<c:if test="${!empty allStockList}">
    <c:forEach var="oneStockCode" items="${allStockList}">
        单个股票信息：${oneStockCode} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>