<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 广告管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript">
    
  	//实现创建和编辑用户
	function newAdvertising(){
		$('#dlg').dialog('open').dialog('setTitle','创建广告');
		
		$('#fm').form('clear');
		
		url = 'addPic.action';
	}
  
  	/* 
	//当编辑用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editRenter(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#dlg').dialog('open').dialog('setTitle','编辑广告');
			
			$('#fm').form('load',row);
			
			//'url' 存储着当保存用户数据时表单回传的 URL 地址。
			url = '/easyUI/renter?id='+row.id;
		} else {
            $.messager.show({	// show error message
                title: 'Error',
                msg: '请选择要编辑的数据'
            });
        }
	}
	 */
	
	//保存广告数据
	function saveAdvertising(){
		
		$('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (result == false){
	                $.messager.show({
	                    title: 'Error(错误)',
	                    msg: result.errorMsg
	                });
	            } else {
	                $('#dlg').dialog('close'); // close the dialog(关闭对话)
					$('#dg').datagrid('reload'); //reload the user data(重新加载广告数据)
	            }
	        }
	    });
		
		/*
			提交表单之前，'onSubmit' 函数将被调用，该函数用来验证表单字段值。
			当表单字段值提交成功，关闭对话框并重新加载 datagrid 数据。
		*/
	}
	
	//步骤 6: 删除一个广告
	function deleteAdvertising(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('确认','确定删除此条目?',function(r){
	            if (r){
	                $.post('deletePic.action',{id:row.id},function(result){
						if (result == true){
							$.messager.show({    //show error message(显示错误消息)
	                            title: 'success',
	                            msg: "删除成功"
	                        });
	                        $('#dg').datagrid('reload');    // reload the user date(重新加载用户数据)
	                    } else {
	                        $.messager.show({    //show error message(显示错误消息)
	                            title: 'error',
	                            msg: "删除失败"
	                        });
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
		
	$(function() {  
        $('#dg').datagrid({  
            title : '广告管理',  
            iconCls : 'icon-ok',  
            width : 600,  
            pageSize : 5,//默认选择的分页是每页5行数据  
            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
            striped : true,//设置为true将交替显示行背景。  
            collapsible : true,//显示可折叠按钮  
            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
            url:'selectPic.action',//url调用Action方法  
            loadMsg : '数据装载中......',  
            singleSelect:true,//为true时只能选择单行  
            fitColumns:true,//允许表格自动缩放，以适应父容器  
            //sortName : 'xh',//当数据表格初始化时以哪一列来排序  
            //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
            remoteSort : false,  
             frozenColumns : [ [ {  
                field : '选择',  
                checkbox : true  
            } ] ],   
            pagination : true,//分页  
            rownumbers : true,//行数
        });   
          
    });  

    </script>
</head>
<body>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdvertising();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteAdvertising();">删除</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid"
       url="selectPic.action"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true"
       idField="itemid" pagination="true" style="width:auto;">
    <thead>
    <tr>
        <th field="ck" checkbox="true">选择</th>
        <th field="pic_num" align="center" width="10%">图片轮播数量</th>
        <th field="pic_url" align="center" width="80%">图片路径</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdvertising();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close');$('#table').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="广告管理" class="easyui-dialog" style="width:550px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post" enctype="multipart/form-data">
        <input hidden="true" id="userid" name="id" type="text"/>
        <table class="main_table">
            <tr>
                <th>主题</th>
                <td><input id="theme" name="theme" type="text"/></td>
            </tr>
            <tr>
                <th>图片轮播数量</th>
                <td><input id="pic_num" name="pic_num" type="text"/></td>
            </tr>
            <tr>
                <th>图片路径：</th>
                 <td><input class="easyui-filebox" id="file" name="file"/></td>
            </tr>
        </table>

    </form>
</div>


</body>
</html>