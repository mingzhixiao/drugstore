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
                url: "/purchasingMenu/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addPurchasingMenu").serialize(),
                success: function (data) {
                    if (data.savePurchasingMenu){
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
        }
        function myFlush() { location.reload();}

        $(function(){

            $.ajax({
                url: "/supplier/allInformation",
                type: "get",
                async: false,
                success: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        $("#supplierId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].id+"</option>");
                    }

                },
                error: function (data) {

                }
            });
        });


    </script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addPurchasingMenu">
        <fieldset>
            <legend>采购单</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group" style="display:none;">
                <label class="col-sm-2 control-label">员工编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="employeeId" type="text" placeholder="张三"  value="${employee.id}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">供应商编号</label>
                <div class="col-sm-4">
                    <select id ="supplierId" name ="supplierId" class="form-control"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">采购日期</label>
                <div class="col-sm-4">
                    <input class="form-control" name="purchasingDate" type="date"/>
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
