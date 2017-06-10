<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示一个用户全部的策略结果生成记录</title>
</head>
<body>
<c:if test="${!empty strategyRecordArrayList}">
    <c:forEach var="userDTO" items="${strategyRecordArrayList}">
        单条策略结果信息：${userDTO.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>