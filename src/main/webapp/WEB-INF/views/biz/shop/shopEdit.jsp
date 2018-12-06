<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#shopEditForm').form({
            url : '${path}/shop/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.code == "000") {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                }
                showMsg(result.msg);
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="shopEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>商家名称</td>
                    <td><input name="id" type="hidden"  value="${shop.id}">
                    <input name="name" placeholder="请输入名称" class="easyui-textbox" data-options="required:true" value="${shop.name}"></td>
                </tr>
                <tr>
                    <td>店铺地址</td>
                    <td><input name="address" style="width:200px;height:50px" class="easyui-textbox" data-options="multiline:true" value="${shop.address}"/></td>
                </tr> 
            </table>
        </form>
    </div>
</div>