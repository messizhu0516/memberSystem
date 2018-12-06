<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#dictAddForm').form({
            url : '${path }/dict/save',
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
                showMsg(result.msg);
                if (result.code == "000") {
                    parent.$.modalDialog.handler.dialog('close');
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow:hidden;padding:3px;" >
        <form id="dictAddForm" method="post">
            <table class="grid">
	            <tr>
					<td><label class="col-sm-3 control-label">标志key：</label></td>
					<td><input class="easyui-textbox" data-options="required:true,prompt:'标志key'" name="markKey" id="markKey"/></td>
				</tr>
				<tr>
					<td><label class="col-sm-3 control-label">字典名称：</label></td>
					<td><input class="easyui-textbox" data-options="required:true,prompt:'字典名称'" name="name" id="name" placeholder="字典名称" /></td>
				</tr>
				<tr>
					<td><label class="col-sm-3 control-label">字典值：</label></td>
					<td><input class="easyui-textbox" data-options="required:true,prompt:'字典值'" name="value" id="value" /></td>
				</tr>
				<tr>
					<td><label class="col-sm-3 control-label">排序：</label></td>
					<td><input class="easyui-numberspinner" data-options="required:true,prompt:'排序',validType:'length[1,3]'" name="sort" id="sort" /></td>
				</tr>
            </table>
        </form>
    </div>
</div>