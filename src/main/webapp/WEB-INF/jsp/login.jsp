<%--
  Created by IntelliJ IDEA.
  User: loohaze
  Date: 2017/6/2
  Time: 下午3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/ssm/user/login" method="post" id="myform">
        <input type="text" id="username" name="username"/>
        <input type="password" id="password" name="password"/>
        <input type="submit" value="提交" id="login" />
    </form>
</body>
</html>
