<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%-- 角色管理 --%>
<jsp:include page="/pages/common/admin.jsp" />
<script type="text/javascript">
	
	//将后台 角色关联的菜单进行渲染
	$(function(){
		$('#tree').tree({
			url : 'getMenuList.action',
			animate : true,
			checkbox : true,
			loadFilter : function(rows) {
				return convert(rows);
			}
		});
	});
	
	//将角色关联的菜单进行拼接
	function formatmenu(val, row) {
		var str = '';
		if(val != null && val.length > 0)
		for (var i = 0; i < val.length; i++) {
			str += val[i].name + ",";
		}
		if (str != null) {
			str = str.substring(0,str.length - 1)

		}
		return str;
	}
	
	//增加
	function showAddDialog() {
		$('#dialog').dialog("open");
		$('#menuTable').datagrid('clearSelections');
		$('#roleForm').form("clear");
		$('#tree').tree('reload'); 
	}
	
	//渲染 菜单样式
	function convert(rows) {
		function exists(rows, parentId) {
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].id == parentId)
					return true;
			}
			return false;
		}
		var nodes = [];
		// get the top level nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (!exists(rows, row.pid)) {
				nodes.push({
					id : row.id,
					text : row.name,
					url : row.url
				});
			}
		}
		var toDo = [];
		for (var i = 0; i < nodes.length; i++) {
			toDo.push(nodes[i]);
		}
		while (toDo.length) {
			var node = toDo.shift(); // the parent node
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (row.pid == node.id) {
					var child = {
						id : row.id,
						text : row.name,
						url : row.url
					};
					if (node.children) {
						node.children.push(child);
					} else {
						node.children = [child];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
	}
	
	//保存
	function save() {
		var nodes = $('#tree').tree('getChecked');
		var s = '';
		for (var i = 0; i < nodes.length; i++) {
			if (s != '')
				var parent = $('#tree').tree('getParent',nodes[i].target);
				if(parent){
					s +=','+parent.id;
				}
				s += ',';
			s += nodes[i].id;
		}
		var name= $("#name").val();
		var remark= $("#remark").val();
		
    	var url="addRole.action?menuid="+s;
        $('#roleForm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
            	result = result.replace('""','');
                if (result != '') {
                    $.messager.show({
                        title: 'Error',
                        msg: result
                    });
                } else {
                    $('#dialog').dialog('close');		// close the dialog
                    $('#table').datagrid('reload');	// reload the user data
                }
            }
        });
	}
	
	//修改
	function showEditDialog(){
	       var row = $('#table').datagrid('getSelected');
           if (row) {
               $('#dialog').dialog('open').dialog('setTitle', 'editMenu');
               $('#roleForm').form('load', row);
               $(row.menus).each(function(i, obj){
            	   var n = $("#tree").tree('find',obj.id);
            	   if(n){
            		   $("#tree").tree('check',n.target);
            	   }
               });
           }else{
               $.messager.show({
                   title: 'Error',
                   msg: '请选择需要编辑的数据'
               });
           }
	}
	
	
	
    //分页功能
    $(function() {  
        $('#table').datagrid({  
            title : '角色管理',  
            iconCls : 'icon-ok',  
            width : 600,  
            pageSize : 5,//默认选择的分页是每页5行数据  
            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
            striped : true,//设置为true将交替显示行背景。  
            collapsible : true,//显示可折叠按钮  
            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
            url:'roleList.action',//url调用Action方法  
            loadMsg : '数据装载中......',  
            singleSelect:true,//为true时只能选择单行  
            fitColumns:true,//允许表格自动缩放，以适应父容器  
            //sortName : 'xh',//当数据表格初始化时以哪一列来排序  
            //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
            remoteSort : false,  
            pagination : true,//分页  
            rownumbers : true,//行数
        });   
   });
	
</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" />
</head>
<body>
	<div id="tb" style="padding: 5px; height: auto">

		<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddDialog();">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showEditDialog();">编辑</a>
			<%--<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRole();">删除</a>--%>
		</div>
	</div>

	<table id="table" class="easyui-datagrid" url="roleList.action"
		toolbar="#tb" singleSelect="true"
		data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
		idField="id" pagination="true" style="width: auto;">
		<thead>
			<tr>
				<th field="ck" checkbox="true">选择</th>
				<th field="id" align="center">id</th>
				<th field="name" align="center">名称</th>
				<th field="menus" align="center" formatter="formatmenu">菜单名称</th>
				<th field="remark" align="center">描述</th>
			</tr>
		</thead>
	</table>
	<%--对话框按钮--%>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save();">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dialog').dialog('close');$('#table');">取消</a>
	</div>

	<%--编辑对话框--%>
	<div id="dialog" title="用户管理" class="easyui-dialog"
		style="width: 550px; height: 420px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="roleForm" method="post">
			<div>
				<input id="id" name="id" type="hidden" />
				<P>
					角色名称:<input id="name" name="name" type="text" class="easyui-validatebox" data-options="required:true,validType:'noNull'"/>
				</P>
				<P>
					角色描述:<input id="remark" name="remark" type="text" />
				</P>
			</div>
			<table>
				<ul id="tree"></ul>
			</table>
		</form>
	</div>

</body>
</html>
