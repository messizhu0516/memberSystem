<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationAddPid').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            panelHeight : 'auto'
        });
        
        $('#organizationAddForm').form({
            url : '${path }/organization/add',
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
    <form id="organizationAddForm" method="post">
        <table class="grid">
            <tr>
                <td>编号</td>
                <td><input name="code" class="easyui-textbox" data-options="required:true,prompt:'请输入部门编号'" ></td>
                <td>部门名称</td>
                <td><input name="name" class="easyui-textbox" data-options="required:true,prompt:'请输入部门名称'" ></td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input name="sort" class="easyui-numberspinner" style="width:150px" required="required" data-options="editable:false" value="0"></td>
                <td>菜单图标</td>
                <td><input name="icon" class="easyui-textbox" data-options="prompt:'请填写菜单图标'" /></td>
            </tr>
            <tr>
                <td>地址</td>
                <td colspan="3"><input name="address" class="easyui-textbox" style="width:390px;"/></td>
            </tr>
            <tr>
                <td>上级部门</td>
                <td colspan="3"><select id="organizationAddPid" name="pid" style="width:200px"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#organizationAddPid').combotree('clear');" >清空</a></td>
            </tr>
        </table>
    </form>
</div>