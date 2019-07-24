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
        function addStoreInformation() {
            $.ajax({
                url: "/store/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addStore").serialize(),
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
    <form class="form-horizontal" id="addStore">
        <fieldset>
            <legend>库存添加</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="medicineMenuNumber" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品ID</label>
                <div class="col-sm-4">
                    <input class="form-control" name="medicineId" type="text" placeholder="20190414011"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品名称</label>
                <div class="col-sm-4">
                    <input class="form-control" name="medicineMenuName" type="text" placeholder="阿莫西林"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">最大库存</label>
                <div class="col-sm-4">
                    <input class="form-control" name="storeMax" type="text" placeholder="500"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">最小库存</label>
                <div class="col-sm-4">
                    <input class="form-control" name="storeMin" type="text" placeholder="100"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品数量</label>
                <div class="col-sm-4">
                    <input class="form-control" name="amount" type="text" placeholder="400"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">有效天数</label>
                <div class="col-sm-4">
                    <input class="form-control" name="deadline" type="text" placeholder="68"/>
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
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addStoreInformation()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
