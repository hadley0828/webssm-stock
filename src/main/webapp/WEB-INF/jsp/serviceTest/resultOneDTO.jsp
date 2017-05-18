<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>策略一的结果</title>
</head>
<body>

<c:if test="${resultDTO!=null}">
    result：${resultDTO.toString()} &nbsp;&nbsp;

    <br>
</c:if>
</body>
</html>