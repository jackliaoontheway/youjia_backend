package com.ryit.entity.admin;

//用户 角色 映射类
public class AdminUserRoleMapping {

	private Integer t_admin_user_id;//用户id
	
	private Integer roles_id;//角色id;
	
	public AdminUserRoleMapping() {
	}
	
	

	public AdminUserRoleMapping(Integer t_admin_user_id, Integer roles_id) {
		this.t_admin_user_id = t_admin_user_id;
		this.roles_id = roles_id;
	}



	public Integer getT_admin_user_id() {
		return t_admin_user_id;
	}

	public void setT_admin_user_id(Integer t_admin_user_id) {
		this.t_admin_user_id = t_admin_user_id;
	}

	public Integer getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(Integer roles_id) {
		this.roles_id = roles_id;
	}
	
	
}
