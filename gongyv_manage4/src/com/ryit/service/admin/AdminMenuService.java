package com.ryit.service.admin;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminMenu;

@Service
public interface AdminMenuService {
    List<AdminMenu> getMenuByUser(String userName);
	List<AdminMenu> findMenuRootList();//查找根文件
	void saveAndUpdate(AdminMenu menu);
	Integer findChildById(Integer id);
	void deleteById(Integer id);
	List<AdminMenu> findAdminMenuList();
	AdminMenu findMenuById(String menuId);
	
	//分页查询 
	
	//分页查询数据
	public List<AdminMenu> getAllAdminMenu(Map<String,Integer> map);
	
	//获取 菜单的总数进行分页操作;
	public Integer getAllRecord();
	
	//根据角色 id 查询出所对应的菜单列表
	public List<AdminMenu> getRoleIdByMenu(Integer roleId);
}
