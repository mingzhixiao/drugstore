var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/medicineMenu/all",
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
        window.location.href = "/medicineMenu/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/medicineMenu/delete/"+id,
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
    alert(id);
    $.ajax({
        url:"/medicineMenu/getMedicineMenuById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            console.debug(data);
            var name = data.data[0].name;
            var medicineMax = data.data[0].medicineMax;
            var medicineMin = data.data[0].medicineMin;
            $("#medicineMenu_number").val(id);
            $("#medicineMenu_name").val(name);
            $("#medicineMenu_max").val(medicineMax);
            $("#medicineMenu_min").val(medicineMin);
        }
    });
}
function updateMedicine() {
    var id =  $("#medicineMenu_number").val();
    var name =  $("#medicineMenu_name").val();
    var medicineMax = $("#medicineMenu_max").val();
    var medicineMin =   $("#medicineMenu_min").val();
    $.ajax({
        url: "/medicineMenu/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",number:id,name:name,medicineMax:medicineMax,medicineMin:medicineMin},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};