package com.ryit.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ryit.entity.admin.AdminUser;
import com.ryit.utils.Constants;

public class BaseController{

	private static final String WEB_USER = "web_user";
	private static final String ADMIN_USER = "admin_user";

	//日志对象
	protected final static Logger LOGGER = Logger.getLogger(Constants.LOG4J_CATEGROY);

	Gson gson= new Gson();
/*
	//得到当前用户
	public User getUsers(HttpServletRequest request){
		return  (User)request.getSession().getAttribute(WEB_USER);
	}*/

	//得到後台當前用戶
	public AdminUser getAdminUsers (HttpServletRequest request){
		return  (AdminUser)request.getSession().getAttribute(ADMIN_USER);
	}

	//删除前台登录用户
	public void clearUsers (HttpServletRequest request){
		request.getSession().removeAttribute(WEB_USER);
	}

	//删除后台登录页面
	public void clearAdminUsers (HttpServletRequest request){
		request.getSession().removeAttribute(ADMIN_USER);
	}

	//失败页面
	public String failedPage(HttpServletRequest request,String content){
		request.setAttribute("content",content);
		request.setAttribute("success",false);
		return Constants.RESULT_PAGE;
	}

	//失败页面
	public String successPage(HttpServletRequest request,String content){
		request.setAttribute("content",content);
		request.setAttribute("success",true);
		return Constants.RESULT_PAGE;
	}
}
