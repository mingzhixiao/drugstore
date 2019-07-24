var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/purchasingMenu/all",
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
        window.location.href = "/purchasingMenu/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/purchasingMenu/delete/"+id,
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
    $("#purchasingMenu_purchasingDate").datepicker({dateFormat:"yy-mm-dd"});
    var id = $(obj).attr("id");
    $.ajax({
        url:"/purchasingMenu/getPurchasingMenuById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var supplierId = data.data[0].supplierId;
            var employeeId = data.data[0].employeeId;
            var purchasingDate = data.data[0].purchasingDate
            var s = purchasingDate.substr(0,10);
            $("#purchasingMenu_id").val(id);
            $("#purchasingMenu_supplierId").val(supplierId);
            $("#purchasingMenu_employeeId").val(employeeId);
            $("#purchasingMenu_purchasingDate").val(s);
        }
    });
}
function purchasingMenuUpdate() {
    var id =  $("#purchasingMenu_id").val();
    var supplierId =  $("#purchasingMenu_supplierId").val();
    var employeeId =   $("#purchasingMenu_employeeId").val();
    var purchasingDate =   $("#purchasingMenu_purchasingDate").val();
    $.ajax({
        url: "/purchasingMenu/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,supplierId:supplierId,employeeId:employeeId,purchasingDate:purchasingDate},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
