<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

    <script src="<%=contextPath%>/assets/js/echarts.js"></script>

    <script type="text/javascript">
        function getKLineInfo() {

            var sdate = "2016-06-02";
            var ldate = "2017-06-02";
            var code = String(${generalScore.stockCode});

            while(code.length < 6){
                code = "0" + code;
            }

            $.ajax({
                url: '<%=request.getContextPath()%>/stockinfo/getDayKLineInfo',
                data: {codeid:code, sdate:sdate, ldate:ldate},
                dataType: "json",
                async:false,
                success: function (result) {
                    mydata = JSON.parse(result);
                    fillKline(mydata);
                },
                error:function () {
                    //alert("!");
                    $.notify({
                        message: "读取k线图数据失败"
                    },{
                        type:'warning',
                        timer:400
                    })
                }
            });
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

        function splitKlineData(rawdata){
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

        function fillKline(rawdata) {
            var daykline = echarts.init(document.getElementById("kline"));

            data0 = splitKlineData(rawdata);

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
                        start: 50,
                        end: 100
                    },
                    {
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 50,
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
            });

        }
        
        function splitMarketData(rawdata) {
            var categoryData = [];
            var values1 = [];
            var values2 = [];

            for(var i = 0; i < rawdata.length; i++){
                categoryData.push(rawdata[i][0]);
                values1.push(rawdata[i][1]);
                values2.push(rawdata[i][2]);
            }
            return{
                categoryMarketData:categoryData,
                values1:values1,
                values2:values2
            };
        }
        
        function fillMarketLine(rawdata) {
            var marketline = echarts.init(document.getElementById("marketline"));
            
            data0 = splitMarketData(rawdata);

            marketline.setOption(option={
                legend:{
                  data:['大盘10日涨跌幅','股票10日涨跌幅']
                },
                tooltip:{
                    trigger: 'axis'
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: data0.categoryMarketData
                },
                yAxis: {
                    scale:true,
                    splitArea: {
                        show: true
                    }
                },
                series:[
                    {
                        name:'大盘10日涨跌幅',
                        type:'line',
                        data: data0.values1,

                    },
                    {
                        name:'股票10日涨跌幅',
                        type:'line',
                        data: data0.values2,
                    }
                ]


            });
        }

        function getMarketLine() {
            var datalist = ${technical.technicalMapDTOArrayList};

            fillMarketLine(datalist);
        }




        function splitCapitalData(rawdata) {
            var category = [];
            var values1 = [];
            var valueAve = [];

            for(var i = 0; i < rawdata.length; i++){
                category.push(rawdata[i][0]);
                values1.push(rawdata[i][1]);
                valueAve.push(rawdata[i][2]);
            }
            return{
                category:category,
                valueSin:values1,
                valueAve:valueAve
            };
        }


        function fillCapitalBar(rawdata) {
            var capitalBar = echarts.init(document.getElementById("capitalBar"));

            data0 = splitCapitalData(rawdata);

            capitalBar.setOption(option={
                legend:{
                  data:['行业平均线','资金净流向']
                },
                tooltip:{
                    trigger: 'axis'
                },
                xAxis:  {
                    type: 'category',
//                    boundaryGap: false,
                    data: data0.category
                },
                yAxis: {
                    scale:true,
                    splitArea: {
                        show: true
                    },
                    name:'万元',
                    nameLocation:'end'
                },
                series:[
                    {
                        name:'行业平均线',
                        type:'line',
                        data: data0.valueAve,
                    },
                    {
                        name:'资金净流向',
                        type:'bar',
                        data: data0.valueSin,
                        barWidth:10
                    }

                ]

            });
        }

        function getCapitalBar() {
            var datalist = ${capital.flowMapList};

            fillCapitalBar(datalist);

        }

        function splitIndustry(rawdata) {
            var category = [];
            var value1 = [];
            var value2 = [];

            for(var i = 0; i < rawdata.length; i++){
                category.push(rawdata[i][0]);
                value1.push(rawdata[i][1]);
                value2.push(rawdata[i][2]);
            }
            return{
                categoryData:category,
                valuesin:value1,
                valueindus:value2
            };

        }

        function fillIndustry(rawdata) {
            var industryline = echarts.init(document.getElementById("industryFlow"));

            data0 = splitIndustry(rawdata);

            industryline.setOption(option={
                tooltip:{
                    trigger: 'axis'
                },
                legend:{
                    data:['行业指数','上证指数']
                },
                xAxis: {
                    type: 'category',
                    data: data0.categoryData
                },
                yAxis: {
                    type:'value'
                },
                series: [{
                    name: '行业指数',
                    type: 'line',
                    data: data0.valuesin,

                }
                    ,{
                        name: '上证指数',
                        type: 'line',
                        data: data0.valueindus,
                    }
                ],
            });
        }

        function getIndustryFlow() {
            var datalist = ${industry.changeList};

            fillIndustry(datalist);
        }


        function fillScoreBar(rawdata) {
            var scoreBar = echarts.init(document.getElementById("scoreView"));

            scoreBar.setOption(option={
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient:'vertical',
                    x : 'left',
                    y : 'center',
                    data:['技术面得分','资金面得分','消息面得分','行业面得分','基本面得分']
                },
                calculable : true,
                series : [
                    {
                        name:'得分',
                        type:'pie',
                        radius : [20, 80],
                        roseType : 'area',
                        x: '50%',               // for funnel
                        max: 40,                // for funnel
                        sort : 'ascending',     // for funnel
                        data:[
                            {value:rawdata[0], name:'技术面得分:'+rawdata[0]},
                            {value:rawdata[1], name:'资金面得分:'+rawdata[1]},
                            {value:rawdata[2], name:'消息面得分:'+rawdata[2]},
                            {value:rawdata[3], name:'行业面得分:'+rawdata[3]},
                            {value:rawdata[4], name:'基本面得分:'+rawdata[4]},
                        ]
                    }
                ]
            });
        }

        function getScoreBar() {
            var score1 = ${generalScore.technicalScore};
            var score2 = ${generalScore.capitalScore};
            var score3 = ${generalScore.messageScore};
            var score4 = ${generalScore.industryScore};
            var score5 = ${generalScore.basicScore};

            var datalist = [];
            datalist.push(score1);
            datalist.push(score2);
            datalist.push(score3);
            datalist.push(score4);
            datalist.push(score5);


            fillScoreBar(datalist);
        }

        $(function () {
            switch ("${generalScore.suggestion}"){
                case "卖出":
                    var sp1 = document.getElementById("sold");
                    sp1.setAttribute("class","label label-danger");
                    break;
                case "减持":
                    var sp2 = document.getElementById("minus");
                    sp2.setAttribute("class","label label-danger");
                    break;
                case "中性":
                    var sp3 = document.getElementById("wait");
                    sp3.setAttribute("class","label label-danger");
                    break;
                case "增持":
                    var sp4 = document.getElementById("plus");
                    sp4.setAttribute("class","label label-danger");
                    break;
                case "买入":
                    var sp5 = document.getElementById("buy");
                    sp5.setAttribute("class","label label-danger");
                    break;
                default:
                    alert("wrong suggestion");
            }
        })
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
                        <li >
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
                        <li >
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
                    <a class="navbar-brand" href="#">股票诊断</a>
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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-4">
                        <div class="card">
                            <div class="header text-center">
                                <h2><b>${generalScore.stockName}</b><small>(${generalScore.stockCode})</small></h2>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-6 col-xs-offset-3">
                                        <div class="text-center">
                                            <h1 style="color: #FF9500">${generalScore.totalScore}<small>分</small></h1>
                                            <br>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1 text-center">
                                        <span id="sold" class="label label-default">卖出</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span id="minus" class="label label-default">减持</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span id="wait" class="label label-default">观望</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span id="plus" class="label label-default">增持</span>
                                        <!--</div>-->
                                        <!--<div class="col-xs-2">-->
                                        <span id="buy" class="label label-default">买入</span>
                                    </div>
                                </div>
                                <hr/>
                                <%--<div class="row" style="margin: auto">--%>
                                    <%--<dl >--%>
                                        <%--<dt>短期</dt>--%>
                                        <%--<dd style="color: #3091B2">短期的强势行情可能结束，投资者及时短线卖出、离场观望为宜。</dd>--%>
                                        <%--<dt>中期</dt>--%>
                                        <%--<dd style="color: #3091B2">上涨趋势有所减缓，可适量高抛低吸。</dd>--%>
                                        <%--<dt>长期</dt>--%>
                                        <%--<dd style="color: #3091B2">迄今为止，共74家主力机构，持仓量总计50.87亿股，占流通A股52.40%</dd>--%>

                                    <%--</dl>--%>
                                <%--</div>--%>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-8">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>分数预览</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row" id="scoreView" style="width:750px;height:190px;">
                                    <script>
                                        getScoreBar();
                                    </script>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>技术面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h4><strong style="padding-left: 50px;font-size: xx-large">诊断结果：</strong><strong class="text-danger" style="font-size: xx-large">${generalScore.technicalDTO.technicalScore}</strong> 击败了${generalScore.technicalDTO.defeatPercent}%的股票</h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style=" color: #e98200;font-size: large">k线图</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content" id="kline" style="width:550px;height:400px;">
                                                <script>
                                                    getKLineInfo();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style=" color: #e98200;font-size: large">市场表现</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content" id="marketline" style="width:550px;height:400px;">
                                                <script>
                                                    getMarketLine();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style=" color: #e98200;font-size: large">成交量数据</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <caption style="padding-left: 15px;font-size: large">个股</caption>
                                                    <thead>
                                                        <tr>
                                                            <th>今日成交量</th>
                                                            <th>近五日平均成交量</th>
                                                            <th>近十日成交量</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td style="color: crimson">${generalScore.technicalDTO.oneDayVolume}</td>
                                                        <td style="color: crimson">${generalScore.technicalDTO.fiveDayVolume}</td>
                                                        <td style="color: crimson">${generalScore.technicalDTO.tenDayVolume}</td>
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
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>资金面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h4><strong style="padding-left: 50px;font-size: xx-large">诊断结果：</strong><strong class="text-danger" style="font-size: xx-large">${generalScore.capitalDTO.capitalScore}</strong> 击败了${generalScore.capitalDTO.defeatPercent}%的股票</h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style=" color: #e98200;font-size: large">资金流向</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content" id="capitalBar" style="width:650px;height:650px;">
                                                <script>
                                                    getCapitalBar();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="card" style="">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style=" color: #e98200;font-size: large">行业资金</strong></p>
                                                <hr>
                                            </div>
                                            <div class="content  table-responsive">
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <caption style="padding-left: 15px;font-size: large">个股</caption>
                                                    <tbody>
                                                    <tr>
                                                        <td>个股今日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.todayStockFlow}</td>
                                                        <td>个股五日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.fiveStockFlow}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>个股十日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.tenStockFlow}</td>
                                                        <td>个股二十日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.twentyStockFlow}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <caption style="padding-left: 15px;font-size: large">行业</caption>
                                                    <tbody>
                                                    <tr>
                                                        <td>行业今日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.todayIndustryFlow}</td>
                                                        <td>行业五日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.fiveIndustryFlow}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>行业十日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.tenIndustryFlow}</td>
                                                        <td>行业二十日流向(万元)</td>
                                                        <td style="color: crimson">${generalScore.capitalDTO.twentyIndustryFlow}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="padding-left: 2%;padding-right: 2%">
                                    <div class="card" >
                                        <div class="header" style="background-color: #d7d7d7">
                                            <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">机构交易</strong> </p>
                                            <hr>
                                        </div>
                                        <div class="content  table-responsive">
                                            <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                <caption style="padding-left: 15px;font-size: large">机构交易</caption>
                                                <thead class="text-center">
                                                <tr>
                                                    <%--<th>股票编号</th>--%>
                                                    <%--<th>股票名称</th>--%>
                                                    <th>累计购买额</th>
                                                    <th>买入席位数</th>
                                                    <th>累计卖出额</th>
                                                    <th>卖出席位数</th>
                                                    <th>净额</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <%--<td style="color: crimson">${generalScore.capitalDTO.stockCode}</td>--%>
                                                    <%--<td style="color: crimson">${generalScore.capitalDTO.stockName}</td>--%>
                                                    <td style="color: crimson">${generalScore.capitalDTO.bAmount}</td>
                                                    <td style="color: crimson">${generalScore.capitalDTO.bCount}</td>
                                                    <td style="color: crimson">${generalScore.capitalDTO.sAmount}</td>
                                                    <td style="color: crimson">${generalScore.capitalDTO.sCount}</td>
                                                    <td style="color: crimson">${generalScore.capitalDTO.net}</td>
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
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>消息面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h4><strong style="padding-left: 50px;font-size: xx-large">诊断结果：</strong><strong class="text-danger" style="font-size: xx-large">${generalScore.messageDTO.messageScore}</strong> 击败了${generalScore.messageDTO.defeatPercent}%的股票</h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #d7d7d7">
                                                        <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">分配预案</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive">
                                                        <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                            <tbody>
                                                            <tr>
                                                                <td>股票代码</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.code}</td>
                                                                <td>股票名称</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.name}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>分配年份</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.year}</td>
                                                                <td>公布日期</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.report_date}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>分红金额（每10股）</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.divi}</td>
                                                                <td>转增和送股数（每10股）</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_allocationDTO.shares}</td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #d7d7d7">
                                                        <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">业绩预测</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive">
                                                        <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                            <tbody>
                                                            <tr>
                                                                <td>股票代码</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.code}</td>
                                                                <td>股票名称</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.name}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>业绩变动类型</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.type}</td>
                                                                <td>发布日期</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.report_date}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>上年同期每股收益</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.pre_eps}</td>
                                                                <td>业绩变动范围</td>
                                                                <td style="color: crimson">${generalScore.messageDTO.message_forecastDTO.out_range}</td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-7">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="header" style="background-color: #d7d7d7">
                                                        <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">个股新闻</strong> </p>
                                                        <hr>
                                                    </div>
                                                    <div class="content table-responsive" style="height: 525px;overflow-y: auto">
                                                        <table class="table table-striped" style="padding-left: 2%;padding-right: 2%">
                                                            <thead class="text-center">
                                                                <tr>
                                                                    <th>标题</th>
                                                                    <th>类型</th>
                                                                    <th>时间</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:set var="startIndex" value="${fn:length(generalScore.messageDTO.messageNewsDTOArrayList)-1 }"></c:set>
                                                                <c:forEach var="one_new" items="${generalScore.messageDTO.messageNewsDTOArrayList}" varStatus="status">
                                                                    <tr>
                                                                        <td><a href="${generalScore.messageDTO.messageNewsDTOArrayList[startIndex-status.index].url}" target="_blank">${generalScore.messageDTO.messageNewsDTOArrayList[startIndex-status.index].title}</a></td>
                                                                        <td>${generalScore.messageDTO.messageNewsDTOArrayList[startIndex-status.index].type}</td>
                                                                        <td>${generalScore.messageDTO.messageNewsDTOArrayList[startIndex-status.index].date}</td>
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
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>行业面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h4><strong style="padding-left: 50px;font-size: xx-large">诊断结果：</strong><strong class="text-danger" style="font-size: xx-large">${generalScore.industryDTO.industryScore}</strong> 击败了${generalScore.industryDTO.defeatPercent}%的股票</h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">行业走势</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content" id="industryFlow" style="width:700px;height:400px;">
                                                <script>
                                                    getIndustryFlow();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">十日涨跌幅</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-bordered" style="padding-left: 2%;padding-right: 2%">
                                                    <thead>
                                                        <tr>
                                                            <th>近十天该行业的涨跌幅</th>
                                                            <th>近十天大盘的涨跌幅</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>${generalScore.industryDTO.tenDaysIndustryChange}</td>
                                                            <td>${generalScore.industryDTO.tenDaysMarketChange}</td>
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
                <div class="row">
                    <div class="col-xs-12 ">
                        <div class="card">
                            <div class="header text-center">
                                <h3><b>基本面诊股</b></h3>
                                <hr/>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h4><strong style="padding-left: 50px;font-size: xx-large">诊断结果：</strong><strong class="text-danger" style="font-size: xx-large">${generalScore.basicDTO.basicScore}</strong> 击败了${generalScore.basicDTO.defeatPercent}%的股票</h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #ff9500;font-size:larger">现金流量</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-responsive v">
                                                    <tbody>
                                                        <tr>
                                                            <td>经营现金净流量对销售收入比率</td>
                                                            <td>${generalScore.basicDTO.basicCashFlowDTO.cfSales}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>资产的经营现金流量回报率</td>
                                                            <td>${generalScore.basicDTO.basicCashFlowDTO.rateOfReturn}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>经营现金净流量与净利润的比率</td>
                                                            <td>${generalScore.basicDTO.basicCashFlowDTO.cfNm}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>经营现金净流量对负债比率</td>
                                                            <td>${generalScore.basicDTO.basicCashFlowDTO.cfLiAbilities}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>现金净流量比率</td>
                                                            <td>${generalScore.basicDTO.basicCashFlowDTO.cashFlowRatio}</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">营收能力</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-responsive v">
                                                    <tbody>
                                                    <tr>
                                                        <td>应收账款周转率</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.arTurnOver}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>应收账款周转天数</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.arTurnDays}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>存货周转率</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.inventoryTurnOver}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>存货周转天数</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.inventoryDays}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>流动资产周转率</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.currentAssetTurnOver}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>流动资产周转天数</td>
                                                        <td>${generalScore.basicDTO.basicEarningDTO.currentAssetDays}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">成长能力</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-responsive v">
                                                    <tbody>
                                                    <tr>
                                                        <td>主营业务收入增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.mbrg}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>净利润增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.nprg}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>净资产增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.nav}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>总资产增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.targ}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>每股收益增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.epsg}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>股东收益增长率</td>
                                                        <td>${generalScore.basicDTO.basicGrowDTO.seg}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">偿债能力</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-responsive v">
                                                    <tbody>
                                                    <tr>
                                                        <td>流动比率</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.currentRatio}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>速动比率</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.quickRatio}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>现金比率</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.cashRatio}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>利息支付倍数</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.icRatio}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>股东权益比率</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.sheqRatio}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>股东权益增长率</td>
                                                        <td>${generalScore.basicDTO.basicPaymentDTO.adRatio}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <br>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card">
                                            <div class="header" style="background-color: #d7d7d7">
                                                <p style="padding-left: 20px"><strong style="color: #e98200;font-size:larger">盈利能力</strong> </p>
                                                <hr>
                                            </div>
                                            <div class="content table-responsive">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <td>净资产收益率</td>
                                                            <td>净利率</td>
                                                            <td>毛利率</td>
                                                            <td>净利润</td>
                                                            <td>每股收益</td>
                                                            <td>营业收入</td>
                                                            <td>每股主营业收入</td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.roe}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.netProfitRatio}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.grossProfitRate}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.netProfits}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.esp}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.bussinessIncome}</td>
                                                            <td>${generalScore.basicDTO.basicProfitDTO.bips}</td>
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


</html>
