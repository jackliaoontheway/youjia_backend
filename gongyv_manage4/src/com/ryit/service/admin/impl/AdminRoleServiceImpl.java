package com.ryit.service.admin.impl;


import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminMenu;
import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminRoleMenuMapping;
import com.ryit.mapper.admin.AdminMenuMapper;
import com.ryit.mapper.admin.AdminRoleMapper;
import com.ryit.service.admin.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminMenuMapper adminMenuMapper;

	/**
	 * 增加角色;
	 */
	public void addRole(AdminRole adminRole) {
		adminRoleMapper.addRole(adminRole);
	}
	
	/**
	 * 用于查询角色的所有信息
	 * @return
	 */
	public List<AdminRole> findAll(){
		return adminRoleMapper.findAll();
	}
	
	/**
	 * 查询所有角色关联菜单查询;
	 */
	public List<AdminRole> findRoleMenu(){
		//查询角色关联菜单
		List<AdminRole> roleMenuAll = adminRoleMapper.findRoleMenu();
		//查询所有角色,（包括未关联菜单的）
		List<AdminRole> roleAll = findAll();
		
		//进行去重:
		if(roleMenuAll != null && roleMenuAll.size() > 0 && roleAll != null && roleAll.size()>0){
			ListIterator<AdminRole> rm = roleAll.listIterator();
			while(rm.hasNext()){
				boolean flag = true;
				AdminRole role = rm.next();//取出下一个
				if(role != null && role.getId() != null){
					Integer rid = role.getId();
					for(AdminRole ar : roleMenuAll){
						if(ar != null && ar.getId() != null){
							if(rid.equals(ar.getId())){
								flag = false;
								break;
							}
						}
					}
				}
				if(flag)
				roleMenuAll.add(role);
			}
		}
		
		roleAll = null;//清除无用列表;
		
		return roleMenuAll;
	}
	
	/**
	 * 修改角色信息:
	 */
	public void editRole(AdminRole adminRole){
		adminRoleMapper.editRole(adminRole);
	}

	/**
	 * 更新第三张表(角色菜单mapping)数据
	 */
	public void addAdminRoleMenuMapping(AdminRoleMenuMapping armm) {
		adminRoleMapper.addAdminRoleMenuMapping(armm);
		
	}
	
	/**
	 * 批量新增
	 */
	public void addAdminRoleMenuList(List<AdminRoleMenuMapping> armms){
		adminRoleMapper.addAdminRoleMenuList(armms);
	}
	
	
	//删除第三张表(角色菜单mapping)数据
	public void deleteAdminRoleMenuMapping(Integer roleId){
		adminRoleMapper.deleteAdminRoleMenuMapping(roleId);
	}
	
	
	/**
	 * 获取 角色的总数进行分页操作;
	 */
	public Integer getAllRecord(){
		return adminRoleMapper.getAllRecord();
	}
	
	/**
	 * 分页查询角色数据
	 */
	public List<AdminRole> getAllAdminRole(Map<String, Integer> map) {
		List<AdminRole> list = adminRoleMapper.getAllAdminRole(map);
		
		if(list != null && list.size() > 0){
			
			//为拥有菜单的角色 匹配 菜单功能;
			for(AdminRole role : list){
				List<AdminMenu> menus = adminMenuMapper.getRoleIdByMenu(role.getId());
				if(menus != null && menus.size() > 0)
				role.setMenus(menus);
			}
			
		}
		
		return list;
	}

	
	/**
	 * 根据用户 id 查询出所对应的角色列表
	 */
	public List<AdminRole> getUserIdByRole(Integer userId) {
		List<AdminRole> list = null;
		if(userId != null){
			list = adminRoleMapper.getUserIdByRole(userId);
		}
		return list;
	}
	
	
	
}
