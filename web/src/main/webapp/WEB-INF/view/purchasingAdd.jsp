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
        function addPurchasing() {
            $.ajax({
                url: "/purchasing/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addPurchasing").serialize(),
                success: function (data) {
                    if (data){
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



        $(function(){

            $.ajax({
                url: "/purchasingMenu/allInformation",
                type: "get",
                async: false,
                success: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        $("#purchasingMenuId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].id+"</option>");
                    }

                },
                error: function (data) {

                }
            });

            $.ajax({
                url: "/medicine/allInformation",
                type: "get",
                async: false,
                success: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        $("#storeMedicineId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].id+"</option>");
                    }

                },
                error: function (data) {

                }
            });


        });
        
        function countTotalPrice() {
           var  amount =parseFloat($("#amount").val()) ;
           var purchsingPrice = parseFloat($("#purchasingPrice").val()) ;
           $("#totalPrice").val(amount * purchsingPrice);

        }
        

    </script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addPurchasing">
        <fieldset>
            <legend>采购详情信息</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">采购详情编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="20190414001"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">采购编号</label>
                <div class="col-sm-4">
                    <select id ="purchasingMenuId" name ="purchasingMenuId" class="form-control"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品ID</label>
                <div class="col-sm-4">
                    <select id ="storeMedicineId" name ="storeMedicineId" class="form-control"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">采购单价</label>
                <div class="col-sm-4">
                    <input class="form-control" id="purchasingPrice" name="purchasingPrice" type="text" placeholder="10"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">数量</label>
                <div class="col-sm-4">
                    <input class="form-control" id="amount" name="amount" type="text" placeholder="100" onblur="countTotalPrice();"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">总价</label>
                <div class="col-sm-4">
                    <input class="form-control"   id="totalPrice" name="totalPrice" type="text" placeholder="1000"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addPurchasing()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
