<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示板块k线图信息</title>
</head>
<body>


<c:if test="${!empty dayKLineArrayList}">
    <c:forEach var="klineDTO" items="${dayKLineArrayList}">
        一天的信息：${klineDTO.toString()} &nbsp;&nbsp;

        <br>
    </c:forEach>
</c:if>


</body>
</html>