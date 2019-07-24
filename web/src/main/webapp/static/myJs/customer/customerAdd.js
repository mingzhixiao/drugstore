function addCustomer() {
    $.ajax({
        url: "/customer/add",
        type: "post",
        async: false,
        dataType: "json",
        data:$("#addCustomerForm").serialize(),
        success: function (data) {
            if (data){
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
};
function myFlush() { location.reload();};