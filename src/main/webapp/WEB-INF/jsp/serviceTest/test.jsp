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
    <c:if test="${stockdto!=null}">
        name：${stockdto.name} &nbsp;&nbsp;
        id: ${stockdto.id} &nbsp;&nbsp;
        market: ${stockdto.market} &nbsp;&nbsp;
        开盘价: ${stockdto.openPrice} &nbsp;&nbsp;
        收盘价: ${stockdto.closePrice} &nbsp;&nbsp;
        最高价: ${stockdto.highPrice} &nbsp;&nbsp;
        最低价: ${stockdto.lowPrice} &nbsp;&nbsp;
        涨跌幅: ${stockdto.uplift} &nbsp;&nbsp;
        成交量: ${stockdto.volume} &nbsp;&nbsp;

        <br>
</c:if>
</body>
</html>
