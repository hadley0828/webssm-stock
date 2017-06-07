<%--
  Created by IntelliJ IDEA.
  User: wangty
  Date: 2017/6/3
  Time: 下午8:42
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


    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script src="text/javascript">
        function getOneDayStockInfo(){

            var stock_id;
            var date;
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
                    <a href="<%=contextPath%>/TODO">
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
                    <a class="navbar-brand" href="#">个股信息</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div class="form-group" style="padding-top: 15px">
                                <input type="text" placeholder="Search" class="form-control">
                            </div>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/dashboard/login" >
                                <i class="ti-user"></i>
                                <p>登录</p>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-bell"></i>
                                <p class="notification">5</p>
                                <p>消息</p>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">消息 1</a></li>
                                <li><a href="#">消息 2</a></li>
                                <li><a href="#">消息 3</a></li>
                                <li><a href="#">消息 4</a></li>
                                <li><a href="#">更多</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ti-settings"></i>
                                <p>设置</p>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>


        <div class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="header"><blockquote>${stock.name} (${stock.id})</blockquote></div>
                        <hr>
                        <div class="row" style="padding-left: 10px">
                            <div class="col-xs-1">
                                <dt>开盘</dt>
                                <dd class="text-danger">${stock.openPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>收盘</dt>
                                <dd>${stock.closePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>最高</dt>
                                <dd class="text-danger">${stock.highPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>最低</dt>
                                <dd class="text-success">${stock.closePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>成交量</dt>
                                <dd>${stock.volume}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>涨跌</dt>
                                <dd class="text-danger">${stock.uplift}</dd>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-3 col-sm-6">
                    <div class="card">
                        <div class="header">
                            <blockquote>其他股票</blockquote>
                        </div>
                        <hr>
                        <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#hot">热门股票</a></li>
                            <li><a data-toggle="pill" href="#collect">收藏股票</a> </li>
                            <li><a data-toggle="pill" href="#history">历史浏览</a> </li>
                        </ul>
                        <div class="tab-content">
                            <div id="hot" class="tab-pane fade in active">
                                <div class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <tbody>
                                        <tr>
                                            <td>中国石油</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        <tr>
                                            <td>中国石化</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="collect" class="tab-pane fade">
                                <div class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <tbody>
                                        <tr>
                                            <td>工商银行</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        <tr>
                                            <td>农业银行</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="history" class="tab-pane fade">
                                <div class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <tbody>
                                        <tr>
                                            <td>万科A</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        <tr>
                                            <td>百度</td>
                                            <td>0.00</td>
                                            <td>0.0%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-9">
                    <div class="card">
                        <div class="header">
                            <blockquote>股票详情</blockquote>
                            <hr>

                        </div>
                        <hr>

                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


</body>

<!--   Core JS Files   -->
<script src="<%=contextPath%>/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="<%=contextPath%>/assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="<%=contextPath%>/assets/js/bootstrap-checkbox-radio.js"></script>

<!--  Charts Plugin -->
<script src="<%=contextPath%>/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="<%=contextPath%>/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="<%=contextPath%>/assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=contextPath%>/assets/js/demo.js"></script>

<%--&lt;%&ndash;<script type="text/javascript">&ndash;%&gt;--%>
    <%--&lt;%&ndash;$(document).ready(function(){&ndash;%&gt;--%>

        <%--&lt;%&ndash;demo.initChartist();&ndash;%&gt;--%>

        <%--&lt;%&ndash;$.notify({&ndash;%&gt;--%>
            <%--&lt;%&ndash;icon: 'ti-gift',&ndash;%&gt;--%>
            <%--&lt;%&ndash;message: "Welcome to <b>Paper Dashboard</b> - a beautiful Bootstrap freebie for your next project."&ndash;%&gt;--%>

        <%--&lt;%&ndash;},{&ndash;%&gt;--%>
            <%--&lt;%&ndash;type: 'success',&ndash;%&gt;--%>
            <%--&lt;%&ndash;timer: 4000&ndash;%&gt;--%>
        <%--&lt;%&ndash;});&ndash;%&gt;--%>

    <%--&lt;%&ndash;});&ndash;%&gt;--%>
<%--</script>--%>
</html>
