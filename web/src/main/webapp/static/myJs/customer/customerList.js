var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/customer/all",
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


    $("#addCustomer").click(function () {
        window.location.href = "/customer/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/customer/delete/"+id,
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
        url:"/customer/getById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var name = data.customer.name;
            var phone = data.customer.phone;
            var set = data.customer.set;
            var email = data.customer.email;
            $("#customer_id").val(id);
            $("#customer_name").val(name);
            $("#customer_phone").val(phone);
            $("#customer_set").val(set);
            $("#customer_email").val(email);
        }
    });
}
function updateCustomer() {
    var id =  $("#customer_id").val();
    var name =  $("#customer_name").val();
    var phone = $("#customer_phone").val();
    var set =   $("#customer_set").val();
    var email = $("#customer_email").val();
    $.ajax({
        url: "/customer/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,name:name,phone:phone,set:set,email:email},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
