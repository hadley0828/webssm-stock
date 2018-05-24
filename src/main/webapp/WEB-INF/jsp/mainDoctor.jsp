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
            var stockSearch = document.getElementById("stockSearch");
            var stockName = stockSearch.value;
            window.location.href = "<%=contextPath%>/doctor/info/?id=${user.account}&stockcode="+stockName;
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
                <c:choose>
                    <c:when test="${user.account != null}">
                        <a href="<%=contextPath%>/dashboard/?id=${user.account}">
                            <img  src="<%=contextPath%>/assets/img/logo.png"  /><B>QUANTOUR</B>
                        </a>
                    </c:when>
                    <c:when  test="${user.account == null}">
                        <a href="<%=contextPath%>/dashboard/">
                            <img  src="<%=contextPath%>/assets/img/logo.png"  /><B>QUANTOUR</B>
                        </a>
                    </c:when>
                </c:choose>
            </div>

            <c:choose>
                <c:when test="${user.account != null}">
                    <ul class="nav">
                        <li>
                            <a href="<%=contextPath%>/dashboard/?id=${user.account}">
                                <i class="ti-home"></i>
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
                        <li style="background-color:#dcdcdc">
                            <a href="<%=contextPath%>/doctor/?id=${user.account}">
                                <i class="ti-support"></i>
                                <p>股票诊断</p>
                            </a>
                        </li>

                    </ul>
                </c:when>
                <c:when test="${user.account == null}">
                    <ul class="nav">
                        <li>
                            <a href="<%=contextPath%>/dashboard/">
                                <i class="ti-home"></i>
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
                        <li style="background-color:#dcdcdc">
                            <a href="<%=contextPath%>/doctor/">
                                <i class="ti-support"></i>
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
                    <a class="navbar-brand" href="#">诊断导航</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li>
                            <c:choose>
                                <c:when test="${user.account != null}">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown" >
                                <i class="ti-user"></i>
                                <p>${user.account}</p>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<%=contextPath%>/dashboard/login">退出登录</a></li>
                            </ul>
                        </li>
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
                                <select class="selectpicker" id="stockSearch" data-live-search="true" data-size="5">
                                    <%--<c:forEach var="oneStock" items="${codeAndName}">--%>
                                    <%--<option value="${oneStock}">${oneStock}</option>--%>
                                    <%--</c:forEach>--%>
                                    <option value="000001 平安银行">000001 平安银行</option>
                                    <option value="000004 国农科技">000004 国农科技</option>
                                    <option value="000005 世纪星源">000005 世纪星源</option>
                                </select>
                                <span class="input-group-addon"><i id="searchIcon" class="ti-search" onclick="add()" onmouseenter="changeColor()" onmouseleave="reColor()"></i> </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="z-index: -1">
                <div class="col-lg-10 col-lg-offset-1" style="z-index: inherit">
                    <div class="card" style="z-index: inherit">
                        <div class="content" style="z-index: inherit">
                            <div class="row">
                                <div class="col-md-6">
                                    <blockquote>单日涨幅前10</blockquote>
                                    <hr>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-striped">
                                            <thead>
                                            <th>股票编号</th>
                                            <th>股票名称</th>
                                            <th>当前价格</th>
                                            <th>涨跌幅</th>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${one_day_list}"  var="hot_stock">
                                                <tr>
                                                    <td id="hot_name_${hot_stock.stockCode}">${hot_stock.stockCode}</td>
                                                    <td>${hot_stock.stockName}</td>
                                                    <td>${hot_stock.newestPrice}</td>
                                                    <td>${hot_stock.changePercent}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <blockquote>猜你喜欢</blockquote>
                                    <hr>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-striped">
                                            <thead>
                                            <th>股票编号</th>
                                            <th>股票名称</th>
                                            <th>当前价格</th>
                                            <th>涨跌幅</th>
                                            </thead>

                                            <tbody>
                                            <c:forEach items="${commendList}" var="oneStock">
                                                <tr>
                                                    <td>${oneStock.id}</td>
                                                    <td>${oneStock.name}</td>
                                                    <td>${oneStock.closePrice}</td>
                                                    <td>${oneStock.uplift}</td>
                                                </tr>


                                            </c:forEach>
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
