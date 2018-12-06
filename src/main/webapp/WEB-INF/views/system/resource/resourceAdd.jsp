<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#resourceAddPid').combotree({
            url : '${path }/resource/allTree',
            parentField : 'pid',
            panelHeight : 'auto'
        });

        $('#resourceAddForm').form({
            url : '${path }/resource/add',
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
    });
</script>
<div style="padding: 3px;">
    <form id="resourceAddForm" method="post">
        <table class="grid">
            <tr>
                <td>资源名称</td>
                <td><input name="name" type="text" placeholder="请输入资源名称" class="easyui-textbox" data-options="required:true" ></td>
                <td>资源类型</td>
                <td>
                    <select name="resourceType" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">菜单</option>
                        <option value="1">按钮</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>资源路径</td>
                <td><input name="url" type="text" placeholder="请输入资源路径" class="easyui-textbox"  ></td>
                <td>打开方式</td>
                <td>
                    <select name="openMode" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option>无(用于上层菜单)</option>
                        <option value="ajax" selected="selected">ajax</option>
                        <option value="iframe">iframe</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>菜单图标</td>
                <td ><input name="icon" class="easyui-textbox" onclick='top.window.openIconDialog(this)'/></td>
                <td>排序</td>
                <td><input name="sort" value="0"  class="easyui-numberspinner" required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select name="status" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">正常</option>
                        <option value="1">停用</option>
                    </select>
                </td>
                <td>菜单状态</td>
                <td>
                    <select name="opened" class="easyui-combobox" data-options="width:150,height:29,editable:false,panelHeight:'auto'">
                        <option value="colsed">关闭</option>
                        <option value="open">打开</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>上级资源</td>
                <td colspan="3">
                    <select id="resourceAddPid" name="pid" style="width: 200px; height: 29px;"></select>
                    <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#resourceAddPid').combotree('clear');" >清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>