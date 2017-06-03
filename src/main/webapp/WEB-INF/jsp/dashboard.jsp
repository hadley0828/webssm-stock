<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/5/16
  Time: 13:57
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
                    <a href="My_dashboard.html">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="compare.html">
                        <i class="ti-flag-alt-2"></i>
                        <p>Compare</p>
                    </a>
                </li>
                <li>
                    <a href="strategy.html">
                        <i class="ti-receipt"></i>
                        <p>Strategy</p>
                    </a>
                </li>
                <li>
                    <a href="personal.html">
                        <i class="ti-user"></i>
                        <p>User</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="ti-pencil-alt2"></i>
                        <p>Icons</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="ti-map"></i>
                        <p>Maps</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="ti-bell"></i>
                        <p>Notifications</p>
                    </a>
                </li>
                <!--<li class="active-pro">-->
                <!--<a href="upgrade.html">-->
                <!--<i class="ti-export"></i>-->
                <!--<p>Upgrade to PRO</p>-->
                <!--</a>-->
                <!--</li>-->
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
                    <a class="navbar-brand" href="#">Dashboard</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div class="form-group" style="padding-top: 15px">
                                <input type="text" placeholder="Search" class="form-control">
                            </div>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-user"></i>
                                <p>未登录</p>
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
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
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
                        <div class="header">
                            <h4 class="title">市场温度计</h4>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="row">
                                <div class="col-md-2 col-sm-4 col-xs-12">
                                    <ul class="nav nav-stacked" role="tablist">
                                        <li class="active">
                                            <a href="#UpDown" role="tab" data-toggle="tab">
                                                涨跌分布
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#limit" role="tab" data-toggle="tab";>
                                                涨跌停
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#unknown" role="tab" data-toggle="tab">
                                                未定
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-8 col-sm-8 col-xs-6">
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="UpDown">
                                            <div class="col-md-6 col-sm-8 col-xs-8">
                                                <div class="card">
                                                    <!--<div class="header"></div>-->
                                                    <div class="content">
                                                        <div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>

                                                        <div class="footer">
                                                            <div class="chart-legend">
                                                                <i class="fa fa-circle text-info"></i> Open
                                                                <i class="fa fa-circle text-danger"></i> Bounce
                                                                <i class="fa fa-circle text-warning"></i> Unsubscribe
                                                            </div>
                                                            <hr>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="limit">
                                            <p>涨跌停</p>
                                        </div>
                                        <div class="tab-pane" id="unknown">
                                            <p>unknown</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">Users Behavior</h4>
                            <p class="category">24 Hours performance</p>
                        </div>
                        <div class="content">
                            <div id="chartHours" class="ct-chart"></div>
                            <div class="footer">
                                <div class="chart-legend">
                                    <i class="fa fa-circle text-info"></i> Open
                                    <i class="fa fa-circle text-danger"></i> Click
                                    <i class="fa fa-circle text-warning"></i> Click Second Time
                                </div>
                                <hr>
                                <div class="stats">
                                    <i class="ti-reload"></i> Updated 3 minutes ago
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">全部股票</h4>
                            <p class="category">Here is a subtitle for this table</p>
                            <hr>
                        </div>
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
                                <tr>
                                    <td>5</td>
                                    <td>000005</td>
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
                                    <td>6</td>
                                    <td>000006</td>
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
                            <hr>
                            <ul class="pagination" style="margin: 0 420px;">
                                <li><a href="#">&laquo;</a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">&raquo;</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">

                <form method="post" action="/dashboard/temple.form">
                    <ul >
                        <%--<li><label id="registerInfo"></label></li>--%>
                        <li><span>用户名</span><input type="text" id="username" name="username" class="ipt field" maxlength="50"/><label></label></li>
                        <li><span>注册邮箱</span><input type="text" id="email" name="email" class="ipt field" maxlength="50"/><label></label></li>
                        <li><span>用户密码</span><input type="password" id="password" name="password" class="ipt field" /><label></label></li>
                        <%--<li><span>确认密码</span><input type="password" id="password2" name="password2" class="ipt field "/><label></label></li>--%>
                        <li><span >提示信息</span>

                        </li>
                        <li id="tipmsg"></li>
                        <li >
                            <div align="right">
                                <!--a <a href="javascript:document.getElementById('form1').submit();">立即注册</a>-->
                                <input type="submit" id="btn_register" value="立即注册" >
                            </div>

                        </li>
                    </ul>
                </form>
            </div>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <%--<button type="button" class="btn btn-primary">提交更改</button>--%>
            <%--</div>--%>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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

<script type="text/javascript">
    $(document).ready(function(){

        demo.initChartist();

        $.notify({
            icon: 'ti-gift',
            message: "Welcome to <b>Paper Dashboard</b> - a beautiful Bootstrap freebie for your next project."

        },{
            type: 'success',
            timer: 4000
        });

    });
</script>
</html>
