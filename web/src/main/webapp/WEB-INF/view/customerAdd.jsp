<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sysname}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.4/jquery.js"></script>
    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>

</head>
<body>
<div class="container">
    <form class="form-horizontal" id="addCustomerForm">
        <fieldset>
            <legend>顾客信息</legend>
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-4">
                    <input class="form-control" name="id" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-4">
                    <input class="form-control" name="name" type="text" placeholder="张三"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">手机</label>
                <div class="col-sm-4">
                    <input class="form-control" name="phone" type="text" placeholder="133xxxx343"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">权限</label>
                <div class="col-sm-4">
                    <input class="form-control" name="set" type="text" placeholder="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-4">
                    <input class="form-control" name="email" type="text" placeholder="wu@com.cn"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="container">
    <div class="col-sm-12" align="center">
        <button class="btn btn-lg btn-primary col-sm-1 col-sm-offset-3" type="submit" onclick="addCustomer()">提交</button>
    </div>
</div>
<jsp:include page="commonJsp/myModal.jsp"></jsp:include>
<script src="/static/myJs/customer/customerAdd.js"></script>
</body>
</html>
