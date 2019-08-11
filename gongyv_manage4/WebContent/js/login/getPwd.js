$(function(){
	 //手机号吗
	 $("#contactInfo").mouseover(function(){
		 	$("#contactInfo").focus();
	  		$("#contactInfo").next().removeAttr("class");
	  		$("#contactInfo").next().text("");
	  		var phone = $('#contactInfo').val();
	  		if(!phone){
		  		$("#errMsg").text("请输入您的手机号码");	  			
	  		}
	  		$("#phoneMsg").attr("class", "input-bg input-a");
	  	 }).blur(function(){
	  		$("#errMsg").text("");
	  		if(this.value.trim().length==0){
	  			var style=$("#contactInfo").next()
	  			style.attr("class","placeholder");
	  			$("#contactInfo").next().text("手机号码");
	  		}else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(this.value.trim())) || this.value.trim().length !=11 ){
	  			$("#errMsg").text("手机号码有误");	 
	  		}
	  		$("#phoneMsg").attr("class", "input-bg");
	   }).change(function(){
	  		if(this.value.trim().length !=0){
	  			
	  		}
	  	});
	 
	 	//手机号吗
		$("#check-code").mouseover(function(){
	  		$("#check-code").focus();
	  		$("#checkCode").attr("class", "code-p code-a");
	  		var code = $('#check-code').val();
	  		if(!code){
	  			$("#errMsg").text("请输入验证码");
	  		}
	  		$("#check-code").next().text("");
		}).blur(function(){
			$("#errMsg").text("");
			$("#checkCode").attr("class", "code-p");
		});
		$("#conSend").hide();
		
	  	 //密码确认
		 $("#passwd").mouseover(function(){
			   	$("#passwd").focus();
		  		$("#passwd").next().removeAttr("class");
		  		$("#passwd").next().text("");
		  		var passwd = $('#passwd').val();
		  		if(!passwd){
		  			$("#errMsg").text("请输入密码");  			
		  		}	  		
		  }).blur(function(){
		  		$("#passwdErr").text("");
		  		if(this.value.trim().length<=7){
		  			var style=$("#passwd").next()
		  			style.attr("class","placeholder fix-left");
		  			$("#passwd").next().text("长度8位起，数字和字母大小写组合");
		  		}else{
		  			 var level = checkPasswd(this.value.trim());
		  			 if(level =="noEnough" || level==""){
			  			 $("#errMsg").text("密码不符合要求");
			  		 }
		  		}
		   }).keyup(function(){
			   checkPasswd(this.value.trim());
		   });
	  		 
	 	 //确认密码
		 $("#confPasswd").mouseover(function(){
			 $("#confPasswd").focus();
		  	 $("#confPasswd").next().removeAttr("class");
		  	 $("#confPasswd").next().text("");
		  	 var confPasswd = $('#confPasswd').val();
	  		 if(!confPasswd){
	  			$("#errMsg").text("请输入再次输入密码");		
	  		 }
		  	 }).blur(function(){
		  		$("#errMsg").text("");
		  		if(this.value.trim().length==0){
		  			var style=$("#confPasswd").next()
		  			style.attr("class","placeholder fix-left");
		  			 $("#confPasswd").next().text("长度8位起，数字和字母大小写组合");
		  		}else{
		  			var passwd= $("#passwd").val().trim();
		  			if(passwd !=this.value.trim()){
		  			  $("#errMsg").text("两次密码不一样");
		  			}
		  		}
		   });
		 
});

function changeCode() {
    $("#get-code").attr("src", "/common/getValidateCode?ts=" + new Date().getTime());
}
/**
 * 下一步提交
 */
function nextSubmit() {
	var phone = $('#contactInfo').val();
	if(!phone && phone.length==0){
		$("#errMsg").text("请输入手机号码");
		return;
	}
	var code = $('#check-code').val();
	if(!code && code.length==0){
		$("#errMsg").text("请输入验证码");
		return;
	}
	$("#passwd-form").submit();
}

//获取短信发送时间
function getTime(){
	var url="/CSSP/getTime";
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		cache : false,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		async:false,
		success : function(result) {
			if(result.residueTime =="0"){
				$("#count-time").hide();
				$("#conSend").show();
			}else{
				$("#count-time").show();
				$("#conSend").hide();
				countDown(result.residueTime);
			}
		}
	});
			
}

/**
 * 倒计时
 * @param maxTime 时长
 */
var timer;
var wut;
function countDown(maxTime){
	$("#errMsg").text("");
	 secs = maxTime; // Number of secs to delay -CHINA-studio
	 wait = secs * 1000;
	 $("#conSend").hide();
	 $("#count-time").show();
	 for(var i=1;i<=(wait/1000);i++) {
		 timer = window.setTimeout("doUpdate(" + i + ")", i * 1000);
	 }
}
 function doUpdate(num) {
	 if(wut==0){
		 window.setTimeout("Timer()", wait);
	 }
	 if(num == (wait / 1000)) {
		$("#count-time").hide();
		$("#conSend").show();
	  }else{
		wut = (wait / 1000) - num;
		$("#count-time").text("("+wut+")秒后可重新发送");
	  }
 }
 function Timer() {
	 $("#count-time").hide();
	 $("#conSend").show();
}
 
 function nextSetPwd(){
	$("#errMsg").text("");
	var code = $('#v-code').val();
	if(!code && code.length==0){
		$("#errMsg").text("请输入验证码");
		return;
	}
	$("#cdoeForm").submit();
 }
 
//检查密码的强度
function checkPasswd(str){
   $("[class*=grade-]").css("background-color","#9EA6A4");
   var level ="";
   if(str.length>5){
	 level=FrmCheck.passlevel(str);
		  	 if(level=="enough"){
				$(".grade-1").css("background-color","#60CFB6");			   		   
		   	 }else if(level=="medium"){
		   		$(".grade-1").css("background-color","#60CFB6");
		   		$(".grade-2").css("background-color","#19AF8D");
	   	     }else if(level=="strong"){
	   			$(".grade-1").css("background-color","#60CFB6");
	   			$(".grade-2").css("background-color","#19AF8D");
	   			$(".grade-3").css("background-color","#009B72");
	   	   }
	   } 
   return level;
}
//设置新密码提交
function setPwdSubmit(){
	$("#errMsg").text("");
	var passwd = $('#passwd').val();
	if(!passwd && passwd.length==0){
		$("#errMsg").text("请输入密码");
		return;
	}
	var level = checkPasswd(passwd.trim());
	 if(level =="noEnough" || level==""){
		 $("#errMsg").text("密码不符合要求");
		 return;
	 }
	
	var confPasswd = $('#confPasswd').val();
	if(!confPasswd && confPasswd.length==0){
		$("#errMsg").text("请输入确认密码");
		return;
	}
	
	if(passwd.trim() !=confPasswd.trim()){
	  $("#errMsg").text("两次密码不一样");
	  return;
	}
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
 	alert(passwor3);
	$("#pwdId").val(passwor3);
	$("#confpwd").val(passwor3);
	$("#passwd-formSet").submit();
 }
/***
 * 重新发送成功
 */
function restSend(){
	$("#errMsg").text("");
	var url = "/CSSP/user/getReSendCode";
	asyncCallService(url, 'get', 'JSON', '', function(data) {
		if (data) {
			if(data == 1){
				getTime();
			}else if(data == 0){
				alert('重新发送短信失败，请稍后重试');
			}
		} 
	}, function() {
		alert('重新发送短信失败，请稍后重试');
	});
}