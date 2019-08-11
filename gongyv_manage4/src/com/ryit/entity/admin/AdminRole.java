package com.ryit.entity.admin;

import java.util.Date;
import java.util.List;

//后台角色表
public class AdminRole {
    private Integer id;

    private String name;//角色名

    private String remark;//标记角色

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String creater;//创建人

    private String updater;//修改人
    
    //菜单
    private List<AdminMenu> menus;

    public AdminRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }

	public String toString() {
		return "AdminRole [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", creater=" + creater + ", updater=" + updater + ", menus="
				+ menus + "]";
	}
    
}
