package com.ryit.entity;

import java.util.Date;

//房屋栋数
public class Building extends AdstractEntity{
	private Integer id;//栋数id
	private Integer pid;//小区编号
	private String buildingNo;//栋数
	private String description;//描述
	
	public Building() {
		
	}
	
	public Building(Integer pid,String buildingNo) {
		this.pid = pid;
		this.buildingNo = buildingNo;
	}
	
	public Building(String created_user, Date created_datetime, String updated_user, Date updated_datetime, Integer id,
			Integer pid,String buildingNo, String description) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		this.id = id;
		this.pid = pid;
		this.buildingNo = buildingNo;
		this.description = description;
	}
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	
	public String toString() {
		return "Building [id=" + id + ", pid=" + pid + ", buildingNo="
				+ buildingNo + ", description=" + description + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((buildingNo == null) ? 0 : buildingNo.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Building other = (Building) obj;
		if (buildingNo == null) {
			if (other.buildingNo != null)
				return false;
		} else if (!buildingNo.equals(other.buildingNo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
      