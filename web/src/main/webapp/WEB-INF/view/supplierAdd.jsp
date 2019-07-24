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
        function addInformation() {
            $.ajax({
                url: "/supplier/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addSupplier").serialize(),
                success: function (data) {
                    if (data.insertStore){
                        $("#myModal").modal('show');
                        $("#myModalLabel").html("添加成功").css("font-family","Microsoft YaHei");
                    }
                    else {
                        $("#myModal").modal('show');
                        $("#myModalLabel").html("添加失败").css("font-family","Microsoft YaHei");
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
    <form class="form-horizontal" id="addSupplier">
        <fieldset>
            <legend>供应商信息</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">供应商编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">供应商名称</label>
                <div class="col-sm-4">
                    <input class="form-control" name="name" type="text" placeholder="张三"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">联系人姓名</label>
                <div class="col-sm-4">
                    <input  class="form-control" name="linkman" type="text" placeholder="张先生"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">手机号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="phone" type="text" placeholder="13260500449"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">地址</label>
                <div class="col-sm-4">
                    <input class="form-control" name="address" type="text" placeholder="武汉市新洲区"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-4">
                    <input class="form-control" name="email" type="text" placeholder="1519900797@qq.com"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addInformation()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
