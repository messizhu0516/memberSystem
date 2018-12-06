<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userAddOrganizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            required : true
        });

        $('#userAddRoleIds').combotree({
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : 'auto'
        });

        $('#userAddForm').form({
            url : '${path}/user/add',
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
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="loginName" class="easyui-textbox" data-options="required:true,validType:'length[4,64]',prompt:'登录名称（4~64位）'" /></td>
                    <td>姓名</td>
                    <td><input name="name" class="easyui-textbox" data-options="required:true,prompt:'请输入姓名'" /></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input name="password" class="easyui-textbox" data-options="required:true,prompt:'请输入密码'"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:150,panelHeight:44">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="easyui-numberbox"/></td>
                    <td>账号类型</td>
                    <td>
                        <select name="userType" class="easyui-combobox" data-options="width:150,panelHeight:44">
                            <option value="s">系统账号</option>
                            <option value="c" selected="selected">企业账号</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><input id="userAddOrganizationId" name="organizationId" /></td>
                    <td>角色</td>
                    <td><input id="userAddRoleIds" name="roleIds" /></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox"/>
                    </td>
                    <td>用户状态</td>
                    <td>
                        <select name="status" class="easyui-combobox" data-options="width:150,panelHeight:44">
                             <option value="0">正常</option>
                             <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>