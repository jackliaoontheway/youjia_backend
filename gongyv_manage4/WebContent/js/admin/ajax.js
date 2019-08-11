function syncCallService(url, type, dataType, data, success, error) {
	$.ajax({
		url : url,
		type : type,
		dataType : dataType,
		data : data,
		async : false,
		cache : false,
		success : function(result) {
			if (result == "n0l0gin") {
				login();
				return;
			}
			success.call(this, result);
		},
		error : function(result) {
			if (result != null && result != undefined) {
				var text = result.responseText;
				if (text != null && text != undefined) {
					var index = text.indexOf("login.jsp");
					if (index != -1) {
						if (window != top) {
							top.location.href = "/";
						} else {
							window.location.href = "/";
						}
					}
				}
			}
			error.call(this, result);
		}
	});
}

function asyncCallService(url, type, dataType, data, success, error) {
	$.ajax({
		url : url,
		type : type,
		dataType : dataType,
		data : data,
		cache : false,
		success : function(result) {
			success.call(this, result);
		},
		error : function(result) {
			if (result != null && result != undefined) {
				var text = result.responseText;
				if (text != null && text != undefined) {
					var index = text.indexOf("login.jsp");
					if (index != -1) {
						if (window != top) {
							top.location.href = "/";
						} else {
							window.location.href = "/";
						}
					}
				}
			}
			error.call(this, result);
		}
	});
}

/**
 * 只能输入数字
 */
function checkNum(str) {
	var pattern = /^\d+$/i;
	if (pattern.test(str)) {
		return true;
	} else {
		return false;
	}
}