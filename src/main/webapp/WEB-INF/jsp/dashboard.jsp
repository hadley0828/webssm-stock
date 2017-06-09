<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <%--Bootstrap table--%>
    <link href="<%=contextPath%>/assets/css/bootstrap-table.min.css" rel="stylesheet" >
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
                    <a class="navbar-brand" href="#">主页</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <form action="<%=contextPath%>/dashboard/stockData" method="post">
                                <div class="form-group" style="padding-top: 15px">
                                    <input type="text" placeholder="Search" class="form-control" id="searchCode">
                                </div>
                            </form>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${username != null}">
                                    <a href="<%=contextPath%>/userInfo/" >
                                        <i class="ti-user"></i>
                                        <p>${username}</p>
                                    </a>
                                </c:when>
                                <c:when test="${username == null}">
                                    <a href="<%=contextPath%>/dashboard/login" >
                                        <i class="ti-user"></i>
                                        <p>登录</p>
                                    </a>
                                </c:when>
                            </c:choose>

                            <%--<ul class="dropdown-menu">--%>
                                <%--<li><a href="#">Notification 1</a></li>--%>
                                <%--<li><a href="#">Notification 2</a></li>--%>
                                <%--<li><a href="#">Notification 3</a></li>--%>
                                <%--<li><a href="#">Notification 4</a></li>--%>
                                <%--<li><a href="#">Another notification</a></li>--%>
                            <%--</ul>--%>
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
                            <blockquote style="font-size: 35px">市场温度计</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-3">
                                    <ul class="nav nav-stacked" role="tablist">
                                        <li class="active">
                                            <blockquote>
                                                <span class="ti-bar-chart"></span>
                                                <a href="#UpDown" role="tab" data-toggle="tab" style="font-size: 25px">涨跌分布</a>
                                                <div class="row">
                                                    <div class="col-xs-6" style="font-size: 10px;color:red">上涨:1000</div>
                                                    <div class="col-xs-6" style="font-size: 10px;color:green">下跌:1000</div>
                                                </div>
                                            </blockquote>

                                        </li>
                                        <li style="padding-top: 30px">
                                            <blockquote>
                                                <span class="ti-exchange-vertical"></span>
                                                <a href="#limit" role="tab" data-toggle="tab" style="font-size: 25px">涨跌停</a>
                                                <div class="row">
                                                    <div class="col-xs-6" style="font-size: 10px;color:red">涨停:1000</div>
                                                    <div class="col-xs-6" style="font-size: 10px;color:green">跌停:1000</div>
                                                </div>
                                            </blockquote>
                                        </li>
                                        <!--<li>-->
                                        <!--<blockquote><a href="#unknown" role="tab" data-toggle="tab">-->
                                        <!--未定-->
                                        <!--</a></blockquote>-->
                                        <!--</li>-->
                                    </ul>
                                </div>
                                <div class="col-xs-9">
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
                            <blockquote>
                                <span class="ti-alarm-clock" style="font-size: 25px">上证指数</span>
                            </blockquote>
                        </div>
                        <hr>
                        <div class="content" style="height: 300px">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-2">
                                    <blockquote>个股查询</blockquote>
                                </div>
                                <div class="col-xs-6 input-group" style="padding-top: 5px">
                                    <input type="text" placeholder="编号/名称" class="form-control">
                                    <span class="input-group-addon"><i id="searchIcon" class="ti-search" onclick="add()" onmouseenter="changeColor()" onmouseleave="reColor()"></i> </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">全部股票</h4>
                            <p class="category">Here is a subtitle for this table</p>
                            <hr>
                        </div>
                        <div class="content table-responsive table-full-width">

                            <table id="stockTable1"></table>

                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">全部股票</h4>
                            <p class="category">Here is a subtitle for this table</p>
                            <hr>
                        </div>
                        <div class="content table-responsive table-full-width">

                            <table id="stockTable2"></table>

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

<%--Bootstrap table--%>
<script src="<%=contextPath%>/assets/js/bootstrap-table.min.js"></script>
<script src="<%=contextPath%>/assets/js/bootstrap-table-zh-CN.js"></script>


<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=contextPath%>/assets/js/demo.js"></script>

<script type="text/javascript">
    $(document).ready(function(){

        demo.initChartist();

        var oTable = new TableInit();
        oTable.Init();








    });

    var TableInit = function () {
        var oTableInit = new Object();

        oTableInit.Init = function () {
            $('#stockTable1').bootstrapTable({
                url:'<%=contextPath%>/dashboard/showRanking',
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: false,                  //是否显示所有的列
                showRefresh: false,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                //uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                columns: [{
                    checkbox: true
                }, {
                    field: 'code',
                    title: '股票代码'
                }, {
                    field: 'percent',
                    title: '涨跌幅'
                }]
            });
        }

        oTableInit.queryParams = function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                //departmentname: $("#txt_search_departmentname").val(),
                //statu: $("#txt_search_statu").val()
            };
            return temp;
        };

        return oTableInit;
    }

    function add() {

    }

    function changeColor() {
        document.getElementById("searchIcon").setAttribute("style","color:orange");
    }

    function reColor() {
        document.getElementById("searchIcon").setAttribute("style","color:black");
    }
</script>



</html>
