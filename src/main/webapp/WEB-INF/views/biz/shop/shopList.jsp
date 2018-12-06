<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var shopDataGrid;
    $(function() {
        shopDataGrid = $('#shopDataGrid').datagrid({
        url : '${path}/shop/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '60',
            title : '编号',
            field : 'id'
        }, {
            width : '200',
            title : '店铺名称',
            field : 'name'
        }, {
            width : '400',
            title : '店铺地址',
            field : 'address'
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/shop/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="shop-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="shopEditFun(\'{0}\');" >编辑</a>', row.id);
                </shiro:hasPermission>
                <shiro:hasPermission name="/shop/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="shop-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="shopDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.shop-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.shop-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#shopToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function shopAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 500,
        height : 300,
        href : '${path}/shop/dialog/shopAdd',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = shopDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#shopAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function shopEditFun(id) {
    parent.$.modalDialog({
        title : '编辑',
        width : 500,
        height : 300,
        href :  '${path}/shop/dialog/shopEdit?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = shopDataGrid;
                var f = parent.$.modalDialog.handler.find('#shopEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function shopDeleteFun(id) {
     parent.$.messager.confirm('询问', '您是否要删除当前店铺？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/shop/delete', {
                 id : id
             }, function(result) {
            	 progressClose();
                 if (result.code == "000") {
                     shopDataGrid.datagrid('reload');
                 }
                 showMsg(result.msg);
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function shopCleanFun() {
    $('#shopSearchForm input').val('');
    shopDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function shopSearchFun() {
     shopDataGrid.datagrid('load', $.serializeObject($('#shopSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="shopSearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="name" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="shopSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="shopCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="shopDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="shopToolbar" style="display: none;">
    <shiro:hasPermission name="/shop/add">
        <a onclick="shopAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>