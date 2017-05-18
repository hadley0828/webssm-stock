<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示所有股票的编号和名称</title>
</head>
<body>
<c:if test="${!empty allStockNameList}">
    <c:forEach var="oneStockName" items="${allStockNameList}">
        单只股票信息：${oneStockName} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>