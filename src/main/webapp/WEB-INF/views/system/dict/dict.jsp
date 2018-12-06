<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var dictDataGrid;
    $(function() {
        dictDataGrid = $('#dictDataGrid').datagrid({
            url : '${path}/dict/dataGrid',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            frozenColumns : [ [ {
                width : '100',
                title : 'ID',
                field : 'id'
            }, {
                width : '80',
                title : '字典名称',
                field : 'name'
            }, {
                width : '80',
                title : '字典值',
                field : 'value'
            }, {
                width : '200',
                title : '字典标志',
                field : 'markKey'
            }, {
                field : 'action',
                title : '操作',
                width : 200,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="/dict/edit">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="dict-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editDictFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/dict/delete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="dict-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteDictFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.dict-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.dict-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#dictToolbar'
        });
    });

    function addDictFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path}/dict/dialog/dictAdd',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dictDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#dictAddForm');
                    f.submit();
                }
            } ]
        });
    }

    function editDictFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/dict/dialog/dictEdit?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dictDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#dictEditForm');
                    f.submit();
                }
            } ]
        });
    }

    function deleteDictFun(id) {
        parent.$.messager.confirm('询问', '您是否要删除当前数据字典？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/dict/delete', {
                    id : id
                }, function(result) {
                    progressClose();
                    showMsg(result.msg);
                    if (result.code == "000") {
                        dictDataGrid.datagrid('reload');
                    }
                }, 'JSON');
            }
        });
    }

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="dictDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="dictToolbar" style="display:none;">
    <shiro:hasPermission name="/dict/add">
        <a onclick="addDictFun();" href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>