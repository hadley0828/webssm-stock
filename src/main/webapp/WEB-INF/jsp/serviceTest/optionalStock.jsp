<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示一个用户的全部自选股的股票编号</title>
</head>
<body>
<c:if test="${!empty userAllStockList}">
    <c:forEach var="oneStock" items="${userAllStockList}">
        一只股票：${oneStock} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>