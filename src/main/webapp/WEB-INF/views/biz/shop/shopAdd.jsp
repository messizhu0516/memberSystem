<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#shopAddForm').form({
            url : '${path}/shop/save',
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
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                }
                showMsg(result.msg);
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="shopAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>店铺名称</td>
                    <td><input name="name" placeholder="请输入名称" class="easyui-textbox" data-options="required:true" value=""></td>
                </tr> 
                <tr>
                    <td>店铺地址</td>
                    <td><input name="address" style="width:200px;height:50px" class="easyui-textbox" data-options="multiline:true" /></td>
                </tr> 
            </table>
        </form>
    </div>
</div>