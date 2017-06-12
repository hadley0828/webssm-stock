<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/5
  Time: 22:32
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

    <script src="<%=contextPath%>/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
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
                    <a class="navbar-brand" href="#">股票诊断</a>
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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-3 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h2><b>万科A</b><small>(000002)</small></h2>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-6 col-xs-offset-3">
                                        <div class="text-center">
                                            <h1 style="color: #FF9500">6.5<small>分</small></h1>
                                            <br>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1 text-center">
                                        <span class="label label-default">卖出</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span class="label label-default">减持</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span class="label label-default">观望</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span class="label label-danger">增持</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span class="label label-default">买入</span>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row" style="margin: auto">
                                    <dl >
                                        <dt>短期</dt>
                                        <dd style="color: #3091B2">短期的强势行情可能结束，投资者及时短线卖出、离场观望为宜。</dd>
                                        <dt>中期</dt>
                                        <dd style="color: #3091B2">上涨趋势有所减缓，可适量高抛低吸。</dd>
                                        <dt>长期</dt>
                                        <dd style="color: #3091B2">迄今为止，共74家主力机构，持仓量总计50.87亿股，占流通A股52.40%</dd>

                                    </dl>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-7">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>分数预览</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p>TODO</p>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>技术面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p style="padding-left: 50px;font-size: xx-large"><strong>诊断结果：</strong><strong class="text-danger">6.2</strong></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style=" color: #ff9500;font-size: large">k线图</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style=" color: #ff9500;font-size: large">市场表现</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style=" color: #ff9500;font-size: large">成交量数据</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>资金面面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p style="padding-left: 50px;font-size: xx-large"><strong>诊断结果：</strong><strong class="text-danger">6.2</strong></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style=" color: #ff9500;font-size: large">资金流向</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="card" style="">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style=" color: #ff9500;font-size: large">行业资金</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content  table-responsive">
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <caption style="padding-left: 15px;font-size: large">个股</caption>
                                                    <tbody>
                                                    <tr>
                                                        <td>个股今日流向(万元)</td>
                                                        <td style="color: crimson">1000</td>
                                                        <td>个股五日流向(万元)</td>
                                                        <td style="color: crimson">10000</td>
                                                    </tr>
                                                    <tr>
                                                        <td>个股十日流向(万元)</td>
                                                        <td style="color: crimson">10</td>
                                                        <td>个股二十日流向(万元)</td>
                                                        <td style="color: crimson">100000</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <caption style="padding-left: 15px;font-size: large">行业</caption>
                                                    <tbody>
                                                    <tr>
                                                        <td>行业今日流向(万元)</td>
                                                        <td style="color: crimson">1000</td>
                                                        <td>行业五日流向(万元)</td>
                                                        <td style="color: crimson">10000</td>
                                                    </tr>
                                                    <tr>
                                                        <td>行业十日流向(万元)</td>
                                                        <td style="color: crimson">10</td>
                                                        <td>行业二十日流向(万元)</td>
                                                        <td style="color: crimson">100000</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="padding-left: 2%;padding-right: 2%">
                                    <div class="card" >
                                        <div class="header" style="background-color: #c1edfb">
                                            <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">机构交易</strong> </p>
                                            <hr>
                                        </div>
                                        <div class="content  table-responsive">
                                            <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                <caption style="padding-left: 15px;font-size: large">机构交易</caption>
                                                <thead class="text-center">
                                                <tr>
                                                    <th>股票编号</th>
                                                    <th>股票名称</th>
                                                    <th>累计购买额</th>
                                                    <th>买入席位数</th>
                                                    <th>累计卖出额</th>
                                                    <th>卖出席位数</th>
                                                    <th>净额</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td style="color: crimson">1000</td>
                                                    <td style="color: crimson">1000</td>
                                                    <td style="color: crimson">1000</td>
                                                    <td style="color: crimson">10000</td>
                                                    <td style="color: crimson">1000</td>
                                                    <td style="color: crimson">1000</td>
                                                    <td style="color: crimson">1000</td>
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
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>消息面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p style="padding-left: 50px;font-size: xx-large"><strong>诊断结果：</strong><strong class="text-danger">6.2</strong></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #c1edfb">
                                                        <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">分配预案</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #c1edfb">
                                                        <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">业绩预测</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-7">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #c1edfb">
                                                        <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">个股新闻</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive">

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
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>行业面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p style="padding-left: 50px;font-size: xx-large"><strong>诊断结果：</strong><strong class="text-danger">6.2</strong></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">行业走势</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">十日涨跌幅</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>基本面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <p style="padding-left: 50px;font-size: xx-large"><strong>诊断结果：</strong><strong class="text-danger">6.2</strong></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">现金流量</strong> </p>
                                                <hr>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">营收能力</strong> </p>
                                                <hr>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">成长能力</strong> </p>
                                                <hr>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">偿债能力</strong> </p>
                                                <hr>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card">
                                            <div class="header" style="background-color: #c1edfb">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">盈利能力</strong> </p>
                                                <hr>
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

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=contextPath%>/assets/js/demo.js"></script>

<script type="text/javascript">
    $(document).ready(function(){

        demo.initChartist();


    });
</script>
</html>
