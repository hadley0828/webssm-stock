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
    <%--<link href="<%=contextPath%>/assets/css/demo.css" rel="stylesheet" />--%>


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=contextPath%>/assets/css/themify-icons.css" rel="stylesheet">

    <%--Bootstrap select--%>
    <link href="<%=contextPath%>/assets/css/bootstrap-select.min.css" rel="stylesheet" >


    <script src="<%=contextPath%>/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="<%=contextPath%>/assets/js/echarts.js"></script>

    <script>
        function getShKLineInfo() {
            var sdate = "2007-01-01";
            var ldate = "2017-06-01";
            var code = "sh000001";

            $.ajax({
                url: '<%=request.getContextPath()%>/stockinfo/getShDayKLine',
                data: {codeid: code, sdate:sdate, ldate:ldate},
                dataType:"json",
                success: function (result) {
                    mydata = JSON.parse(result);
                    fillCharts(mydata);
                },
                error:function () {
                    alert("查找上证指数失败！");
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
            var daykline = echarts.init(document.getElementById('sh000001'));


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
                            start: 95,
                            end: 100
                        },
                        {
                            show: true,
                            type: 'slider',
                            y: '90%',
                            start: 95,
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

        function getUpDownInfo() {
            var date = "2017-06-01";

            $.ajax({
                url:'<%=request.getContextPath()%>/stockinfo/getUpDown',
                data:{date:date},
                dataType:"json",
                success:function (result) {
                    myUpDownData = JSON.parse(result);
                    fillUpDownCharts(myUpDownData);
                }
            });
        }

        function splitUpDownData(rawdata) {
            var categoryData = [];
            for (var i = 0; i < rawdata.length; i++) {
                categoryData.push(rawdata[i]);
            }
            return {
                categoryUpDownData: categoryData
            };
        }

        function fillUpDownCharts(rawdata) {
            var upDownCharts = echarts.init(document.getElementById("updownPic"));

            data0 = splitUpDownData(rawdata);

            upDownCharts.setOption(
                option = {
                    title : {
                        text: '涨跌分布',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:['跌幅8%-跌停','跌幅6%-跌幅8%','跌幅4%-跌幅6%','跌幅2%-跌幅4%','跌幅2%-0','0-涨幅2%','涨幅2%-涨幅4%','涨幅4%-涨幅6%','涨幅6%-涨幅8%','涨幅8%-涨停']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series :
                        {
                            name:'面积模式',
                            type:'pie',
                            radius : [30, 110],
                            center : ['50%', '50%'],
                            roseType : 'area',
                            data:[
                                {value:data0.categoryUpDownData[0], name:'跌幅8%-跌停'},
                                {value:data0.categoryUpDownData[1], name:'跌幅6%-跌幅8%'},
                                {value:data0.categoryUpDownData[2], name:'跌幅4%-跌幅6%'},
                                {value:data0.categoryUpDownData[3], name:'跌幅2%-跌幅4%'},
                                {value:data0.categoryUpDownData[4], name:'跌幅2%-0'},
                                {value:data0.categoryUpDownData[5], name:'0-涨幅2%'},
                                {value:data0.categoryUpDownData[6], name:'涨幅2%-涨幅4%'},
                                {value:data0.categoryUpDownData[7], name:'涨幅4%-涨幅6%'},
                                {value:data0.categoryUpDownData[8], name:'涨幅6%-涨幅8%'},
                                {value:data0.categoryUpDownData[7], name:'涨幅8%-涨停'}
                            ]
                        }

                });
        }

        function getLimitInfo() {
            var date = "2017-06-01";

            $.ajax({
                url:'<%=request.getContextPath()%>/stockinfo/getLimit',
                data:{date:date},
                dataType:"json",
                success:function (result) {
                    myLimitData = JSON.parse(result);
                    fillLimitCharts(myLimitData);
                }
            });
        }

        function splitLimitData(rawdata){
            var categoryData = [];
            var upvalues = [];
            var downvalues = [];

            for(var i = 0; i < rawdata.length; i++){
                categoryData.push(rawdata[i][0]);
                upvalues.push(rawdata[i][1]);
                downvalues.push(rawdata[i][2]);
            }
            return{
                categoryLimitData: categoryData,
                limitUpValues: upvalues,
                limitDownValues: downvalues
            };
        }

        function fillLimitCharts(rawdata){
            var limitCharts = echarts.init(document.getElementById("limitPic"));

            data0 = splitLimitData(rawdata);

            limitCharts.setOption(option={
                title:{
                    text: "涨跌停",
                    textStyle:{
                        fontSize: 16
                    },
                    left: 40
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    },
                    right:20
                },
                legend: {
                    data:['涨停','跌停']
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: data0.categoryLimitData
                },
                yAxis: {
                    scale:true,
                    splitArea: {
                        show: true
                    }
                },
                dataZoom: [
                    {
                        type: 'inside',
                        start: 20,
                        end: 100
                    },
                    {
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 20,
                        end: 100
                    }
                ],
                series:[
                    {
                        name:'涨停',
                        type:'line',
                        data: data0.limitUpValues,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                        {
                        name:'跌停',
                            type:'line',
                            data:data0.limitDownValues,
                            markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }}
                     ]
            });
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function(){

//            demo.initChartist();









        });



        function add() {

        }

        $(function () {
            $(".selectpicker").selectpicker({dropuAuto:false});
        })

        function changeColor() {
            document.getElementById("searchIcon").setAttribute("style","color:orange");
        }

        function reColor() {
            document.getElementById("searchIcon").setAttribute("style","color:black");
        }

//        $(function () {
//            var availableTags = new Array();
//            var i=0;
//
//
//            $("#tags").autocomplete({source:availableTags});
//        });
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
                                    <a href="<%=contextPath%>/userInfo/" >
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
                                        <script>
                                            function getMarketInfo() {
                                                var date = "2017-06-01";

                                                $.ajax({
                                                    url: '<%=request.getContextPath()%>/stockinfo/getMarketInfo',

                                                });
                                            }
                                        </script>
                                        <li class="active">
                                            <blockquote>
                                                <span class="ti-bar-chart"></span>
                                                <a href="#UpDown" role="tab" data-toggle="tab" style="font-size: 25px">涨跌分布</a>
                                                <div class="row">

                                                    <div class="col-xs-6"  style="font-size: 10px;color:red">上涨:1000</div>
                                                    <div class="col-xs-6"  style="font-size: 10px;color:green">下跌:1000</div>
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
                                            <div id="updownPic" style="width: 700px;height: 400px;">
                                                <script >
                                                    getUpDownInfo();
                                                </script>
                                            </div>
                                        </div>

                                        <div class="tab-pane" id="limit">
                                            <div id="limitPic" style="width: 700px;height: 400px;">
                                                <script>
                                                    getLimitInfo();
                                                </script>
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
                <div class="col-md-12">
                    <div class="card">
                        <div class="header">
                            <blockquote>
                                <span class="ti-alarm-clock" style="font-size: 25px">上证指数</span>
                            </blockquote>

                            <hr>
                            <div id="sh000001" style="width: 1150px;height:400px;">
                                <script>
                                    getShKLineInfo();
                                </script>
                            </div>
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
                                    <script>

                                    </script>
                                    <select name="stock" id="stockSearch" class="form-control selectpicker show-tick dropup" data-live-search="true">
                                        <c:forEach var="oneStock" items="${codeAndName}">
                                            <option value="${oneStock}">${oneStock}</option>
                                        </c:forEach>
                                    </select>
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
