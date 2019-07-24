<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/2
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>


    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- jq -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>



    <!-- 样式 -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">

    <!-- js文件 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>


    <!-- 中文压缩包 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        查询条件
    </div>
    <div class="panel-body form-group" style="margin-bottom:0px;">
        <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">姓名：</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="Name" id="search_name"/>
        </div>
        <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">手机号：</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="Name" id="search_tel"/>
        </div>
        <div class="col-sm-1 col-sm-offset-4">
            <button class="btn btn-primary" id="search_btn">查询</button>
        </div>
    </div>
</div>
<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
    <button id="btn_delete" type="button" class="btn btn-default" <%--style="display: none;"--%>>
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
    </button>
    <button id="btn_add" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
    </button>
</div>
<table id="table"></table>
<script type="text/javascript">
    var datas  = [];
    var g = [];
    $.ajax({
        url: "/emps/all",
        dataTye: "json",
        type: "get",
        success: function (data) {
          /*  for(var i=0;i<2;i++){
                datas[i] = {"name":data.valueDate[i].name+"号","age":data.valueDate[i].password+"岁"};
            }*/
            function tableHeight() {
                return $(window).height() - 140;
            };

            //根据窗口调整表格高度
            $(window).resize(function() {
                $('#table').bootstrapTable('resetView', {
                    height: tableHeight()
                })
            });
            $('#table').bootstrapTable({


                sidePagination:'client',

                queryParams: function (params) {    // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                    return {
                        limit: params.limit, // 每页要显示的数据条数
                        offset: params.offset // 每页显示数据的开始行号
                        //sort: params.sort, // 要排序的字段
                        //sortOrder: params.order, // 排序规则
                        //dataId: $("#dataId").val() // 额外添加的参数
                    };
                },
             //   responseHandler: responseHandler,



                pagination: true,

                pageNumber:1,

                pageSize: 10,

                queryParamsType:'limit',

                pageList: [1, 5, 10, 15, 20],

                striped: true ,

                cache: false,

                height:tableHeight(),//高度调整



                paginationFirstText: "首页",
                paginationPreText: "上一页",
                paginationNextText: "下一页",
                paginationLastText: "末页",

                clickToSelect: true,

                //iconSize: 'pager',
                showRefresh : true,


                search:true,

                minimumCountColumns: 2,
                showColumns: true,
                //showExport:true,
                //exportTypes:['excel','xlsx'],

                //contentType: "application/x-www-form-urlencoded",
               // clickToSelect: false,

                clickToSelect: true,
                showToggle:true,
                cardView: false,

                toolbar: '#toolbar',



                columns: [
                    {
                        title:'全选',
                        field:'select',
                        checkbox:true,
                        width:25,
                        align:'center',
                        valign:'middle'
                    },
                    {
                        field:"id",
                        title:"ID",
                        align:"center",
                        valign:"middle",
                        sortable:"true"
                    },
                    {
                        field:"name",
                        title:"用户名",
                        align:"center",
                        valign:"middle"},
                    {
                        field:"password",
                        title:"密码",
                        align:"center",
                        valign:"middle"},
                    {
                        field:"email",
                        title:"邮箱",
                        align:"center",
                        valign:"middle"},
                    {
                        field:"phone",
                        title:"手机",
                        align:"center",
                        valign:"middle"},
                    {
                        field:"set",
                        title:"权限",
                        align:"center",
                        valign:"middle",
                        sortable:"true"},
                    {
                        field: 'operation',
                        title: '操作',
                        align: 'center',
                        events:operateEvents,//给按钮注册事件
                        formatter:addFunctionAlty//表格中增加按钮
                    }
                ],
                locale:'zh-CN',//中文支持,
                data:data
            });
        }
    });


    // 修改按钮、删除按钮
    function addFunctionAlty(value, row, index) {
        return [
            '<button type="button" id="btn_edit" class="btn btn-default" data-toggle="modal" data-target="#ModalInfo">修改</button>  ',
            '<button id="btn_delete" class="btn btn-warning">删除</button>'
        ].join('');
    }
    window.operateEvents = {

        // 点击修改按钮执行的方法
        'click #btn_edit': function (e, value, row, index) {
            // 写自己的方法。。。
        },
        // 点击删除按钮执行的方法
        'click #btn_delete': function (e, value, row, index) {
            // 写自己的方法。。。
        }
    };
    //没有请求,自己写50条数据
/*    for(var i=0;i<50;i++){
        datas[i]={"name":i+"号","age":i+"岁"}
    }*/
    console.log(datas);
    //datas[0]={"name":i+"号","age":i+"岁"}

</script>
</body>
</html>
