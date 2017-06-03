<%--
  Created by IntelliJ IDEA.
  User: loohaze
  Date: 2017/6/3
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function webRegister() {

            var loginname = $("#u").val();
            var password = $("#p").val();

//            alert(loginname);
//            alert("loginname:" + loginname);
//            alert( $("#u").val());

            if(loginname == ""){
                alert("用户名不得为空!");
//                $("#u").tips({
//                    side:2,
//                    msg:'用户名不得为空',
//                    bg: '#AE81FF',
//                    time: 3
//                });
//                $("#u").focus;
//                return false;
            }

            if(password == ""){
                alert("密码不得为空!");
                $("#p").tips({
                    side:2,
                    msg:"密码不得为空",
                    bg: '#AE81FF',
                    time: 3
                });
                $("#p").focus;
                return false;
            }


            $.ajax({
                type: "POST",
                url: '<%=request.getContextPath()%>/user/register',
//                contentType:'application/json',
                data:{id: loginname, password: password},
                dataType: "json",
                cache: false,
//                async: false,
                success: function (data) {
                    var mydata = JSON.parse(data);

                    if(mydata.code == "0"){
                        alert(mydata.ms);
                    }else if(mydata.code == "1"){
                        alert(mydata.ms);
                        window.location.href="login";
                    }
                }
            });
        }
    </script>
</head>
<body>

    <form action=""
          name = "registerform"
          accept-charset="utf-8"
          id = "register_form"
    <%--class = ""--%>
          method = "post"
    >
        <input type="hidden" name = "did" value = "0"/>
        <input type="hidden" name = "to" value = "log"/>

        <div class="uinArea" id = "uinArea">
            <label class="input-tips" for="u"> 账号：</label>
            <div class="inputOuter" id = "uArea">
                <input type="text" id = "u" name = "loginId"/>
            </div>
        </div>

        <div class="pwdArea" id="pwdArea">
            <label class="input-tips" for="p">密码：</label>
            <div class="inputOuter" id="pArea">
                <input type="password" id="p" name="pwd"/>
            </div>
        </div>

        <div style="padding-left:50px;margin-top:20px;">
            <input type="button"
                   id="btn_login"
                   value="注 册"
                   onclick= "webRegister()"
                   style="width:150px;"
                   class="button_blue"/>
        </div>

    </form>
</body>
</html>
