var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/store/all",
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
        window.location.href = "/store/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/store/delete/"+id,
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
        url:"/store/getStoreById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var medicineMenuNumber = data.data[0].medicineMenuNumber;
            var medicineMenuName = data.data[0].medicineMenuName;
            var storeMax = data.data[0].storeMax;
            var storeMin = data.data[0].storeMin;
            var amount = data.data[0].amount;
            var deadline = data.data[0].deadline;
            var productionDate = data.data[0].productionDate;
            $("#store_medicineId").val(id);
            $("#store_medicineMenuNumber").val(medicineMenuNumber);
            $("#store_medicineMenuName").val(medicineMenuName);
            $("#store_storeMax").val(storeMax);
            $("#store_storeMin").val(storeMin);
            $("#store_amount").val(amount);
            $("#store_deadline").val(deadline);
            $("#store_productionDate").val(productionDate);
        }
    });
}
function updateStore() {
    var medicineId =  $("#store_medicineId").val();
    var medicineMenuNumber =  $("#store_medicineMenuNumber").val();
    var medicineMenuName = $("#store_medicineMenuName").val();
    var storeMax =   $("#store_storeMax").val();
    var storeMin =   $("#store_storeMin").val();
    var amount =   $("#store_amount").val();
    var deadline =   $("#store_deadline").val();
    var productionDate =   $("#store_productionDate").val();
    $.ajax({
        url: "/store/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",medicineId:medicineId,medicineMenuName:medicineMenuName,medicineMenuNumber:medicineMenuNumber,storeMax:storeMax,storeMin:storeMin,amount:amount,deadline:deadline,productionDate:productionDate},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
