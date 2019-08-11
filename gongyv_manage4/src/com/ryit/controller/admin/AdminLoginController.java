package com.ryit.controller.admin;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.controller.BaseController;
import com.ryit.entity.admin.AdminMenu;
import com.ryit.entity.admin.AdminUser;
import com.ryit.service.admin.AdminMenuService;
import com.ryit.service.admin.AdminUserService;
import com.ryit.service.admin.LdapAuthenticationService;
import com.ryit.utils.UserSession;

/**
 * Created by 722665 on 2015/8/1.
 */
@Controller
@RequestMapping("pages/admin")
public class AdminLoginController extends BaseController {
	@Autowired
    private LdapAuthenticationService ldapAuthenticationBiz;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 登录校验
     * @param request
     * @param loginName
     * @param password
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody String login(HttpServletRequest request, String loginName, String password, HttpServletResponse response) {
        String code = request.getParameter("securityCode");
        
        //验证码是否输入正确
        String vcode = (String) request.getSession().getAttribute("verify");
        if (!code.equalsIgnoreCase(vcode)) {
            return "1";
        }
        if (password.isEmpty()) {
            return "1";
        }
        AdminUser userByName = adminUserService.getUserByName(loginName);
        if (null == userByName) {
            return "3";
        }
        
        //密码校验
        if (!userByName.getName().equals(password)) {
        	return "2";
        }

        HttpSession session = request.getSession();
        session.setAttribute("admin_user", userByName);
        
        //boolean authentication =true;
        boolean authentication = ldapAuthenticationBiz.authentication(loginName, password);
        if (authentication) {
            UserSession.setAdminUser(userByName);
            return "0";
        } else {
            return "4";
        }

    }
    
    /**
     * 登录成功跳转页面
     * @return
     */
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String adminHome() {
        return "/pages/admin/admin_home.jsp";
    }

    /**
     * 跳转至登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String aLogin() {
        return "/pages/admin/admin_login.jsp";
    }
    
    /**
     * 获取 登录用户
     * @param request
     * @return
     */
    @RequestMapping("/checkAdminLogin")
    public @ResponseBody Object checkAdminLogin(HttpServletRequest request) {
        AdminUser user = getAdminUsers(request);
        if(user == null){
            return "0";
        }else{
            return "1";
        }
    }
    
    @RequestMapping(value = "/aloginoff", method = RequestMethod.GET)
    public void aloginoff() {
        UserSession.removeUser();

    }

    @RequestMapping("/adminLogout")
    public String adminLogout(HttpServletRequest request) {
        clearAdminUsers(request);
        return "/pages/admin/admin_login.jsp";
    }
    
    /**
     * admin_home 主界面 显示菜单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/MenuList")
    public @ResponseBody Object  aMenuList(HttpServletRequest request, HttpServletResponse response) {
    	List<AdminMenu> adminMenuList = null;
        AdminUser adminUser = UserSession.getAdminUser();
        adminMenuList = adminMenuService.getMenuByUser(adminUser.getLoginName());
        System.out.println(adminMenuList + " ................................. ");
        return adminMenuList;
    }
    
}
