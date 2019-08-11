package com.ryit.entity.admin;

// 角色 菜单 映射类
public class AdminRoleMenuMapping {
	
	private Integer t_admin_role_id;//角色id
	
	private Integer menus_id;//菜单id;
	

	public AdminRoleMenuMapping() {
	}
	
	
	
	public AdminRoleMenuMapping(Integer t_admin_role_id, Integer menus_id) {
		this.t_admin_role_id = t_admin_role_id;
		this.menus_id = menus_id;
	}



	public Integer getT_admin_role_id() {
		return t_admin_role_id;
	}

	public void setT_admin_role_id(Integer t_admin_role_id) {
		this.t_admin_role_id = t_admin_role_id;
	}

	public Integer getMenus_id() {
		return menus_id;
	}

	public void setMenus_id(Integer menus_id) {
		this.menus_id = menus_id;
	}
	
	
}
