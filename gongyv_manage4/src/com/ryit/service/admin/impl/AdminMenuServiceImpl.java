package com.ryit.service.admin.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminMenu;
import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminUser;
import com.ryit.mapper.admin.AdminMenuMapper;
import com.ryit.mapper.admin.AdminRoleMapper;
import com.ryit.mapper.admin.AdminUserMapper;
import com.ryit.service.admin.AdminMenuService;


@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminMenuMapper adminMenuMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<AdminMenu> getMenuByUser(String userName) {
        AdminUser user = adminUserMapper.getUserByName(userName);
        if (user == null) {
            return null;
        }
        List<AdminMenu> menus = new ArrayList<>();
        List<AdminRole> roles = adminRoleMapper.getUserIdByRole(user.getId());
        for (AdminRole role : roles) {
        	List<AdminMenu> ms = adminMenuMapper.getRoleIdByMenu(role.getId());
            menus.addAll(ms);
        }

        return menus;
    }
    
    public AdminUser getUser(String name) {
        return adminUserMapper.getUserByName(name);
    }

	/**
	 * 查询所有 根菜单;
	 */
	public List<AdminMenu> findMenuRootList() {
		return adminMenuMapper.findMenuRootList();
	}

	/**
	 * 增加和修改;
	 */
	public void saveAndUpdate(AdminMenu menu) {
		if(menu != null && menu.getId() == null)
		adminMenuMapper.save(menu);
		else if(menu != null && menu.getId() != null)
		adminMenuMapper.edit(menu);
		
	}

	/**
	 * 查询是否有子菜单;
	 */
	public Integer findChildById(Integer id) {
		return adminMenuMapper.findChildById(id);
	}

	/**
	 * 删除菜单
	 */
	public void deleteById(Integer id) {
		adminMenuMapper.deleteById(id);
	}

	/**
	 * 查询所有菜单
	 */
	public List<AdminMenu> findAdminMenuList() {
		// TODO Auto-generated method stub
		return adminMenuMapper.findAll();
	}
	

	/**
	 * 查询指定 id 的菜单
	 */
	public AdminMenu findMenuById(String menuId) {
		// TODO Auto-generated method stub
		return adminMenuMapper.findMenuById(menuId);
	}
	
	
	/**
	 * 获取 菜单的总数进行分页操作;
	 * @return
	 */
	public Integer getAllRecord(){
		return adminMenuMapper.getAllRecord();
	}

	/**
	 * 分页查询菜单数据
	 */
	public List<AdminMenu> getAllAdminMenu(Map<String,Integer> map){
		return adminMenuMapper.getAllAdminMenu(map);
	}
	
	
	//根据角色 id 查询出所对应的菜单列表
	public List<AdminMenu> getRoleIdByMenu(Integer roleId){
		List<AdminMenu> list = null;
		if(roleId != null){
			list = adminMenuMapper.getRoleIdByMenu(roleId);
		}
		return list;
	}
}
