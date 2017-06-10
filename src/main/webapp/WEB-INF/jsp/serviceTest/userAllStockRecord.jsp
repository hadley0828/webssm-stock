<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示一个用户全部的个股浏览记录</title>
</head>
<body>
<c:if test="${!empty userDTOArrayList}">
    <c:forEach var="userDTO" items="${userDTOArrayList}">
        单条股票信息：${userDTO.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>