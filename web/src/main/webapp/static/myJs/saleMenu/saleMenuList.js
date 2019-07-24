var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/saleMenu/all",
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
        window.location.href = "/saleMenu/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/saleMenu/delete/"+id,
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
        url:"/saleMenu/getSaleMenuById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var employeeId = data.data[0].employeeId;
            var customerId = data.data[0].customerId;
            var saleDate = data.data[0].saleDate;
            $("#saleMenu_id").val(id);
            $("#saleMenu_employeeId").val(employeeId);
            $("#saleMenu_customerId").val(customerId);
            $("#saleMenu_saleDate").val(saleDate);
        }
    });
}
function updateSaleMenu() {
    var id =  $("#saleMenu_id").val();
    var employeeId =  $("#saleMenu_employeeId").val();
    var customerId = $("#saleMenu_customerId").val();
    var saleDate =   $("#saleMenu_saleDate").val();
    $.ajax({
        url: "/saleMenu/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,employeeId:employeeId,customerId:customerId,saleDate:saleDate},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
