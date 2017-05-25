<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示涨幅排行榜</title>
</head>
<body>
<c:if test="${!empty waveDTOArrayList}">
    <c:forEach var="wavedto" items="${waveDTOArrayList}">
        单个股票信息：${wavedto.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>
<c:if test="${!empty waveDTOArrayList1}">
    <c:forEach var="wavedto1" items="${waveDTOArrayList1}">
        单个股票信息：${wavedto1.toString()} &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>

</body>
</html>