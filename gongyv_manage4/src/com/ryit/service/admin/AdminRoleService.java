package com.ryit.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminRoleMenuMapping;

@Service
public interface AdminRoleService {
	
	void addRole(AdminRole adminRole);
	
	//查询 角色 光联菜单查询;
	public List<AdminRole> findRoleMenu();
	
	//修改角色信息:
	void editRole(AdminRole adminRole);
	
	//删除第三张表(角色菜单mapping)数据
	void deleteAdminRoleMenuMapping(Integer roleId);
	
	//更新第三张表(角色菜单mapping)数据
	void addAdminRoleMenuMapping(AdminRoleMenuMapping armm);
	
	void addAdminRoleMenuList(List<AdminRoleMenuMapping> armms);
	
	List<AdminRole> findAll();
	
	//分页查询 
	
	//分页查询数据
	public List<AdminRole> getAllAdminRole(Map<String,Integer> map);
	
	//获取 菜单的总数进行分页操作;
	public Integer getAllRecord();
	
	//根据用户 id 查询出所对应的角色列表
	public List<AdminRole> getUserIdByRole(Integer userId);

}
