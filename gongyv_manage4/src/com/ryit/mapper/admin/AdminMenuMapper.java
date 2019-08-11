package com.ryit.mapper.admin;

import java.util.List;
import java.util.Map;

import com.ryit.entity.admin.AdminMenu;

/**
 * Created by 722665 on 2015/8/4.
 */
public interface AdminMenuMapper
{
	
	public void save(AdminMenu adminMenu);//
	
	public void edit(AdminMenu adminMenu);//

    public AdminMenu getByID(Integer id);//

    public List<AdminMenu> findAll();//
	
	List<AdminMenu> findMenuRootList();//

	Integer findChildById(Integer id);//

	void deleteById(Integer id);//

	AdminMenu findMenuById(String menuId);//
	
	//分页查询 
	
	//分页查询数据
	public List<AdminMenu> getAllAdminMenu(Map<String,Integer> map);
	
	//获取 菜单的总数进行分页操作;
	public Integer getAllRecord();
	
	//根据角色 id 查询出所对应的菜单列表
	public List<AdminMenu> getRoleIdByMenu(Integer roleId);

}
