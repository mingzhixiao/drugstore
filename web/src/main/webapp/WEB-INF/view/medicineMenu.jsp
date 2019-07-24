<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>



    <!-- 引入bootstrap样式 -->
    <link href="/webjars/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet">
    <!-- 样式 -->
    <link rel="stylesheet" href="/static/table_dist/bootstrap-table.css">



    <!-- jq -->
    <script src="/webjars/jquery/2.1.4/jquery.js"></script>



    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>

    <script src="/static/table_dist/bootstrap-table.js"></script>


    <!-- 中文压缩包 -->
    <script src="/static/table_dist/locale/bootstrap-table-zh-CN.min.js"></script>
    <%--行内编辑  start--%>
    <link rel="stylesheet" href="/static/table_dist/extensions/editable/bootstrap-editable.css">

    <script src="/static/table_dist/extensions/editable/bootstrap-editable.js"></script>
    <script src="/static/table_dist/extensions/editable/bootstrap-table-editable.js"></script>
    <%--行内编辑 end--%>
    <%--导出 js  start--%>
    <script src="/static/table_dist/extensions/export/tableExport.js"></script>
    <script src="/static/table_dist/extensions/export/bootstrap-table-export.js"></script>
    <%--导出 end--%>

    <link rel="stylesheet" href="/static/public/static/css/plugins/bootstrapValidator/bootstrapValidator.min.css"/>
    <link href="/static/public/static/css/animate.min.css" rel="stylesheet">
    <script src="/static/public/static/js/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>
    <title>three</title>
</head>
<body>
<div class="tableBody">
    <div class="panel panel-default">
        <div class="panel-heading">
            查询条件
        </div>
        <div class="panel-body form-group" style="margin-bottom:0px;">
            <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">编号：</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="Name" id="search_name"/>
            </div>
            <label class="col-sm-1 control-label" style="text-align: right; margin-top:5px">药品名称：</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" name="Name" id="search_tel"/>
            </div>
            <div class="col-sm-1 col-sm-offset-4">
                <button class="btn btn-primary" id="search_btn">查询</button>
            </div>
        </div>
    </div>

    <div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
    </div>
    <table id="mytab" class="table table-hover"></table>
</div>


<div class="addBody" style="width:100%; display: none;position: absolute;top:10px">
    <div class="container">
        <form class="form-horizontal" id="addMedicineMenu">
            <fieldset>
                <legend>药品类型添加</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label">编号</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="number" type="text" placeholder="2019040501"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">药品名称</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="name" type="text" placeholder="阿莫西林"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">最大库存</label>
                    <div class="col-sm-4">
                        <input  class="form-control" name="medicineMax" type="text"  placeholder="500"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">最小库存</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="medicineMin" type="text" placeholder="50"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="container">
        <div class="col-sm-3" align="center">
            <button class="btn btn-primary" type="submit" onclick="addCustomer()">提交</button>
        </div>
        <div class="col-sm-3"  align="center">
            <button type="button" class="btn  btn-primary"  id="add_backBtn">返回</button>
        </div>
    </div>
</div>


<jsp:include page="commonJsp/myModal.jsp"></jsp:include>

