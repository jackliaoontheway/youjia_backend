package com.ryit.service.admin;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ryit.entity.admin.AdminRole;
import com.ryit.entity.admin.AdminUser;

@Service
public interface AdminUserService {
    public AdminUser getUserByName(String name);
    /**
     * 添加后台用户
     * @param name
     * @return
     */
    public AdminUser addAdminUser(AdminUser users);

    public List<AdminUser> getAllUser();

    public List<AdminRole> getallRole();
    
    public  void saveUserAndRole(AdminUser user, String roles);
    
    //删除用户
    void delete(String userIds);
    
    //查询所有用户
    List<AdminUser> findAll();
    
    //查询指定用户
    AdminUser getUserById(Integer id);
    
    //查询用户是否存在;
	public Integer findUserIsExist(AdminUser user);
	
	//查询 用户关联角色查询;
	public List<AdminUser> findUserRole();
	
	
	//分页查询 
	
	//分页查询数据
	public List<AdminUser> getAllAdminUser(Map<String,Integer> map);
	
	//获取 菜单的总数进行分页操作;
	public Integer getAllRecord();
}
