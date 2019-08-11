<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 退房管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <script src="${pageContext.request.contextPath}/portal/js/common/md5.js"></script>
    <script src="${pageContext.request.contextPath}/js/login/common.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript">
	
     // 状态模块
     function formatst(value) {
         if (value == 0) {
             return "未处理";
         } else if (value == 1) {
             return "已处理";
         } else if (value == 2) {
         	return "待审核";
         }
         return "";
     }

    
	//实现创建和编辑用户
	/* 	
	function newRenter(){
		$('#dlg').dialog('open').dialog('setTitle','创建租户');
		
		$('#fm').form('clear');
		
		url = '/easyUI/users?users=new';
	}
	 */
	 
	//当编辑用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
	function editRenter(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#dlg').dialog('open').dialog('setTitle','编辑退房');
			
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
	
	
	//保存租户数据
	function saveRenter(){
		
		$('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (result.errorMsg){
	                $.messager.show({
	                    title: 'Error(错误)',
	                    msg: result.errorMsg
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
	                $.post('/easyUI/users?users=destroy',{id:row.id},function(result){
	                    if (result.success){
	                        $('#dg').datagrid('reload');    // reload the user date(重新加载用户数据)
	                    } else {
	                        $.messager.show({    //show error message(显示错误消息)
	                            title: 'Error',
	                            msg: result.errorMsg
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
        $("#dg").datagrid('load', {
        	//bid key : value; 键值对形式; 发送请求会封装到 request 中;
        	bid:$('#bnum').val(),
            rid:$('#rnum').val(),
            phone:$('#phoneNum').val(),
            identity_card:$('#identity').val(),
            state:$("#sid").combobox('getValue')//获取select框;
        });
    }
		
    </script>
</head>
<body>

<div id="toolbar" style="padding:5px;height:auto">
    <div>
     	退租人:
    	<input id="bnum" style="width:100px"
                    url="data/combobox_data.json"
                    valueField="id" textField="text"/>

		栋号:
    	<input id="rnum" class="easyui-combobox" style="width:100px"
                    url="data/combobox_data.json"
                    valueField="id" textField="text"/>

		房间号:
    	<input id="rnum" class="easyui-combobox" style="width:100px"
                    url="data/combobox_data.json"
                    valueField="id" textField="text"/>
		状态:
    	<select id="sid" class="easyui-combobox" editable="false"  panelHeight="auto" style="width:100px">
            <option id="renter" value="0">未处理</option>
            <option id="renter" value="1">已处理</option>
            <option id="norenter" value="2">待审核</option>
        </select>

		<!-- 电话号码:
        <input id="phoneNum" type="text" width="100" style="width:100px" /> -->
              
               身份证:
        <input id="identity" type="text" width="100" style="width:100px" />
        
        <a onclick="searchorder();" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
    <div style="margin-bottom:5px">
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRenter();">添加</a>--%>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRenter();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRenter();">编辑</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid"
       url="/easyUI/renter?renter=all"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
       idField="id" pagination="true" style="width:auto;">
    <thead>
    <tr>
        <th field="ck" checkbox="true">选择</th>

        <th field="name" width="8%" align="center">退租人</th>
        <th field="bid" width="8%" align="center">栋号</th>
        <th field="rid" width="8%" align="center" >房号</th>
        <th field="phone" width="7%" align="center" >联系电话</th>
        <th field="Identity_card" width="7%" align="center">身份证号码</th>
        <th field="cause" width="10%" align="center">退租原因</th>
        <th field="return_cash " width="10%" align="center">押金</th>
        <th field="reality_return_cash" width="10%" align="center">实际退房押金</th>
        <th field="deduct_cash_cause" width="10%" align="center">扣款原因</th>
        <th field="state" width="10%" align="center" formatter="formatst">退房状态</th>
    </tr>
    </thead>
</table>

<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" id = "saveBu" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRenter();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dialog').dialog('close');$('#table').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="退房管理" class="easyui-dialog" style="background-color:#c9eeff; width:350px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <div>
            <p>
            	<label style="margin-right:22px">退租人:</label>
            	<input  id = "user_name" name="user_name"  class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </p>
            <p>
            	<label style="margin-right:28px">栋号:</label>
            	<input type="password" id = "password" name="password" class="easyui-textbox"
            		data-options="required:true,validType:'minLength[6]'" />
            </p>
            <P>
            	<label style="margin-right:10px">房号:</label>
            	<input id="name" name="name" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	<label style="margin-right:10px">联系电话:</label>
            	<input id="rid" name="rid" class="easyui-combobox" style="width:100px"
                    url="data/combobox_data.json"
                    valueField="id" textField="text"/>
            </P>
            <P>
            	<label style="margin-right:10px">身份证号码:</label>
            	<input id="bid" name="bid" class="easyui-combobox" style="width:100px"
                    url="data/combobox_data.json"
                    valueField="id" textField="text"/>
            </P>
            <P>
            	<label style="margin-right:10px">退租原因:</label>
            	<input id="phone" name="phone" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
           	
            <P>
            	<label>押金:</label>
            	<input id="identity_card" name="identity_card" class="easyui-textbox"
            		data-options="required:true,validType:'noNull'"/></P>
            <P>
            	<label>实际退房押金:</label>
            	<input id="ide_card_pic_z" name="ide_card_pic_z" 
            		class="easyui-validatebox" type="file"/>
            </P>
            <P>
            	<label>扣款原因:</label>
            	<input id="ide_card_pic_f" name="ide_card_pic_f" 
            		class="easyui-validatebox" type="file"/>
            </P>
            
          <!--   <P>
            	<label style="margin-right:10px">退房状态</label>
            	<input id="self_pic" name="self_pic" class="easyui-validatebox"
            		data-options="validType:'email'" type="file"/>
            </P> -->
          
            <P>
            	<label style="margin-right:30px">退房状 态:</label>
            	<select class="easyui-combobox" editable="false" id="state" name="state" 
    				 panelHeight="auto" style="width:auto">
		            <option id="daiding" value="0">未处理</option>
		            <option id="ok" value="1">已处理</option>
		            <option id="bohui" value="2">待审核</option>
		        </select>
            </P>


        </div>
    </form>
</div>

</body>
</html>