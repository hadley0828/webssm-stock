<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>显示市场温度计信息</title>
</head>
<body>

<%--<c:if test="${!empty data}">--%>
<%--<c:forEach var="date" items="${data}">--%>
<%--date: ${date};--%>
<%--</c:forEach>--%>
<%--</c:if>--%>
<c:if test="${marketdto!=null}">
    市场名字：${marketdto.name} &nbsp;&nbsp;
    成交量：${marketdto.volume} &nbsp;&nbsp;
    日期：${marketdto.date} &nbsp;&nbsp;
    涨停数：${marketdto.limitup} &nbsp;&nbsp;
    跌停数：${marketdto.limitdown} &nbsp;&nbsp;
    涨5%: ${marketdto.upfive} &nbsp;&nbsp;
    跌5%: ${marketdto.downfive} &nbsp;&nbsp;
    差价涨5%: ${marketdto.upnum} &nbsp;&nbsp;
    差价跌5%: ${marketdto.downnum} &nbsp;&nbsp;
    <br>
</c:if>
</body>
</html>