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


    <div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>审核不通过
        </button>
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>审核通过
        </button>
    </div>
    <table id="mytab" class="table table-hover"></table>





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







    });

    function addCustomer() {
        $.ajax({
            url: "/store/add",
            type: "post",
            async: true,
            dataType: "json",
            data:$("#addStore").serialize(),
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



    //分页查询
    function queryAlarmPage(store_medicine) {

        $('#mytab').bootstrapTable('destroy');
        $('#mytab').bootstrapTable({
            data_local: "zh-US",//表格汉化
            url: '/store/all',
            idField: "id",//指定主键列
            toolbar: '#toolbar',//指定工作栏
            toolbarAlign: 'left',//工具栏对齐方式
            buttonsAlign: 'right',//按钮对齐方式
            pagination: true, //分页
            sidePagination: 'server',
            pageNumber: 1,
            pageSize: 10,
            pageList: [1, 5, 10, 15, 20, 100000],
            paginationFirstText: "首页",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationLastText: "末页",
            contentType: "application/x-www-form-urlencoded",
            undefinedText: "空",//当数据为 undefined 时显示的字符
            showRefresh: true,//刷新按钮
            showToggle: true,//卡片式
            striped: true, //是否显示行间隔色
            rowStyle: rowStyle,//每一行的颜色
            showColumns: true,
            search: true, //显示搜索框
            showExport: true,
            buttonsAlign: "right",  //按钮位置
            exportTypes: ['excel'],  //导出文件类型
            queryParamsType: 'limit',
            columns: [
                {
                    title: '全选',
                    field: 'select',
                    checkbox: true,
                    width: 25,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'medicineId',
                    title: '编号',
                    align: 'center',
                    sortable: true
                }, {
                    field: 'medicineMenuNumber',
                    title: '种类编号',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '用户名',
                        validate: function (v) {
                            if (!v) return '用户名不能为空';
                        }
                    }
                }, {
                    field: 'medicineMenuName',
                    title: '药品名称',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '药品名称',
                        validate: function (v) {
                            if (!v) return '密码不能为空';
                        }
                    }
                }, {
                    field: 'storeMax',
                    title: '最大库存',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '最大库存',
                        validate: function (v) {
                            if (!v) return '手机号不能为空';
                        }
                    }
                }, {
                    field: 'storeMin',
                    title: '最小库存',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '最小库存',
                        validate: function (v) {
                            if (!v) return '邮箱不能为空';
                        }
                    }
                },
                {
                    field: 'amount',
                    title: '库存',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '最小库存',
                        validate: function (v) {
                            if (!v) return '邮箱不能为空';
                        }
                    }
                },
                {
                    field: 'deadline',
                    title: '截止日期',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '最小库存',
                        validate: function (v) {
                            if (!v) return '邮箱不能为空';
                        }
                    }
                },
                {
                    field: 'productionDate',
                    title: '生产日期',
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: '生产日期',
                        validate: function (v) {
                            if (!v) return '邮箱不能为空';
                        }
                    }
                }
            ],
            queryParams: function (params) {
                return {
                    limit: params.limit,
                    page: (params.offset + params.limit) / params.limit > 1 ? (params.offset + params.limit) / params.limit : 1,
                    name: $("#search_name").val()
                }
            }
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

    function myFlush() { location.reload();};
</script>
</body>
</html>
