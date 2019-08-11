package com.ryit.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.controller.BaseController;
import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminUser;
import com.ryit.service.admin.AdminRoleService;
import com.ryit.service.admin.AdminUserService;

@Controller
@RequestMapping("pages/admin")
//
public class AdminUserManage extends BaseController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping(value = "/adminUserList")
    public String adminUserList() {
        return "/admin/adminUserManage";
    }

    
    @RequestMapping(value = "/UserList")
    public String userList() {
        return "/admin/user_manage";
    }
    
    /**
     * 查询所有 用户
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/getalluser")
    public
    @ResponseBody
    Object getalluser(HttpServletResponse response, HttpServletRequest request) {
    	
    	List<AdminUser> list = null;
		
		//分页
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		if(page != null && rows != null){
			int currentPage = 0;
			int pageSize = 5;
			if(rows.matches("^[0-9]*$"))
				pageSize = Integer.valueOf(rows);//分页的数
			
			if(page.matches("^[0-9]*$")){
				currentPage = Integer.valueOf(page) - 1;//当前页数
				if(currentPage < 0)
					currentPage = 0;
				currentPage = currentPage * pageSize;
			}
			
			
			Map<String,Integer> pageing = new HashMap<String,Integer>();
			pageing.put("currentPage", currentPage);
			pageing.put("pageSize", pageSize);
			
			list = adminUserService.getAllAdminUser(pageing);
			
		}
		
		
		Integer total = adminUserService.getAllRecord();//获取数据总数;
		if(total == null)
			total = 0;
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);	
		
		return map;
    }
    
    /**
     * 查询所有 角色
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/getallRole")
    public @ResponseBody Object getallRole(HttpServletResponse response, HttpServletRequest request) {
    	List<AdminRole> roles = adminRoleService.findAll();
        return roles;
    }
    
    /**
     * 增加 和 修改 用户
     * @param response
     * @param request
     * @param user
     * @param ros
     * @return
     */
    @RequestMapping(value = "/saveUserAndRole", method = RequestMethod.POST)
    public
    @ResponseBody
    Object saveUserAndRole(HttpServletResponse response, HttpServletRequest request, AdminUser user, String ros) {
    	//判定用户是否已注册
    	
    	Integer isExist = adminUserService.findUserIsExist(user);
    	if(user.getId() == null){
	    	if(isExist != null){
	    		return "1";
	    	}
    	}
       adminUserService.saveUserAndRole(user, ros);
        return "";
    }

    /**
     * 删除用户
     * @param response
     * @param request
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/delAdminUser", method = RequestMethod.POST)
    public
    @ResponseBody
    Object delAdminUser(HttpServletResponse response, HttpServletRequest request, String userIds) {
        adminUserService.delete(userIds);
        return "true";
    }
    
    /**
     * 获取 用户 根据id 查询
     * @param response
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getadminUserByid", method = RequestMethod.POST)
    public
    @ResponseBody
    Object getAdminUserByid(HttpServletResponse response, HttpServletRequest request, Integer id) {
        AdminUser user = adminUserService.getUserById(id);
        if (user != null) {
            return user;
        } else {
            return "";
        }

    }

}
