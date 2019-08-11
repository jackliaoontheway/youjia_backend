package com.ryit.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.controller.BaseController;
import com.ryit.entity.admin.AdminMenu;
import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminRoleMenuMapping;
import com.ryit.entity.admin.AdminUser;
import com.ryit.service.admin.AdminMenuService;
import com.ryit.service.admin.AdminRoleService;

@Controller
@RequestMapping("pages/admin")
public class AdminRoleController extends BaseController {
	@Autowired
	private AdminRoleService roleService;
	@Autowired
	private AdminMenuService adminMenuService;

	@RequestMapping(value = "/adminRoleList")
	public String adminRoleList() {
		return "/admin/adminRoleManage";
	}
	
	/**
	 * 分页查询角色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleList")
	public @ResponseBody Object roleList(HttpServletRequest request, HttpServletResponse response) {
		List<AdminRole> list = null;
		
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
			
			list = roleService.getAllAdminRole(pageing);
			
		}
		
		
		Integer total = roleService.getAllRecord();//获取数据总数;
		if(total == null)
			total = 0;
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);	
		
		return map;
	}

	@RequestMapping("/editRole")
	public String editRole() {

		return "/admin/editRole";
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/roleAdd";
	}

	// @RequestMapping("/add")
	// public void add(AdminRole role) {
	// roleService.add(role);
	// j.setSuccess(true);
	// j.setMsg("添加成功！");
	// return j;
	// }
	/**
	 * 菜单管理
	 */
	@RequestMapping("/getAdminMenuList")
	public @ResponseBody Map<String,Object> getAdminMenuList(HttpServletRequest request, HttpServletResponse response,
			AdminMenu menu) {
		List<AdminMenu> list = null;
		
		if(menu != null && menu.getName() == null){
			//list = adminMenuService.findAdminMenuList();//查询所有
			
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
				
				list = adminMenuService.getAllAdminMenu(pageing);
				
			}
			
		}else{
			list = new ArrayList<AdminMenu>();
			
			AdminMenu am = adminMenuService.findMenuById(menu.getName());
			list.add(am);
		}
		
		Integer total = adminMenuService.getAllRecord();//获取数据总数;
		if(total == null)
			total = 0;
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);	
		
		return map;
	}

	/**
	 *  得到菜单列表
	 * @param request
	 * @param response
	 * @return 菜单列表的 json形式
	 */
	@RequestMapping("/getMenuList")
	public @ResponseBody Object getMenuTree(HttpServletRequest request, HttpServletResponse response) {
		List<AdminMenu> menus = adminMenuService.findAdminMenuList();
		Collections.sort(menus);
		return menus;
	}

	@RequestMapping("/getAdminMenu")
	public String getAdminMenu() {
		return "/admin/adminMenuManage";
	}
	
	/**
	 * 查询所有   根菜单列表
	 * @param response
	 * @return
	 */
	@RequestMapping("/getRootMenu")
	public @ResponseBody Object getRootMenu(HttpServletResponse response) {
		AdminMenu menu = new AdminMenu();
		menu.setId(-1);
		menu.setName("根目录");
		List<AdminMenu> listMenu = adminMenuService.findMenuRootList();
		listMenu.add(0, menu);
		return listMenu;

	}

	
	/**
	 * 增加 和 修改 菜单信息
	 * @param response
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping("/addAndUpdate")
	public Object addAndUpdate(HttpServletResponse response, HttpServletRequest request, AdminMenu menu) {
		if (menu.getPid() <= 0) {
			menu.setPid(null);
		}
		
		adminMenuService.saveAndUpdate(menu);
		return null;
	}
	
	/**
	 * 删除 菜单
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/delAdminMenu")
	public @ResponseBody Object delAdminMenu(HttpServletResponse response, HttpServletRequest request, Integer id) {
		// 查找是否有子目录，如有提示不能删除
		Integer findChild = adminMenuService.findChildById(id);
		if (findChild == 0) {

			adminMenuService.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 增加 和 修改 角色信息
	 * @param response
	 * @param request
	 * @param name
	 * @param remark
	 * @param menuid
	 * @param id
	 * @return
	 */
	@RequestMapping("/addRole")
	public @ResponseBody Object addRole(HttpServletResponse response, HttpServletRequest request, String name,
			String remark, String menuid, String id) {
			AdminRole adminRole = new AdminRole();
			AdminUser loginUser = getAdminUsers(request);
			Date currentDate = new Date();
			
			adminRole.setName(name);
			adminRole.setRemark(remark);
			
			//获取菜单id;
			String[] stringArr = menuid.split(",");
			Set<String> arrary = new HashSet<String>();
			for (String mId : stringArr) { // 去除重复数据
				arrary.add(mId);
			}
			
			if (id != null && !"".equals(id)) {
				adminRole.setId(Integer.parseInt(id));
				
				
				if(loginUser != null){//修改人/时间
					adminRole.setUpdater(loginUser.getLoginName());
					adminRole.setUpdateTime(currentDate);
				}
				
				int t_admin_role_id = adminRole.getId();
				List<AdminRoleMenuMapping> adminRoleMenuList = new ArrayList<AdminRoleMenuMapping>();
				//用于修改角色菜单关联表
				for (String menuId : arrary) {
					if ((!"".equals(menuId.trim())) && menuId.matches("^[0-9]*$") ) {
						//进行添加操作
						adminRoleMenuList.add(new AdminRoleMenuMapping(t_admin_role_id, Integer.valueOf(menuId)));
					}
				}
				
				editRole(adminRole,adminRoleMenuList);
				
			} else {
				if(loginUser != null){//创建人/时间
					adminRole.setCreater(loginUser.getLoginName());
					adminRole.setCreateTime(currentDate);
				}
				
				roleService.addRole(adminRole);//添加角色;
				
				//更新 第三张表(角色菜单映射表)
				int t_admin_role_id = adminRole.getId();
				//用于添加 角色菜单关联表
				for (String menuId : arrary) {
					
					if ((!"".equals(menuId.trim())) && menuId.matches("^[0-9]*$") ) {
						//进行添加操作
						roleService.addAdminRoleMenuMapping(new AdminRoleMenuMapping(t_admin_role_id, Integer.valueOf(menuId)));
					}
				}
				
			}
			
		return null;
	}
	
	//修改
	private void editRole(AdminRole adminRole,List<AdminRoleMenuMapping> roleMenuList){
		if(adminRole != null && roleMenuList != null && roleMenuList.size() > 0){
			if(adminRole.getId() != null){
				//修改 角色信息;
				roleService.editRole(adminRole);
				
				//删除第三张表 角色关联的菜单数据;
				roleService.deleteAdminRoleMenuMapping(adminRole.getId());
				//增加 新的关联菜单数据
				for(AdminRoleMenuMapping armm : roleMenuList){
					roleService.addAdminRoleMenuMapping(armm);
				}
			}
		}
	}

}
