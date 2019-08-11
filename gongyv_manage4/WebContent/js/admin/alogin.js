$(function() {
	$("#LoginForm input[name='userName']").focus();
	$("#ChangeSecurityCode").bind("click", function() {
		$("#img_security_code").attr("src", "verifyCode.action?r=" + new Date().getTime());
		$("#LoginForm input[name='securityCode']").val("").focus();
	});

	$("#btnLogin").click(function() {
		login();
	});
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	login();   
	     }
	}
});

function DelCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	document.cookie = name + "=; expires=" + exp.toGMTString();
}

function login() {
	if (!login_validate()) {
		return;
	}else{
		DelCookie("JSESSIONID");
		submitForm();
	}
}

function login_validate() {
	var loginName = $("#LoginForm input[name='loginName']");
	var password = $("#LoginForm input[name='password']");
	var securityCode = $("#LoginForm input[name='securityCode']");
	
	if (loginName.val() == "") {
		$("#errMsg").html("请输用户名");
		loginName.focus();
		return false;
	}
	if (password.val() == "") {
		$("#errMsg").html("请输入密码");
		password.focus();
		return false;
	}
	if (securityCode.val() == "" || securityCode.val().length < 4) {
		$("#errMsg").html("请输入4位验证码");
		securityCode.focus();
		return false;
	}
	$("#msg_error").html("");
	return true;
}

function validateCode(){
	$("#img_security_code").attr("src", "verifyCode.action?r=" + new Date().getTime());
	$("#LoginForm input[name='securityCode']").val("").focus();
}

function submitForm(){
	var url = "login.action";
	var para = getFormPara($("#LoginForm"));
	
	$.post(url, para, function(data) {
		if (data == "1" || data == 1) {
			$("#errMsg").text("验证码错误");
			validateCode();
			securityCode.focus();
			return;
		}
		if (data == "2" || data == 2) {
			$("#errMsg").text("用户名或密码错误");
			validateCode();
			return;
		}
		if (data == "3" || data == 3) {
			$("#errMsg").text("用户不存在");
			validateCode();
			return;
		}
		if (data == "4" || data == 4) {
			$("#errMsg").text("登陆失败,系统异常");
			validateCode();
			return;
		}
		if (data == "0" || data == 0) {
			window.location.href = "Home.action";
		}
	});
	
};