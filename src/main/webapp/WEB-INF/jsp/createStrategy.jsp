<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO"%>
<%@ page import="com.quantour.ssm.model.CustomizeStrategy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO" %>
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
    <script src="<%=contextPath%>/assets/js/echarts.js"></script>

    <script>
        
        $(function () {
            if(${strategy != null}){
//                名称和描述
                document.getElementById("strategyName").value="${strategy.strategyName}";
                document.getElementById("s_intro").value="${strategy.strategyExplanation}";

//                择股设置
                document.getElementById("stock_pool").value="${strategy.stockPondDTO.stockPondChosen}";
                document.getElementById('index_component').value="${strategy.stockPondDTO.indexIngredient}";
                document.getElementById('block').value="${strategy.stockPondDTO.block}";
                document.getElementById('industry').value="${strategy.stockPondDTO.industry}";
                document.getElementById('st_stock').value="${strategy.stockPondDTO.STStock}";
                document.getElementById('exchange').value="${strategy.stockPondDTO.exchange}"
                document.getElementById('concept').value="${strategy.stockPondDTO.concept}";
                document.getElementById('area').value="${strategy.stockPondDTO.region}";

                document.getElementById("cycle").value="${strategy.tradeModelDTO.transferCycle}";
                document.getElementById("max_num").value="${strategy.tradeModelDTO.maxHoldStockNumber}";

//                选股指标
                var data = "${strategy.screeningConditionDTOArrayList}";
                data = data.substring(1,data.length-1);
//                alert(data);
                data = ", "+data;
                var datalist = data.split(", ScreeningConditionDTO");
//                alert(datalist[0]);
//                alert(datalist[1]);
//                alert(datalist[2]);
                for(var i=1;i<datalist.length;i++){
                    var oneScreen = datalist[i].substring(1,datalist[i].length-1);
                    var conList = oneScreen.split(",");

                    conList[1] = conList[1].split("=");
                    conList[2] = conList[2].split("=");
                    conList[2][1] = conList[2][1].substring(1,conList[2][1].length-1);
                    conList[3] = conList[3].split("=");
                    conList[3][1] = conList[3][1].substring(1,conList[3][1].length-1);
                    conList[4] = conList[4].split("=");
                    conList[5] = conList[5].split("=");
//                    alert(conList[1][1]);
//                    alert(conList[2][1]);
//                    alert(conList[3][1]);
//                    alert(conList[4][1]);
//                    alert(conList[5][1]);

                    var screen =  document.getElementById("open");
                    var selectname = conList[1][1];
//                    alert(selectname);
                    switch(selectname){
                        case "'开盘价'":
                            screen = document.getElementById("open");
                            selectname = "open";
                            break;
                        case "'收盘价'":
                            screen = document.getElementById("close");
                            selectname = "close";
                            break;
                        case "'最高价'":
                            screen = document.getElementById("high");
                            selectname = "high";
                            break;
                        case "'最低价'":
                            screen = document.getElementById("low");
                            selectname = "low";
                            break;
                        case "'前日收盘价'":
                            screen = document.getElementById("last");
                            selectname = "last";
                            break;
                        case "'当日成交量'":
                            screen = document.getElementById("volume");
                            selectname = "volume";
                            break;
                        case "'5日平均成交量'":
                            screen = document.getElementById("volume5");
                            selectname = "volume5";
                            break;
                        case "'20日平均成交量'":
                            screen = document.getElementById("volume20");
                            selectname = "volume20";
                            break;
                        case "'1日涨幅'":
                            screen = document.getElementById("up");
                            selectname = "up";
                            break;
                        case "'5日涨幅'":
                            screen = document.getElementById("up5");
                            selectname = "up5";
                            break;
                        case "'20日涨幅'":
                            screen = document.getElementById("up20");
                            selectname = "up20";
                            break;
                        case "'上市天数'":
                            screen = document.getElementById("list");
                            selectname = "list";
                            break;
                        case "'交易天数'":
                            screen = document.getElementById("trade");
                            selectname = "trade";
                            break;
                        default:
                            screen = document.getElementById("open");
                            alert(selectname)
                            break;
                    }

                    var divlist = screen.getElementsByTagName('div');
                    divlist[1].firstElementChild.value= conList[2][1];
                    show(selectname);
                    divlist[2].firstElementChild.value=conList[3][1];
                    divlist[3].firstElementChild.value=conList[4][1];
                    divlist[5].firstElementChild.value=conList[5][1];
                    divlist[6].firstElementChild.checked=true;
//                    alert(divlist[6].firstElementChild.checked);
                }
        }
        });


        function createStrategy() {
            var createrID = "${user.account.toString()}";

            var sDate = document.getElementById("sdate").value;
            var lDate = document.getElementById("ldate").value;
            var blockCode = document.getElementById("blockCode").value;

            if(blockCode == '上证50'){
                blockCode = "sh000016";
            }else if(blockCode == '沪深300'){
                blockCode = "sh000300";
            }else if(blockCode == '中证500'){
                blockCode = "sh000905";
            }

            var strategyName = document.getElementById('strategyName').value;
            var strategyInfo = document.getElementById('s_intro').value;
            var now = "<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); %>";
            var strategyID = createrID+ " " +now;

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

            var screen1 = document.getElementById("open");
            var list1 = screen1.getElementsByTagName('div');
            var conditionName1 = list1[0].innerHTML;
            var compareSymbol1 = list1[1].firstElementChild.value;
            var scope1 = list1[2].firstElementChild.value;
            var firstValue1 = list1[3].firstElementChild.value;
            var secondValue1 = list1[5].firstElementChild.value;


            var screen2 = document.getElementById("close");
            var list2 = screen2.getElementsByTagName('div');
            var conditionName2 = list2[0].innerHTML;
            var compareSymbol2 = list2[1].firstElementChild.value;
            var scope2 = list2[2].firstElementChild.value;
            var firstValue2 = list2[3].firstElementChild.value;
            var secondValue2 = list2[5].firstElementChild.value;
            var check2 = list2[6].firstElementChild.value;

            var screen3 = document.getElementById("high");
            var list3 = screen3.getElementsByTagName('div');
            var conditionName3 = list3[0].innerHTML;
            var compareSymbol3 = list3[1].firstElementChild.value;
            var scope3 = list3[2].firstElementChild.value;
            var firstValue3 = list3[3].firstElementChild.value;
            var secondValue3 = list3[5].firstElementChild.value;

            var screen4 = document.getElementById("low");
            var list4 = screen4.getElementsByTagName('div');
            var conditionName4 = list4[0].innerHTML;
            var compareSymbol4 = list4[1].firstElementChild.value;
            var scope4 = list4[2].firstElementChild.value;
            var firstValue4 = list4[3].firstElementChild.value;
            var secondValue4 = list4[5].firstElementChild.value;

            var screen5 = document.getElementById("last");
            var list5 = screen5.getElementsByTagName('div');
            var conditionName5 = list5[0].innerHTML;
            var compareSymbol5 = list5[1].firstElementChild.value;
            var scope5 = list5[2].firstElementChild.value;
            var firstValue5 = list5[3].firstElementChild.value;
            var secondValue5 = list5[5].firstElementChild.value;

            var screen6 = document.getElementById("volume");
            var list6 = screen6.getElementsByTagName('div');
            var conditionName6 = list6[0].innerHTML;
            var compareSymbol6 = list6[1].firstElementChild.value;
            var scope6 = list6[2].firstElementChild.value;
            var firstValue6 = list6[3].firstElementChild.value;
            var secondValue6 = list6[5].firstElementChild.value;

            var screen7 = document.getElementById("volume5");
            var list7 = screen7.getElementsByTagName('div');
            var conditionName7 = list7[0].innerHTML;
            var compareSymbol7 = list7[1].firstElementChild.value;
            var scope7 = list7[2].firstElementChild.value;
            var firstValue7 = list7[3].firstElementChild.value;
            var secondValue7 = list7[5].firstElementChild.value;

            var screen8 = document.getElementById("volume20");
            var list8 = screen8.getElementsByTagName('div');
            var conditionName8 = list8[0].innerHTML;
            var compareSymbol8 = list8[1].firstElementChild.value;
            var scope8 = list8[2].firstElementChild.value;
            var firstValue8 = list8[3].firstElementChild.value;
            var secondValue8 = list8[5].firstElementChild.value;

            var screen9 = document.getElementById("up");
            var list9 = screen9.getElementsByTagName('div');
            var conditionName9 = list9[0].innerHTML;
            var compareSymbol9 = list9[1].firstElementChild.value;
            var scope9 = list9[2].firstElementChild.value;
            var firstValue9 = list9[3].firstElementChild.value;
            var secondValue9 = list9[5].firstElementChild.value;

            var screen10 = document.getElementById("up5");
            var list10 = screen10.getElementsByTagName('div');
            var conditionName10 = list10[0].innerHTML;
            var compareSymbol10 = list10[1].firstElementChild.value;
            var scope10 = list10[2].firstElementChild.value;
            var firstValue10 = list10[3].firstElementChild.value;
            var secondValue10 = list10[5].firstElementChild.value;

            var screen11 = document.getElementById("up20");
            var list11 = screen11.getElementsByTagName('div');
            var conditionName11 = list11[0].innerHTML;
            var compareSymbol11 = list11[1].firstElementChild.value;
            var scope11 = list11[2].firstElementChild.value;
            var firstValue11 = list11[3].firstElementChild.value;
            var secondValue11 = list11[5].firstElementChild.value;

            var screen12 = document.getElementById("list");
            var list12 = screen12.getElementsByTagName('div');
            var conditionName12 = list12[0].innerHTML;
            var compareSymbol12 = list12[1].firstElementChild.value;
            var scope12 = list12[2].firstElementChild.value;
            var firstValue12 = list12[3].firstElementChild.value;
            var secondValue12 = list12[5].firstElementChild.value;

            var screen13 = document.getElementById("trade");
            var list13 = screen13.getElementsByTagName('div');
            var conditionName13 = list13[0].innerHTML;
            var compareSymbol13 = list13[1].firstElementChild.value;
            var scope13 = list13[2].firstElementChild.value;
            var firstValue13 = list13[3].firstElementChild.value;
            var secondValue13 = list13[5].firstElementChild.value;




            var map = "strategyID="+strategyID+"&sDate="+sDate+"&lDate="+lDate+ "&blockCode="+blockCode+"&createrID="+createrID+"&strategyName="+strategyName+ "&strategyExplanation="+strategyInfo +"&createTime="+now
                        + "&stockPondChosen="+stock_pool +"&IndexIngredient="+index_component+ "&block="+block + "&industry="+ industry + "&concept="+concept + "&STStock="+st_stock + "&exchange="+exchange+ "&region="+area
                        + "&transferCycle="+cycle + "&max_num="+ max_num
                        ;

            if(document.getElementById("check1").checked){
                if(compareSymbol1=='区间' || compareSymbol1=='排名%区间')
                    map = map + "&conditionName1="+conditionName1 + "&compareSymbol1="+compareSymbol1 + "&scope1="+scope1 + "&firstValue1="+firstValue1 + "&secondValue1="+secondValue1;
                else
                    map = map + "&conditionName1="+conditionName1 + "&compareSymbol1="+compareSymbol1 + "&scope1="+scope1 + "&firstValue1="+firstValue1;
            }
            if(document.getElementById("check2").checked){
                if(compareSymbol2=='区间' || compareSymbol2=='排名%区间')
                    map = map + "&conditionName2="+conditionName2 + "&compareSymbol2="+compareSymbol2 + "&scope2="+scope2 + "&firstValue2="+firstValue2 + "&secondValue2="+secondValue2;
                else
                    map = map + "&conditionName2="+conditionName2 + "&compareSymbol2="+compareSymbol2 + "&scope2="+scope2 + "&firstValue2="+firstValue2;
            }
            if(document.getElementById("check3").checked){
                if(compareSymbol3=='区间' || compareSymbol3=='排名%区间')
                    map = map + "&conditionName3="+conditionName3 + "&compareSymbol3="+compareSymbol3 + "&scope3="+scope3 + "&firstValue3="+firstValue3 + "&secondValue3="+secondValue3;
                else
                    map = map + "&conditionName3="+conditionName3 + "&compareSymbol3="+compareSymbol3 + "&scope3="+scope3 + "&firstValue3="+firstValue3;

            }
            if(document.getElementById("check4").checked){
                if(compareSymbol4=='区间' || compareSymbol4=='排名%区间')
                    map = map + "&conditionName4="+conditionName4 + "&compareSymbol4="+compareSymbol4 + "&scope4="+scope4 + "&firstValue4="+firstValue4 + "&secondValue4="+secondValue4;
                else
                    map = map + "&conditionName4="+conditionName4 + "&compareSymbol4="+compareSymbol4 + "&scope4="+scope4 + "&firstValue4="+firstValue4;

            }
            if(document.getElementById("check5").checked){
                if(compareSymbol5=='区间' || compareSymbol5=='排名%区间')
                    map = map + "&conditionName5="+conditionName5 + "&compareSymbol5="+compareSymbol5 + "&scope5="+scope5 + "&firstValue5="+firstValue5 + "&secondValue5="+secondValue5;
                else
                    map = map + "&conditionName5="+conditionName5 + "&compareSymbol5="+compareSymbol5 + "&scope5="+scope5 + "&firstValue5="+firstValue5
            }
            if(document.getElementById("check6").checked){
                if(compareSymbol6=='区间' || compareSymbol6=='排名%区间')
                    map = map + "&conditionName6="+conditionName6 + "&compareSymbol6="+compareSymbol6 + "&scope6="+scope6 + "&firstValue6="+firstValue6 + "&secondValue6="+secondValue6;
                else
                    map = map + "&conditionName6="+conditionName6 + "&compareSymbol6="+compareSymbol6 + "&scope6="+scope6 + "&firstValue6="+firstValue6

            }
            if(document.getElementById("check7").checked){
                if(compareSymbol7=='区间' || compareSymbol7=='排名%区间')
                    map = map + "&conditionName7="+conditionName7 + "&compareSymbol7="+compareSymbol7 + "&scope7="+scope7 + "&firstValue7="+firstValue7 + "&secondValue7="+secondValue7;
                else
                    map = map + "&conditionName7="+conditionName7 + "&compareSymbol7="+compareSymbol7 + "&scope7="+scope7 + "&firstValue7="+firstValue7;

            }
            if(document.getElementById("check8").checked){
                if(compareSymbol8=='区间' || compareSymbol8=='排名%区间')
                    map = map + "&conditionName8="+conditionName8 + "&compareSymbol8="+compareSymbol8 + "&scope8="+scope8 + "&firstValue8="+firstValue8 + "&secondValue8="+secondValue8;
                else
                    map = map + "&conditionName8="+conditionName8 + "&compareSymbol8="+compareSymbol8 + "&scope8="+scope8 + "&firstValue8="+firstValue8;

            }
            if(document.getElementById("check9").checked){
                if(compareSymbol9=='区间' || compareSymbol9=='排名%区间')
                    map = map + "&conditionName9="+conditionName9 + "&compareSymbol9="+compareSymbol9 + "&scope9="+scope9 + "&firstValue9="+firstValue9 + "&secondValue9="+secondValue9;
                else
                    map = map + "&conditionName9="+conditionName9 + "&compareSymbol9="+compareSymbol9 + "&scope9="+scope9 + "&firstValue9="+firstValue9;

            }
            if(document.getElementById("check10").checked){
                if(compareSymbol10=='区间' || compareSymbol10=='排名%区间')
                    map = map + "&conditionName10="+conditionName10 + "&compareSymbol10="+compareSymbol10 + "&scope10="+scope10 + "&firstValue10="+firstValue10 + "&secondValue10="+secondValue10;
                else
                    map = map + "&conditionName10="+conditionName10 + "&compareSymbol10="+compareSymbol10 + "&scope10="+scope10 + "&firstValue10="+firstValue10;

            }
            if(document.getElementById("check11").checked){
                if(compareSymbol11=='区间' || compareSymbol11=='排名%区间')
                    map = map + "&conditionName11="+conditionName11 + "&compareSymbol11="+compareSymbol11 + "&scope11="+scope11 + "&firstValue11="+firstValue11 + "&secondValue11="+secondValue11;
                else
                    map = map + "&conditionName11="+conditionName11 + "&compareSymbol11="+compareSymbol11 + "&scope11="+scope11 + "&firstValue11="+firstValue11;

            }
            if(document.getElementById("check12").checked){
                if(compareSymbol12=='区间' || compareSymbol12=='排名%区间')
                    map = map + "&conditionName12="+conditionName12 + "&compareSymbol12="+compareSymbol12 + "&scope12="+scope12 + "&firstValue12="+firstValue12 + "&secondValue12="+secondValue12;
                else
                    map = map + "&conditionName12="+conditionName12 + "&compareSymbol12="+compareSymbol12 + "&scope12="+scope12 + "&firstValue12="+firstValue12;

            }
            if(document.getElementById("check13").checked){
                if(compareSymbol13=='区间' || compareSymbol13=='排名%区间')
                    map = map + "&conditionName13="+conditionName13 + "&compareSymbol13="+compareSymbol13 + "&scope13="+scope13 + "&firstValue13="+firstValue13 + "&secondValue13="+secondValue13;
                else
                    map = map + "&conditionName13="+conditionName13 + "&compareSymbol13="+compareSymbol13 + "&scope13="+scope13 + "&firstValue13="+firstValue13;

            }

            $.ajax({
                type:"POST",
                url:'<%=request.getContextPath()%>/strategy/createCustomizeStrategy',
                data: {map:map},
                dataType:"json",
                async:true,
                success:function (result) {
                    mydata = JSON.parse(result);
                    //alert(mydata.result);
                    $.notify({
                        message: mydata.result
                    },{
                        type:'warning',
                        timer:400
                    })
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
                    <a class="navbar-brand" href="#">策略生成</a>
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
                            <label for="strategyName">策略名称</label>
                            <input type="text" class="form-control" id="strategyName" style="width: 30%">
                            <br>
                            <label for="s_intro">策略说明</label>
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
                                    <input type="checkbox" id="check1">
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
                                    <input type="checkbox" id="check2">
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
                                    <input type="checkbox" id="check3">
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
                                    <input type="checkbox" id="check4">
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
                                    <input type="checkbox" id="check5">
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
                                    <input type="checkbox" id="check6">
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
                                    <input type="checkbox" id="check7">
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
                                    <input type="checkbox" id="check8">
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
                                    <input type="checkbox" id="check9">
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
                                    <input type="checkbox" id="check10">
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
                                    <input type="checkbox" id="check11">
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
                                    <input type="checkbox" id="check12">
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
                                    <input type="checkbox" id="check13">
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
                                <div class="col-xs-2" style="text-align:center;padding-top: 10px">回测时间:</div>
                                <div class="col-xs-3">
                                    <input type="date" class="form-control" id="sdate">
                                </div>
                                <div class="col-xs-1" style="text-align: center;padding-top: 10px">-</div>
                                <div class="col-xs-3">
                                    <input type="date" class="form-control" id="ldate">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2" style="text-align: center;padding-top: 10px">收益基准:</div>
                                <div class="col-xs-2">
                                    <select class="form-control" id="blockCode">
                                        <option>上证50</option>
                                        <option>沪深300</option>
                                        <option>中证500</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <button class="btn btn-success" style="padding-left: 15px" onclick="start()">开始回测</button>
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
                                        <td id="re1"></td>
                                        <td id="re2"></td>
                                        <td id="re3"></td>
                                        <td id="re4"></td>
                                        <td id="re5"></td>
                                        <td id="re6"></td>
                                        <td id="re7"></td>
                                        <td id="re8"></td>
                                        <td id="re9"></td>
                                        <td id="re10"></td>
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
                                <li class="active"><a data-toggle="pill" href="#first">收益曲线</a>
                                    <div id="straLine1" style="width: 1100px; height: 400px;">

                                    </div>
                                </li>
                                <li><a data-toggle="pill" href="#second">收益周期统计</a>
                                    <div id="straLine2" style="width: 1100px; height: 400px;">

                                    </div>
                                </li>
                            </ul>
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

        var userId = "${user.account.toString()}";
        var sDate = document.getElementById("sdate").value;
        var lDate = document.getElementById("ldate").value;
        var blockCode = document.getElementById("blockCode").value;

        if(blockCode == '上证50'){
            blockCode = "sh000016";
        }else if(blockCode == '沪深300'){
            blockCode = "sh000300";
        }else if(blockCode == '中证500'){
            blockCode = "sh000905";
        }


        var strategyName = document.getElementById('strategyName').value;
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

        var screen1 = document.getElementById("open");
        var list1 = screen1.getElementsByTagName('div');
        var conditionName1 = list1[0].innerHTML;
        var compareSymbol1 = list1[1].firstElementChild.value;
        var scope1 = list1[2].firstElementChild.value;
        var firstValue1 = list1[3].firstElementChild.value;
        var secondValue1 = list1[5].firstElementChild.value;


        var screen2 = document.getElementById("close");
        var list2 = screen2.getElementsByTagName('div');
        var conditionName2 = list2[0].innerHTML;
        var compareSymbol2 = list2[1].firstElementChild.value;
        var scope2 = list2[2].firstElementChild.value;
        var firstValue2 = list2[3].firstElementChild.value;
        var secondValue2 = list2[5].firstElementChild.value;
        var check2 = list2[6].firstElementChild.value;

        var screen3 = document.getElementById("high");
        var list3 = screen3.getElementsByTagName('div');
        var conditionName3 = list3[0].innerHTML;
        var compareSymbol3 = list3[1].firstElementChild.value;
        var scope3 = list3[2].firstElementChild.value;
        var firstValue3 = list3[3].firstElementChild.value;
        var secondValue3 = list3[5].firstElementChild.value;

        var screen4 = document.getElementById("low");
        var list4 = screen4.getElementsByTagName('div');
        var conditionName4 = list4[0].innerHTML;
        var compareSymbol4 = list4[1].firstElementChild.value;
        var scope4 = list4[2].firstElementChild.value;
        var firstValue4 = list4[3].firstElementChild.value;
        var secondValue4 = list4[5].firstElementChild.value;

        var screen5 = document.getElementById("last");
        var list5 = screen5.getElementsByTagName('div');
        var conditionName5 = list5[0].innerHTML;
        var compareSymbol5 = list5[1].firstElementChild.value;
        var scope5 = list5[2].firstElementChild.value;
        var firstValue5 = list5[3].firstElementChild.value;
        var secondValue5 = list5[5].firstElementChild.value;

        var screen6 = document.getElementById("volume");
        var list6 = screen6.getElementsByTagName('div');
        var conditionName6 = list6[0].innerHTML;
        var compareSymbol6 = list6[1].firstElementChild.value;
        var scope6 = list6[2].firstElementChild.value;
        var firstValue6 = list6[3].firstElementChild.value;
        var secondValue6 = list6[5].firstElementChild.value;

        var screen7 = document.getElementById("volume5");
        var list7 = screen7.getElementsByTagName('div');
        var conditionName7 = list7[0].innerHTML;
        var compareSymbol7 = list7[1].firstElementChild.value;
        var scope7 = list7[2].firstElementChild.value;
        var firstValue7 = list7[3].firstElementChild.value;
        var secondValue7 = list7[5].firstElementChild.value;

        var screen8 = document.getElementById("volume20");
        var list8 = screen8.getElementsByTagName('div');
        var conditionName8 = list8[0].innerHTML;
        var compareSymbol8 = list8[1].firstElementChild.value;
        var scope8 = list8[2].firstElementChild.value;
        var firstValue8 = list8[3].firstElementChild.value;
        var secondValue8 = list8[5].firstElementChild.value;

        var screen9 = document.getElementById("up");
        var list9 = screen9.getElementsByTagName('div');
        var conditionName9 = list9[0].innerHTML;
        var compareSymbol9 = list9[1].firstElementChild.value;
        var scope9 = list9[2].firstElementChild.value;
        var firstValue9 = list9[3].firstElementChild.value;
        var secondValue9 = list9[5].firstElementChild.value;

        var screen10 = document.getElementById("up5");
        var list10 = screen10.getElementsByTagName('div');
        var conditionName10 = list10[0].innerHTML;
        var compareSymbol10 = list10[1].firstElementChild.value;
        var scope10 = list10[2].firstElementChild.value;
        var firstValue10 = list10[3].firstElementChild.value;
        var secondValue10 = list10[5].firstElementChild.value;

        var screen11 = document.getElementById("up20");
        var list11 = screen11.getElementsByTagName('div');
        var conditionName11 = list11[0].innerHTML;
        var compareSymbol11 = list11[1].firstElementChild.value;
        var scope11 = list11[2].firstElementChild.value;
        var firstValue11 = list11[3].firstElementChild.value;
        var secondValue11 = list11[5].firstElementChild.value;

        var screen12 = document.getElementById("list");
        var list12 = screen12.getElementsByTagName('div');
        var conditionName12 = list12[0].innerHTML;
        var compareSymbol12 = list12[1].firstElementChild.value;
        var scope12 = list12[2].firstElementChild.value;
        var firstValue12 = list12[3].firstElementChild.value;
        var secondValue12 = list12[5].firstElementChild.value;

        var screen13 = document.getElementById("trade");
        var list13 = screen13.getElementsByTagName('div');
        var conditionName13 = list13[0].innerHTML;
        var compareSymbol13 = list13[1].firstElementChild.value;
        var scope13 = list13[2].firstElementChild.value;
        var firstValue13 = list13[3].firstElementChild.value;
        var secondValue13 = list13[5].firstElementChild.value;

        var map = "userId="+userId+"&sDate="+sDate+"&lDate="+lDate+ "&blockCode="+blockCode +"&straName="+ strategyName + "&straIntro="+strategyInfo+ "&time="+now
            + "&stockPondChosen="+stock_pool +"&IndexIngredient="+index_component+ "&block="+block + "&industry="+ industry + "&concept="+concept + "&STStock="+st_stock + "&exchange="+exchange+ "&region="+area
            + "&transferCycle="+cycle + "&max_num="+ max_num
        ;

        if(document.getElementById("check1").checked){
            if(compareSymbol1=='区间' || compareSymbol1=='排名%区间')
                map = map + "&conditionName1="+conditionName1 + "&compareSymbol1="+compareSymbol1 + "&scope1="+scope1 + "&firstValue1="+firstValue1 + "&secondValue1="+secondValue1;
            else
                map = map + "&conditionName1="+conditionName1 + "&compareSymbol1="+compareSymbol1 + "&scope1="+scope1 + "&firstValue1="+firstValue1;
        }
        if(document.getElementById("check2").checked){
            if(compareSymbol2=='区间' || compareSymbol2=='排名%区间')
                map = map + "&conditionName2="+conditionName2 + "&compareSymbol2="+compareSymbol2 + "&scope2="+scope2 + "&firstValue2="+firstValue2 + "&secondValue2="+secondValue2;
            else
                map = map + "&conditionName2="+conditionName2 + "&compareSymbol2="+compareSymbol2 + "&scope2="+scope2 + "&firstValue2="+firstValue2;
        }
        if(document.getElementById("check3").checked){
            if(compareSymbol3=='区间' || compareSymbol3=='排名%区间')
                map = map + "&conditionName3="+conditionName3 + "&compareSymbol3="+compareSymbol3 + "&scope3="+scope3 + "&firstValue3="+firstValue3 + "&secondValue3="+secondValue3;
            else
                map = map + "&conditionName3="+conditionName3 + "&compareSymbol3="+compareSymbol3 + "&scope3="+scope3 + "&firstValue3="+firstValue3;

        }
        if(document.getElementById("check4").checked){
            if(compareSymbol4=='区间' || compareSymbol4=='排名%区间')
                map = map + "&conditionName4="+conditionName4 + "&compareSymbol4="+compareSymbol4 + "&scope4="+scope4 + "&firstValue4="+firstValue4 + "&secondValue4="+secondValue4;
            else
                map = map + "&conditionName4="+conditionName4 + "&compareSymbol4="+compareSymbol4 + "&scope4="+scope4 + "&firstValue4="+firstValue4;

        }
        if(document.getElementById("check5").checked){
            if(compareSymbol5=='区间' || compareSymbol5=='排名%区间')
                map = map + "&conditionName5="+conditionName5 + "&compareSymbol5="+compareSymbol5 + "&scope5="+scope5 + "&firstValue5="+firstValue5 + "&secondValue5="+secondValue5;
            else
                map = map + "&conditionName5="+conditionName5 + "&compareSymbol5="+compareSymbol5 + "&scope5="+scope5 + "&firstValue5="+firstValue5
        }
        if(document.getElementById("check6").checked){
            if(compareSymbol6=='区间' || compareSymbol6=='排名%区间')
                map = map + "&conditionName6="+conditionName6 + "&compareSymbol6="+compareSymbol6 + "&scope6="+scope6 + "&firstValue6="+firstValue6 + "&secondValue6="+secondValue6;
            else
                map = map + "&conditionName6="+conditionName6 + "&compareSymbol6="+compareSymbol6 + "&scope6="+scope6 + "&firstValue6="+firstValue6

        }
        if(document.getElementById("check7").checked){
            if(compareSymbol7=='区间' || compareSymbol7=='排名%区间')
                map = map + "&conditionName7="+conditionName7 + "&compareSymbol7="+compareSymbol7 + "&scope7="+scope7 + "&firstValue7="+firstValue7 + "&secondValue7="+secondValue7;
            else
                map = map + "&conditionName7="+conditionName7 + "&compareSymbol7="+compareSymbol7 + "&scope7="+scope7 + "&firstValue7="+firstValue7;

        }
        if(document.getElementById("check8").checked){
            if(compareSymbol8=='区间' || compareSymbol8=='排名%区间')
                map = map + "&conditionName8="+conditionName8 + "&compareSymbol8="+compareSymbol8 + "&scope8="+scope8 + "&firstValue8="+firstValue8 + "&secondValue8="+secondValue8;
            else
                map = map + "&conditionName8="+conditionName8 + "&compareSymbol8="+compareSymbol8 + "&scope8="+scope8 + "&firstValue8="+firstValue8;

        }
        if(document.getElementById("check9").checked){
            if(compareSymbol9=='区间' || compareSymbol9=='排名%区间')
                map = map + "&conditionName9="+conditionName9 + "&compareSymbol9="+compareSymbol9 + "&scope9="+scope9 + "&firstValue9="+firstValue9 + "&secondValue9="+secondValue9;
            else
                map = map + "&conditionName9="+conditionName9 + "&compareSymbol9="+compareSymbol9 + "&scope9="+scope9 + "&firstValue9="+firstValue9;

        }
        if(document.getElementById("check10").checked){
            if(compareSymbol10=='区间' || compareSymbol10=='排名%区间')
                map = map + "&conditionName10="+conditionName10 + "&compareSymbol10="+compareSymbol10 + "&scope10="+scope10 + "&firstValue10="+firstValue10 + "&secondValue10="+secondValue10;
            else
                map = map + "&conditionName10="+conditionName10 + "&compareSymbol10="+compareSymbol10 + "&scope10="+scope10 + "&firstValue10="+firstValue10;

        }
        if(document.getElementById("check11").checked){
            if(compareSymbol11=='区间' || compareSymbol11=='排名%区间')
                map = map + "&conditionName11="+conditionName11 + "&compareSymbol11="+compareSymbol11 + "&scope11="+scope11 + "&firstValue11="+firstValue11 + "&secondValue11="+secondValue11;
            else
                map = map + "&conditionName11="+conditionName11 + "&compareSymbol11="+compareSymbol11 + "&scope11="+scope11 + "&firstValue11="+firstValue11;

        }
        if(document.getElementById("check12").checked){
            if(compareSymbol12=='区间' || compareSymbol12=='排名%区间')
                map = map + "&conditionName12="+conditionName12 + "&compareSymbol12="+compareSymbol12 + "&scope12="+scope12 + "&firstValue12="+firstValue12 + "&secondValue12="+secondValue12;
            else
                map = map + "&conditionName12="+conditionName12 + "&compareSymbol12="+compareSymbol12 + "&scope12="+scope12 + "&firstValue12="+firstValue12;

        }
        if(document.getElementById("check13").checked){
            if(compareSymbol13=='区间' || compareSymbol13=='排名%区间')
                map = map + "&conditionName13="+conditionName13 + "&compareSymbol13="+compareSymbol13 + "&scope13="+scope13 + "&firstValue13="+firstValue13 + "&secondValue13="+secondValue13;
            else
                map = map + "&conditionName13="+conditionName13 + "&compareSymbol13="+compareSymbol13 + "&scope13="+scope13 + "&firstValue13="+firstValue13;

        }

        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/strategy/runStrategy',
            data:{map:map},
            dataType:"json",
            async:true,
            success:function (result) {
                mydata = JSON.parse(result);
                fillStragetyInfo(mydata);
                fillStragetyPic1(JSON.parse(mydata.daysProfitList));
                fillStragetyPic2(JSON.parse(mydata.indexprofitvo));
            }
        });
        

        function fillStragetyInfo(rawdata) {
            document.getElementById("re1").innerHTML = '本策略';
            document.getElementById("re2").innerHTML = mydata.yearProfit;
            document.getElementById("re3").innerHTML = mydata.standardProfit;
            document.getElementById("re4").innerHTML = mydata.alpha;
            document.getElementById("re5").innerHTML = mydata.beta;
            document.getElementById("re6").innerHTML = mydata.sharpRate;
            document.getElementById("re7").innerHTML = mydata.profitWaveRate;
            document.getElementById("re8").innerHTML = mydata.infoPercent;
            document.getElementById("re9").innerHTML = mydata.maxBack;
            document.getElementById("re10").innerHTML = mydata.turnoverRate;
        }

        function splitStra2Data(rawdata) {
            var categoryData = [];
            var values1 = [];
            var values2 = [];
            var plusCyc = rawdata[0];
            var minusCyc = rawdata[1];
            var winRate = rawdata[2];


            for(var i = 3; i < rawdata.length; i++){
                categoryData.push(rawdata[i][0]);
                if(rawdata[i][0] > 0){
                    values2.push(rawdata[i][1]);
                    values1.push(0);
                }else if(rawdata[i][0] < 0){
                    values2.push(0);
                    values1.push(rawdata[i][1]);
                }else if(String.valueOf(rawdata[i][0]) == (String)("-0.0")){
                    values2.push(0);
                    values1.push(rawdata[i][1]);
                }else if(String.valueOf(rawdata[i][0]) == (String)("0.0")){
                    values1.push(0);
                    values2.push(rawdata[i][1]);
                }
            }

            return{
                catagoryData:categoryData,
                values1: values1,
                values2: values2,
                plus:plusCyc,
                minus:minusCyc,
                winRate:winRate
            };
        }
        function fillStragetyPic2(rawdata) {
            var straLine2 = echarts.init(document.getElementById("straLine2"));

            data0 = splitStra2Data(rawdata);

            straLine2.setOption(option={
                title:{
                    text:'正收益周期数:' + data0.plus + ' 负收益周期数:'+ data0.minus + ' 赢率:' + data0.winRate,
                    left:'center',
                    right:'center'
                },
                legend:{
                    right:25,
                    data:['负收益周期数','正收益周期数']
                },
                toolbox: {
                    // y: 'bottom',
                    feature: {
                        saveAsImage: {
                            pixelRatio: 2
                        }
                    }
                },
                tooltip: {},
                xAxis: {
                    type: 'category',
                    data: data0.catagoryData,
                },
                yAxis: {
                    type:'value'
                },
                series: [{
                    name: '负收益周期数',
                    type: 'bar',
                    data: data0.values1,

                }
                ,{
                    name: '正收益周期数',
                    type: 'bar',
                    data: data0.values2,
                }
                ],
            });

        }

        function splitStra1Data(rawdata) {
            var categoryData = [];
            var values1 = [];
            var values2 = [];

            for(var i = 0; i < rawdata.length; i++){
                categoryData.push(rawdata[i][0]);
                values1.push(rawdata[i][1]);
                values2.push(rawdata[i][2]);
            }
            return{
                categoryData: categoryData,
                values1:values1,
                values2:values2
            };
        }

        function fillStragetyPic1(rawdata) {
            var straLine1 = echarts.init(document.getElementById("straLine1"));


            data0 = splitStra1Data(rawdata);

            straLine1.setOption(option={
                legend:{
                    data:['基准收益率','策略收益率']
                },
                tooltip: {
                    trigger: 'none',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                toolbox:{
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        saveAsImage: {}
                    },
                    right:20
                },
                xAxis:
                    [
                        {
                            type: 'category',
                            axisTick: {
                                alignWithLabel: true
                            },
                            axisLine: {
                                onZero: true,
                                lineStyle: {
                                    color: '#d14a61'
                                }
                            },
                            axisPointer: {
                                label: {
                                    formatter: function (params) {
                                        return  '收益率:' + params.value
                                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                                    }
                                }
                            },
                            data:data0.categoryData

                        },
                        {
                            type: 'category',
                            axisTick: {
                                alignWithLabel: true
                            },
                            axisLine: {
                                onZero: true,
                                lineStyle: {
                                    color: '#5793f3'
                                }
                            },
                            axisPointer: {
                                label: {
                                    formatter: function (params) {
                                        return  '收益率:' + params.value
                                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                                    }
                                }
                            },
                            data:data0.categoryData
                        }
                    ],
                yAxis:{
                    scale:true,
                    axisTick: {
                        alignWithLabel: true
                    },
                    splitArea: {
                        show: true
                    }

                },
                dataZoom:[
                    {
                        xAxisIndex:[0,1],
                        type: 'inside',
                        start: 0,
                        end: 100
                    },
                    {
                        xAxisIndex:[0,1],
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 0,
                        end: 100
                    }
                ],
                series: [
                    {
                        name:'基准收益率',
                        type:'line',
                        data: data0.values1,
                        smooth: true,
                    },
                    {
                        name:'策略收益率',
                        type:'line',
                        data: data0.values2,
                        smooth: true,
                        xAxisIndex: 1,
                    }
                ]
            });
        }

        document.getElementById("dataList").setAttribute("style","display");
        document.getElementById("chart").setAttribute("style","display");
    }
</script>
</html>
