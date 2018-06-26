<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.quantour.ssm.dto.stockDTO" %><%--
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

    <script src="<%=contextPath%>/assets/js/echarts.js"></script>

    <script type="text/javascript">

//        function showNews() {
//            window.location.href = "http://www.baidu.com";
//        }

        function getKLineInfo(){

            var sdate = "2007-01-01";
            var ldate = "2017-06-01";
            var code = String(${stock.id});

            while(code.length < 6){
                code = "0" + code;
            }
//            alert(code);

            $.ajax({
                url: '<%=request.getContextPath()%>/stockinfo/getDayKLineInfo',
                data: {codeid:code, sdate:sdate, ldate:ldate},
                dataType: "json",
                success: function (result) {
                    mydata = JSON.parse(result);
                    fillCharts(mydata);
                },
                error:function () {
                    $.notify({
                        message: "获取K线图数据失败！"
                    },{
                        type:'warning',
                        timer:400
                    })
                }
            });

        }

        function splitData(rawdata) {
            var categoryData = [];
            var values = [];
            for (var i = 0; i < rawdata.length; i++) {
                categoryData.push(rawdata[i].splice(0,1)[0]);
                values.push(rawdata[i]);
            }
            return {
                categoryData: categoryData,
                values: values
            };
        }

        function calculateMA(dayCount) {
            var result = [];
            for (var i = 0, len = data0.values.length; i < len; i++) {
                if (i < dayCount) {
                    result.push('-');
                    continue;
                }
                var sum = 0;
                for (var j = 0; j < dayCount; j++) {
                    sum += data0.values[i - j][1];
                }
                result.push(sum / dayCount);
            }
            return result;
        }

        function fillCharts(rawdata){
            var daykline = echarts.init(document.getElementById('dayKLine'));


//            alert("!");

            data0 = splitData(rawdata);

            daykline.setOption(my_option ={
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                legend: {
                    data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
                },
                grid: {
                    left: '10%',
                    right: '10%',
                    bottom: '15%'
                },
                xAxis: {
                    type: 'category',
                    data: data0.categoryData,
                    scale: true,
                    boundaryGap : false,
                    axisLine: {onZero: false},
                    splitLine: {show: false},
                    splitNumber: 20,
                    min: 'dataMin',
                },
                yAxis: {
                    scale: true,
                    splitArea: {
                        show: true
                    }
                },
                dataZoom: [
                    {
                        type: 'inside',
                        start: 90,
                        end: 100
                    },
                    {
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 90,
                        end: 100
                    }
                ],
                series: [
                    {
                        name: '日K',
                        type: 'candlestick',
                        data: data0.values,
                        markPoint: {
                            label: {
                                normal: {
                                    formatter: function (param) {
                                        return param != null ? Math.round(param.value) : '';
                                    }
                                }
                            },
                            data: [
                                {
                                    name: 'XX标点',
                                    coord: ['2013/5/31', 2300],
                                    value: 2300,
                                    itemStyle: {
                                        normal: {color: 'rgb(41,60,85)'}
                                    }
                                },
                                {
                                    name: 'highest value',
                                    type: 'max',
                                    valueDim: 'highest'
                                },
                                {
                                    name: 'lowest value',
                                    type: 'min',
                                    valueDim: 'lowest'
                                },
                                {
                                    name: 'average value on close',
                                    type: 'average',
                                    valueDim: 'close'
                                }
                            ],
                            tooltip: {
                                formatter: function (param) {
                                    return param.name + '<br>' + (param.data.coord || '');
                                }
                            }
                        },
                        markLine: {
                            symbol: ['none', 'none'],
                            data: [
                                [
                                    {
                                        name: 'from lowest to highest',
                                        type: 'min',
                                        valueDim: 'lowest',
                                        symbol: 'circle',
                                        symbolSize: 10,
                                        label: {
                                            normal: {show: false},
                                            emphasis: {show: false}
                                        }
                                    },
                                    {
                                        type: 'max',
                                        valueDim: 'highest',
                                        symbol: 'circle',
                                        symbolSize: 10,
                                        label: {
                                            normal: {show: false},
                                            emphasis: {show: false}
                                        }
                                    }
                                ],
                                {
                                    name: 'min line on close',
                                    type: 'min',
                                    valueDim: 'close'
                                },
                                {
                                    name: 'max line on close',
                                    type: 'max',
                                    valueDim: 'close'
                                }
                            ]
                        }
                    },
                    {
                        name: 'MA5',
                        type: 'line',
                        data: calculateMA(5),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA10',
                        type: 'line',
                        data: calculateMA(10),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA20',
                        type: 'line',
                        data: calculateMA(20),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA30',
                        type: 'line',
                        data: calculateMA(30),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },

                ]
            }
            );
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
                                <p style="font-size: 14px;">主页</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/compare/?id=${user.account}">
                                <i class="ti-flag-alt-2"></i>
                                <p style="font-size: 14px;">股票对比</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/strategy/?id=${user.account}">
                                <i class="ti-receipt"></i>
                                <p style="font-size: 14px;">股市策略</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/doctor/?id=${user.account}">
                                <i class="ti-support"></i>
                                <p style="font-size: 14px;">股票诊断</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/userInfo/?id=${user.account}">
                                <i class="ti-desktop"></i>
                                <p style="font-size: 14px;">个人中心</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/help/>">
                                <i class="ti-help"></i>
                                <p style="font-size: 14px;">帮助文档</p>
                            </a>
                        </li>

                    </ul>
                </c:when>
                <c:when test="${user.account == null}">
                    <ul class="nav">
                        <li>
                            <a href="<%=contextPath%>/dashboard/">
                                <i class="ti-home"></i>
                                <p style="font-size: 14px;">主页</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/compare/">
                                <i class="ti-flag-alt-2"></i>
                                <p style="font-size: 14px;">股票对比</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/strategy/">
                                <i class="ti-receipt"></i>
                                <p style="font-size: 14px;">股市策略</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/doctor/">
                                <i class="ti-support"></i>
                                <p style="font-size: 14px;">股票诊断</p>
                            </a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/help/>">
                                <i class="ti-help"></i>
                                <p style="font-size: 14px;">帮助文档</p>
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
                    <a class="navbar-brand" href="#">个股信息</a>
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
                <div class="col-md-12">
                    <div class="card">

                        <div class="row">
                            <div class="header">
                                <div class="row">
                                    <div class="col-md-3">
                                        <blockquote style="font-size: 25px">
                                            ${stock.name} (${stock.id})
                                        </blockquote>
                                    </div>
                                    <div class="col-md-2" style="padding-top: 13px">
                                        <p style="font-size: 20px">当前价格 ${stock.closePrice}</p>
                                    </div>
                                    <div class="col-md-2" style="padding-top: 13px">
                                        <p style="font-size: 20px">涨跌幅 ${stock.uplift}</p>
                                    </div>
                                    <div class="col-md-2 col-md-offset-3" style="padding-top: 10px">
                                        <button class="btn btn-default" onclick="addMine()">添加至自选</button>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </div>
                        <p style="padding-left: 30px;font-size: 20px;color: #e98200">今日数据</p>
                        <div class="row" style="padding-left: 30px">
                            <div class="col-xs-1">
                                <dt>开盘</dt>
                                <dd>${stock.openPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>收盘</dt>
                                <dd>${stock.closePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>最高</dt>
                                <dd style="color: red">${stock.highPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>最低</dt>
                                <dd style="color: green">${stock.closePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>成交量</dt>
                                <dd>${stock.volume}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>涨跌</dt>
                                <dd>${stock.uplift}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>所属行业</dt>
                                <dd>${stock.stockIndustry}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>所在区域</dt>
                                <dd>${stock.stockArea}</dd>
                            </div>
                        </div>
                        <br>
                        <p style="padding-left: 30px;font-size: 20px;color: #e98200">明日预测数据</p>
                        <div class="row" style="padding-left: 30px">
                            <div class="col-xs-1">
                                <dt>上升突破价位</dt>
                                <dd>${nextDay.risingBreakthroughPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>上升阻力价位</dt>
                                <dd>${nextDay.risingResistancePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>下跌支撑价位</dt>
                                <dd>${nextDay.declineSupportPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>下跌反转点价位</dt>
                                <dd>${nextDay.declineReversePrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>心理价位</dt>
                                <dd>${nextDay.targetPrice}</dd>
                            </div>
                            <div class="col-xs-1">
                                <dt>收盘价位</dt>
                                <dd>${nextDay.closePrice}</dd>
                            </div>

                        </div>
                        <hr>
                    </div>
                </div>

            </div>

            <div class="row">


                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>股票详情
                                <span class="ti-help-alt" title="术语解释"
                                      data-container="body" data-toggle="popover" data-placement="right"
                                      data-content="日k:记录的是股票一天内价格变动情况 MAn:n日均线值">
                                </span>
                                <script>
                                    $(function () {
                                        $("[data-toggle='popover']").popover().on("mouseenter", function () {
                                            var _this = this;
                                            $(this).popover("show");
                                            $(this).siblings(".popover").on("mouseleave", function () {
                                                $(_this).popover('hide');
                                            });
                                        }).on("mouseleave", function () {
                                            var _this = this;
                                            setTimeout(function () {
                                                if (!$(".popover:hover").length) {
                                                    $(_this).popover("hide")
                                                }
                                            }, 100);
                                        });
                                    });
                                </script>
                            </blockquote>
                            <div id="dayKLine" style="width: 1150px;height:400px;"></div>
                            <script>
                                getKLineInfo();
                            </script>
                        </div>
                        <hr>

                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="card">
                        <div class="header">
                            <blockquote>相关新闻</blockquote>
                        </div>
                        <hr>
                        <div style="height:380px; overflow-y:auto" class="content table-responsive">
                            <table class="table table-striped" style="padding-left: 2%;padding-right: 2%">
                                <thead class="text-center">
                                <tr>
                                    <th>标题</th>
                                    <th style="width: 96px">类型</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:set  var="startIndex" value="${fn:length(stock.newsDTOArrayList)-1 }"></c:set>
                                    <c:forEach var="one_news" items="${stock.newsDTOArrayList}" varStatus="status">
                                        <tr>
                                            <td><a href="${stock.newsDTOArrayList[startIndex-status.index].url}" target="_blank">${stock.newsDTOArrayList[startIndex-status.index].title}</a></td>
                                            <td>${stock.newsDTOArrayList[startIndex-status.index].type}</td>
                                            <td>${stock.newsDTOArrayList[startIndex-status.index].date}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="header">
                            <blockquote>其他股票</blockquote>
                        </div>
                        <hr>
                        <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#hot">热门股票</a></li>
                            <li><a data-toggle="pill" href="#collect">自选股票</a> </li>
                            <li><a data-toggle="pill" href="#history">历史浏览</a> </li>
                        </ul>
                        <div class="tab-content">
                            <div id="hot" class="tab-pane fade in active">
                                <div style="height:340px; overflow-y:auto" class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>股票名称</th>
                                            <th>股票代码</th>
                                            <th>当前价格</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="hot_stock" items="${hot_list}">
                                            <tr>
                                                <td id="hot_name_${hot_stock.stockCode}">${hot_stock.stockName}</td>
                                                <td>${hot_stock.stockCode}</td>
                                                <td>${hot_stock.newestPrice}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="collect" class="tab-pane fade">
                                <div style="height:340px; overflow-y:auto" class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>股票名称</th>
                                            <th>股票代码</th>
                                            <th>当前价格</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="oneStock" items="${optionalStockList}">
                                            <tr>
                                                <td>${oneStock.name}</td>
                                                <td>${oneStock.id}</td>
                                                <td>${oneStock.closePrice}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="history" class="tab-pane fade">
                                <div style="height:340px; overflow-y:auto" class="content table-responsive table-full-width">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>股票名称</th>
                                            <th>股票代码</th>
                                            <th>当前价格</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="oneStock" items="${allRecordStock}">
                                            <tr>
                                                <td>${oneStock.stock_name}</td>
                                                <td>${oneStock.code_id}</td>
                                                <td>${oneStock.closePrice}</td>
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

<script>
    function addMine() {
        var user_id = "${user.account}";
        var code_id = "${stock.id}";
        var date_time = "<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); %>";

        if(user_id == ""){
            $.notify({
                message: "请先登录"
            },{
                type:'warning',
                timer:400
            });
        }else{
            $.ajax({
                type: 'POST',
                url:'<%=request.getContextPath()%>/stockinfo/addStock',
                data:{user_id:user_id, code_id:code_id},
                dataType:'json',
                success:function (result) {
                    mydata = JSON.parse(result);
                    $.notify({
                        message: mydata.result
                    },{
                        type:'warning',
                        timer:400
                    });
                }
            });
        }
    }
</script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<%--<script src="<%=contextPath%>/assets/js/demo.js"></script>--%>

<%--<!--   echarts   --!>--%>



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
