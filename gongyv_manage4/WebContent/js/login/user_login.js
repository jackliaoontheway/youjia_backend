var version ="v1.2"; 
$(function(){
    	$("#count").click(function(){
    		$("#count").focus();
    		$(".main-check-p").html("");
    	}).blur(function(){
    	 	
    	});
    	
      	$("#passwd").click(function(){
      		$("#passwd").focus();
      		$(".main-check-p").html("");
    	}).blur(function(){
    		 
    	});
      	
      	$("#check-code").mouseover(function(){
      		$("#check-code").focus();
      		$(".main-check-p").html("");
    	}).blur(function(){
    		 
    	}).keydown(function(e){
    		if(e.keyCode==13){
    			submitForm();
    			}
    		});
      	
      	//表单提交
      	$("#submit-login").click(function(){
      		submitForm();	
      				
      	});
    });
    
    function changeCode() {
    	$("#get-code").attr("src", "verifyCode.action?ts=" + new Date().getTime());
    }
    
    function submitForm(){
    	var count= $("#count");
    	if (count.val().trim().length==0){
    		$(".main-check-p").html("请输入登录名");
    		count.focus();
    		return;
    	}
    	var passwd=$("#passwd");
    	if (passwd.val().trim().length==0){
    		$(".main-check-p").html("请输入密码");
    		passwd.focus();
    		return;
    	}
    	var valiate=$("#check-code");
    	if (valiate.val().trim().length==0){
    		$(".main-check-p").html("请输入验证码");
    		valiate.focus();
    		return;
    	}
    	
    	var url = "/CSSP/user/login";
    	var para = getFormPara($("#login-form"));
	 	var password=hex_md5($("#passwd").val().trim());
	 	var passwor2="";
	 	var passwor3="";
	 	for(var i=password.length-1; i>0;  i-- ){
	 		passwor2 +=password[i];
	 	}
	 	passwor2 = hex_md5(password);
	 	for(var j=passwor2.length-1; j>0; j-- ){
	 		passwor3 +=passwor2[j];
	 	}
  	    para.passwd= passwor3;
    	$.post(url, para, function(data) {
    		if (data == "vcode error") {
    			$(".main-check-p").html("验证码错误");
    		} else if (data == "userpass error") {
    			$(".main-check-p").html("用户名或密码错误");
    		} else if (data == "sys error") {
    			$(".main-check-p").html("系统发生错误，请重试");	
    		} else if (data == "db error") {
    			$(".main-check-p").html("数据库发生错误");	
    		}else if(data =="userNotActivit"){
    			$(".main-check-p").html("用户没有激活，请联系管理员");
    		} 
    		else if (data == "success"){
    			location = "/CSSP/ahome";
    		} else {
    			$(".main-check-p").html("发生错误");			
    		}
    		changeCode();
    	});
    	
    };