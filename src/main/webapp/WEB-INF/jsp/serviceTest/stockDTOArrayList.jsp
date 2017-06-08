<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示选择的几只股票的当天数据</title>
</head>
<body>
<c:if test="${!empty stockDTOArrayList}">
    <c:forEach var="stockDTO" items="${stockDTOArrayList}">
        一只股票的信息：${stockDTO.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>