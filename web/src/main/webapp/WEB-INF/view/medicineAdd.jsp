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
                url: "/medicine/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addMedicine").serialize(),
                success: function (data) {
                    if (data.success){
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
                url: "/medicineMenu/allInformation",
                type: "get",
                async: false,
                success: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        $("#medicineMenuNumber").append("<option value='"+data.rows[i].number+"'>"+data.rows[i].number+"</option>");
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
    <form class="form-horizontal" id="addMedicine">
        <fieldset>
            <legend>药品详情添加</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品种类编号</label>
                <div class="col-sm-4">
                    <select id ="medicineMenuNumber" name ="medicineMenuNumber" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">库存编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="12" oninput = "value=value.replace(/[^\d]/g,'')"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">有效期</label>
                <div class="col-sm-4">
                    <input  class="form-control" name="validate" type="text" oninput = "value=value.replace(/[^\d]/g,'')"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">适用类型</label>
                <div class="col-sm-4">
                    <input class="form-control" name="type" type="text" placeholder="感冒"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">拼英简写</label>
                <div class="col-sm-4">
                    <input class="form-control" name="simplify" type="text" placeholder="amxl"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性状</label>
                <div class="col-sm-4">
                    <input class="form-control" name="shape" type="text" placeholder="颗粒"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">规格</label>
                <div class="col-sm-4">
                    <input class="form-control" name="format" type="text" placeholder="规格(克)"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">生产日期</label>
                <div class="col-sm-4">
                    <input class="form-control" name="productionDate" type="date"/>
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
