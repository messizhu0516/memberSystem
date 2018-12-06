<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userEditorganizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            panelHeight : 'auto',
            value : '${user.organizationId}'
        });

        $('#userEditRoleIds').combotree({
            url : '${path }/role/tree',
            parentField : 'pid',
            panelHeight : 'auto',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : ${roleIds}
        });

        $('#userEditForm').form({
            url : '${path }/user/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
        $("#userEditSex").val('${user.sex}');
        $("#userEditUserType").val('${user.userType}');
        $("#userEditStatus").val('${user.status}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userEditForm" method="post">
            <div class="light-info" style="overflow: hidden;padding: 3px;">
                <div style="color:red;">密码不修改请留空。</div>
            </div>
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td>
	                    <input name="id" type="hidden"  value="${user.id}" />
	                    <input name="loginName" class="easyui-textbox" data-options="required:true,validType:'length[4,64]',prompt:'登录名称（4~64位）'" value="${user.loginName}"/>
                    </td>
                    <td>姓名</td>
                    <td><input name="name" class="easyui-textbox" data-options="required:true,prompt:'请输入姓名'" value="${user.name}"/></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input name="password" class="easyui-textbox" data-options="prompt:'密码不修改请留空'"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="userEditSex" class="easyui-combobox" data-options="width:150,panelHeight:44">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="easyui-numberbox" value="${user.age}"/></td>
                    <td>账号类型</td>
                    <td>
                        <select name="userType" id="userEditUserType" class="easyui-combobox" data-options="width:150,panelHeight:44">
                            <option value="s">系统账号</option>
                            <option value="c" selected="selected">企业账号</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><input id="userEditorganizationId" name="organizationId" /></td>
                    <td>角色</td>
                    <td><input id="userEditRoleIds" name="roleIds" /></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox" value="${user.phone}"/>
                    </td>
                    <td>用户状态</td>
                    <td>
                        <select name="status" id="userEditStatus" class="easyui-combobox" data-options="width:150,panelHeight:44">
                             <option value="0">正常</option>
                             <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>