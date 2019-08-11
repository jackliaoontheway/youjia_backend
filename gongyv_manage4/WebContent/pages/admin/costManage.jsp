<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%-- 费用管理 --%>
    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript">
    
	  //当编辑费用时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
		function editCost(){
			var row = $('#dg').datagrid('getSelected');
			if(row){
				$('#dlg').dialog('open').dialog('setTitle','编辑费用');
				
				$('#fm').form('load',row);
				
				//'url' 存储着当保存费用数据时表单回传的 URL 地址。
				url = 'updateCost.action';
			} else {
	            $.messager.show({	// show error message
	                title: 'Error',
	                msg: '请选择要编辑的数据'
	            });
	        }
		}
		
		
		//保存费用数据
		function saveCost(){
			
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
						$('#dg').datagrid('reload'); //reload the user data(重新加载费用数据)
		            }
		        }
		    });
	        
			
		}
	</script>
</head>
<body>

<div id="toolbar" style="padding:5px;height:auto" class="datagrid-toolbar">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCost();">编辑</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid"
       url="findCost.action"
       toolbar="#toolbar"
       singleSelect="true" data-options="fit:true,selectOnCheck:true,checkOnSelect:true"
       idField="id" pagination="true" style="width:auto;">
    <thead>
    <tr>
    	<th field="ck" checkbox="true">选择</th>
        <th field="ele_unit_price" align="center" width="33%">电费单价</th>
        <th field="cold_unit_price" align="center" width="33%">冷水费单价</th>
        <th field="hot_unit_price" align="center" width="33%" >热水费单价</th>
    </tr>
    </thead>
</table>


<%--对话框按钮--%>
<div id="dlg-buttons">
    <a href="#" id = "saveBu" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCost();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close');$('#dg').datagrid('reload');">取消</a>
</div>

<%--编辑对话框--%>
<div id="dlg" title="费用管理" class="easyui-dialog" style="background-color:#c9eeff; width:230px;height:320px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <div>
            <P>
            	电费单价:
            	<input id="ele_unit_price" name="ele_unit_price" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	冷水费单价:
            	<input id="cold_unit_price" name="cold_unit_price" class="easyui-textbox"
            		data-options="required:true,validType:'noNull'"/>
            </P>
            <P>
            	热水费单价:
            	<input id="hot_unit_price" name="hot_unit_price" class="easyui-textbox" 
            		data-options="required:true,validType:'noNull'"/>
            </P>
        </div>
    </form>
</div>

</body>
</html>