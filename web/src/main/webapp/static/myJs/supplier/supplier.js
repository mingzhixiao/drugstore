var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/supplier/all",
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
        window.location.href = "/supplier/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/supplier/delete/"+id,
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
        url:"/supplier/getSupplierById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            var name = data.data[0].name;
            var linkman = data.data[0].linkman;
            var phone = data.data[0].phone;
            var address = data.data[0].address;
            var email = data.data[0].email;
            $("#supplier_id").val(id);
            $("#supplier_name").val(name);
            $("#supplier_linkman").val(linkman);
            $("#supplier_phone").val(phone);
            $("#supplier_address").val(address);
            $("#supplier_email").val(email);
        }
    });
}
function supplierUpdate() {
    var id =  $("#supplier_id").val();
    var name =  $("#supplier_name").val();
    var linkman = $("#supplier_linkman").val();
    var phone =   $("#supplier_phone").val();
    var address =   $("#supplier_address").val();
    var email =   $("#supplier_email").val();
    $.ajax({
        url: "/supplier/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,name:name,linkman:linkman,phone:phone,address:address,email:email},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};
