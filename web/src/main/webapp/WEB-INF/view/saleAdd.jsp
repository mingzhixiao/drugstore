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
        function addSaleInformation() {
            $.ajax({
                url: "/sale/add",
                type: "post",
                async: false,
                dataType: "json",
                data:$("#addMedicine").serialize(),
                success: function (data) {
                    if (data.add){
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
                url: "/saleMenu/allInformation",
                type: "get",
                async: false,
                success: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        $("#saleMenuId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].id+"</option>");
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



      function countPrice() {
          var salePrice = parseFloat($("#salePrice").val());
          var saleVipPrice = parseFloat($("#saleVipPrice").val());
          var amount = parseFloat($("#amount").val());

          if (saleVipPrice == 0){
              $("#totalPrice").val(salePrice * amount);
          } else {
              $("#totalPrice").val(saleVipPrice * amount);
          }
      }
    </script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addMedicine">
        <fieldset>
            <legend>销售详情单</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">销售单编号</label>
                <div class="col-sm-4">
                    <select id ="saleMenuId" name ="saleMenuId" class="form-control"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">药品ID</label>
                <div class="col-sm-4">
                    <select id ="storeMedicineId" name ="storeMedicineId" class="form-control"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">售卖价格</label>
                <div class="col-sm-4">
                    <input class="form-control" id="salePrice" name="salePrice" type="text" onblur="countPrice();"  value="9.00"  placeholder="9.00" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">会员价格</label>
                <div class="col-sm-4">
                    <input class="form-control" id="saleVipPrice" name="saleVipPrice" onblur="countPrice();" type="text" value="0"  placeholder="8.00" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">数量</label>
                <div class="col-sm-4">
                    <input class="form-control" id="amount" name="amount" type="text"  onblur="countPrice();" placeholder="2"  value="1" oninput = "value=value.replace(/[^\d]/g,'')"  required/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">总价</label>
                <div class="col-sm-4">
                    <input class="form-control" name="totalPrice"  id="totalPrice" type="18"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addSaleInformation()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
</body>
</html>
