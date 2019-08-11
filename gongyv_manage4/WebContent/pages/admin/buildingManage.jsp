<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 房屋栋数管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/portal/js/common/common.js"
            charset="UTF-8"></script>
    <script type="text/javascript">

    

	function newBuilding(){
		$('#dlg').dialog('open').dialog('setTitle','创建房屋栋数');
		
		$('#fm').form('clear');
		
		url = 'saveBuil.action';
		
	}

	//当编辑房屋时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editBuilding(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#dlg').dialog('open').dialog('setTitle','编辑房屋栋数');
			
			$('#fm').form('load',row);
			
			//'url' 存储着当保存用户数据时表单回传的 URL 地址。
			url = 'updateBuil.action?id='+row.id;
		} else {
	        $.messager.show({	// show error message
	            title: 'Error',
	            msg: '请选择要编辑的数据'
	        });
	    }
	}
	
	//步骤 5：保存房屋数据
	function saveBuilding(){
		$('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (result.success){
	                $.messager.show({
	                    title: 'success',
	                    msg: result.success
	                });
	 				$('#dlg').dialog('close');
					$('#dg').datagrid('reload');
	            } else {
					$.messager.show({
	                    title: 'error',
	                    msg: result.error
	                });
	                $('#dlg').dialog('close'); // close the dialog(关闭对话)
					$('#dg').datagrid('reload'); //reload the user data(重新加载用户数据)
	            }
	        }
	    });
		
		/*
			提交表单之前，'onSubmit' 函数将被调用，该函数用来验证表单字段值。
			当表单字段值提交成功，关闭对话框并重新加载 datagrid 数据。
		*/
	}

	/*
		步骤 6: 删除一个用户
		我们使用下面的代码来移除一个用户：
	*/
	
	function deleteBuilding(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('确认','确定删除此条目?',function(r){
	            if (r){
	                $.post('delBuil.action?building=destroy',{id:row.id},function(result){
	                    if (result == true){
		 					$.messager.show({    //show error message(显示错误消息)
	                            title: 'success',
	                            msg: "删除成功！"
			                });
							$('#dlg').dialog('close');
	                        $('#dg').datagrid('reload');    // reload the user date(重新加载用户数据)
	                    } else {
	                        $.messager.show({    //show error message(显示错误消息)
	                            title: 'success',
	                            msg: "此信息不存在！"
	                        });
							 $('#dlg').dialog('close'); // close the dialog(关闭对话)
							$('#dg').datagrid('reload'); //reload the user data(重新加载用户数据)
	                    }
	                },'json');
	            }
	        });
	    	} else {
		    $.messager.show({	// show error message
		        title: 'Error',
		        msg: '请选择要删除的数据'
		    });
		}
	}
    
	//获取 查询区的值 - 模块;
	function searchorder(){
		//$("#dg") 获取 <table id="dg">, datagrid('load(一个固定参数)')
        $("#dg").datagrid('load', {
        	//bid key : value; 键值对形式; 发送请求会封装到 request 中;
        	buildingNo:$('#bnum').combobox('getText'),
        	pid:$('#pids').combobox('getValue'),
        });
        $('#dg').datagrid('reload');
    }
	
	
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
        
  	
  	 $(function() {    
		   // 下拉框选择控件，下拉框的内容是动态查询数据库信息 
		   $('#pids').combobox({  
		       url:'selectPname.action',  
		       editable:false, //不可编辑状态 
		       cache: false, 
		       panelHeight: '150', 
		       valueField:'pid',   
		       textField:'pname', 
		    onHidePanel: function(){ 
		      $("#bnum").combobox("setValue",'');//清空课程 
		      var pid = $('#pids').combobox('getValue'); 
		     $.ajax({ 
		      type: "POST", 
		      url: 'findBIdBname.action?pid='+pid, 
		      cache: false, 
		      dataType : "json", 
		      success: function(data){ 
		      $("#bnum").combobox("loadData",data); 
		           } 
		        });    
		       } 
		});   
		   $('#bnum').combobox({  
		     //url:'itemManage!categorytbl',  
		     editable:false, //不可编辑状态 
		     cache: false, 
		     panelHeight: '150',//自动高度适合 
		     valueField:'id',   
		     textField:'buildingNo'
		     }); 
		}); 
  	
    </script>
</head>
<body>

<div id="toolbar" style="padding:5px;height:auto">
    <div>
    	小区名称:
    	<input id="pids" class="easyui-combobox" style="width:125px" 
    	data-options="valueField:'id',textField:'pname',prompt:'请选择小区名称!'"/>
    
       	 栋数:
    	<input id="bnum" class="easyui-combobox" style="width:100px"
                   url="" valueField="id" textField="text">
        <a onclick="searchorder();" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBuilding();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteBuilding();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBuilding();">编辑</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid"
       url="selectBuil.action"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
       idField="id" pagination="true" style="width:auto;">
    <thead>
    <tr>
        <th field="ck" checkbox="true">选择</th>
        <th field="pname" width="10%" align="center">小区名称</th>
        <th field="buildingNo" width="15%" align="center">栋数</th>
        <th field="description" width="20%" align="center">描述</th>
        <th field="created_user" width="10%" align="center">创建人</th>
        <th field="created_datetime" width="17%" align="center" formatter="formatDate">创建时间</th>
        <th field="updated_user" width="10%" align="center">修改人</th>
        <th field="updated_datetime" width="17%" align="center" formatter="formatDate">修改时间</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBuilding();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close');$('#bg').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="房屋栋数管理" class="easyui-dialog" style="background-color:#c9eeff;width:350px;height:320px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <P>小区名称:<input id="pid" name="pid" class="easyui-combobox" editable='false'
            data-options="valueField:'pid',textField:'pname',url:'findPname.action',prompt:'请选择小区'"/></P>
            <P>栋数:<input id="buildingNo" name="buildingNo" class="easyui-textbox" type="text"/></P>
            <P>描述:<input id="description" name="description" class="easyui-textbox" type="text"/></P>
        </table>
    </form>
</div>
</body>
</html>