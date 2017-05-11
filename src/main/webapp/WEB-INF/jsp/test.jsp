<%--
  Created by IntelliJ IDEA.
  User: loohaze
  Date: 2017/5/11
  Time: 下午4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<c:if test="${!empty data}">--%>
    <%--<c:forEach var="date" items="${data}">--%>
            <%--date: ${date};--%>
        <%--</c:forEach>--%>
<%--</c:if>--%>
    <c:if test="${stock!=null}">
        serial：${stock.stockSerial} &nbsp;&nbsp;
        date: ${stock.stockDate} &nbsp;&nbsp;
        open: ${stock.openPrice} &nbsp;&nbsp;
        high: ${stock.highPrice} &nbsp;&nbsp;
        low: ${stock.lowPrice} &nbsp;&nbsp;
        close: ${stock.closePrice} &nbsp;&nbsp;
        volume: ${stock.volume} &nbsp;&nbsp;
        adjclose: ${stock.adjClose} &nbsp;&nbsp;
        code: ${stock.stockCode} &nbsp;&nbsp;
        name: ${stock.stockName} &nbsp;&nbsp;
        market: ${stock.market} &nbsp;&nbsp;
        <br>
</c:if>
</body>
</html>
