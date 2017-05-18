<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示前三十天的涨跌停数量</title>
</head>
<body>
<c:if test="${!empty numberList}">
    <c:forEach var="oneDayNumber" items="${numberList}">
        当天的涨跌停信息：${oneDayNumber.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>