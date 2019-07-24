<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html id="home_html">
<head>
    <meta charset="utf-8">
    <title>${sysname}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css">

    <link rel="stylesheet" href="/static/clock/style.css">


    <script src="/webjars/jquery/2.1.4/jquery.js"></script>
    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>
    <style type="text/css">
        .menu_list{width:268px;margin:0 auto;}
        .menu_head{
            height: 47px;
            line-height: 47px;
            padding-left: 38px;
            font-size: 14px;
            color: #525252;
            cursor: pointer;
            border-left: 1px solid #e1e1e1;
            border-right: 1px solid #e1e1e1;
            border-bottom: 1px solid #e1e1e1;
            border-top: 1px solid #F1F1F1;
            position: relative;
            margin: 0px;
            font-weight: bold;
            background: #f1f1f1 url(../../static/img/pro_left.png) center right no-repeat;
        }
        .menu_list .current{background:#f1f1f1 url(../../static/img/pro_down.png) center right no-repeat;}
        .menu_body{
            line-height: 38px;
            border-left: 1px solid #e1e1e1;
            backguound: #fff;
            border-right: 1px solid #e1e1e1;
        }
        .menu_body a{display:block;height:38px;line-height:38px;padding-left:38px;color:#777777;background:#fff;text-decoration:none;border-bottom:1px solid #e1e1e1;}
        .menu_body a:hover{text-decoration:none;}

        #home_html{
            height: 100%;
        }
        #home_body{
            width: 100%;
            height: 100%;
            margin: 0px;
            border: 0px;
        }#home_head{
             width: 100%;
             height: 10%;
          /*   background-color:#5CB85C;*/
         }
        #home_contain{
            width: 100%;
            height: 86%;
        }
        #home_left{
            height: 100%;
          /*  background-color:#5CB85C;*/
            margin-left :-15px;
            margin-right:-15px;
        }
        #home_right{
            height: 100%;
        }
        #home_footer{
            width: 100%;
            height: 4%;
        }
        #firstpane{
            width: 100%;
        }
        #home_index{

            border: 0px;
            margin: 0px;
        }
/*        div{
            border:1px solid red;
        }*/
    </style>
    <script>
        $(document).ready(function(){
            $("#firstpane .menu_body:eq(0)").show();
            $("#firstpane h3.menu_head").click(function(){
                $(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
                $(this).siblings().removeClass("current");
            });
            $("#secondpane .menu_body:eq(0)").show();
            $("#secondpane h3.menu_head").mouseover(function(){
                $(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
                $(this).siblings().removeClass("current");
            });

            var t = null;
            t = setTimeout(time,1000);//开始执行
            function time()
            {
                clearTimeout(t);//清除定时器
                dt = new Date();
                var h=dt.getHours();
                var m=dt.getMinutes();
                var s=dt.getSeconds();
                document.getElementById("home_date").innerHTML =  h+"时"+m+"分"+s+"秒";
                t = setTimeout(time,1000); //设定定时器，循环执行
            }
        });


    </script>
</head>
<body id="home_body" style="height: 100%;">
<div class="container" id="home_head">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">wzw</a>
            </div>
            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a><span class="glyphicon glyphicon-time" style="font-size: 21px"></span>:</a></li>
                    <li><a> <span id="home_date"></span></a></li>
                    <li><a><span class="glyphicon glyphicon-user" style="font-size: 21px"></span>:</a></li>
                    <li><a>${employee.name}</a></li>
                    <li><a href="/employee/goModify" target="main">修改密码</a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-off" style="font-size: 21px"></span></a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>


<div class="container" id="home_contain">
    <div class="col-xs-3 col-md-3" id="home_left"  scrolling="yes" style="overflow: scroll;">
        <div id="firstpane" class="menu_list">
            <c:if test="${employee.set == 1}">
                <h3 class="menu_head current">员工管理</h3>
                <div style="display:none" class="menu_body">
                    <a href="/employee/goAdd" target="main">添加员工</a>
                    <a href="/emps/four" target="main">员工信息</a>
                </div>
            </c:if>
            <h3 class="menu_head">顾客信息管理</h3>
            <div style="display:none" class="menu_body">
                <a href="/customer/goAdd" target="main">添加顾客信息</a>
                <a href="/customer/goAll" target="main">顾客信息查询</a>
            </div>
            <h3 class="menu_head">供应商管理</h3>
            <div style="display:none" class="menu_body">
                <a href="/supplier/goAll" target="main">供应商信息管理</a>
                <a href="/supplier/goAdd" target="main">添加供应商</a>
            </div>
            <h3 class="menu_head current">仓库管理</h3>
            <div style="display:none" class="menu_body">
                <a href="/medicineMenu/goAdd" target="main">新建药品种类</a>
                <a href="/medicineMenu/goAll" target="main">药品种类查询</a>
                <a href="/medicine/goAdd" target="main">新建库存药品</a>
                <a href="/medicine/goAll" target="main">库存药品查询</a>
                <a href="/store/goAll" target="main">库存查询</a>
<%--
                <a href="" target="main">库存报警</a>--%>
            </div>
            <h3 class="menu_head">采购管理</h3>
            <div style="display:none" class="menu_body">
                <a href="/purchasingMenu/goAdd" target="main">采购订单</a>
                <a href="/purchasingMenu/goAll" target="main">采购订单查询</a>
                <a href="/purchasing/goAdd" target="main">采购单</a>
                <a href="/purchasing/goAll" target="main">采购单查询</a>

            </div>
            <h3 class="menu_head">销售管理</h3>
            <div style="display:none" class="menu_body">
                <a href="/saleMenu/goAdd" target="main">添加销售订单</a>
                <a href="/saleMenu/goAll" target="main">销售订单</a>
                <a href="/sale/goAdd" target="main">添加销售单</a>
                <a href="/sale/goAll" target="main">销售单</a>
            </div>
            <c:if test="${employee.set == 1}">
<%--                <h3 class="menu_head">经营分析</h3>--%>
                <div style="display:none" class="menu_body">
                    <a href="/store/goAll" target="main">库存查询</a>
<%--                    <a href="" target="main">药品销售排行</a>
                    <a href="" target="main">销售预测</a>--%>
                </div>
                <h3 class="menu_head">财务管理</h3>
                <div style="display:none" class="menu_body">
                    <a href="/income/goAll" target="main">收入查询</a>
                 <%--   <a href="" target="main">费用管理</a>--%>
                    <%--<a href="" target="main">费用月统计表</a>--%>
                    <%--<a href="" target="main">情况查询</a>--%>
                </div>
            </c:if>
            <h3 class="menu_head">系统管理</h3>
            <div style="display:none" class="menu_body">
               <%-- <a href="" target="main">登录日志</a>--%>
                <a href="/employee/goModify" target="main">修改密码</a>
                <a href="/employee/goModifyOneself" target="main">修改个人信息</a>
                <a href="/logout" target="main">系统退出</a>
            </div>
        </div>

    </div>

    <div class="col-xs-9 col-md-9" id="home_right">
        <iframe src="/emps/clock" name="main" id="home_index" frameborder="0" width="100%" scrolling="yes" height="100%" >



        </iframe>
    </div>


</div>
<div class="container" id="home_footer">
    <div class="col-xs-3" id="footer"  style="margin-left :-15px;margin-right:-15px;font-family: '仿宋';background-color:#5CB85C;margin-left :-15px;margin-right:-15px;">

    </div>
    <div class="col-xs-9" align="center">
        © wzw 版权所有 如有疑问电话联系：13260500449
        　　
    </div>
</div>
</body>
</html>
