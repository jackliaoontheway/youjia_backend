<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 租户管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <script src="${pageContext.request.contextPath}/portal/js/common/md5.js"></script>
    <script src="${pageContext.request.contextPath}/js/login/common.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript">
	
     // 状态模块
     function formatst(value) {
         if (value == 1) {
             return "注册未审核";
         } else if (value == 2) {
             return "审核通过";
         } else if (value == 3) {
         	return "驳回";
         } else if (value ==0){
        	 return 全部;
         }
         return "";
     }
   //背景
 	$(function(){
 		$("#dg").datagrid({
    	 		rowStyler: function(index,row){
    	 			//index: 下标,row对象
    	 			var state = row.state;//添加条件,row表示当前行,可以获取 field=“state” 属性值;
    	 			if(state == 1){
    	 				return 'background-color:#72F047;';
    	 			}else if(state == 2){
    	 				return "background-color:#F04747;";
    	 			}else if(state == 3){
    	 				return "background-color:#ADBA0B;";
    	 			}
    	 			/*
    	 				绿色 : #72F047;
    	 				黄色 : #ADBA0B;
    	 				红色 : #F04747;
    	 			*/
    	 		}
    	    });
 	});

  // 状态模块
     function formatst1(value) {
         if (value == 1) {
             return "未租";
         } else if (value == 2) {
             return "已租";
         } else if (value == 0){
        	 return "全部";
         }
         return "";
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
	
	//当编辑用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editRenter(){
	
         
		var row = $('#dg').datagrid('getSelected');
		if(row){
		
			$('#dlg').dialog('open').dialog('setTitle','编辑租户');
			
			$('#fm').form('load',row);
			
			//'url' 存储着当保存用户数据时表单回传的 URL 地址。
			url = 'updateRenter.action?id='+row.id;
		} else {
            $.messager.show({	// show error message
                title: 'Error',
                msg: '请选择要编辑的数据'
            });
        }
	}
	
	
	//保存租户数据
	function saveRenter(){
		
		$('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
				
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (result.message != "修改成功！！！"){
	                $.messager.show({
	                    title: 'Error(错误)',
	                    msg: result.message
	                });
	            } else {
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
	
	//步骤 6: 删除一个租户
	function deleteRenter(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
		
	        $.messager.confirm('确认','确定删除此条目?',function(r){
	            if (r){
	                $.post('deleteRenter.action?renter=delete',{id:row.id},function(result){
	                    if (result.success){
	                        $('#dg').datagrid('reload');    // reload the user date(重新加载用户数据)
	                    } else {
	                        $('#dg').datagrid('reload');
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
		   // 下拉框选择控件，下拉框的内容是动态查询数据库信息 
		   $('#pid').combobox({  
			   url:"selectPlotState.action",  
			      // editable:false, //不可编辑状态 
			       cache: false, 
			       panelHeight: '150', 
			       valueField:'id',   
			       textField:'pname',
			       onHidePanel: function(){ 
					   $("#bid").combobox("setValue",null);//清空课程 
					   var id= $('#pid').combobox("getValue");   
					   $.ajax({ 
					       type: "POST", 
					       url: 'selectBuildingState.action?id=' + id, 
					       cache: false, 
					       dataType : "json", 
				       	   success: function(data){ 
				      			$("#bid").combobox("loadData",data); 
				          	
				       	   } 
				        });    
				     } 
		 }); 
		   $('#bid').combobox({  
			     //url:'itemManage!categorytbl',  
			   //  editable:false, //不可编辑状态 
			     cache: false, 
			     panelHeight: '150',//自动高度适合 
			     valueField:'id',   
			     textField:'buildingNo', 
			       
			       onHidePanel: function(){ 
					      $("#rid").combobox("setValue",null);//清空课程 
					      var pid= $('#bid').combobox("getValue");    
					     $.ajax({ 
					      type: "POST", 
					      url: 'selectRoomstates.action?pid=' + pid, 
					      cache: false, 
					      dataType : "json", 
					      success: function(data){ 
					      $("#rid").combobox("loadData",data); 
					           } 
					        });    
					       } 
		   
			     }); 
		   
		 $('#rid').combobox({  
		     //url:'itemManage!categorytbl',  
		   //  editable:false, //不可编辑状态 
		     cache: false, 
		     panelHeight: '150',//自动高度适合 
		     valueField:'id',   
		     textField:'room_num'
		     }); 
		 });
		   
	 
	 
	 
	 
	 
	    $(function() {    
		   // 下拉框选择控件，下拉框的内容是动态查询数据库信息 
		   $('#pnum').combobox({  
			   url:"selectPlotState.action",  
			      // editable:false, //不可编辑状态 
			       cache: false, 
			       panelHeight: '150', 
			       valueField:'id',   
			       textField:'pname',
			       onHidePanel: function(){ 
					   $("#bnum").combobox("setValue",null);//清空课程 
					   var id= $('#pnum').combobox("getValue");   
					   $.ajax({ 
					       type: "POST", 
					       url: 'selectBuildingState.action?id=' + id, 
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
			   //  editable:false, //不可编辑状态 
			     cache: false, 
			     panelHeight: '150',//自动高度适合 
			     valueField:'id',   
			     textField:'buildingNo', 
			       
			       onHidePanel: function(){ 
					      $("#rnum").combobox("setValue",null);//清空课程 
					      var pid= $('#bnum').combobox("getValue");    
					     $.ajax({ 
					      type: "POST", 
					      url: 'selectRoomstate.action?pid=' + pid, 
					      cache: false, 
					      dataType : "json", 
					      success: function(data){ 
					      $("#rnum").combobox("loadData",data); 
					           } 
					        });    
					       } 
		   
			     }); 
		   
		 $('#rnum').combobox({  
		     //url:'itemManage!categorytbl',  
		   //  editable:false, //不可编辑状态 
		     cache: false, 
		     panelHeight: '150',//自动高度适合 
		     valueField:'id',   
		     textField:'room_num'
		     }); 
		 });
	
	//获取 查询区的值 - 模块;
	function searchorder(){
		$("#dg").datagrid('load', {
    		bid:$('#bnum').combobox('getValue'),
    		rid:$('#rnum').combobox('getValue'),
         	pid:$('#pnum').combobox('getValue'),
            identity_card:$('#identity').val(),
            state:$("#sid").combobox('getValue'),//获取select框;
            r_state:$("#rsid").combobox('getValue')//获取select框;
    
        }); 
      
    }
	function resetting(){
		$('#pnum').combobox("setValue",null);
		$('#bnum').combobox("setValue",null);
		$('#rnum').combobox("setValue",null);
    	$('#identity').val("");
    	$("#sid").combobox('setValue',0),//获取select框;
        $("#rsid").combobox('setValue',0)//获取select框;
	}
	 $(function() {  
	        $('#dg').datagrid({  
	            title : '租户管理',  
	            iconCls : 'icon-ok',  
	            width : 600,  
	            pageSize : 5,//默认选择的分页是每页5行数据  
	            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
	            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	            striped : true,//设置为true将交替显示行背景。  
	            collapsible : true,//显示可折叠按钮  
	            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	            url:'selectAllRenter.action',//url调用Action方法  
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
		

	 	function formatButtonZ(value,rec){
	 		
			return "<a class='editcls' onclick='showEditDialogZ("+rec.id+ ")' href='javascript:void(0)'>点击查看</a>";
		}
		
		function formatButtonF(value,rec){
			return "<a class='editcls' onclick='showEditDialogF("  +rec.id+ ")' href='javascript:void(0)'>点击查看</a>";
		}
		
		function formatButtonB(value,rec){
			
			return "<a class='editcls' onclick='showEditDialogB("  +rec.id+ ")' href='javascript:void(0)'>点击查看</a>";
			
			
		}
			
		
		function showEditDialogZ(zid) {
			
			 $.post('showPicZ.action',{id:zid},function(result){
				
	                    if (result.ide_card_pic_z){
							var xxx = $('#abc').dialog('open').dialog('setTitle','查看图片');
							
							
							var img = '<iframe scrolling="auto" frameborder="0"  src="' + result.ide_card_pic_z + '" style="width:100%;height:100%;"></iframe>';
							xxx.append(im);
							
	                    } else {
	                        $.messager.show({
	                            title: 'Error',
	                            msg: result.errorMsg
	                        });
	                    }
	                },'json');

	    }
		
		function showEditDialogF(zid) {
			 $.post('showPicF.action',{id:zid},function(result){
					
	                    if (result.ide_card_pic_f){
							var xxx = $('#abc').dialog('open').dialog('setTitle','查看图片');
							
							
							var img = '<iframe scrolling="auto" frameborder="0"  src="' + result.ide_card_pic_f + '" style="width:100%;height:100%;"></iframe>';
							xxx.append(im);
			
	                    } else {
	                        $.messager.show({
	                            title: 'Error',
	                            msg: result.errorMsg
	                        });
	                    }
	                },'json');

	    }
		
		function showEditDialogB(zid) {
			 $.post('showPicB.action',{id:zid},function(result){
					
	                    if (result.self_pic){
							var xxx = $('#abc').dialog('open').dialog('setTitle','查看图片');
							var img = '<iframe scrolling="auto" frameborder="0"  src="' + result.self_pic + '" style="width:100%;height:100%;"></iframe>';
							xxx.append(im);
	                    } else {
	                        $.messager.show({
	                            title: 'Error',
	                            msg: result.errorMsg
	                        });
	                    }
	                },'json');

	    }
    </script>
    <title>租户管理</title>
</head>
<body>
    

<div id="toolbar" style="padding:5px;height:auto">
    <div>
    	小区:
    	<input id="pnum" class="easyui-combobox" style="width:140px" editable='false'/>
     	栋数:
    	<input id="bnum" class="easyui-combobox" style="width:140px" editable='false'/>
		房间号:
    	<input id="rnum" class="easyui-combobox" style="width:140px" editable='false'/>
		状态:
    	<select id="sid" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:100px">
    		<option id="renter" value="0">全部</option>
            <option id="renter" value="1">注册未审核</option>
            <option id="renter" value="2">审核通过</option>
            <option id="norenter" value="3">驳回</option>
        </select>
      	 租房状态:
    	<select id="rsid" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:100px">
    		<option id="renter" value="0">全部</option>
            <option id="renter" value="1">未租</option>
            <option id="renter" value="2">已租</option>
           
        </select>              
               身份证:
        <input id="identity" type="text" width="100" style="width:100px" />
        
        <a onclick="searchorder()" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a onclick="resetting()"  href="#" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
    </div>
    <div style="margin-bottom:5px">
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRenter();">添加</a>--%>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRenter();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRenter();">编辑</a>
        
    </div>
</div>


<table id="dg"  title="租户管理" class="easyui-datagrid"
      url="selectAllRenter.action"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
       idField="id" pagination="true" style="width:auto;" rownumbers="true" fitColumns="true">
    <thead >
    <tr>
        <th field="user_name" align="center">用户名</th>
        <th field="password" align="center">密码</th>
        <th field="name" align="center" >真实姓名</th>
        <th field="pname" align="center" >所属小区</th>
        <th field="bname" align="center" >所属栋数</th>
        <th field="rname" align="center">所属房号</th>
        <th field="phone" align="center">联系电话</th>
        <th field="identity_card" align="center">身份证号码</th>
        <th field="ide_card_pic_z"  align="center" formatter="formatButtonZ">身份证照片(正面)</th>
        <th field="ide_card_pic_f"  align="center" formatter="formatButtonF">身份证照片(反面)</th>
        <th field="self_pic"  align="center" formatter="formatButtonB">本人照片</th>
        <th field="state" align="center" formatter="formatst">状态</th>
        <th field="r_state" align="center" formatter="formatst1">租房状态</th>    
        <th field="created_user" align="center">创建人</th>
        <th field="created_datetime" align="center" formatter="formatDate">创建时间</th>
        <th field="updated_user" width="10%" align="center">修改人</th>
        <th field="updated_datetime" width="10%" align="center" formatter="formatDate">修改时间</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" id = "saveBu" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRenter();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close');$('#dg').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="租户管理" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <div>
            <p>
            	<label style="margin-right:28px">密 码:</label>
            	<input type="password" id = "password" name="password" class="easyui-textbox"
            		data-options="required:true,validType:'minLength[6]'" data-options="required:true,validType:'noNull'"/>
            </p>
            <P>
            	<label style="margin-right:10px">真实姓名:</label>
            	<input id="name" name="name" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
             <P>
            	<label style="margin-right:10px">所属小区:</label>
            	<input id="pid"  name="pname" class="easyui-combobox" editable='false' style="width:140px" 
            	data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:10px">所属栋数:</label>
            	<input id="bid"  name="bname" class="easyui-combobox" editable='false' style="width:140px" 
            	data-options="valueField:'id',textField:'buildingNo' ,required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:10px">所属房号:</label>
            	<input id="rid"  name="rname" class="easyui-combobox" editable='false' style="width:140px" 
            	data-options="valueField:'id',textField:'room_num',required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:10px">联系电话:</label>
            	<input id="phone" name="phone" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>

            <P>
            	<label style="margin-right:30px">状 态:</label>
            	<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width: 100px">
		            <option id="daiding" value="1">注册未审核</option>
		            <option id="ok" value="2">审核通过</option>
		            <option id="bohui" value="3">驳回</option>
		        </select>
            </P>
              <P>
            	<label style="margin-right:10px">租房状 态:</label>
            	<select class="easyui-combobox" editable="false" id="r_state" name="r_state" 
    				 panelHeight="auto" style="width: 100px">
		            <option id="" value="1">未租</option>
		            <option id="ok" value="2">已租</option>
		            <option id="bohui" value="3">待租</option>
		        </select>
            </P>
        </div>
    </form>
</div>
<div id="abc" title="xxx" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" >
     
    
    
</div>
</body>
</html>