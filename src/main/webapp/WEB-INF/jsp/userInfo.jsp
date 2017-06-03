<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/3
  Time: 20:21
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
                <li class="active">
                    <a href="">
                        <i class="ti-panel"></i>
                        <p>主页</p>
                    </a>
                </li>
                <%--<li>--%>
                <%--<a href="">--%>
                <%--<i class="ti-user"></i>--%>
                <%--<p>个股信息</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <li>
                    <a href="">
                        <i class="ti-view-list-alt"></i>
                        <p>股票对比</p>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="ti-text"></i>
                        <p>策略应用</p>
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
            <div class="container-fluid">
                <div class="row">
                    <!--<div class="col-lg-4 col-md-5">-->
                    <!--<div class="card card-user">-->
                    <!--<div class="image">-->
                    <!--<img src="assets/img/background.jpg" alt="..."/>-->
                    <!--</div>-->
                    <!--<div class="content">-->
                    <!--<div class="author">-->
                    <!--<img class="avatar border-white" src="assets/img/faces/face-2.jpg" alt="..."/>-->
                    <!--<h4 class="title">Chet Faker<br />-->
                    <!--<a href="#"><small>@chetfaker</small></a>-->
                    <!--</h4>-->
                    <!--</div>-->
                    <!--<p class="description text-center">-->
                    <!--"I like the way you work it <br>-->
                    <!--No diggity <br>-->
                    <!--I wanna bag it up"-->
                    <!--</p>-->
                    <!--</div>-->
                    <!--<hr>-->
                    <!--<div class="text-center">-->
                    <!--<div class="row">-->
                    <!--<div class="col-md-3 col-md-offset-1">-->
                    <!--<h5>12<br /><small>Files</small></h5>-->
                    <!--</div>-->
                    <!--<div class="col-md-4">-->
                    <!--<h5>2GB<br /><small>Used</small></h5>-->
                    <!--</div>-->
                    <!--<div class="col-md-3">-->
                    <!--<h5>24,6$<br /><small>Spent</small></h5>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--<div class="card">-->
                    <!--<div class="header">-->
                    <!--<h4 class="title">Team Members</h4>-->
                    <!--</div>-->
                    <!--<div class="content">-->
                    <!--<ul class="list-unstyled team-members">-->
                    <!--<li>-->
                    <!--<div class="row">-->
                    <!--<div class="col-xs-3">-->
                    <!--<div class="avatar">-->
                    <!--<img src="assets/img/faces/face-0.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive">-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--<div class="col-xs-6">-->
                    <!--DJ Khaled-->
                    <!--<br />-->
                    <!--<span class="text-muted"><small>Offline</small></span>-->
                    <!--</div>-->

                    <!--<div class="col-xs-3 text-right">-->
                    <!--<btn class="btn btn-sm btn-success btn-icon"><i class="fa fa-envelope"></i></btn>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</li>-->
                    <!--<li>-->
                    <!--<div class="row">-->
                    <!--<div class="col-xs-3">-->
                    <!--<div class="avatar">-->
                    <!--<img src="assets/img/faces/face-1.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive">-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--<div class="col-xs-6">-->
                    <!--Creative Tim-->
                    <!--<br />-->
                    <!--<span class="text-success"><small>Available</small></span>-->
                    <!--</div>-->

                    <!--<div class="col-xs-3 text-right">-->
                    <!--<btn class="btn btn-sm btn-success btn-icon"><i class="fa fa-envelope"></i></btn>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</li>-->
                    <!--<li>-->
                    <!--<div class="row">-->
                    <!--<div class="col-xs-3">-->
                    <!--<div class="avatar">-->
                    <!--<img src="assets/img/faces/face-3.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive">-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--<div class="col-xs-6">-->
                    <!--Flume-->
                    <!--<br />-->
                    <!--<span class="text-danger"><small>Busy</small></span>-->
                    <!--</div>-->

                    <!--<div class="col-xs-3 text-right">-->
                    <!--<btn class="btn btn-sm btn-success btn-icon"><i class="fa fa-envelope"></i></btn>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</li>-->
                    <!--</ul>-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--</div>-->

                    <div class="col-xs-12 ">
                        <div class="card">
                            <div class="header">
                                <h3 class="title" style="color: #FF9500;">账户信息</h3>
                            </div>
                            <hr/>
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label >用户名</label>
                                                <input type="text" class="form-control border-input" disabled value="test">
                                            </div>
                                        </div>
                                        <!--<div class="col-md-3">-->
                                        <!--<div class="form-group">-->
                                        <!--<label>Username</label>-->
                                        <!--<input type="text" class="form-control border-input" placeholder="Username" value="michael23">-->
                                        <!--</div>-->
                                        <!--</div>-->
                                        <!--<div class="col-md-4">-->
                                        <!--<div class="form-group">-->
                                        <!--<label for="email">Email address</label>-->
                                        <!--<input type="email" class="form-control border-input" placeholder="Email" id="email">-->
                                        <!--</div>-->
                                        <!--</div>-->
                                    </div>

                                    <h4 class="title" style="margin: auto;color: #68B3C8">个人信息</h4>


                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>性别</label>
                                                <input type="text" class="form-control border-input" placeholder="请输入性别" value="男">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>年龄</label>
                                                <input type="text" class="form-control border-input" placeholder="请输入年龄" value="21">
                                            </div>
                                        </div>
                                    </div>

                                    <h4 class="title" style="margin: auto;color: #68B3C8">联系方式</h4>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>地址</label>
                                                <input type="text" class="form-control border-input" placeholder="请填写联系地址" value="nju">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>电话号码</label>
                                                <input type="number" class="form-control border-input" placeholder="请输入电话号码" value="7890">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>固定电话</label>
                                                <input type="number" class="form-control border-input" placeholder="请输入固话号码" value="9982">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>邮箱地址</label>
                                                <input type="email" class="form-control border-input" placeholder="请输入邮箱地址">
                                            </div>
                                        </div>
                                    </div>

                                    <h4 class="title" style="margin: auto;color: #68B3C8">个人简介</h4>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>About Me</label>
                                                <textarea rows="5" class="form-control border-input" placeholder="Here can be your description" value="Mike">Oh so, your weak rhyme
You doubt I'll bother, reading into it
I'll probably won't, left to my own devices
But that's the difference in our opinions.</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">更新</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
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
