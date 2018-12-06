<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#resourceEditPid').combotree({
            url : '${path }/resource/allTree',
            parentField : 'pid',
            panelHeight : 100,
            value : '${resource.pid}'
        });
        
        $('#resourceEditForm').form({
            url : '${path }/resource/edit',
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
                    parent.layout_west_tree.tree('reload');
                }
            }
        });
        $("#resourceEditStatus").val("${resource.status}");
        $("#resourceEditType").val("${resource.resourceType}");
        $("#resourceEditOpenMode").val("${resource.openMode}");
        $("#resourceEditOpened").val("${resource.opened}");
    });
</script>
<div style="padding: 3px;">
    <form id="resourceEditForm" method="post">
        <table  class="grid">
            <tr>
                <td>资源名称</td>
                <td>
                    <input name="id" type="hidden"  value="${resource.id}" >
                    <input name="name" type="text" placeholder="请输入资源名称" value="${resource.name}" class="easyui-textbox" data-options="required:true" >
                </td>
                <td>资源类型</td>
                <td>
                    <select id="resourceEditType" name="resourceType" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">菜单</option>
                        <option value="1">按钮</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>资源路径</td>
                <td><input name="url" type="text" value="${resource.url}" placeholder="请输入资源路径" class="easyui-textbox" ></td>
                <td>打开方式</td>
                <td>
                    <select id="resourceEditOpenMode" name="openMode" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="ajax">ajax</option>
                        <option value="iframe">iframe</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>菜单图标</td>
                <td><input name="icon" value="${resource.icon}" onclick='top.window.openIconDialog(this)' class="easyui-textbox"/></td>
                <td>排序</td>
                <td><input name="sort" value="${resource.sort}" class="easyui-numberspinner" style="width:150px; height: 29px;" required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select id="resourceEditStatus" name="status" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">正常</option>
                        <option value="1">停用</option>
                    </select>
                </td>
                <td>菜单状态</td>
                <td>
                    <select id="resourceEditOpened" name="opened" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="colsed">关闭</option>
                        <option value="open">打开</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>上级资源</td>
                <td colspan="3"><select id="resourceEditPid" name="pid" style="width: 200px; height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#resourceEditPid').combotree('clear');" >清空</a></td>
            </tr>
        </table>
    </form>
</div>
