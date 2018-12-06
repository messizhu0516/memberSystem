<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationEditPid').combotree({
            url : '${path }/organization/tree?flag=false',
            parentField : 'pid',
            panelHeight : 'auto',
            onBeforeSelect : function (node) {
            	var hidOrgEdId = $('#hidOrgEdId').val();
            	if (hidOrgEdId == node.id) {
            		showMsg("不能选择本身，作为上级部门");
            		return false;
            	}
            },
            value : '${organization.pid}'
        });
        
        $('#organizationEditForm').form({
            url : '${path }/organization/edit',
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
                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');
                }
            }
        });
    });
</script>
<div style="padding: 3px;">
    <form id="organizationEditForm" method="post">
        <table class="grid">
            <tr>
                <td>编号</td>
                <td>
                	<input id="hidOrgEdId" name="id" type="hidden"  value="${organization.id}" />
                	<input name="code" class="easyui-textbox" data-options="required:true,prompt:'请输入部门编号'" value="${organization.code}" />
                </td>
                <td>部门名称</td>
                <td><input name="name" class="easyui-textbox" data-options="required:true,prompt:'请输入部门名称'" value="${organization.name}" /></td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input name="sort"  class="easyui-numberspinner" value="${organization.sort}" style="widtd:150px" required="required" data-options="editable:false"></td>
                <td>菜单图标</td>
                <td ><input name="icon" class="easyui-textbox" data-options="prompt:'请填写菜单图标'" value="${organization.icon}" /></td>
            </tr>
            <tr>
                <td>地址</td>
                <td colspan="3"><input name="address" class="easyui-textbox" style="width:390px;" value="${organization.address}"/></td>
            </tr>
            <tr>
                <td>上级部门</td>
                <td colspan="3"><select id="organizationEditPid" name="pid" style="width:200px;height:29px;"></select>
                <a class="easyui-linkbutton" href="javascript:;" onclick="$('#organizationEditPid').combotree('clear');" >清空</a></td>
            </tr>
        </table>
    </form>
</div>