<script>
    $(function () {
        //根据窗口调整表格高度
        $(window).resize(function() {
            $('#mytab').bootstrapTable('resetView', {
                height: tableHeight()
            })
        });
        queryAlarmPage();


/*        $("#btn_add").click(function() {
            $("#mytab").bootstrapTable('insertRow', {
                index: 0,
                row: {
                    id: '',
                    name: '',
                    password: '',
                    phone:'',
                    email:'',
                    set:''
                }
            });
        });*/

/*        $("#btn_add").click(function() {
            alert(JSON.stringify($("#mytab").bootstrapTable('getData')));
        });*/

        //增加按钮事件
        $('#btn_add').click(function(){
            $('.tableBody').addClass('animated slideOutLeft');
            setTimeout(function(){
                $('.tableBody').removeClass('animated slideOutLeft').css('display','none');
            },500)
            $('.addBody').css('display','block');
            $('.addBody').addClass('animated slideInRight');
        });

        //增加页面返回按钮事件
        $('#add_backBtn').click(function() {
            $('.addBody').addClass('animated slideOutLeft');
            setTimeout(function(){
                $('.addBody').removeClass('animated slideOutLeft').css('display','none');
            },500)
            $('.tableBody').css('display','block').addClass('animated slideInRight');
            $('#addForm').data('bootstrapValidator').resetForm(true);
        });


        $("#btn_delete").click(
            //批量删除
            function deleteUserList() {
                //获取所有被选中的记录
                var rows = $("#mytab").bootstrapTable('getSelections');
                if (rows.length== 0) {
                    alert("请先选择要删除的记录!");
                    return;
                }
                var ids = '';
                for (var i = 0; i < rows.length; i++) {
                    ids += rows[i]['number'] + ",";
                }
                ids = ids.substring(0, ids.length - 1);
                alert(ids);
                $.ajax({
                    url :"/medicineMenu/deleteAll",
                    type: "delete",
                    data: {
                        ids:ids
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data){
                            //alert(2);
                            $("#myModal").modal('show');
                            $("#myModalLabel").html("删除成功").css("font-family","Microsoft YaHei");
                        }
                    },
                    error:function () {
                        //alert(2);
                        $("#myModal").modal('show');
                        $("#myModalLabel").html("删除失败").css("font-family","Microsoft YaHei");
                    }
                });
            });

    });

    function addCustomer() {
        $.ajax({
            url: "/medicineMenu/add",
            type: "post",
            async: true,
            dataType: "json",
            data:$("#addMedicineMenu").serialize(),
            success: function (data) {
                console.debug(data);
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

    function tableHeight() {
        return $(window).height() - 140;
    };


    //查询按钮事件
    $('#search_btn').click(function(){
        $('#mytab').bootstrapTable('refresh', {url: '/medicineMenu/all'});
    })
    
    //分页查询
    function queryAlarmPage(){
        $('#mytab').bootstrapTable('destroy');
        $('#mytab').bootstrapTable({
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
                field: 'number',
                title: '药品种类编号',
                align:'center',
                sortable: true
            }, {
                field: 'name',
                title: '药品名称',
                align:'center',
                editable: {
                    type: 'text',
                    title: '用户名',
                    validate: function(v) {
                        if (!v) return '用户名不能为空';
                    }
                }
            }, {
                field: 'medicineMax',
                title: '最大库存',
                align:'center',
                    editable: {
                        type: 'text',
                        title: '密码',
                        validate: function(v) {
                            if (!v) return '密码不能为空';
                        }
                    }
            }, {
                field: 'medicineMin',
                title: '最小库存',
                align:'center',
                editable: {
                    type: 'text',
                    title: '最小库存',
                    validate: function(v) {
                        if (!v) return '最小库存不能为空';
                    }
                }
            }
            ],

            data_local: "zh-US",//表格汉化
            url: '/medicineMenu/all',
            idField: "number",//指定主键列
            toolbar:'#toolbar',//指定工作栏
            toolbarAlign:'left',//工具栏对齐方式
            buttonsAlign:'right',//按钮对齐方式
            pagination: true, //分页
            sidePagination: 'server',
            pageNumber:1,
            pageSize:10,
            pageList: [1, 5, 10, 15, 20,100000],

            paginationFirstText: "首页",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationLastText: "末页",
            contentType: "application/x-www-form-urlencoded",
            undefinedText: "空",//当数据为 undefined 时显示的字符

            showRefresh : true,//刷新按钮
            showToggle:true,//卡片式
            striped: true, //是否显示行间隔色
            rowStyle:rowStyle,//每一行的颜色
            showColumns:true,
            search: true, //显示搜索框

            showExport: true,
            buttonsAlign:"right",  //按钮位置
            exportTypes:['excel'],  //导出文件类型

            queryParamsType:'limit',

            queryParams: function (params) {
                return {
                    limit: params.limit,
                    page:(params.offset+ params.limit) / params.limit > 1?(params.offset+params.limit) / params.limit : 1 ,
                    name:$("#search_name").val()
                }
            },
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    contentType: "application/json;charset=UTF-8",
                    url: "/medicineMenu/update?",
                    type:"put",
                    data:JSON.stringify(row),
                    success: function (data) {
                        if (data.update == true) {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    },
                    complete: function () {

                    }

                });
            },
        });


        $('#addMedicineMenu').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '登录名不能为空'
                        },
                        stringLength:{
                            min:5,
                            max:15,
                            message:'姓名为5-10位'
                        }
                    }
                },
                Name: {
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },
                        stringLength:{
                            min:2,
                            max:10,
                            message:'姓名为2-10位'
                        }
                    }
                },
                'RoleID[]': {
                    validators: {
                        notEmpty: {
                            message: '角色至少选择一种'
                        }
                    }
                },
                Pwd:{
                    validators:{
                        notEmpty:{
                            message:'密码不能为空'
                        },
                        stringLength:{
                            min:6,
                            max:128,
                            message:'密码为6-128位'
                        }
                    }

                },
                Tel: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        stringLength: {
                            min: 11,
                            max: 11,
                            message: '手机号必须为11位'
                        },
                        regexp: {
                            regexp: /^1(3|4|5|7|8)\d{9}$/,
                            message: '请填写正确的手机号'
                        }
                    }
                },
                Email: {
                    validators: {
                        notEmpty:{
                            message:'邮箱不能为空'
                        },
                        regexp: {
                            regexp:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ,
                            message: '无效的邮箱'
                        }
                    }
                },
                Attribute: {
                    validators: {
                        notEmpty: {
                            message: '状态不能为空'
                        }
                    }
                }
            }
        });

        //弹出框取消按钮事件
        $('.popup_de .btn_cancel').click(function(){
            $('.popup_de').removeClass('bbox');
        });
        //弹出框关闭按钮事件
        $('.popup_de .popup_close').click(function() {
            $('.popup_de').removeClass('bbox');
        });

    }
    var rowStyle = function (row, index) {
        var classes = ['success', 'info'];
        if (index % 2 === 0) {//偶数行
            return { classes: classes[0]};
        } else {//奇数行
            return {classes: classes[1]};
        }
    };

    function operateFormatter(value,row,index){
        if(value==1){
           /* return '<i class="fa fa-lock" style="color:red"></i>'*/
            return '经理'
        }else if(value==0){
           /* return '<i class="fa fa-unlock" style="color:green"></i>'*/
            return '员工'
        }else{
            return '无'
        }
    }
    function myFlush() { location.reload();};
</script>
</body>
</html>
