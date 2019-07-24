<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <!-- 引入bootstrap样式 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入bootstrap-table样式 -->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">

    <!-- jquery -->
    <script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!-- bootstrap-table.min.js -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <!-- 引入中文语言包 -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">

            <div>

                <div class="center-block" style="width: 240px;">
                    <input type="text" id="searchText" class="form-control pull-left glyphicon glyphicon-search"
                           style="width: 200px;" placeholder="角色名/是否可用">
                    <button id="searchBtn" class="btn btn-default glyphicon glyphicon-search"></button>
                </div>

                <div id="toolbar" class="btn-group">
                    <button type="button" class="btn btn-default glyphicon glyphicon-pencil" id="btn-add">新增</button>
                    <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="btn-edit">编辑</button>
                    <button type="button" class="btn btn-default glyphicon glyphicon-remove" id="btn-del">删除</button>
                </div>
            </div>

            <table id="test-table"></table>

        </div>
    </div>
</div>
    <script>
        test.js文件代码

        $(function() {

            /*load页面之后，加载数据*/
            initTable();

            /*点击删除*/
            $("#btn-del").click(function() {
                //逻辑
            });


            /*点击新增*/
            $("#btn-add").click(function() {
                //逻辑
            });

            /*
             * 点击编辑，弹出层
             */
            $("#btn-edit").click(function() {
                //逻辑
            });


        });



        /* 改变可用状态available*/
        function changeStatus(event) {
            //逻辑
        }

        /* 初始化表格 */
        function initTable(){
            var rows = new Array();

            $('#role-table').bootstrapTable({
                method : 'POST',  //服务器数据的请求方式 'get' or 'post'
                contentType : "application/x-www-form-urlencoded",  //服务端分页，必须改成这个
                url : '/test/test/getList',  //服务器数据的加载地址
                dataType : 'json',  //服务器返回的数据类型
                cache : false, //设置禁用 AJAX 数据缓存
                pagination : true,   //设置为 true 会在表格底部显示分页条
                paginationLoop : false,  //设置分页条禁用循环的功能。
                sidePagination : "server",  //分页方式：client客户端分页，server服务端分页（*）
                searchOnEnterKey : true,  //启用搜索框时，设置true则当按下enter键触发搜索方法，否则自动触发
                striped : true,  //设置隔行变色效果
                clickToSelect : true,  //在点击行时，自动选择rediobox 和 checkbox
                queryParams : queryParams,  //请求服务器时附加的参数
                queryParamsType : '',   //设置为 'limit' 则会发送符合 RESTFul 格式的参数.
                minimumCountColumns : 2,  //当列数小于此值时，将隐藏内容列下拉框。
                paginationPreText : '上一页',
                paginationNextText : '下一页',
                pageNumber : 1,  //初始化加载第一页，默认第一页
                pageSize : 15,  //每页的记录行数（*）
                pageList : [5,10,15],  //可供选择的每页的行数（*）
                toolbar : '#toolbar',  //指定工具按钮组的容器
                toolbarAlign : 'right',  //指定 toolbar 水平方向的位置。'left' or 'right'
                uniqueId : "id",  //每一行的唯一标识，一般为主键列
                responseHandler : responseHandler,  //加载服务器数据之前的处理程序，可以用来格式化数据。  参数：res为从服务器请求到的数据。
                /*
                 * 监听事件：行全选/多选/取消多选时需要改变样式——选中多行时不可编辑，只可删除
                 */
                onCheckAll: function (rows) {
                    $("#btn-edit").attr("disabled","disabled");
                },
                onUncheckAll: function (rows) {
                    $("#btn-edit").removeAttr("disabled");
                },
                onCheck: function (row) {
                    rows.push(row);
                    if (rows.length > 1) {
                        $("#btn-edit").attr("disabled","disabled");
                    } else {
                        $("#btn-edit").removeAttr("disabled");
                    }
                },
                onUncheck: function (row) {
                    removeByValue(rows,row);
                    if (rows.length > 1) {
                        $("#btn-edit").attr("disabled","disabled");
                    } else {
                        $("#btn-edit").removeAttr("disabled");
                    }
                },
                columns: [
                    {
                        selectItemName :'btSelectItem',
                        checkbox:true,
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'id',
                        title : '序号',
                        visible:false,
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'roleName',
                        title : '角色名',
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'available',
                        title : '是否可用',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            if (value == '0') {
                                return '是';
                            } else if (value == '1') {
                                return '否';
                            }
                        }
                    },{
                        field : 'createTime',
                        title : '创建时间',
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'updateTime',
                        title : '修改时间',
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'available',
                        title : '操作',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            if (value == '0') {
                                return '<button type="button" class="btn btn-link btn-sm" onclick="changeStatus(this);">禁用</button>';
                            } else if (value == '1') {
                                return '<button type="button" class="btn btn-link btn-sm" onclick="changeStatus(this);">激活</button>';
                            }
                        }
                    }]
            });
        }
        function refresh() {

            $("#test-table").bootstrapTable('refreshOptions',{url:'/test/test/getList'});

        }
        function queryParams(params) {
            //searchText 即搜索框输入的搜索条件
            var searchText = $("#searchText").val();
            if (searchText == '是') {
                searchText = '0';
            } else if (searchText == '否') {
                searchText = '1';
            }
            var param = {
                pageSize : this.pageSize, // 页面大小
                pageNumber : this.pageNumber, // 页码
                searchText : searchText
            }
            return param;
        }
        function responseHandler(res) {
            if (res) {
                return {
                    "rows" : res.result,
                    "total" : res.total
                };
            } else {
                return {
                    "rows" : [],
                    "total" : 0
                };
            }
        }
        function removeByValue(arr, val) {
            for(var i=0; i<arr.length; i++) {
                if(arr[i] == val) {
                    arr.splice(i, 1);
                    break;
                }
            }
        }

    </script>


</body>
</html>
