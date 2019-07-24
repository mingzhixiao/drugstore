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
        function addIncomerInformation() {
            $.ajax({
                url: "/income/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addIncome").serialize(),
                success: function (data) {
                    if (data.saveIncome){
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
    <form class="form-horizontal" id="addIncome">
        <fieldset>
            <legend>收入</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="medicineMenuNumber" type="text" placeholder="2015040101"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品名称</label>
                <div class="col-sm-4">
                    <input  class="form-control" name="medicineMenuName" type="text" placeholder="阿莫西林"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">收入</label>
                <div class="col-sm-4">
                    <input class="form-control" name="income" type="text" placeholder="982.23"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">支出</label>
                <div class="col-sm-4">
                    <input class="form-control" name="expense" type="text" placeholder="258.23"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">月</label>
                <div class="col-sm-4">
                    <input class="form-control" name="month" type="text" placeholder="1-12"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">年</label>
                <div class="col-sm-4">
                    <input class="form-control" name="year" type="2019"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">总收入</label>
                <div class="col-sm-4">
                    <input class="form-control" name="totalIncome" type="text"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addIncomerInformation()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
