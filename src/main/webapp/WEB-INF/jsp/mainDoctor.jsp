    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangty
  Date: 2017/6/13
  Time: 下午5:53
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
    <%--<link href="<%=contextPath%>/assets/css/demo.css" rel="stylesheet" />--%>


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=contextPath%>/assets/css/themify-icons.css" rel="stylesheet">

    <%--Bootstrap select--%>
    <link href="<%=contextPath%>/assets/css/bootstrap-select.min.css" rel="stylesheet" >


    <script src="<%=contextPath%>/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="<%=contextPath%>/assets/js/echarts.js"></script>

    <script type="text/javascript">


        function add() {
            window.location.href = "<%=contextPath%>/doctor/info/?id=${user.account}"
        }

        function changeColor() {
            document.getElementById("searchIcon").setAttribute("style","color:orange");
        }

        function reColor() {
            document.getElementById("searchIcon").setAttribute("style","color:black");
        }

    </script>
</head>
<body>
<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">

        <!--
            Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
            Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
        -->

        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    Quantour
                </a>
            </div>

            <c:choose>
                <c:when test="${user.account != null}">
                    <ul class="nav">
                        <li>
                            <a href="<%=contextPath%>/dashboard/?id=${user.account}">
                                <i class="ti-panel"></i>
                                <p>主页</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/compare/?id=${user.account}">
                                <i class="ti-flag-alt-2"></i>
                                <p>股票对比</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/strategy/?id=${user.account}">
                                <i class="ti-receipt"></i>
                                <p>股市策略</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/doctor/?id=${user.account}">
                                <i class="ti-user"></i>
                                <p>股票诊断</p>
                            </a>
                        </li>

                    </ul>
                </c:when>
                <c:when test="${user.account == null}">
                    <ul class="nav">
                        <li>
                            <a href="<%=contextPath%>/dashboard/">
                                <i class="ti-panel"></i>
                                <p>主页</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/compare/">
                                <i class="ti-flag-alt-2"></i>
                                <p>股票对比</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/strategy/">
                                <i class="ti-receipt"></i>
                                <p>股市策略</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/doctor/">
                                <i class="ti-user"></i>
                                <p>股票诊断</p>
                            </a>
                        </li>

                    </ul>
                </c:when>
            </c:choose>

        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">主页</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li>
                            <c:choose>
                                <c:when test="${user.account != null}">
                                    <a href="<%=contextPath%>/userInfo/?id=${user.account}" >
                                        <i class="ti-user"></i>
                                        <p>${user.account}</p>
                                    </a>
                                </c:when>
                                <c:when test="${user.account == null}">
                                    <a href="<%=contextPath%>/dashboard/login" >
                                        <i class="ti-user"></i>
                                        <p>登录</p>
                                    </a>
                                </c:when>
                            </c:choose>


                        </li>

                    </ul>

                </div>
            </div>
        </nav>

        <div class="content">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <div class="card">
                        <div class="content">
                            <blockquote>搜索股票</blockquote>
                            <div class="col-md-6 input-group">
                                <input type="text" placeholder="编号/名称" class="form-control" id="tags">
                                <span class="input-group-addon"><i id="searchIcon" class="ti-search" onclick="add()" onmouseenter="changeColor()" onmouseleave="reColor()"></i> </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <div class="card">
                        <div class="content">
                            <div class="row">
                                <div class="col-md-6">
                                    <blockquote>某排行</blockquote>
                                    <hr>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-striped">
                                            <thead>
                                            <th style="width: 72px;">序号</th>
                                            <th style="width: 108px;">编号</th>
                                            <th style="width: 108px">名称</th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    现价
                                                    <i></i>
                                                </a>
                                            </th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    涨跌幅(%)
                                                </a>
                                            </th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    涨跌
                                                </a>
                                            </th>
                                            <th style="width: 108px;">成交量</th>
                                            <th style="width: 72px;">加自选</th>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>000001</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>000002</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>000003</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>000004</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <blockquote>某排行</blockquote>
                                    <hr>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-striped">
                                            <thead>
                                            <th style="width: 72px;">序号</th>
                                            <th style="width: 108px;">编号</th>
                                            <th style="width: 108px">名称</th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    现价
                                                    <i></i>
                                                </a>
                                            </th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    涨跌幅(%)
                                                </a>
                                            </th>
                                            <th style="width: 108px;">
                                                <a href="#">
                                                    涨跌
                                                </a>
                                            </th>
                                            <th style="width: 108px;">成交量</th>
                                            <th style="width: 72px;">加自选</th>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>000001</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>000002</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>000003</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>000004</td>
                                                <td>xx股票</td>
                                                <td>0.0</td>
                                                <td>0.0%</td>
                                                <td>0.0</td>
                                                <td>1000000</td>
                                                <td>
                                                    <a href="#">
                                                        <i class="ti-plus"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!--   Core JS Files   -->

<script src="<%=contextPath%>/assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="<%=contextPath%>/assets/js/bootstrap-checkbox-radio.js"></script>

<!--  Charts Plugin -->
<%--<script src="<%=contextPath%>/assets/js/chartist.min.js"></script>--%>

<!--  Notifications Plugin    -->
<script src="<%=contextPath%>/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<%--<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>--%>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="<%=contextPath%>/assets/js/paper-dashboard.js"></script>

<%--Bootstrap select--%>
<script src="<%=contextPath%>/assets/js/bootstrap-select.min.js"></script>
<script src="<%=contextPath%>/assets/js/defaults-zh_CN.min.js"></script>


<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<%--<script src="<%=contextPath%>/assets/js/demo.js"></script>--%>



</html>
