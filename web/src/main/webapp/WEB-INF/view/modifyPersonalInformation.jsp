<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sysname}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.4/jquery.js"></script>
    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>

</head>
<body>
<div class="container">
    <div class="col-sm-offset-2 col-sm-2">

        <button class="btn btn-primary" type="button" id="sendCodeBtn">发送验证码</button>

    </div>
</div>
<div class="container">
    <form class="form-horizontal" id="modify">
        <fieldset>
            <legend>修改密码</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">新密码</label>
                <div class="col-sm-4">
                    <input class="form-control" name="new_password" id="new_password" type="password" required="true" minlength="6"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-4">
                    <input class="form-control" name="sure_password" id="sure_password" type="password" onblur="confirm_password(); " minlength="6" required="true"/>
                </div>
                <div class="col-sm-2 control-label">
                    <label id="msg_password_modify" style="color: red"></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-4">
                    <input class="form-control" name="vCode" type="text" id="vCode"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2" align="center">
                    <button class="btn btn-primary" type="button" id="modify_change" onclick="confirm_password()">确定</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
<script>
    var submit_change = false;


    $(function(){
        $("#modify_change").click(function () {
            confirm_password();
            var password = $("#sure_password").val();
            var vCode = $("#vCode").val();
            if(submit_change) {
                //alert(2);
                submit_change=false;
                $.ajax({
                    url: "/employee/modify",
                    type: "post",
                    async: false,
                    dataType: "json",
                    data:{password:password,vCode:vCode},
                    success: function (data) {
                        alert(3);
                        if (data.update){
                            $("#myModal").modal('show');
                            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
                        }
                        else {
                            alert(4);
                            $("#myModal").modal('show');
                            $("#myModalLabel").html("修改失败").css("font-family","Microsoft YaHei");
                        }
                    },
                    error: function () {
                        alert("dd");
                    }
                    });
                 }
            });

        $("#sendCodeBtn").click(function(){
                $.ajax({
                    url: "/email/sendCode",
                    type: "put",
                    async: false,
                    success: function (data) {
                       // alert(3);
                        if (data.send){
                            $("#myModal").modal('show');
                            $("#myModalLabel").html("发送成功").css("font-family","Microsoft YaHei");
                        }
                        else {
                            alert(4);
                            $("#myModal").modal('show');
                            $("#myModalLabel").html("发送失败").css("font-family","Microsoft YaHei");
                        }
                    },
                    error: function () {
                        alert("dd");
                    }

                });




        });

     });
    function confirm_password() {
        var new_password = $("#new_password").val();
        var sure = $("#sure_password").val();
        if (new_password == sure){
            submit_change = true;
            $("#msg_password_modify").html("");
        }
        else {
            $("#msg_password_modify").html("两次输入不一样");
        }
    }


    function myFlush() { location.reload();};

</script>
</body>
</html>
