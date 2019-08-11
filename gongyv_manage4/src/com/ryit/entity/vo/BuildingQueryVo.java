package com.ryit.entity.vo;

import java.util.Date;

public class BuildingQueryVo {

	private int id;
	private int pid;//小区编号
	private String pname;//小区名称
	private String buildingNo;//栋数
	private String description;//描述
	private String created_user;//创建人
	private Date created_datetime;//创建时间
	private String updated_user;//修改人
	private Date updated_datetime;//修改时间
	
	
	public BuildingQueryVo(){
		
	}
	
	public BuildingQueryVo(int id,int pid,String pname,String buildingNo,String description,String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		this.id = id;
		this.pname = pname;
		this.pid = pid;
		this.buildingNo = buildingNo;
		this.description = description;
		this.created_user = created_user;
		this.created_datetime = created_datetime;
		this.updated_user = updated_user;
		this.updated_datetime = updated_datetime;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public Date getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}

	public String getUpdated_user() {
		return updated_user;
	}

	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}

	public Date getUpdated_datetime() {
		return updated_datetime;
	}

	public void setUpdated_datetime(Date updated_datetime) {
		this.updated_datetime = updated_datetime;
	}

	
	public String toString() {
		return "BuildingQueryVo [id=" + id + ", pid=" + pid + ", pname="
				+ pname + ", buildingNo=" + buildingNo + ", description="
				+ description + ", created_user=" + created_user
				+ ", created_datetime=" + created_datetime + ", updated_user="
				+ updated_user + ", updated_datetime=" + updated_datetime + "]";
	}


	

	
	

	
	

	
	
}
