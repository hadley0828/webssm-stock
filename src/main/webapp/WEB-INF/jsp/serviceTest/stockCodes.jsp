<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>根据几个股票名称查找对应的股票编号</title>
</head>
<body>
<c:if test="${!empty codeList}">
    <c:forEach var="stockCode" items="${codeList}">
        单个股票编号：${stockCode} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>