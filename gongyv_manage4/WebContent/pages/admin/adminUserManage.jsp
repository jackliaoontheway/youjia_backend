<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%-- 用户管理 --%>
<jsp:include page="/pages/common/admin.jsp" />
<script type="text/javascript">
	
	//拼接显示所属角色
	function formatrole(val, row) {
	    var str = '';
	    if(val != null && val.length > 0)
	    for (var i = 0; i < val.length; i++) {
	        str += val[i].name + ",";
	    }
	    if (str != null) {
	        str = str.substring(0, str.length - 1);
	    }
	
	    return str;
	}
	
	//增加
	function showAddDialog() {
	    $('#dialog').dialog("open");
	    $('#roleTable').datagrid('clearSelections');
	    $('#usForm').form("clear");
	}
	//修改
	function showEditDialog() {
		var rows = $('#table').datagrid('getSelected');
		if (rows){
		    $.post('getadminUserByid.action',
		            {id: rows.id},
		            function (result) {
		                if (result === "") {
		                    $.messager.show({	// show error message
		                        title: 'Error',
		                        msg: result
		                    });
		                } else {
		                    $('#dialog').dialog("open");
		                    $('#usForm').form("load", result);
		                    if(result.roles != null && result.roles.length > 0)
		                    for (var i = 0; i < result.roles.length; i++) {
		                        var rid = result.roles[i];
		                        setSelect("#roleTable", rid);
		                    }
		                }
		            }, 'json');
		} else {
		 	$.messager.show({	// show error message
               title: 'Error',
               msg: '请选择要编辑的数据'
            });
		}
	
	}
	
	//渲染 角色的样式
	function setSelect(dategrid, role) {
	    var rows = $(dategrid).datagrid("getRows");
	    for (var i = 0; i < rows.length; i++) {
	        var rowid = rows[i].id;
	        if (rowid == role.id) {
	            $(dategrid).datagrid("selectRow", i);
	        }
	    }
	}
	//删除用户
	function deleteUser() {
	    var rols = $('#table').datagrid("getSelections");
	    if (rols) {
	        var roids = '';
	        for (var i = 0; i < rols.length; i++) {
	            roids += rols[i].id + ',';
	        }
	        $.messager.confirm('确认', '确定删除选定的条目?', function (r) {
	            if (r) {
	                $.post('delAdminUser.action', {userIds: roids}, function (result) {
	                    if (result == true) {
	                        $('#table').datagrid('reload');
	                        $.messager.show({	// show error message
	                            title: 'success',
	                            msg: '删除成功'
	                        });
	                    } else {
	                        $.messager.show({	// show error message
	                            title: 'error',
	                            msg: '删除失败'
	                        });
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
	
	//保存
	function save() {
	    var rols = $('#roleTable').datagrid("getSelections");
	    var roids = '';
	    for (var i = 0; i < rols.length; i++) {
	        roids += rols[i].id + ',';
	    }
	    $('#usForm').form('submit', {
	        url: "saveUserAndRole.action",
	        queryParams: {ros: roids},
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	var result = data.replace(/[\ \"?]/g, "");
	        	if("1"==result){
	        		 $.messager.show({	// show error message
	                     title: 'Error',
	                     msg: '该账号已注册'
	                 });
	        		 $('#dialog').dialog('close');
	        	}else{
	                $('#dialog').dialog('close');		// close the dialog
	                $('#table').datagrid('reload');	// reload the user data
	        	}
	        }
	    });
	}
	
	//查询账号
	function searchorder(){
	    $("#table").datagrid('load', {
	    	loginName:$('#loginName').val(),
	    	name:$('#name').val()
	    });
	}
	
	//格式化 时间
	var months = ["1","2","3","4","5","6","7","8","9","10","11","12"];
  	function formatDate(value){
  		var date = new Date(value);
  		var year = date.getFullYear();
  		var month = date.getMonth();
  		var day = date.getDate();
  		var hours = date.getHours();
  		var minutes = date.getMinutes();
  		var seconds = date.getSeconds();
  		return  year + "/" + months[date.getMonth()] + "/" + day + " " + hours + ":" + minutes + ":" + seconds;
  	}
  	
  	
  	
  //分页功能
    $(function() {  
        $('#table').datagrid({  
            title : '用户管理',  
            iconCls : 'icon-ok',  
            width : 600,  
            pageSize : 5,//默认选择的分页是每页5行数据  
            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
            striped : true,//设置为true将交替显示行背景。  
            collapsible : true,//显示可折叠按钮  
            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
            url:'getalluser.action',//url调用Action方法  
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteUser();">删除</a>
		</div>
	</div>

	<table id="table" class="easyui-datagrid" url="getalluser.action"
		toolbar="#tb" singleSelect="true"
		data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
		idField="id" pagination="true" style="width: auto;">
		<thead>
			<tr>
				<th field="ck" checkbox="true">选择</th>
				<th field="id" align="center">id</th>
				<th field="loginName" align="center">登录账号</th>
				<th field="name" align="center">名称</th>
				<th field="roles" align="center" formatter="formatrole">所属角色</th>
				<th field="createTime" align="center" formatter="formatDate">创建时间</th>
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
		<form id="usForm" method="post">
	        <div>
	            <P>登录账号:<input id="loginName" name="loginName" type="text" data-options="required:true,validType:'noNull'"/>
	            </P>
				<input id="id" name="id" type="hidden" />
				
	            <P>姓名:<input id="name" name="name" type="text"/></P>
	        </div>
	        <table id="roleTable" class="easyui-datagrid"
	               url="getallRole.action"
	               title="角色列表"
	               singleSelect="false" data-options="selectOnCheck:true,checkOnSelect:true"
	               idField="id" style="height:auto;">
	            <thead>
	            <tr>
	                <th field="ck" checkbox="true">选择</th>
	                <th field="name" align="center">角色</th>
	            </tr>
	            </thead>
	        </table>
	
	    </form>
	</div>

</body>
</html>
