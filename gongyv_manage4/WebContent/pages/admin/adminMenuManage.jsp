<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 菜单管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/portal/js/common/common.js"
		charset="UTF-8"></script>
    <script type="text/javascript">
    	//更改 "所属节点" 的样式
    	function formateNote(val,rec){
    	
    		if(val !=null && ""!= val){
    			var rows = $("#table").datagrid("getRows");
    			for (var i=0; i<rows.length; i++){
    				if(val == rows[i].id){
    					return"<span style='color: red;'>"+rows[i].name+"</span>";
    				}
    			}
    		}else{
    			return "<span style='color: green;'>根目录</span>";
    		}
    	}
    	
    	//打开增加界面
        function showAddDialog() {
            $('#dialog').dialog("open");
            $('#menuForm').form("clear");
        }

        //保存数据
        function save(){
        	var url="addAndUpdate.action";
            $('#menuForm').form('submit', {
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
        };
        
        //修改菜单
        function showEditDialog(){
            var row = $('#table').datagrid('getSelected');
            if (row) {
             	if(row.pid ==null){
             		row.pid=-1;
             	}
                $('#dialog').dialog('open').dialog('setTitle', 'editMenu');
                $('#menuForm').form('load', row);
            }else{
                $.messager.show({
                    title: 'Error',
                    msg: '请选择需要编辑的数据'
                });
            }
        };
        
        //删除菜单
        function deleteMenu() {
            var row = $('#table').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认', '确定删除此条目?', function (r) {
                    if (r) {
                        $.post('delAdminMenu.action', {id: row.id}, function (result) {
                            if (result == true) {
                                $.messager.show({	// show error message
                                    title: 'success',
                                    msg: '删除成功'
                                });
                                $('#table').datagrid('reload');
                            } else {
                                $.messager.show({	// show error message
                                    title: 'error',
                                    msg: '有子菜单，不能删除'
                                });
                                $('#table').datagrid('reload');
                            }
                        }, 'json');
                    }
                });
            } else {
                $.messager.show({	// show error message
                    title: 'Error',
                    msg: '请选择要删除的数据'
                });
            }
        }
        
        //查询指定的菜单名;
        function searchorder(){
            $('#table').datagrid('reload',{
            	name:$('#name').val()
            });
        }
        
        //分页功能
        $(function() {  
	        $('#table').datagrid({  
	            title : '菜单管理',  
	            iconCls : 'icon-ok',  
	            pageSize : 5,//默认选择的分页是每页5行数据  
	            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
	            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	            striped : true,//设置为true将交替显示行背景。  
	            collapsible : true,//显示可折叠按钮  
	            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	            url:'getAdminMenuList.action',//url调用Action方法  
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
</head>
<body>

<div id="tb" style="padding:5px;height:auto">
    <div>
    	  菜单名称:<input id="name" type="text" width="100" style="width:100px" />
        <a onclick="searchorder();" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddDialog();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showEditDialog();">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteMenu();">删除</a>
    </div>
</div>

<table id="table" class="easyui-datagrid"
    url="getAdminMenuList.action"
    toolbar="#tb"
    singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
    idField="id" pagination="true" style="width:auto;">
    <thead>
    <tr>
        <th field="ck" checkbox="true">选择</th>
        <th field="id" align="center">id</th>
        <th field="name" align="center">菜单名称</th>
        <th field="url" align="center">URL地址</th>
        <th field="pid" align="center" formatter="formateNote" >所属节点</th>
        <th field="remark" align="center">详细说明</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dialog').dialog('close');$('#table').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dialog" title="菜单管理" class="easyui-dialog" style="width:550px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="menuForm" method="post">
    	<table>
            <P>菜单名称:<input id="name" name="name" type="text"  class="easyui-validatebox" data-options="required:true,validType:'noNull'"/>
            </P>
				<input id="id" name="id" type="hidden" />
            <P>URL  地址:<input id="url" name="url"  class="easyui-validatebox" type="text"/> </P>
         	<P>所属节点:<input id="pid" name="pid"  class="easyui-combobox" 
                                   data-options="required:true,validType:'noNull', valueField:'id',textField:'name',url:'getRootMenu.action'"/></P>
       		<P>详细说明:<input id="remark" name="remark" type="text"/></P>
         </table>
    </form>
</div>
</body>
</html>