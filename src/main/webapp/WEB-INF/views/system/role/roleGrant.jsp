<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var resourceTree;
    $(function() {
        resourceTree = $('#resourceTree').tree({
            url : '${path }/resource/treeForGrant',
            parentField : 'pid',
            checkbox : true,
            onClick : function (node) {},
            onCheck:function (node) {
            	// 此功能与初始化选中角色所拥有权限时，触发的话会造成，比如某个父类节点没有权限，但是触发此事件却选中了，待日后解决
                /* var node1 = resourceTree.tree('getParent', node.target);
                if (node1) {
	                resourceTree.tree('check', node1.target);
                } */
            },
            onLoadSuccess : function(node, data) {
                progressLoad();
                $.post( '${path }/role/findResourceIdListByRoleId', {
                    id : '${id}'
                }, function(result) {
                    var ids = [];
                    if (result.code == "000" && result.obj !=undefined && result.obj !="" && result.obj !=null) {
                        ids = $.stringToList(result.obj + '');
	                    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	var jd = resourceTree.tree('find', ids[i]);
	                            if (jd) {
	                                resourceTree.tree('check', jd.target);
	                            }
	                        }
	                    }
                    }
                }, 'JSON');
                progressClose();
            },
            cascadeCheck : false
        });

        $('#roleGrantForm').form({
            url : '${path }/role/grant',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var checknodes = resourceTree.tree('getChecked');
                var ids = [];
                if (checknodes && checknodes.length > 0) {
                    for ( var i = 0; i < checknodes.length; i++) {
                        ids.push(checknodes[i].id);
                    }
                }
                $('#resourceIds').val(ids);
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.code == "000") {
                    parent.$.modalDialog.handler.dialog('close');
                }
                showMsg(result.msg);
            }
        });
    });

    function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
    function checkInverse() {
        var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
        var checknodes = resourceTree.tree('getChecked');
        if (unchecknodes && unchecknodes.length > 0) {
            for ( var i = 0; i < unchecknodes.length; i++) {
                resourceTree.tree('check', unchecknodes[i].target);
            }
        }
        if (checknodes && checknodes.length > 0) {
            for ( var i = 0; i < checknodes.length; i++) {
                resourceTree.tree('uncheck', checknodes[i].target);
            }
        }
    }
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'west'" title="系统资源" style="width:300px">
        <div class="well well-small">
            <form id="roleGrantForm" method="post">
                <input name="id" type="hidden"  value="${id}" readonly="readonly">
                <input id="resourceIds" name="resourceIds" type="hidden" />
                <ul id="resourceTree"></ul>
            </form>
        </div>
    </div>
    <div data-options="region:'center'"  style="overflow:hidden; padding: 10px;">
        <div>
            <button class="btn btn-success" onclick="checkAll();">全选</button>
            <br /> <br />
            <button class="btn btn-warning" onclick="checkInverse();">反选</button>
            <br /> <br />
            <button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
        </div>
    </div>
</div>