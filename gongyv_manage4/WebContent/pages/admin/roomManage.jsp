<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 房间管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/portal/js/common/common.js"
		charset="UTF-8"></script>
    <script type="text/javascript">
    
    
 	// 房间类型模块 --- 待改
    function formatType(value) {
       if(value == 0){
    	   return "单间";
       }else if(value == 1){
    	   return "一房一厅";
       }else if(value == 2){
    	   return "二房一厅";
       }else if(value == 3){
    	   return "三房一厅";
       }else if(value == 4){
    	   return "三房两厅";
       }else if(value == 5){
    	   return "四房一厅";
       }else if(value == 6){
    	   return "四房二厅";
       }
        return "";
    }
 	
 	
 	// 状态模块
    function formatState(value,row) {
    	 if(value == 1){
    		 return "未租";
    	 }else if(value == 2){
    		 return "已租";
    	 }
    }
 	
 	
 	function formatCashState(value,row){
 		if(value == 1){
 			return "未处理";
 		}else if(value == 2){
 			return "已处理";
 		}
 	}
 	
  	//实现创建和编辑房间
	function newRoom(){
		$('#dlg').dialog('open').dialog('setTitle','创建房间');
		
		$('#fm').form('clear');
		
		url = 'addRoom.action';
	}
	//当编辑房间时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editRoom(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#dlg').dialog('open').dialog('setTitle','编辑房间');
			
			$('#fm').form('load',row);
			
			//'url' 存储着当保存房间数据时表单回传的 URL 地址。
			url = 'update.action?id='+row.id;
			
		} else {
            $.messager.show({	// show error message
                title: 'Error',
                msg: '请选择要编辑的数据'
            });
        }
	}
	
	//保存房间数据
	function saveRoom(){
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
					$('#dg').datagrid('reload'); //reload the user data(重新加载房间数据)
	            } 
	        }
	    });
		
		/*
			提交表单之前，'onSubmit' 函数将被调用，该函数用来验证表单字段值。
			当表单字段值提交成功，关闭对话框并重新加载 datagrid 数据。
		*/
	}
	
	//步骤 6: 删除一个房间
	function deleteRoom(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('确认','确定删除此条目?',function(r){
	            if (r){
	                $.post('del_room.action',{id:row.id},function(result){
	                    if (result == true){
							$.messager.show({    //show error message(显示错误消息)
	                            title: 'success',
	                            msg: "删除成功"
	                        });
	                        $('#dg').datagrid('reload');    // reload the user date(重新加载房间数据)
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
		
	
	//获取 查询区的值 - 模块;
	function searchorder(){
		
		//$("#dg") 获取 <table id="dg">, datagrid('load(一个固定参数)')
        $('#dg').datagrid('load', {
        	//bid key : value; 键值对形式; 发送请求会封装到 request 中;
        	bid:$('#bnum').combobox('getValue'),
           room_num:$('#rnum').combobox('getValue'),
         	room_type:$("#rtype").combobox('getValue'),//获得查询房间类型input框中的值
            state:$("#rst").combobox('getValue')//获得状态中的值//获取select框;
        });
    }
	
	//背景
	$(function(){
		$("#dg").datagrid({
   	 		rowStyler: function(index,row){
   	 			//index: 下标,row对象
   	 			var state = row.state;//添加条件,row表示当前行,可以获取 field=“state” 属性值;
   	 			if(state == 1){
   	 				return 'background-color:#72F047;';
   	 			}else if(state = 2){
   	 				return "background-color:#F04747;";
   	 			}
   	 			/*
   	 				绿色 : #72F047;
   	 				黄色 : #ADBA0B;
   	 				红色 : #F04747;
   	 			*/
   	 		}
   	    });
	});
	
	
	//查询框
	$(function() {    
		   // 下拉框选择控件，下拉框的内容是动态查询数据库信息 
		   $('#pids').combobox({
			   url:'findIdPname.action',  //获取小区信息
		       editable:false, //不可编辑状态 
		       cache: false, 
		       panelHeight: '150', 
		       valueField:'id',   
		       textField:'pname',
		       onHidePanel: function(){
		    	   $("#bnum").combobox("setValue",'');//清空课程
		    	   var pd = $('#pids').combobox('getValue');
		    	   $.ajax({ 
		    		   type: "POST", 
		    		   url: 'findBname.action?pd='+ pd,//获取栋数信息
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
		     textField:'buildingNo',
		     onHidePanel: function(){
		    	   $("#rnum").combobox("setValue",'');//清空课程
		    	   var bid = $('#bnum').combobox('getValue');
		    	   $.ajax({ 
		    		   type: "POST", 
		    		   url: 'findByRoom_num.action?bid='+bid,
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
			   	 editable:false, //不可编辑状态 
			     cache: false, 
			     panelHeight: '150',//自动高度适合 
			     valueField:'id',   
			     textField:'room_num'
			}); 
		   
	  }); 
	
	
	//注册、修改
	 $(function() {    
		   // 下拉框选择控件，下拉框的内容是动态查询数据库信息 
		   $('#pid').combobox({  
		       url:'findIdAndPname.action',  
		       editable:false, //不可编辑状态 
		       cache: false, 
		       panelHeight: '150', 
		       valueField:'id',   
		       textField:'pname', 
		    onHidePanel: function(){ 
		      $("#bid").combobox("setValue",'');//清空课程 
		      var pid = $('#pid').combobox('getValue'); 
		     $.ajax({ 
		      type: "POST", 
		      url: 'findIdAndBname.action?id='+pid, 
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
		     editable:false, //不可编辑状态 
		     cache: false, 
		     panelHeight: '150',//自动高度适合 
		     valueField:'bid',   
		     textField:'buildingNo'
		     }); 
		}); 
	
	
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
	 
	
    </script>
</head>
<body>

<div id="toolbar" style="padding:5px;height:auto" class="datagrid-toolbar">
	<%-- 查询区 --%>
    <div>
    	小区名称:
    	<input id="pids" class="easyui-combobox" style="width:125px" 
    	data-options="valueField:'pid',textField:'pname',prompt:'请选择小区名称!'"/>
    	
    	栋号:
    	<input id="bnum" class="easyui-combobox" style="width:100px" 
    	data-options="valueField:'id',textField:'buildingNo',prompt:'请选择栋数!'"/>
    	
		房间号:
    	<input id="rnum" class="easyui-combobox" style="width:100px" 
    	data-options="valueField:'id',textField:'room_num',prompt:'请选择房号!'"/>
				
		 房屋类型:
		<!-- <input id="rtype" class="easyui-combobox" name="room_type" style="width:130px"
		data-options="valueField:'room_type',textField:'room_type',url:'findRoomType.action',prompt:'请选择房间类型!'"/> -->
		<select id="rtype" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:100px">
    		<option value="">请选择!</option>
    		<option id="renter" value="0">单间</option>
    		<option id="renter" value="1">一房一厅</option>
            <option id="renter" value="2">二房一厅</option>
            <option id="renter" value="3">三房一厅</option>
            <option id="renter" value="4">三房两厅</option>
            <option id="renter" value="5">四房一厅</option>
            <option id="renter" value="6">四房两厅</option>
        </select>
		
		状态:
    	<select id="rst" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:70px"
    	data-options="prompt:'请选择'">
            <option id="renter" value="0"></option>
            <option id="renter" value="1">未租</option>
            <option id="norenter" value="2">已租</option>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchorder()">查询</a>
    	
    </div>
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRoom();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRoom();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRoom();">编辑</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid"
       url="selectAllS.action"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
       idField="id" pagination="true" style="width:auto;">
    <thead>
    <tr>
        <th field="ck" checkbox="true" >选择</th>
       <!--  <th field="id" align="center">id</th> -->
        <th field="pname"  align="center" style="width:10%">小区名称</th>
        <th field="buildingNo"  align="center" style="width:6%">栋数</th>
        <th field="room_num"  align="center" style="width:7%">房号</th>
        <th field="money"  align="center" style="width:7%">租金</th>
        <th field="room_type"  align="center" style="width:8%" formatter="formatType">房间类型</th>
        <th field="cash_pay"  align="center" style="width:8%">押金</th>
        <th field="mgr_fee"  align="center" style="width:7%">管理费</th>
        <th field="state"  align="center" formatter="formatState" style="width:8%">状态</th>
        <th field="cash_state"  align="center" formatter="formatCashState" style="width:8%">处理状态</th>
        <th field="admin"  align="center" style="width:7%">管理员</th>
        <th field="created_user"  align="center" style="width:7%">创建人</th>
        <th field="created_datetime"  align="center" style="width:15%" formatter="formatDate">创建时间</th>
        <th field="updated_user"  align="center" style="width:7%">修改人</th>
        <th field="updated_datetime"  align="center" style="width:15%" formatter="formatDate">修改时间</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRoom();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close');$('#dg').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="房间管理" class="easyui-dialog" style="background-color:#c9eeff;width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
    	<table>
    		<p>
    			<label style="margin-right:35px">小区:</label>
    			<input id="pid" class="easyui-combobox" name="pname" editable='false'
				data-options="valueField:'id',textField:'pname',url:'',prompt:'请选择小区!'"/>
    		</p>
    		<p>
    			<label style="margin-right:35px">栋号:</label>
    			<input id="bid" class="easyui-combobox" name="buildingNo" editable='false'
				data-options="valueField:'buildingNo',textField:'buildingNo',url:'findIdAndBname.action',prompt:'请选择房号所属的栋号!'"/>
    		</p>
			<P>
				<label style="margin-right:28px">房 号:</label>
				<input id="room_num" name="room_num" type="text" class="easyui-textbox"/>
			</P>
			
			<P>
				<label style="margin-right:28px">租 金:</label>
				<input id="money" name="money" type="number" class="easyui-textbox"/>
			</P>
			
			<P>            
            	<label style="margin-right:10px">房间类型:</label>
            	<select id="room_type" name="room_type" class="easyui-combobox" editable="false"  
            	panelHeight="auto" style="width:125px" data-options="prompt:'请选择房间类型!'">
		    		<option id="renter" value="0">单间</option>
		    		<option id="renter" value="1">一房一厅</option>
		            <option id="renter" value="2">二房一厅</option>
		            <option id="renter" value="3">三房一厅</option>
		            <option id="renter" value="4">三房两厅</option>
		            <option id="renter" value="5">四房一厅</option>
		            <option id="renter" value="6">四房两厅</option>
		        </select> 
				<!-- <input id="room_type" class="easyui-combobox" name="room_type" 
				data-options="valueField:'room_type',textField:'room_type',url:'findRoomType.action',prompt:'请选择房间类型!'"/> -->
            </P>
            
            <P>
				<label style="margin-right:28px">押 金:</label>
				<input id="cash_pay" name="cash_pay" type="number" class="easyui-textbox"/>
			</P>
			
			<P>
				<label style="margin-right:21px">管理费:</label>
				<input id="mgr_fee" name="mgr_fee" type="number" class="easyui-textbox"/>
				
				
			</P>
			<P>
				<label style="margin-right:28px">状 态:</label>
    			<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width: 100px; height: 22px;" data-options="prompt:'请选择已租、未租状态!'">
    				<option id="norenter" value="1">未租</option>
		            <option id="renter" value="2">已租</option>
		        </select>
            </P>
            
            <P>
				<label style="margin-right:10px">处理状态:</label>
    			<select class="easyui-combobox" editable="false" id="cash_state" name="cash_state" 
    				 panelHeight="auto" style="width: 100px; height: 22px;" data-options="prompt:'请选择处理状态!'">
    				<option id="norenter" value="1">未处理</option>
		            <option id="renter" value="2">已处理</option>
		        </select>
            </P>
            <P>
				<label style="margin-right:21px">管理员:</label>
    			<input id="admin" name="admin" class="easyui-combobox"
    			data-options="valueField:'admin',textField:'admin',url:'findAdmin.action',prompt:'请选择管理员!'"/>
    			
            </P>
			
        </table>
    </form>
</div>
</body>
</html>