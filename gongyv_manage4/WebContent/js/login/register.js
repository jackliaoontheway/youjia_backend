$(function(){
	
	  $(".placeholder").on("click", function(e){
		  $(this).prev("input").focus();
	  });
	  
	  $("input[type='text']").focus(function(){
		 //alert("aaa"); 
	  });
	  
	
  	 //email
  	 $("#email").click(function(){
  		$("#email").focus();
  		//$("#email").next().removeAttr("class");
  		//$("#email").next().text("");
  		$("#message").text("请输入您的邮件");
  	 }).blur(function(){
  		$("#emailErr").text("");
  		if(this.value.trim().length==0){
  			var style=$("#email").next();
  			//style.attr("class","placeholder");
  			//$("#email").next().text("邮箱");
  		}else{
  			var email= FrmCheck.email(this.value.trim());
  			if(email !="pass"){
  				$("#emailErr").text("邮件格式错误");
  			} 
  			 checkEmail(this.value.trim());
  		}
  	 }).change(function(){
  		if(this.value.trim().length !=0){
  			var email= FrmCheck.email(this.value.trim());
  			if(email =="pass") {
  				 checkEmail(this.value.trim());
  			}
  		}
  	 });
  	 
  	 
  	 //帐号
	 $("#count").click(function(){
		 	$("#count").focus();
	  		//$("#count").next().removeAttr("class");
	  		//$("#count").next().text("");	
	  		$("#message").text("请输入您登录帐号");
	  	 }).blur(function(){
	  		 $("#countErr").text("");	
	  		if(this.value.trim().length==0){
	  			var style=$("#count").next()
	  			style.attr("class","placeholder");
	  			//$("#count").next().text("登录帐号");
	  		}else{
	  			var info=FrmCheck.illegalChar(this.value.trim());
	  			if(!info)
	  			 $("#countErr").text("不能含有特殊字符");	
	  			checkCount(this.value.trim());
	  		}
	   }).change(function(){
	  		if(this.value.trim().length !=0){
	  			var info=FrmCheck.illegalChar(this.value.trim());
	  			if(info){
	  				checkCount(this.value.trim());
	  			}
	  		}
	  	 });
  	 //公司名称
	 $("#company").click(function(){
		 	$("#company").focus();
	  		//$("#company").next().removeAttr("class");
	  		//$("#company").next().text("");
	  		$("#message").text("请输入您的公司名称");
	  	 }).blur(function(){
	  		if(this.value.trim().length==0){
	  			var style=$("#company").next()
	  			//style.attr("class","placeholder");
	  			//$("#company").next().text("公司名称");
	  		}
	   });
 	 //手机号吗
	 $("#phone").click(function(){
		 	$("#phone").focus();
	  		//$("#phone").next().removeAttr("class");
	  		//$("#phone").next().text("");
	  		$("#message").text("请输入您的手机号码");
	  	 }).blur(function(){
	  		$("#phoneErr").text("");
	  		if(this.value.trim().length==0){
	  			var style=$("#phone").next()
	  			//style.attr("class","placeholder");
	  			//$("#phone").next().text("手机号码");
	  		}else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(this.value.trim())) || this.value.trim().length !=11 ){
	  			$("#phoneErr").text("手机号有误");	
	  			
	  		}else{
	  			checkPhone(this.value.trim());
	  		}
	   }).change(function(){
	  		if(this.value.trim().length !=0){
	  			checkPhone(this.value.trim());
	  		}
	  	 });
	 
  	 //密码确认
	 $("#passwd").click(function(){
		   $("#passwd").focus();
	  		//$("#passwd").next().removeAttr("class");
	  		//$("#passwd").next().text("");
	  		 $("#message").text("请输入密码");
	  	 }).blur(function(){
	  		$("#passwdErr").text("");
	  		if(this.value.trim().length==0){
	  			var style=$("#passwd").next()
	  			//style.attr("class","placeholder");
	  			//$("#passwd").next().text("密码(密码长度6~16位,数字、字母、字符至少包含两种)");
	  			// $("#passwdErr").text("密码(密码长度6~16位,数字、字母、字符至少包含两种)");
	  		
	  		}else{
	  			var level = checkPasswd(this.value.trim());
	  			 if(level =="noEnough" || level==""){
		  			 $("#passwdErr").text("长度8位起，数字和字母大小写组合");
		  		 }
	  		}
	   }).keyup(function(){
		   checkPasswd(this.value.trim());
	   });
  		 
 	 //确认密码
	 $("#confPasswd").click(function(){
		 $("#confPasswd").focus();
	  	 //$("#confPasswd").next().removeAttr("class");
	  	 //$("#confPasswd").next().text("");
	  	 $("#message").text("请输入再次输入密码");
	  	 }).blur(function(){
	  		$("#confpasswdErr").text("");
	  		if(this.value.trim().length==0){
	  			var style=$("#confPasswd").next()
	  			//style.attr("class","placeholder");
	  			// $("#confPasswd").next().text("密码(密码长度6~16位,数字、字母、字符至少包含两种)");
	  			// $("#confpasswdErr").text("两次密码不一样");
	  		}else{
	  			var passwd= $("#passwd").val().trim();
	  			if(passwd !=this.value.trim()){
	  			  $("#confpasswdErr").text("两次密码不一样");
	  			}
	  		}
	   });
	 
	 $("#getVcode").click(function(){
		 $("#vcode").next().removeAttr("class");
	  	 $("#vcode").next().text("");
	  	$("#vcode").focus();
	 });
	 
	 //提交表单 
	 $("#registerStep1").click(function(){
		 submitForm();
	 });
	 
  	});
  	
	//检查密码的强度
  	function checkPasswd(str){
  		$("[class*=grade-]").css("background-color","#9EA6A4");
  		 var level ="";
  	   if(str.length>7){
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
  	};
  	//验证邮箱
  	function checkEmail(str){
  		var url = "/CSSP/user/checkEmail";
    	var para = {email: str} ;
    	$.get(url, para, function(data) {
    		  if (data == "change") {
    			$("#emailErr").text("该邮件已注册");
    			emailStatus="该邮件已注册";
    		} 
    	});
  	};
  	
  	//验证帐号和手机号码
  	function checkCount(str ){
  		var url="/CSSP/user/checkCount";
  		var para={count: str}
  		$.get(url, para, function(data){
  			if(data=="change"){
  				$("#countErr").text("帐号已被注册");
  			}
  		});
  	};
  //验证手机号码
  	function checkPhone(str){
  		var url="/CSSP/user/checkPhone";
  		var para={phone: str}
  		$.get(url, para, function(data){
  			if(data=="change"){
  				$("#phoneErr").text("手机号已被注册");
  			}
  		});
  	};
  	
  	//更换验证码
    function changeCode() {
    	$("#validCode").attr("src", "/common/getValidateCode?ts=" + new Date().getTime());
    }
  	
  	
  	function submitForm(){
  		//邮件验证
  		 var email2  = $("#email").val().trim().length >0;
  		 var email3  = $("#emailErr").text().trim().length < 1;
  		 if ( !email2 || !email3 ){
  			$("#emailErr").text("请验证邮箱");
  			$("#email").focus();
  			return;
  		 }
  		 //帐号验证
  		var count1= $("#count").val().trim().length>1;
  		var count2= $("#countErr").text().trim().length < 1;
  		if(!count1 || !count2 ){
  			$("#countErr").text("请验证帐号信息");
  			$("#count").focus();
  		};
  		//公司名称验证
  		var company1=$("#company").val().trim().length>1;
  		var company2=$("#campanErr").text().trim().length<1;
		if(!company1 || !company2  ){
  			$("#campanErr").text("请验证公司名称");
  			$("#company").focus();
  		};
  		//只能输入数字
  		var phone1=$("#phone").val().trim().length>1;
  		var phone2=$("#phoneErr").text().trim().length<1;
  		if(!phone1 || !phone2 ){
  			$("#message").text("请验证手机号码");
  			$("#phone").focus();
  		};
  		
  		//密码验证
  		var password1=$("#passwd").val().trim().length>1;
  		var password2=$("#passwdErr").text().trim().length<1;
		if(!password1 || !password2 ){
  			$("#passwdErr").text("请验证密码");
  			$("#passwd").focus();
  		};
  		
  		//确认密码验证
  		var confPasswd1=$("#confPasswd").val().trim().length>1;
  		var confPasswd2=$("#confpasswdErr").text().trim().length<1;
		if(!confPasswd1 || !confPasswd2 ){
  			$("#confpasswdErr").text("两次密码不一至");
  			$("#confPasswd").focus();
  		};
  		
  		//验证码验证
//  		var validCode=$("#validCode").val().trim().length>1;
//		if(!validCode){
//  			$("#message").text("请输入验证码");
//  			$("#validCode").focus();
//  		};
  	 	var url = "/CSSP/user/register";
  	 	var para="";
    	var para = getFormPara($("#regiter-form"));
    	
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
  	    para.password= passwor3;
    	$.post(url, para, function(data) {
    	 if (data == "success"){
    			location = "/CSSP/user/registerSucess?user="+$("#count").val().trim();
    		} else {
    			alert("注册失败");
    			$("#message").html("注册失败");			
    		}
    		//changeCode();
    	});
  	}
  	/**
  	 * 获取短信验证码
  	 */
  	function getMessageCode(){
  		var mobile = $('#phone').val();
  		if(mobile.trim().length<1){
  			$("vcodeErr").text("请输入手机号再试");
  			return;
  		}
  		if(!(/^1[3,7,4,5,8]\d{9}$/.test(mobile))){
  			$("vcodeErr").text("手机号出错");
  			return;
  		};
  		$.ajax({
  	 			type: 'post',
  	 			url: '/user/sendVcodeMsg',
  	 			data: 'mobile=' + mobile,
  	 			success: function(data){
  					if(data =="0"){
  						$("vcodeErr").text('验证码发送失败');
  					}else if(data =="error"){
  						$("vcodeErr").text('手机号不可用');	
  					}else if(data =="exsit"){
  						$('#vcodeErr').click(function(){location.href='/checkId/login?mobile='+mobile;});
  						//showMsg('',180000,'_exisMsg');
  					}else{
  						$("#vcode").focus();
  						//util.hsTime(data.maxTime);
  					}
  				},
  			    error : function() {
  			    	//showMsg('你的网络有点小问题~<br>');
  			    }
  		});
  	}