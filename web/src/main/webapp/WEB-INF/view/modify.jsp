<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sysname}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.4/jquery.js"></script>
    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>

    <script>


        $(function(){



            $("#modify_submit").click(function () {
                addCustomer();
            });
        });

        function addCustomer() {
            var name = $("#name").val();
            var phone = $("#phone").val();
            var email = $("#email").val();
            var id = $("#id").val();
            var employeeInformation = {
                id:id,
                name:name,
                email:email,
                phone:phone
            };
            console.log(employeeInformation);
            $.ajax({
                url: "/employee/updateOneself",
                type: "put",
                async: true,
                dataType: "json",
                contentType: "application/json",
                data:JSON.stringify(employeeInformation),
                success: function (data) {
                    console.debug(data);
                    if (data){
                        $("#myModal").modal('show');
                        $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
                    }
                    else {
                        $("#myModal").modal('show');
                        $("#myModalLabel").html("修改失败").css("font-family","Microsoft YaHei");
                    }
                },
                error: function (data) {

                }
            });
        };
        function myFlush() { location.reload();};
    </script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addEmployee">
        <fieldset>
            <legend>修改信息</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" id = "id" type="text"  value="${employee.id}" disabled/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-4">
                    <input class="form-control" name="name" id="name" type="text" value="${employee.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">手机</label>
                <div class="col-sm-4">
                    <input class="form-control" name="phone" id="phone" type="text" value="${employee.phone}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-4">
                    <input class="form-control" name="email" id="email" type="text" value="${employee.email}"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" id="modify_submit">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
