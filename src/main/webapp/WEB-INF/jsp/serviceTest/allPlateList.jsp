<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示单只股票所有所属板块</title>
</head>
<body>
<c:if test="${!empty allPlateList}">
    <c:forEach var="onePlateName" items="${allPlateList}">
        单个板块信息：${onePlateName} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>