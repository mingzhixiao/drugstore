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
        function addCustomer() {
            $.ajax({
                url: "/medicineMenu/add",
                type: "post",
               async: false,
                 dataType: "json",

              data:$("#addMedicineMenu").serialize(),
                success: function (data) {
                    if (data.saveMedicineMenu){
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
        function myFlush() {
            location.reload();};
    </script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addMedicineMenu">
        <fieldset>
            <legend>药品类型添加</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="number" type="text" placeholder="2019040501"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品名称</label>
                <div class="col-sm-4">
                    <input class="form-control" name="name" type="text" placeholder="阿莫西林"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">最大库存</label>
                <div class="col-sm-4">
                    <input  class="form-control" name="medicineMax" type="text"  placeholder="500"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">最小库存</label>
                <div class="col-sm-4">
                    <input class="form-control" name="medicineMin" type="text" placeholder="50"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addCustomer()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
