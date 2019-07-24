
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- jq -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>


    <!-- 引入bootstrap样式 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <!-- 样式 -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">

    <!-- js文件 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>

    <!-- 中文压缩包 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <title>three</title>
</head>


<body>
<!--工具-->
<div id="toolbar" class="btn-group">
    <button id="btn-add" type="button" class="btn">
        <span aria-hidden="true" class="icon icon-plus-sign"></span>新增
    </button>
    <button id="btn-del" type="button" class="btn">
        <span aria-hidden="true" class="icon icon-remove-sign"></span>删除
    </button>
</div>
<!--bootstrap-table表格-->
<table id="data-table"></table>


<script>
    var $table = $('#data-table');

    $(window).resize(function () {
        //防止表头与表格不对齐
        $table.bootstrapTable('resetView');
    });

    $(function () {
        //使用严格模式
        "use strict";
        //表格初始化
        tableInit();
        $table.bootstrapTable('hideLoading');//隐藏加载
    })

    function tableInit() {
        /**
         * 初始化Table
         */
        //先销毁表格
        $table.bootstrapTable('destroy');
        //再初始化表格
        $table.bootstrapTable({
            //请求地址,此处数据为本地加载
            url: "http://127.0.0.1:8888/emps/all",
            //请求方式
            method: "get",
            //请求内容类型
            contentType: "application/x-www-form-urlencoded",
            //数据类型
            dataType: "json",
            //table高度：如果没有设置，表格自动根据记录条数觉得表格高度
            height: '582',
            //是否显示行间隔色
            striped: true,
            //是否启用排序
            sortable: true,
            //排序方式
            sortOrder: "asc",
            //是否使用缓存
            cache: false,
            //每行的唯一标识
            uniqueId: "id",
            //指定工具栏
            toolbar: "#toolbar",
            //显示刷新按钮
            showRefresh: true,
            //切换显示样式
            showToggle: true,
            //默认显示详细视图
            cardView: false,
            //是否显示搜索
           // search: true,
            //是否显示分页
            pagination: true,
            //是否启用点击选中行
            clickToSelect: true,
            //最少要显示的列数
            //minimumCountColumns: 2,
            //显示隐藏列
            showColumns: true,
            //cell没有值时显示
            undefinedText: '-',
            //分页方式：client客户端分页，server服务端分页
            sidePagination: "client",
            //每页的记录行数
            pageSize: 20,
            //初始化加载第1页，默认第1页
            pageNumber: 1,
            //可供选择的每页的行数
            pageList: "[10, 20, 50, 80, 100]",
            paginationFirstText: "首页",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationLastText: "末页",
            //按钮样式
            buttonsClass: 'btn',
            //分页器class
            iconSize: 'pager',
            //查询条件
            queryParams: queryParams,
            //表头
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
                title: '操作',
                field: 'operate',
                align: 'center',
                events: window.operateEvents,
                formatter: genderOpt//如需操作行数据,直接添加formatter对应函数名参数分别为value, row, index
                }],
            onLoadSuccess: function (res) {//可不写
                //加载成功时
                console.log(res);
            }, onLoadError: function (statusCode) {
                return "加载失败了"
            }, formatLoadingMessage: function () {
                //正在加载
                return "拼命加载中...";
            }, formatNoMatches: function () {
                //没有匹配的结果
                return '无符合条件的记录';
            }
        })
    }

    //行数据转化demo
    function genderDel(value, row, index) {
        /**
         * 替换delete数据为文字
         * @param {string} value 选值
         * @param {object} row 行数据
         * @param {number} index 行索引
         * @return {string} 返回状态或"-"
         */
        if (value == null || value == undefined) {
            return "-";
        } else if (value == 1) {
            return "已删除";
        } else if (value == 0) {
            return "正常";
        }
    }
    //return还可以return字符串拼接
    function genderOpt() {
        /**
         * 自定义列内容
         */
        return [
            '<a class="trbtn-edit" href="javascript:void(0)" title="编辑">',
            '<i class="icon icon-pencil"></i>',
            '</a>  ',
            '<a class="trbtn-remove" href="javascript:void(0)" title="删除">',
            '<i class="icon icon-trash"></i>',
            '</a>'
        ].join('');
    }
    //操作事件建议卸载内部,防止第一次点击操作不生效
    window.operateEvents = {
        /**
         * 注册操作按钮事件
         */
        'click .trbtn-edit': function (e, value, row, index) {
            editData(row);
        },
        'click .trbtn-remove': function (e, value, row, index) {
            delData(row.custNo);
        }
    };

    function queryParams(params) {
        /**
         * 查询条件与分页数据
         * @return {object} 返回参数对象
         */
        //排序方式
        params.order = "modify_time desc";
        //第几页：指定跳转
        params.nowPage = this.pageNumber;
        //name
        params.custName = $("#search-name").val();
        //工具栏 参数
        console.log("查询条件");
        console.log(params);
        return params;
    }

    function refresh() {
        /**
         * 刷新表格数据
         */
        $table.bootstrapTable('refresh');
        //$table.bootstrapTable('refresh'.{url:""});//刷新时调用接口防止表格无限销毁重铸时出现英文
    }

    //事件部分
    $("#btn-query").on("click", function () {
        /** * 查询 */
        refresh();
    })
    $("#btn-add").on("click", function () {
        console.log("新增页面");
    });

    function editData(row) {//row为表格内一行的数据传值
        console.log("修改页面")
    }

    function delData(strData) {
        /**
         * 删除 单行or多行
         * @param {string} strData 单行选中 数据
         * @param {object} strData 多行中行 数组
         */
        //多条数据转换
        if (typeof strData == "object") {
            strData = strData.join();
        }

        //确认操作
        if(confirm('确定要删除用户编号为' + strData + '数据?')){
            /**
             * callback
             * @param {boolean} result：true>= OK, false>= Cancel
             */
            if (!result) {
                console.log("Cancel");
                return;
            }
            console.log("OK");
            console.log("删除数据");
            //组织数据-转换
            var sendData = {param:strData};
            console.log(sendData);
            $.ajax({
                url: 'user/deleteTest',
                method: 'POST',
                contentType: "application/x-www-form-urlencoded",
                data: sendData,
                //阻止深度序列化，向后台传送数组
                traditional: true,
                async : false,//这里同步，请按实际需求设置
                //成功
                success: function (msg) {
                    console.log();
                },
                //请求错误
                error: function (err) {
                    console.log(err);
                }
            })
        };
    }

    $("#btn-del").on("click", function () {
        /**
         * 多行删除
         */
        var checkDatas = $table.bootstrapTable('getSelections');//获取选中项
        console.log(checkDatas);
        if (checkDatas.length < 1) {
            alert("请先选择一条或多条数据");
        } else {
            var arr = [];
            for (var i = 0; i < checkDatas.length; i++) {
                arr.push(checkDatas[i].custNo);
            }
            console.log(arr);
            delData(arr);
        }
    });
</script>
</body>
</html>
