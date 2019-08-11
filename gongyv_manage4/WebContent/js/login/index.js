var father=[{id: 1, name:"订单管理"  },{id: 2, name:"产品管理"  },
			{id: 3, name:"会员管理"  },{id: 4, name:"服务管理"  }];
var children=[{id:5,fatherid: 1, name: "订单管理1", hf:"http://www.baidu.com"}, 
			  {id:5,fatherid: 1, name: "订单管理2", hf:"http://www.baidu.com"},
			  {id:5,fatherid: 1, name: "订单管理3", hf:"http://www.baidu.com"},
			  {id:5,fatherid: 1, name: "订单管理4", hf:"http://www.baidu.com"},
			  {id:5,fatherid: 1, name: "订单管理5", hf:"http://www.baidu.com"},
			  {id:5,fatherid: 1, name: "订单管理6", hf:"http://www.baidu.com"},
			  {id:5,fatherid: 1, name: "订单管理7", hf:"http://www.baidu.com"}];
//页面加载后自动加载
$(function(){
	//loadMenu();	
});
   
   //动态加载菜单数据 
   
function displaySubMenu(li) {
 var subMenu = li.getElementsByTagName("ul")[0]; 
 subMenu.style.display = "block"; 
} 
function hideSubMenu(li) { 
	var subMenu = li.getElementsByTagName("ul")[0]; 
	subMenu.style.display = "none"; 
} 