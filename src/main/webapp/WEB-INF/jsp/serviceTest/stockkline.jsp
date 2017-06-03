<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示K线图信息</title>
</head>
<body>


<c:if test="${!empty klineDTOArrayList}">
    <c:forEach var="klineDTO" items="${klineDTOArrayList}">
        股票编号：${klineDTO.id} &nbsp;&nbsp;
        开盘价: ${klineDTO.openPrice} &nbsp;;&nbsp;
        收盘价: ${klineDTO.closePrice} &nbsp;&nbsp;
        最高价: ${klineDTO.highPrice} &nbsp;&nbsp;
        最低价: ${klineDTO.lowPrice} &nbsp;&nbsp;
        日期: ${klineDTO.date}  &nbsp;&nbsp;
        <br>
    </c:forEach>
</c:if>


</body>
</html>