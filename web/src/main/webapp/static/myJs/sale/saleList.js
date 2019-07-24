var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/sale/all",
        data:{"page":1},
        dataTye: "json",
        type: "get",
        success: function (data) {
            if (first++ > 1)
                location.reload();
        }
    });


    var flag = $("#checkAll").checked;
    flag = true;
    $("#checkAll").click(function(){
        var checks = $("input[name=ck]");
        if (flag){
            for(var i=0;i < checks.length;i++){
                checks[i].checked = true;
            }
            flag = false;
        } else {
            for(var i=0;i < checks.length;i++){
                checks[i].checked = false;
            }
            flag = true;
        }
    });

    $("#deleteAll").click(function(){
        var deleteFlag =0;
        var str ="";
        var cks = $("input[name=ck]");
        for (var i = 0; i < cks.length; i++) {
            if(cks[i].checked){
                deleteFlag++;
                str += cks[i].value + ",";
            }
        }
        if (deleteFlag > 0){
            $("#myModal").modal('show');
            $("#myModalLabel").html("确定要删除"+deleteFlag+"条信息么").css("font-family","Microsoft YaHei");
        } else {
            $("#myModal").modal('show');
            $("#myModalLabel").html("至少选择一个信息").css("font-family","Microsoft YaHei");
        }

    });


    $("#add").click(function () {
        window.location.href = "/sale/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/sale/delete/"+id,
        type: "post",
        data: {_method:"DELETE"},
        dataType: "json",
        success: function (data) {
            if (data){
                //alert(2);
                $("#myModal").modal('show');
                $("#myModalLabel").html("删除成功").css("font-family","Microsoft YaHei");
            }
        }
    });

};
function showUpdate(obj) {
    var id = $(obj).attr("id");
    $.ajax({
        url:"/sale/getSaleById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var saleMenuId = data.data[0].saleMenuId;
            var storeMedicineId = data.data[0].storeMedicineId;
            var salePrice = data.data[0].salePrice;
            var saleVipPrice = data.data[0].saleVipPrice;
            var amount = data.data[0].amount;
            var totalPrice = data.data[0].totalPrice;
            $("#sale_id").val(id);
            $("#sale_saleMenuId").val(saleMenuId);
            $("#sale_medicineId").val(storeMedicineId);
            $("#sale_price").val(salePrice);
            $("#sale_vipPrice").val(saleVipPrice);
            $("#sale_amount").val(amount);
            $("#sale_totalPrice").val(totalPrice);
        }
    });
}
function updateSale() {
    var id =  $("#sale_id").val();
    var saleMenuId =  $("#sale_saleMenuId").val();
    var storeMedicineId = $("#sale_medicineId").val();
    var salePrice =   $("#sale_price").val();
    var saleVipPrice = $("#sale_vipPrice").val();
    var amount = $("#sale_amount").val();
    var totalPrice = $("#sale_totalPrice").val();
    $.ajax({
        url: "/sale/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,saleMenuId:saleMenuId,storeMedicineId:storeMedicineId,salePrice:salePrice,saleVipPrice:saleVipPrice,amount:amount,totalPrice:totalPrice},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
