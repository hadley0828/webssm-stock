<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示所有的自定义策略</title>
</head>
<body>
<c:if test="${!empty allList}">
    <c:forEach var="oneStra" items="${allList}">
        单个策略的信息：${oneStra} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
</body>
</html>