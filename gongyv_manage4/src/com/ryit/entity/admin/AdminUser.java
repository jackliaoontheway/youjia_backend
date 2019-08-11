package com.ryit.entity.admin;

import java.util.Date;
import java.util.List;

//后台用户表
public class AdminUser {
    private Integer id;

    //后台登录用户名
    private String loginName;

    //后台用户名
    private String name;
    
    //创建时间
    private Date createTime;

    //是否有效：1无效, 2 有效
    private boolean status = true;

    //角色
    private List<AdminRole> roles;


    public AdminUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }

	public String toString() {
		return "AdminUser [id=" + id + ", loginName=" + loginName + ", name="
				+ name + ", createTime=" + createTime + ", status=" + status
				+ ", roles=" + roles + "]";
	}

	
    
    
}
