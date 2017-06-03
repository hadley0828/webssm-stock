<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/3
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();

%>
<!doctype html>
<html lang = zh-CN>
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<%=contextPath%>/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="<%=contextPath%>/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Quantour III</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="<%=contextPath%>/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="<%=contextPath%>/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="<%=contextPath%>/assets/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=contextPath%>/assets/css/demo.css" rel="stylesheet" />


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=contextPath%>/assets/css/themify-icons.css" rel="stylesheet">
</head>
<body>
<div class="row">
    <div class="col-xs-4 col-xs-offset-4">
        <div class="card">
            <div class="header" style="background-color: #f4f3ef">
                <h4 class="title" style="color:#FF9500">login</h4>
                <hr/>
            </div>
            <div class="content">
                <div class="row">
                    <div class="col-xs-6 col-xs-offset-3">
                        <div class="form-group">
                            <label>username</label>
                            <input type="text" class="form-control border-input"  placeholder="username" name="userName">
                        </div>
                    </div>
                    <div class="col-xs-6 col-xs-offset-3">
                        <div class="form-group">
                            <label>Password</label>
                            <input type="text" class="form-control border-input"  placeholder="password" name="password">
                        </div>
                    </div>
                    <div class="col-xs-6 col-xs-offset-3">
                        <p>
                            "click here to "
                            <a herf="<%=contextPath%>/dashboard/regist">regist</a>
                        </p>

                    </div>

                    <div class="col-xs-4 col-xs-offset-8">
                        <button class="btn btn-primary">login</button>
                    </div>
                </div>
                <div class="footer">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
