<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%-- 租金登记 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    
    <title>租金登记</title>
    <script type="text/javascript">
 	
    // 状态模块
    function formatState(value) {
    	 if (value == 1) {
             return "已支付";
         } else if (value == 2) {
             return "未支付";
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
  	//实现创建和编辑租金登记
	function newRent(){
		$('#dlg3').dialog('open').dialog('setTitle','创建租金登记');
		
		$('#fm3').form('clear');
		
		url = 'addRent.action?users=new';
		$('#dg').datagrid('reload');
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
	//当编辑租金登记时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editRent(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#dlg2').dialog('open').dialog('setTitle','编辑租金登记');
			$('#fm2').form('load',row);
			
			//'url' 存储着当保存租金登记数据时表单回传的 URL 地址。
			url = 'updateRent.action?id='+row.id;
		} else {
            $.messager.show({	// show error message
                title: 'Error',
                msg: '请选择要编辑的数据'
            });
        }
	}
	
	
	//保存租金登记数据
	function saveRent1(){

		$('#fm3').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	           
	            if (result.message != "添加成功"){
	                 $('#dlg3').dialog('close'); // close the dialog(关闭对话)
					 $('#dg').datagrid('reload'); //reload the user data(重新加载租金登记数据)
					 $.messager.show({
							 title: 'succeed(成功)',
							 msg: "添加成功"
						 });
	            } else {
		 			 $('#dlg3').dialog('close'); // close the dialog(关闭对话)
					 $.messager.show({
						 title: 'Error(错误)',
						 msg: "添加失败"
					 });
	            }
	        }
	    });
	}
		//保存租金登记数据
		function saveRent2(){
			
			$('#fm2').form('submit',{
		        url: url,
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		           
		            if (result.message != "修改成功"){
		                $('#dlg2').dialog('close'); // close the dialog(关闭对话)
						 $('#dg').datagrid('reload'); //reload the user data(重新加载租金登记数据)
						 $.messager.show({
								 title: 'succeed(成功)',
								 msg: "修改成功"
							 });
		            } else {
			 			 $('#dlg2').dialog('close'); // close the dialog(关闭对话)
						 $.messager.show({
							 title: 'Error(错误)',
							 msg: "result.message"
						 });
		            }
		        }
		    });
		
		/*
			提交表单之前，'onSubmit' 函数将被调用，该函数用来验证表单字段值。
			当表单字段值提交成功，关闭对话框并重新加载 datagrid 数据。
		*/
	}
	
	//步骤 6: 删除一个租金登记
	function deleteRent(){
	    var row = $('#dg').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('确认','确定删除此条目?',function(r){
	            if (r){
	                $.post('deleteRent.action?users=destroy',{id:row.id},function(result){
		                    if (result == true){
								 $.messager.show({
					                 title: 'success',
					                 msg: "删除成功"
					             });
		                        $('#dg').datagrid('reload');    // reload the user date(重新加载租金登记数据)
		                    } else {
								$.messager.show({
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
	function searchtime(){
		//$("#dg") 获取 <table id="dg">, datagrid('load(一个固定参数)')
        $("#dg").datagrid('load', {
        	//bid key : value; 键值对形式; 发送请求会封装到 request 中;
        	pid:$('#pid').combobox("getValue"),
        	bid:$('#bid').combobox("getValue"),
        	rid:$('#rid').combobox("getValue"),
        	user:$('#user').val(),
            start_time:$('#startTime').datetimebox("getValue"),
            end_time:$('#endTime').datetimebox("getValue"),
            state:$("#state").combobox('getValue'),//获取select框;
        });
    }
	function resetting(){
		$('#pid').combobox("setValue",null);
		$('#bid').combobox("setValue",null);
		$('#rid').combobox("setValue",null);
    	user:$('#user').val("");
        $('#startTime').datetimebox("setValue",null);
        $('#endTime').datetimebox("setValue",null);
        $("#state").combobox("setValue",0);//获取select框;
	}

	
	var months = ["1","2","3","4","5","6","7","8","9","10","11","12"];
	
	function formatDate(value){
		var date = new Date(value);
		
		return date.getFullYear() + "/" + months[date.getMonth()] + "/" + date.getDate();
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
					      url: 'selectRoomstate.action?pid=' + pid, 
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
		   $('#pnumx').combobox({  
			   url:"selectPlotState.action",  
			      // editable:false, //不可编辑状态 
			       cache: false, 
			       panelHeight: '150', 
			       valueField:'id',   
			       textField:'pname',
			       onHidePanel: function(){ 
					   $("#bnumx").combobox("setValue",null);//清空课程 
					   var id= $('#pnumx').combobox("getValue");   
					   $.ajax({ 
					       type: "POST", 
					       url: 'selectBuildingState.action?id=' + id, 
					       cache: false, 
					       dataType : "json", 
				       success: function(data){ 
				      		$("#bnumx").combobox("loadData",data); 
				          	
				       		} 
				        });    
				     } 
		 }); 
		   $('#bnumx').combobox({  
			     //url:'itemManage!categorytbl',  
			   // editable:false, //不可编辑状态 
			     cache: false, 
			     panelHeight: '150',//自动高度适合 
			     valueField:'id',   
			     textField:'buildingNo', 
			       
			       onHidePanel: function(){ 
					      $("#rnumx").combobox("setValue",null);//清空课程 
					      var pid= $('#bnumx').combobox("getValue");    
					     $.ajax({ 
					      type: "POST", 
					      url: 'selectRoomstatex.action?pid=' + pid, 
					      cache: false, 
					      dataType : "json", 
					      success: function(data){ 
					      $("#rnumx").combobox("loadData",data); 
					           } 
					        });    
					       } 
		   
			     }); 
		   
		 $('#rnumx').combobox({  
		     //url:'itemManage!categorytbl',  
		   //  editable:false, //不可编辑状态 
		     cache: false, 
		     panelHeight: '150',//自动高度适合 
		     valueField:'id',   
		     textField:'room_num'
		     }); 
		 });
	
	 function init_datagrid(){
			
	 $(function() { 
		 	//var arr_columns = assemble_dg_columns();
	        $('#dg').datagrid({  
	            title : '租金登记',  
	            iconCls : 'icon-ok',  
	            width : 600, 
	            pageSize : 5,//默认选择的分页是每页5行数据  
	            pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合  
	            nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
	            striped : true,//设置为true将交替显示行背景。  
	            collapsible : true,//显示可折叠按钮  
	            toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	            url:'selectAllRent.action',//url调用Action方法  
	            loadMsg : '数据装载中......',  
	            singleSelect:true,//为true时只能选择单行  
	            fitColumns:true,//允许表格自动缩放，以适应父容器  
	            //sortName : 'xh',//当数据表格初始化时以哪一列来排序  
	            //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
	           remoteSort : false,
	           // columns:[arr_columns],
	           // idField: "id",
	           // singleSelect:true,
	           // selectOnCheck:true,
	          //  checkOnSelect:true,
	            //emptyMsg:"列表为空",
	          //  onLoadSuccess:onLoadSuccess_dg,
	             frozenColumns : [ [ {  
	                field : '选择',  
	                checkbox : true  
	            } ] ],   
	            pagination : true,//分页  
	            rownumbers : true,//行数
	        });   
	          
	    });  
	 
	}
	 /*
	function assemble_dg_columns(){
		var arr =  new Array();
		arr.push({"field":"pname","align":"center","width":"7%","title":"小区"});
		arr.push({"field":"bname","align":"center","width":"7%","title":"栋数"});
		arr.push({"field":"rname","align":"center","width":"7%","title":"房间号"});
		arr.push({"field":"user","align":"center","width":"7%","title":"租户姓名"});
		arr.push({"field":"identity_card","align":"center","width":"7%","title":"身份证"});
		arr.push({"field":"last_cold_water","align":"center","width":"7%","title":"上月冷水用量"});
		arr.push({"field":"last_cold_water_fe","align":"center","width":"7%","title":"上月冷水费"});
		arr.push({"field":"cold_water","align":"center","width":"7%","title":"冷水量(吨)"});
		arr.push({"field":"cold_water_fe","align":"center","width":"7%","title":"冷水费"});
		arr.push({"field":"use_cold_water","align":"center","width":"7%","title":"实际冷水量"});
		arr.push({"field":"cold_unit_price","align":"center","width":"7%","title":"冷水单价"});
		arr.push({"field":"last_hot_water","align":"center","width":"7%","title":"上月热水用量"});
		arr.push({"field":"last_hot_water_fe","align":"center","width":"7%","title":"上月热水费"});
		arr.push({"field":"hot_water","align":"center","width":"7%","title":"热水用量(吨)"});
		arr.push({"field":"hot_water_fe","align":"center","width":"7%","title":"热水费"});
		arr.push({"field":"use_hot_water","align":"center","width":"7%","title":"实际热水量"});
		arr.push({"field":"hot_unit_price","align":"center","width":"7%","title":"热水单价"});
		arr.push({"field":"last_electric_fee","align":"center","width":"7%","title":"上月用电量(度)"});
		arr.push({"field":"last_electric_fee_fe","align":"center","width":"7%","title":"上月电费"});
		arr.push({"field":"electric_fee","align":"center","width":"7%","title":"用电量(度)"});
		arr.push({"field":"electric_fee_fe","align":"center","width":"7%","title":"用电量费"});
		arr.push({"field":"use_electric","align":"center","width":"7%","title":"实际用电量"});
		arr.push({"field":"ele_unit_price","align":"center","width":"7%","title":"电费单价"});	
		arr.push({"field":"net_fee","align":"center","width":"7%","title":"网费"});
		arr.push({"field":"mgr_fee","align":"center","width":"7%","title":"管理费"});
		arr.push({"field":"state","align":"center","width":"7%","formatter":"formatState","title":"状态"});
		arr.push({"field":"rent_rental","align":"center","width":"7%","title":"本月房租总额"});
		arr.push({"field":"sum","align":"center","width":"7%","title":"盈利额"});
		
		arr.push({"field":"created_user","align":"center","width":"7%","title":"创建人"});
		arr.push({"field":"created_datetime","align":"center","width":"7%","formatter":"formatDate","title":"创建时间"});
		arr.push({"field":"updated_user","align":"center","width":"7%","title":"修改人"});
		arr.push({"field":"updated_datetime","align":"center","formatter":"formatDate","width":"7%","title":"修改时间"});
		return arr;
	}
	function onLoadSuccess_dg(data){
		autoMergeCells("dg",['sum'],"")
	}
	function autoMergeCells(table_id,field_arr,judge){
		var rows = $("#"+table_id).datagrid("getRows");
		if(NULL(field_arr) || NULL(rows)){
			return;
		}
		for(var i=1;i<rows.length;i++){
			for(var k=0;k<field_arr.length;k++){
				var field = field_arr[k]
				if(NOTNULL(judge)){
					if(rows[i][judge] != rows[i-1][judge]){
						break;
					}
				}
				var rowspan = 2;
				for(var j=2;i-j>=0;j++){
					if(rows[i][field] != rows[i-j][field]){
						break;
					}else{
						if(NOTNULL(judge)){
							if(rows[i][judge] != rows[i-j][judge]){
								break;
							}
						}
						rowspan=j+1;
					}
				}
				$("#"+table_id).datagrid('mergeCells0',{
					index: i-rowspan+1,
					field:field,
					rowspan:rowspan
				})
			}
		}
	}
	function NOTNULL(obj){
		if(typeof(obj) == "undefined" ||obj === ""|| obj == null || obj =="null"){
			return false;
		}
		return true;
	}
	function NULL(obj){
		if(typeof(obj) == "undefined" ||obj === ""|| obj == null || obj =="null"){
			return true;
		}
		return false;
	}
	*/
    </script>
</head>
<body>


<div id="toolbar" style="padding:5px;height:auto">
    <div>
  		小区:
    	<input id="pid" class="easyui-combobox" style="width:100px" editable='false'/>
     	栋数:
    	<input id="bid" class="easyui-combobox" style="width:100px" editable='false'/>
		房间号:
    	<input id="rid" class="easyui-combobox" style="width:100px" editable='false'/>
    	开始时间: <input id="startTime"  editable='false' class="easyui-datetimebox" style="width:100px"/>
    	结束时间: <input id="endTime"  editable='false' class="easyui-datetimebox" style="width:100px"/>
    	用户名:
    	<input id="user" type="text" style="width:100px"/>
    		状态:
    	<select id="state" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:100px">
    		<option id="renter" value="0">全部</option>
            <option id="renter" value="1">已支付</option>
            <option id="renter" value="2">未支付</option>
        </select>
		<a onclick="searchtime()"  href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a onclick="resetting()"  href="#" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
	
    </div>

    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRent();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRent();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRent();">编辑</a>
    </div>
</div>


<table id="dg" class="easyui-datagrid"
       url="selectAllRent.action"
       toolbar="#toolbar"
       singleSelect="false" data-options="fit:true"
       idField="id" pagination="true" style="width:auto;">
    <thead>
	    <tr>
	    	<th field="ck" checkbox="true" >选择</th>
	    	<th field="pname" align="center"width="7%" >小区</th>
	        <th field="bname" align="center" width="7%">栋数</th>
	        <th field="rname" align="center"width="7%" >房间号</th>
	        <th field="user" align="center"width="7%" >租户姓名</th>
	        <th field="identity_card" align="center"width="7%" >身份证</th>
	        <th field="last_cold_water" align="center"width="7%" >上月冷水用量</th>
	        <th field="last_cold_water_fe" align="center"width="7%" >上月冷水费</th>
	        <th field="cold_water" align="center"width="7%" >冷水量(吨)</th>
	        <th field="cold_water_fe" align="center"width="7%" >冷水费</th>
	        <th field="use_cold_water" align="center"width="7%" >实际冷水量</th>
	        <th field="cold_unit_price" align="center" width="7%">冷水单价</th>
	        <th field="last_hot_water" align="center"width="7%" >上月热水用量</th>
	        <th field="last_hot_water_fe" align="center"width="7%" >上月热水费</th>
	        <th field="hot_water" align="center"width="7%" >热水用量(吨)</th>
	        <th field="hot_water_fe" align="center"width="7%" >热水费</th>
	        <th field="use_hot_water" align="center"width="7%" >实际热水量</th>
	        <th field="hot_unit_price" align="center" width="7%">热水单价</th>
	        <th field="last_electric_fee" align="center" width="7%">上月用电量(度)</th>
	        <th field="last_electric_fee_fe" align="center" width="7%">上月电费</th>
	        <th field="electric_fee" align="center" width="7%">用电量(度)</th>
	        <th field="electric_fee_fe" align="center" width="7%">用电量费</th>
	    	<th field="use_electric" align="center"width="7%" >实际用电量</th>
	        <th field="ele_unit_price" align="center"width="7%" >电费单价</th>
	        <th field="net_fee" align="center" width="7%">网费</th>
	        <th field="mgr_fee" align="center" width="7%">管理费</th>
	        <th field="state" align="center"width="7%" formatter="formatState">状态</th>
	        <th field="rent_rental" align="center"width="7%" >本月房租总额</th>
	        <th field="sum" align="center"width="7%" >盈利额</th>
	        <th field="created_user" align="center">创建人</th>
       	 	<th field="created_datetime" align="center" formatter="formatDate">创建时间</th>
       		<th field="updated_user" align="center">修改人</th>
      		<th field="updated_datetime" align="center" formatter="formatDate">修改时间</th>
		</tr>
	</thead>
	
</table>


 <%--对话框按钮--%>
<!-- <div id="buttons">
    <a href="#" id = "saveBu1" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRent1();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg1').dialog('close');$('#table').datagrid('reload');">取消</a>
</div> -->
 
<%--编辑对话框--%>
<!-- <div id="dlg1" title="租金登记" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#buttons">
    <form id="fm1" method="post">
        <div>
        	 <P>
            	<label style="margin-right:30px">小区:</label>
            	<input  id="pnumx" name="pid" class="easyui-combobox"/>
            </P>
            <P>
            	<label style="margin-right:30px">栋 数:</label>
            	<input  id="bnumx" name="bid" class="easyui-combobox"/>
            </P>
            <P>
            	<label style="margin-right:24px">房间号:</label>
            	<input id="rnumx" name="room_num" class="easyui-combobox"/>
            </P>
            <P>
            	<label>冷水量(吨):</label>
            	<input id="cold_water" name="cold_water" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label>热水量(吨):</label>
            	<input id="hot_water" name="hot_water" class="easyui-textbox"
            		data-options="required:true,validType:'noNull'"/></P>
            <P>
            	<label style="margin-right:11px">电费(度):</label>
            	<input id="electric_fee" name="electric_fee" 
            		class="easyui-textbox"/>
            </P>
            <P>
            	<label style="margin-right:30px">网 费:</label>
            	<input id="net_fee" name="net_fee" 
            		class="easyui-textbox"/>
            </P>
            <P>
            	<label style="margin-right:30px">状 态:</label>
            	<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width: 153px; height: 22px;">
    				 <option id="norenter" value="1">已支付</option>
    				 <option id="renter" value="2">未支付</option>
		           
		        </select>
            </P>
            <input id="mgr_fee" name="mgr_fee" class="easyui-textbox" type="hidden"/>

        </div>
    </form>
</div> -->


<%--对话框按钮--%>
<div id="dlg-buttons2">
    <a href="#" id = "saveBu2" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRent2();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg2').dialog('close');$('#table').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg2" title="租金登记" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons2"> 
    <form id="fm2" method="post">
        <div>
            <P>
            	<label>冷水量(吨):</label>
            	<input id="cold_water" name="cold_water" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label>热水量(吨):</label>
            	<input id="hot_water" name="hot_water" class="easyui-textbox"
            		data-options="required:true,validType:'noNull'" type="number"/></P>
            <P>
            	<label style="margin-right:11px">电费(度):</label>
            	<input id="electric_fee" name="electric_fee" 
            		class="easyui-textbox" data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label style="margin-right:30px">网 费:</label>
            	<input id="net_fee" name="net_fee" 
            		class="easyui-textbox" data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label style="margin-right:30px">状 态:</label>
            	<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width: 153px; height: 22px;" data-options="required:true,validType:'noNull'" >
    				 <option id="norenter" value="1">已支付</option>
    				 <option id="renter" value="2">未支付</option>
		           
		        </select>
            </P>
            <input id="mgr_fee" name="mgr_fee" class="easyui-textbox" type="hidden"/>
        </div>
    </form>
</div>


<%--对话框按钮--%>
<div id="dlg-buttons3">
    <a href="#" id = "saveBu3" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRent1();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg3').dialog('close');$('#table').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg3" title="租金登记" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons3"> 
    <form id="fm3" method="post">
        <div>
        	 <P>
            	<label style="margin-right:30px">小区:</label>
            	<input  id="pnumx" name="pid" class="easyui-combobox" editable='false' data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:30px">栋 数:</label>
            	<input  id="bnumx" name="bid" class="easyui-combobox" editable='false' data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:24px">房间号:</label>
            	<input id="rnumx" name="room_num" class="easyui-combobox" editable='false' data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label>冷水量(吨):</label>
            	<input id="cold_water" name="cold_water" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label>热水量(吨):</label>
            	<input id="hot_water" name="hot_water" class="easyui-textbox"
            		data-options="required:true,validType:'noNull'" type="number"/></P>
            <P>
            	<label style="margin-right:11px">电费(度):</label>
            	<input id="electric_fee" name="electric_fee" 
            		class="easyui-textbox" data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label style="margin-right:30px">网 费:</label>
            	<input id="net_fee" name="net_fee" 
            		class="easyui-textbox" data-options="required:true,validType:'noNull'" type="number"/>
            </P>
            <P>
            	<label style="margin-right:30px">状 态:</label>
            	<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width: 153px; height: 22px;" data-options="required:true,validType:'noNull'" >
    				 <option id="norenter" value="1">已支付</option>
    				 <option id="renter" value="2">未支付</option>
		           
		        </select>
            </P>
            <input id="mgr_fee" name="mgr_fee" class="easyui-textbox" type="hidden"/>

        </div>
    </form>
</div>


</body>
</html>
