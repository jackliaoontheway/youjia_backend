package com.ryit.service.admin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminUser;
import com.ryit.entity.admin.AdminUserRoleMapping;
import com.ryit.mapper.admin.AdminRoleMapper;
import com.ryit.mapper.admin.AdminUserMapper;
import com.ryit.service.admin.AdminUserService;

/**
 * Created by 722665 on 2015/8/1.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
	//注入用户管理后台业务DAO层
    @Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;


    @Override
    public AdminUser getUserByName(String name) {
        return adminUserMapper.getUserByName(name);
    }

	@Override
	public AdminUser addAdminUser(AdminUser users) {
		AdminUser user = null;
		if(null != users){
			adminUserMapper.addAdminUser(users);
			user = adminUserMapper.getUserById(users.getId());
			
		}
		return user;
	}

	@Override
	public List<AdminUser> getAllUser() {
		return adminUserMapper.getAllUser();
	}

	@Override
	public List<AdminRole> getallRole() {
		return adminRoleMapper.findAll();
	}
	
	/**
	 * 保存 和 修改
	 */
	@Override
	public void saveUserAndRole(AdminUser user, String roles) {
		if(user != null && roles != null){
			AdminUser userByName = adminUserMapper.getUserByName(user.getLoginName());
			String[] split = roles.split(",");
			List<Integer> ids=new ArrayList<>();
			for (String roleId : split) {
				if((!"".equals(roleId.trim())) && roleId.matches("^[0-9]*$")){
					Integer integer = Integer.valueOf(roleId);
					ids.add(integer);
				}
			}
			List<AdminRole> adminRoles=adminRoleMapper.getRoleByIds(ids);
			
			if(userByName == null){
				//添加新 用户
				adminUserMapper.addAdminUser(user);
				
				if(adminRoles != null && adminRoles.size() > 0){
					//获取新用户id
					int t_admin_user_id = user.getId();
					
					for(AdminRole ar : adminRoles){
						//添加 第三张表 (用户角色mapping)表数据;
						adminUserMapper.addAdminUserRoleMapping(new AdminUserRoleMapping(t_admin_user_id,ar.getId()));
					}
					
					
				}
			}else{//进行修改操作
				adminUserMapper.editUser(user);
				
				//删除第三张表 用户的角色数据;
				adminUserMapper.deleteAdminUserRoleMapping(user.getId());
				
				Integer t_admin_user_id = user.getId();
				
				for(AdminRole ar : adminRoles){
					//添加 第三张表 (用户角色mapping)表数据;
					adminUserMapper.addAdminUserRoleMapping(new AdminUserRoleMapping(t_admin_user_id,ar.getId()));
				}
				
			}
		}
	}

	@Override
	public void delete(String userIds) {
		String[] split = userIds.split(",");
		List<Integer> ids=new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String userId = split[i];
			if(!"".equals(userId.trim()) && userId.matches("^[0-9]*$")){
				Integer id = Integer.valueOf(userId);
				ids.add(id);
				adminUserMapper.deleteAdminUserRoleMapping(id);
			}
		}
		adminUserMapper.delete(ids);
	}

	@Override
	public AdminUser getUserById(Integer id) {
		return adminUserMapper.getUserById(id);
	}

	@Override
	public Integer findUserIsExist(AdminUser user) {
		return adminUserMapper.findUserIsExist(user);
	}
	
	
	public List<AdminUser> findAll(){
		return adminUserMapper.findAll();
	}
	
	/**
	 * 查询 用户关联角色表
	 */
	public List<AdminUser> findUserRole(){
		//查询用户关联菜单
		List<AdminUser> userRoleAll = adminUserMapper.findUserRole();
		//查询所有用户,（包括未关联角色的）
		List<AdminUser> userAll = findAll();
		
		//进行去重:
		if(userRoleAll != null && userRoleAll.size() > 0 && userAll != null && userAll.size()>0){
			ListIterator<AdminUser> ua = userAll.listIterator();
			while(ua.hasNext()){
				boolean flag = true;
				AdminUser user = ua.next();
				if(user != null && user.getId() != null){
					Integer rid = user.getId();
					for(AdminUser ar : userRoleAll){
						if(ar != null && ar.getId() != null){
							if(rid.equals(ar.getId())){
								flag = false;
								break;
							}
						}
					}
				}
				if(flag)
				userRoleAll.add(user);
			}
		}
		
		userAll = null;//清除无用列表;
		
		return userRoleAll;
		
	}
	
	/**
	 * 分页查询用户的数据
	 */
	public List<AdminUser> getAllAdminUser(Map<String, Integer> map) {
		
		List<AdminUser> list = adminUserMapper.getAllAdminUser(map);
		
		if(list != null && list.size() > 0){
			
			//为拥有菜单的角色 匹配 菜单功能;
			for(AdminUser user : list){
				List<AdminRole> roles = adminRoleMapper.getUserIdByRole(user.getId());
				if(roles != null && roles.size() > 0)
					user.setRoles(roles);
			}
			
		}
		
		return list;
		
	}
	
	/**
	 * 获取 用户的总数进行分页操作;
	 */
	public Integer getAllRecord() {
		return adminUserMapper.getAllRecord();
	}
	
}
