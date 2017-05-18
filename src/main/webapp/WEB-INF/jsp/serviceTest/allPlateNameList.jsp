<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示所有板块的名称</title>
</head>
<body>
<c:if test="${!empty allPlateNameList}">
    <c:forEach var="onePlateName" items="${allPlateNameList}">
        单个板块信息：${onePlateName} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>