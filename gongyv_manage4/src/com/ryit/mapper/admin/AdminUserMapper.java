package com.ryit.mapper.admin;

import java.util.List;
import java.util.Map;

import com.ryit.entity.admin.AdminUser;
import com.ryit.entity.admin.AdminUserRoleMapping;

/**
 * Created by 722665 on 2015/8/1.
 */
public interface AdminUserMapper {
    public AdminUser getUserByName(String name);//
    /**
     * 添加后台用户
     * @param name
     * @return
     */
    public void addAdminUser(AdminUser users);//

    List<AdminUser> getAllUser();//

    void delete(List<Integer> ids);//

    AdminUser getUserById(Integer id);//
	
    public Integer findUserIsExist(AdminUser user);//
    
    List<AdminUser> findAll();
    
    //查询 用户关联角色查询;
  	public List<AdminUser> findUserRole();
    
    //修改用户信息:
  	void editUser(AdminUser adminUser);
  	
  	//新增第三张表(角色菜单mapping)数据
  	void addAdminUserRoleMapping(AdminUserRoleMapping aurm);
  	
  	//删除第三张表(用户角色mapping)数据
  	void deleteAdminUserRoleMapping(Integer userId);
  	
	//分页查询 
	
	//分页查询数据
	public List<AdminUser> getAllAdminUser(Map<String,Integer> map);
	
	//获取 菜单的总数进行分页操作;
	public Integer getAllRecord();
    
}
