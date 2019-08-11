package com.ryit.entity.admin;

import java.util.Date;

//管理员菜单表
public class AdminMenu implements Comparable<AdminMenu>{

	private Integer id;

    private String name;//菜单名

    private String url;//前端页面 url;

    private Integer pid = 0;//父id;

    private String remark;//标记角色

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String creater;//创建人

    private String updater;//修改人

    public AdminMenu() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    @Override
    public int compareTo(AdminMenu o) {
        return this.getId()-o.getId();
    }

	public String toString() {
		return "AdminMenu [id=" + id + ", name=" + name + ", url=" + url
				+ ", pid=" + pid + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", creater="
				+ creater + ", updater=" + updater + "]";
	}
    
    
    
    
}
