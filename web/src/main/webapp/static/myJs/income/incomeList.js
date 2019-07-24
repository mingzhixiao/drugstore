var first = 0;
$(function(){
    first++;
    $.ajax({
        url: "/income/all",
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
        window.location.href = "/income/goAdd";
    })






});


function deleteOne(id) {
    $.ajax({
        url :"/income/delete/"+id,
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
        url:"/income/getIncomeById/"+id,
        type:"get",
        dateType: "json",
        success:function (data) {
            console.debug(data);
            var medicineMenuNumber = data.data[0].medicineMenuNumber;
            var medicineMenuName = data.data[0].medicineMenuName;
            var income = data.data[0].income;
            var expense = data.data[0].expense;
            var month = data.data[0].month;
            var year = data.data[0].year;
            var totalIncome = data.data[0].totalIncome;
            $("#income_id").val(id);
            $("#income_medicineNumber").val(medicineMenuNumber);
            $("#income_medicineName").val(medicineMenuName);
            $("#income_income").val(income);
            $("#income_expense").val(expense);
            $("#income_month").val(month);
            $("#income_year").val(year);
            $("#income_totalIncome").val(totalIncome);
        }
    });
}
function updateEmployee() {
    var id =  $("#income_id").val();
    var medicineMenuNumber =  $("#income_id").val();
    var medicineMenuName = $("#income_medicineNumber").val();
    var income =   $("#income_income").val();
    var expense = $("#income_expense").val();
    var month = $("#income_month").val();
    var year = $("#income_year").val();
    var totalIncome = $("#income_totalIncome").val();
    $.ajax({
        url: "/income/update?",
        dataType: "json",
        type: "post",
        data: {_method:"PUT",id:id,medicineMenuNumber:medicineMenuNumber,medicineMenuName:medicineMenuName,income:income,expense:expense,month:month,year:year,totalIncome:totalIncome},
        success: function (data) {
            $("#myModal").modal('show');
            $("#myModalLabel").html("修改成功").css("font-family","Microsoft YaHei");
        }

    });
}

function myFlush() { location.reload();};