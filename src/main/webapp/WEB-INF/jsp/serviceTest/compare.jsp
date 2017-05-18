<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示比较的信息</title>
</head>
<body>
<c:if test="${!empty compareDTOArrayList}">
    <c:forEach var="compareDTO" items="${compareDTOArrayList}">
        单只股票信息：${compareDTO.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>