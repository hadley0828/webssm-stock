<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangty
  Date: 2017/6/9
  Time: 下午6:42
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
    
    <script>


        function createStrategy() {
            var strategyID = "1";
            var createrID = "2";
            var strategyName = "3";
            var strategyInfo = document.getElementById('s_intro').value;
            var now = "<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); %>";

            var stock_pool = document.getElementById('stock_pool').value;
            var index_component = document.getElementById('index_component').value;
            var block = document.getElementById('block').value;
            var industry = document.getElementById('industry').value;
            var st_stock = document.getElementById('st_stock').value;
            var exchange = document.getElementById('exchange').value;
            var concept = document.getElementById('concept').value;
            var area = document.getElementById('area').value;

            var cycle = document.getElementById('cycle').value;
            var max_num = document.getElementById('max_num').value;



            var map = "strategyID="+strategyID+"&createrID="+createrID+"&strategyName="+strategyName+ "&strategyExplanation="+(escape(strategyInfo)) +"&createTime="+now
                        + "&stockPondChosen="+stock_pool +"&IndexIngredient="+index_component+ "&block="+block + "&industry="+ industry + "&concept="+concept + "&STStock="+st_stock + "&exchange="+exchange+ "&region="+area
                        + "&transferCycle="+cycle + "&max_num="+ max_num
                        ;


            $.ajax({
                type:"POST",
                url:'<%=request.getContextPath()%>/strategy/createCustomizeStrategy',
                data: {map:map},
                dataType:"json",
                success:function (result) {
                    
                }
            });
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
                    <a class="navbar-brand" href="#">Dashboard</a>
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
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <div class="row">
                                <div class="col-sm-4"><blockquote>制定策略</blockquote></div>
                                <div class="col-xs-offset-11">
                                    <button class="btn btn-success" onclick="createStrategy()">创建</button>
                                </div>
                            </div>
                            <textarea class="form-control" id="s_intro" placeholder="请输入策略说明" rows="3"></textarea>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>择股设置</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-2" style="padding-top: 10px">选择股票池:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="stock_pool">
                                        <option>全部股票</option>
                                        <option>自选池股票</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row" style="padding-top: 10px">
                                <div class="col-xs-2" style="padding-top: 10px">指数成份:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="index_component">
                                        <option>全选</option>
                                        <option>sh000016</option>
                                        <option>sh000300</option>
                                        <option>sh000905</option>
                                    </select>
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">板块:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="block">
                                        <option>全选</option>
                                        <option>主板</option>
                                        <option>创业板</option>
                                        <option>中小板</option>
                                    </select>
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">行业:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="industry">
                                        <c:forEach items="${industryBlock}" var="industry">
                                            <option>${industry}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row" style="padding-top: 10px">
                                <div class="col-xs-2" style="padding-top: 10px">ST股票:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="st_stock">
                                        <option>包含ST</option>
                                        <option>排除ST</option>
                                        <option>仅有ST</option>
                                    </select>
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">交易所:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="exchange">
                                        <option>全选</option>
                                        <option>上海</option>
                                        <option>深圳</option>
                                    </select>
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">概念:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="concept">
                                        <c:forEach items="${conceptBlock}" var="concept">
                                            <option>${concept}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row" style="padding-top: 10px">
                                <div class="col-xs-2" style="padding-top: 10px;">地域板块</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="area">
                                        <c:forEach items="${areaBlock}" var="area">
                                            <option>${area}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-2" style="padding-top: 10px">调仓周期:</div>
                                <div class="col-xs-1">
                                    <input type="text" class="form-control" id="cycle">
                                </div>
                                <div class="col-xs-2" style="padding-top: 10px">最大持仓股票数:</div>
                                <div class="col-xs-1">
                                    <input type="text" class="form-control" id="max_num">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>选股指标</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-2"><h5>指标</h5></div>
                                <div class="col-xs-2"><h5>比较符</h5></div>
                                <div class="col-xs-2"><h5>范围</h5></div>
                                <div class="col-xs-5"><h5>值</h5></div>
                                <div class="col-xs-1"><h5>操作</h5></div>
                            </div>
                            <hr>
                            <div class="row" id="open">
                                <div class="col-xs-2" style="padding-top: 10px">开盘价</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('open')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="close" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">收盘价</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('close')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="high" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">最高价</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('high')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="low" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">最低价</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('low')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="last" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">前日收盘价</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('last')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="volume" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">当日成交量</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('volume')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="volume5" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">5日平均成交量</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('volume5')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="volume20" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">20日平均成交量</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('volume20')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="up" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">1日涨幅</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('up')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="up5" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">5日涨幅</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('up5')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="up20" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">20日涨幅</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('up20')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="list" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">上市天数</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('list')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                            <div class="row" id="trade" style="padding-top: 5px">
                                <div class="col-xs-2" style="padding-top: 10px">交易天数</div>
                                <div class="col-xs-2">
                                    <select class="form-control"  onchange="show('trade')">
                                        <option value="大于">大于</option>
                                        <option value="小于">小于</option>
                                        <option value="等于">等于</option>
                                        <option value="区间">区间</option>
                                        <option value="最大排名">最大排名</option>
                                        <option value="最小排名">最小排名</option>
                                        <option value="排名区间">排名区间</option>
                                        <option value="最大%排名">最大%排名</option>
                                        <option value="最小%排名">最小%排名</option>
                                        <option value="排名%区间">排名%区间</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <select class="form-control" style="display: none">
                                        <option>全部</option>
                                        <option>行业内</option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px;text-align: center"><p style="display: none">-</p></div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-control" style="display: none">
                                </div>
                                <div class="col-xs-1" style="padding-top: 10px">
                                    <input type="checkbox">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>策略回测</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-2" style="padding-top: 10px">回测时间:</div>
                                <div class="col-xs-2">
                                    <input type="date" class="form-control">
                                </div>
                                <div class="col-xs-1" style="text-align: center;padding-top: 10px">-</div>
                                <div class="col-xs-2">
                                    <input type="date" class="form-control">
                                </div>
                                <div class="col-xs-2" style="text-align: right;padding-top: 10px">收益基准:</div>
                                <div class="col-xs-2">
                                    <select class="form-control">
                                        <option>上证50</option>
                                        <option>沪深300</option>
                                        <option>中证500</option>
                                    </select>
                                </div>
                            </div>
                            <button class="btn btn-success" onclick="start()">开始回测</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="dataList" style="display: none">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>收益统计</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>投资组合</th>
                                        <th>年化收益率</th>
                                        <th>基准年化收益率</th>
                                        <th>阿尔法</th>
                                        <th>贝塔</th>
                                        <th>夏普比率</th>
                                        <th>收益波动率</th>
                                        <th>信息比率</th>
                                        <th>最大回撤</th>
                                        <th>换手率</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>本策略</td>
                                        <td>291.19%</td>
                                        <td>14.7%</td>
                                        <td>282.65%</td>
                                        <td>0.42</td>
                                        <td>7.27</td>
                                        <td>39.51%</td>
                                        <td>6.76</td>
                                        <td>17.11%</td>
                                        <td>--</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="chart" style="display: none">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>收益图</blockquote>
                            <hr>
                        </div>
                        <div class="content">
                            <ul class="nav nav-pills">
                                <li class="active"><a data-toggle="pill" href="#first">收益曲线</a> </li>
                                <li><a data-toggle="pill" href="#second">收益周期统计</a> </li>
                            </ul>
                            <div class="tab-content">
                                <div id="first" class="tab-pane fade in active">1st</div>
                                <div id="second" class="tab-pane fade">2nd</div>
                            </div>
                            <hr>
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
<%--<script src="<%=contextPath%>/assets/js/bootstrap-checkbox-radio.js"></script>--%>

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


    function show(selectname) {
        var line = document.getElementById(selectname);
        var divs = line.getElementsByTagName('div');
        var value = divs[1].firstElementChild.value;
        switch (value){
            case "大于":
                divs[2].firstElementChild.setAttribute("style","display:none");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "小于":
                divs[2].firstElementChild.setAttribute("style","display:none");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "等于":
                divs[2].firstElementChild.setAttribute("style","display:none");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "区间":
                divs[2].firstElementChild.setAttribute("style","display:none");
                divs[4].firstElementChild.setAttribute("style","display");
                divs[5].firstElementChild.setAttribute("style","display");
                break;
            case "最大排名":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "最小排名":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "排名区间":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display");
                divs[5].firstElementChild.setAttribute("style","display");
                break;
            case "最大%排名":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "最小%排名":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display:none");
                divs[5].firstElementChild.setAttribute("style","display:none");
                break;
            case "排名%区间":
                divs[2].firstElementChild.setAttribute("style","display");
                divs[4].firstElementChild.setAttribute("style","display");
                divs[5].firstElementChild.setAttribute("style","display");
                break;
        }
    }

    function start() {
        document.getElementById("dataList").setAttribute("style","display");
        document.getElementById("chart").setAttribute("style","display");
    }
</script>
</html>
