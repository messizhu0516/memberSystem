<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var sysLogDataGrid;
    $(function () {
    	sysLogDataGrid = $('#sysLogDataGrid').datagrid({
            url: '${path}/sysLog/dataGrid',
            striped: true,
            pagination: true,
            singleSelect: true,
            fitColumns : true,
            idField: 'id',
            sortName : 'create_time',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100],
            columns: [[{
                width: '80',
                title: '登录名',
                align: 'center',
                field: 'loginName'
            }, {
                width: '100',
                title: '登录IP地址',
                align: 'center',
                field: 'clientIp'
            }, {
                width: '800',
                title: '内容',
                field: 'optContent'
            }, {
                width: '130',
                title: '操作时间',
                align: 'center',
                field: 'createTime'
            }]]
        });
    });
    
    function searchSysLogFun() {
    	sysLogDataGrid.datagrid('load', $.serializeObject($('#searchSysLogForm')));
    }
    
    function cleanSysLogFun() {
        $('#searchSysLogForm input').val('');
        sysLogDataGrid.datagrid('load', {});
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height:35px;padding:3px;background-color:#fff">
        <form id="searchSysLogForm">
            <table>
                <tr>
                    <th>登录名：</th>
                    <td><input name="loginName" class="easyui-textbox" data-options="prompt:'请输入登录名称'"/></td>
                    <th>操作时间：</th>
                    <td>
                        <input class="Wdate" id="startDate" name="startDate" value="" placeholder="开始时间" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至
                        <input class="Wdate" id="endDate" name="endDate" value="" placeholder="结束时间" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-%d %H:%m:%s',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                        <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search'" onclick="searchSysLogFun();">查询</a>
                        <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-remove-circle'" onclick="cleanSysLogFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true">
        <table id="sysLogDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>