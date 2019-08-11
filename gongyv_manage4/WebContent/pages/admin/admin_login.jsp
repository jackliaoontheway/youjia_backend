<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/pages/common/admin.jsp"/>
    <title>管理员登录</title>
    <link href="${pageContext.request.contextPath}/themes/css/alogin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/themes/css/login.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/login/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/alogin.js" type="text/javascript"></script>
</head>
<body>
<%
    Cookie cookie=new Cookie("JSESSIONID", null);
    cookie.setMaxAge(-1);
    response.addCookie(cookie);
%>
    <form id="LoginForm" runat="server">
    <div class="Main">
        <ul>
            <li class="top"></li>
            <li class="top2"></li>
            <li class="topA"></li>
            <li class="topB"></li>
            <li class="topC"></li>
            <li class="topD">
                <ul class="login">
                    <li><span style="font-size: 12px;color: RED;" id="errMsg"></span></li>
                    <li><span class="left">用户名：</span> <span style="left">
                        <%--<input id="Text1" type="text" class="txt" />  --%>
                        <input type="text" name="loginName" class="txt" id="loginName"/>
                     
                    </span></li>
                    <li><span class="left">密&nbsp;&nbsp;&nbsp;码：</span> <span style="left">
                       <%--<input id="Text2" type="text" class="txt" />  --%>
                        <input type="password" name="password"  class="txt"/>
                    </span></li>

                    <li>
                        <span class="left">验证码：</span>
                        <span style="left">
                            <input id="code" type="text" name="securityCode" class="txtCode" />
                            <%--<input class="txtCode" type="text" size="6" id="code" name="securityCode"/>--%>
                        </span>

                        <span style="align-content: center">
							<a id="ChangeSecurityCode" title="点击更换" href="javascript:;" class="txtCodePic">
                                <img id="img_security_code" src="verifyCode.action" alt="sorry !!!  图片无法加载 " width="80" height="24"/>
                            </a>
						</span>
                    </li>

                </ul>
            </li>
            <li class="topE"></li>
            <li class="middle_A"></li>
            <li class="middle_B"></li>
            <li class="middle_C">
            <span class="btn">
                <img alt="" id="btnLogin" src="${pageContext.request.contextPath}/images/adminLogin/btnlogin.gif" />
            </span>
            </li>
            <li class="middle_D"></li>
            <li class="bottom_A"></li>
            <li class="bottom_B">
                <div class="copy-right">&copy; 2019 软易IT 版权所有 <a href="http://www.miitbeian.gov.cn" style="color: #E01F1F;" target="_blank">粤ICP备08034243号</a></div>
            </li>
        </ul>
    </div>
    </form>
</body>
</html>
