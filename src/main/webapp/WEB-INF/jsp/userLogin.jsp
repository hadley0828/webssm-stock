<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/3
  Time: 15:39
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
    <script type="text/javascript">
        function webLogin() {

            var loginname = $("#u").val();
            var password = $("#p").val();
            var mydata = "";

//            alert(loginname);
//            alert("loginname:" + loginname);
//            alert( $("#u").val());

            if(loginname == ""){
                //alert("用户名不得为空!");
                $.notify({
                    message: "用户名不得为空"
                },{
                    type:'warning',
                    timer:400
                })
                return;
            }

            if(password == ""){
                //alert("密码不得为空!");
                $.notify({
                    message: "密码不得为空！"
                },{
                    type:'warning',
                    timer:400
                })
                return;
            }


            $.ajax({
                type: "POST",
                url: '<%=request.getContextPath()%>/user/login',
//                contentType:'application/json',
                data:{id: loginname, password: password},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    mydata = JSON.parse(data);
                    if(mydata.code == "0"){
                        //alert(mydata.ms);
                        $.notify({
                            message: mydata.ms
                        },{
                            type:'warning',
                            timer:400
                        })
                    }else if(mydata.code == "1"){
                        //alert(mydata.ms);
                        $.notify({
                            message: mydata.ms
                        },{
                            type:'warning',
                            timer:400
                        })
                        window.location.href = "<%=contextPath%>/dashboard/?id=" + mydata.data.account;
                    }
//                    alert(mydata.data[0].account);

                }

            });

            <%--$.ajax({--%>
                <%--type:"POST",--%>
                <%--url: '<%=request.getContextPath()%>/dashboard/user',--%>
                <%--data:{id: mydata.data.account},--%>
                <%--dataType:"json",--%>
                <%--cache:false,--%>
                <%--async:false,--%>
                <%--success:function () {--%>
                    <%--alert("!");--%>
                <%--}--%>
            <%--});--%>
        }
    </script>


</head>
<body>
<div class="row">
    <div class="col-xs-4 col-xs-offset-4">
        <div class="card">
            <div class="header" style="background-color: #f4f3ef">
                <h4 class="title" style="color:#FF9500">Login</h4>
                <hr/>
            </div>
            <div class="content">
                <div class="row">
                    <div class="col-xs-6 col-xs-offset-3">
                        <div class="form-group">
                            <label>username</label>
                            <input type="text" id="u" class="form-control border-input"  placeholder="username" name="userName">
                        </div>
                    </div>
                    <div class="col-xs-6 col-xs-offset-3">
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" id="p" class="form-control border-input"  placeholder="password" name="password">
                        </div>
                    </div>
                    <div class="col-xs-6 col-xs-offset-3">
                        <p>
                            点击这里来
                            <a href="<%=contextPath%>/dashboard/regist">注册</a>
                        </p>

                    </div>

                    <div class="col-xs-4 col-xs-offset-8">
                        <%--<button class="btn btn-primary">login</button>--%>
                        <input type="button"
                               id="btn_login"
                               value="登 录"
                               onclick= "webLogin()"
                               style="width:100px;"
                               class="btn btn-primary"/>
                    </div>
                </div>
                <div class="footer">

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
