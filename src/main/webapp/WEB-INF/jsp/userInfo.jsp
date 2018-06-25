<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script src="<%=contextPath%>/assets/js/jquery-1.10.2.js" type="text/javascript"></script>

    <script type="text/javascript">
        function update() {
            var id = document.getElementById("id").value;
            var sex=document.getElementById("sex").value;
            var age=document.getElementById("age").value;
            var address=document.getElementById("address").value;
            var phoneNumber=document.getElementById("phoneNumber").value;
            var mail=document.getElementById("mail").value;
            var introduction=document.getElementById("introduction").value;
            var name = document.getElementById("name").value;
            var birthday = document.getElementById("birthday").value;

            if (id == '' || sex == '' || age == '' || address == '' || phoneNumber == '' || mail == '' || introduction == '' || name == '' || birthday == ''){
                $.notify({
                    message: "请将信息填写完整！"
                },{
                    type:'warning',
                    timer:400
                });
            }else{
                $.ajax({
                    type:"POST",
                    url:'<%=request.getContextPath()%>/userInfo/update',
                    data:{id:id,sex:sex, age:age,address:address, phone:phoneNumber,mail:mail,intro:introduction,name:name,birthday:birthday},
                    dataType:'json',
                    cache:false,
                    async:false,
                    success:function (result) {
                        mydata = JSON.parse(result);
                        if(mydata.result == "更新成功"){
                            $.notify({
                                message: "更新成功！"
                            },{
                                type:'success',
                                timer:400
                            });
                            window.location.href = "<%=request.getContextPath()%>/userInfo/?id=" + id;
                        }
                    }
                });
            }
        }
        
        function deleteStock(code) {
            var id = document.getElementById("id").value;
            var stockcode = code;

            $.ajax({
                type:"POST",
                url:'<%=request.getContextPath()%>/userInfo/delete',
                data:{id:id, code:code},
                dataType:'json',
                cache:false,
                async:false,
                success:function (result) {
                    mydata = JSON.parse(result);
                    var parent = document.getElementById('stocklist');
//                    alert("code"+code);
                    var child = document.getElementById("code"+code);
//                    alert(child.firstElementChild.innerHTML)
                    parent.removeChild(child);
                }
            });
        }

        function intoStra(straid) {
            var userid = document.getElementById("id").value;
            var straid = straid;

            window.location.href = "<%=request.getContextPath()%>/strategy/createStrategy/?id="+userid+"&strategyid="+straid;

        }
        
        function deletestra(straid) {
            var straid = straid;

            $.ajax({
                type:"POST",
                url:'<%=request.getContextPath()%>/userInfo/deleteStra',
                data:{straid:straid},
                dataType:'json',
                cache:false,
                async:false,
                success:function (result) {
                    mydata = JSON.parse(result);
                    var parent = document.getElementById('stralist');
//                    alert("code"+code);
                    var child = document.getElementById("stra"+straid);
//                    alert(child.firstElementChild.innerHTML)
                    parent.removeChild(child);
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
                        <li style="background-color:#dcdcdc">
                            <a href="<%=contextPath%>/userInfo/?id=${user.account}">
                                <i class="ti-desktop"></i>
                                <p style="font-size: 14px;">个人中心</p>
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
                    <a class="navbar-brand" href="#">个人信息</a>
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


                    <div class="col-md-6 ">
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
                                                <input type="text" id="id" class="form-control" disabled value="${user.account}">
                                            </div>
                                        </div>

                                    </div>

                                    <h4 class="title" style="margin: auto;color: #68B3C8">个人信息</h4>


                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>性别</label>
                                                <select class="form-control" id="sex">
                                                    <option value="保密" <c:if test="${user.sex=='保密'}">selected="true"</c:if>>保密</option>
                                                    <option value="男" <c:if test="${user.sex=='男'}">selected="true"</c:if>>男</option>
                                                    <option value="女" <c:if test="${user.sex=='女'}">selected="true"</c:if>>女</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>年龄</label>
                                                <input type="text" id="age" class="form-control" style="background: #FAF0E6" placeholder="请输入年龄" value="${user.age}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>姓名</label>
                                                <input type="text" id="name" class="form-control" style="background: #FAF0E6" placeholder="请输入姓名" value="${user.name}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-5">
                                                <label>生日</label>
                                                <input type="text" id="birthday" class="form-control" style="background: #FAF0E6" placeholder="请输入生日" value="${user.birthday}">
                                            </div>
                                        </div>
                                    </div>
                                    <h4 class="title" style="margin: auto;color: #68B3C8">联系方式</h4>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>地址</label>
                                                <input type="text" id="address" class="form-control" style="background: #FAF0E6" placeholder="请填写联系地址" value="${user.address}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>电话号码</label>
                                                <input type="number" id="phoneNumber" class="form-control" style="background: #FAF0E6" placeholder="请输入电话号码" value="${user.handsetNumber}">
                                            </div>
                                        </div>

                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>邮箱地址</label>
                                                <input type="email" id="mail" class="form-control" style="background: #FAF0E6" placeholder="请输入邮箱地址" value="${user.mail}">
                                            </div>
                                        </div>
                                    </div>

                                    <h4 class="title" style="margin: auto;color: #68B3C8">个人简介</h4>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>介绍一下自己</label>
                                                <textarea rows="5" id="introduction" class="form-control" style="background: #FAF0E6" placeholder="请输入个人简介" value="${user.account}">${user.introduction}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <input type="button"
                                               id="btn_update"
                                               value="更 新"
                                               onclick= "update()"
                                               style="width:100px;"
                                               class="btn btn-info btn-fill btn-wd"/>
                                        <%--<button type="submit" class="btn btn-info btn-fill btn-wd" onclick="update()">更新</button>--%>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h3 class="title" style="color: #FF9500;">自选股票</h3>
                                    </div>
                                    <hr/>
                                    <div style="height:325px; overflow-y:auto"class="content table-responsive">
                                        <table class="table table-striped" style="padding-right: 2%;padding-left: 2%">
                                            <thead>
                                                <th>股票编号</th>
                                                <th>股票名称</th>
                                                <th>当前价格</th>
                                                <th>涨跌幅</th>
                                                <th>操作</th>
                                            </thead>


                                            <tbody id="stocklist">
                                            <c:forEach items="${optionalStockList}" var="oneStock">
                                                <tr id="code${oneStock.id}">
                                                    <td><a href="<%=contextPath%>/stockinfo/?id=${user.account}&stockCode=${oneStock.id}">${oneStock.id}</a></td>
                                                    <td>${oneStock.name}</td>
                                                    <td>${oneStock.closePrice}</td>
                                                    <td>${oneStock.uplift}</td>
                                                    <td><a onclick="deleteStock('${oneStock.id}')">删除</a></td>
                                                </tr>
                                            </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h3 class="title" style="color: #FF9500;">自定策略</h3>
                                    </div>
                                    <hr/>
                                    <div style="height:325px; overflow-y:auto"class="content table-responsive">
                                        <table class="table table-striped" style="padding-right: 2%;padding-left: 2%">
                                           <thead>
                                                <th>策略名称</th>
                                                <th>创建时间</th>
                                                <th>详情</th>
                                                <th>删除</th>
                                           </thead>
                                           <tbody id="stralist">
                                           <c:forEach items="${strategyList}" var="oneStrategy">
                                               <tr id="stra${oneStrategy.strategyID}">
                                                   <td>${oneStrategy.strategyName}</td>
                                                   <td>${oneStrategy.createTime}</td>
                                                   <td><a onclick="intoStra('${oneStrategy.strategyID}')">详情</a></td>
                                                   <td><a onclick="deletestra('${oneStrategy.strategyID}')">删除</a></td>
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
<%--<script src="<%=contextPath%>/assets/js/demo.js"></script>--%>

<script src="<%=contextPath%>/assets/js/laydate/laydate.js" type="text/javascript"></script>

<script type="text/javascript">
//    $(document).ready(function(){
//
//        demo.initChartist();
//
//
//    });
var today = getNowFormatDate();
var birthday = laydate.render({
    elem: '#birthday'
    ,max: today
});

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
</script>
</html>
