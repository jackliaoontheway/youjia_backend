/**
 * 左边菜单
 */
$(function() {

	 
	var ifh = tabElement.height() - tabElement.find(".om-tabs-headers").outerHeight();
	//左侧菜单
	$("#navTreet").omTree({
        dataSource:"/common/user/function",
        simpleDataModel: true,
        onClick:function(nodeData, event) {
        	if(nodeData.url) {
        		var tabId = tabElement.omTabs('getAlter', 'tab_'+nodeData.id);
        		if(tabId){
        			tabElement.omTabs('activate', tabId);
        		}else{
                	tabElement.omTabs('add',{
                        title : nodeData.text, 
                        tabId : 'tab_'+nodeData.id,
                        content : "<iframe class='navTreeIframe_tab_"+nodeData.id+"' allowTransparency='true' scrolling='no' id='navTreeIframe_tab_"+nodeData.id+"' border=0 frameBorder='no' name='inner-frame' src='"+nodeData.url+"' height='"+ifh+"' width='100%'></iframe>",
                        closable : true
                    });
        		}
        	}
        }
    });
	

});


function loginoff() {
	asyncCallService("/aloginoff", 'get', 'JSON', '', function (result) {
		window.location.href = "/alogin";
  	}, function(){
  		window.location.href = "/alogin";
  	});
}


function getAdminMenuList() {
	asyncCallService("/aMenuList", 'get', 'JSON', '', function (result) {
		alert(result);
  	}, function(){
  		window.location.href = "/alogin";
  	});
}