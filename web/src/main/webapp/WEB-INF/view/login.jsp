<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="/static/css/login.css" />
</head>
<body>
<div class="login_main">
    <c:if test="${not empty msg}">
        <label style="color: red">${msg}</label>
    </c:if>
    <h2>登录</h2>
    <form action="/login/login"  method="post">
        <div class="inputBox">
            <select  name="typeId">
                <option value='2'>选择类型</option>
                <option value='1'>经理</option>
                <option value='0'>员工</option>
            </select>
        </div>
        <div class="inputBox">
            <input type="text" name="username" required="true"  autocomplete="off" minlength="2" maxlength="5"/>
            <label>用户名</label>
        </div>
        <div class="inputBox">
            <input type="password" name="password" required="true" minlength="6" maxlength="20"/>
            <label>密码</label>
        </div>
        <input type="submit" value="确定" />
    </form>
</div>
<script type="text/javascript" src="/static/js/canvas-nest.js"></script>
</body>
</html>
