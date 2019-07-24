var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/medicine/all",
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
        window.location.href = "/medicine/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/medicine/delete/"+id,
        type: "post",
        data: {_method:"DELETE"},
        dataType: "json",
        success: function (data) {
            if (data){
                //alert(2);
                $("#myModal").modal('show');
                $("#myModalLabel").html("删除成功").css("font-family","Microsoft YaHei");
                /*                  setTimeout(function(){
                                      $("#myModal").modal('hide');
                                  },1200);*/
                //window.location.href = "/customer/all";
            }
        }
    });

};
function showUpdate(obj) {
    var id = $(obj).attr("id");
    alert(id);
    $.ajax({
        url:"/medicine/getMedicineById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            console.debug(data);
            var medicineMenuNumber = data.data[0].medicineMenuNumber;
            var type = data.data[0].type;
            var simplify = data.data[0].simplify;
            var shape = data.data[0].shape;
            var format = data.data[0].format;
            var validate = data.data[0].validate;
            var productionDate = data.data[0].productionDate;
            $("#medicine_id").val(id);
            $("#medicine_medicineMenuNumber").val(medicineMenuNumber);
            $("#medicine_type").val(type);
            $("#medicine_simplify").val(simplify);
            $("#medicine_shape").val(shape);
            $("#medicine_validate").val(validate);
            $("#medicine_format").val(format);
            $("#medicine_productionDate").val(productionDate);
        }
    });
}
function updateMedicine() {
    var id =  $("#medicine_id").val();
    var medicineMenuNumber =  $("#medicine_medicineMenuNumber").val();
    var type = $("#medicine_type").val();
    var simplify =   $("#medicine_simplify").val();
    var shape = $("#medicine_shape").val();
    var format = $("#medicine_format").val();
    var productionDate = $("#medicine_productionDate").val();
    var validate = $("#medicine_validate").val();

    $.ajax({
        url: "/medicine/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,medicineMenuNumber:medicineMenuNumber,type:type,simplify:simplify,shape:shape,format:format,validate:validate,productionDate:productionDate},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};