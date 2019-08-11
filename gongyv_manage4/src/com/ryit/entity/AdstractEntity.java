package com.ryit.entity;

import java.util.Date;

public abstract class AdstractEntity {

	private String created_user;//创建人
	private Date created_datetime;//创建时间
	private String updated_user;//修改人
	private Date updated_datetime;//修改时间
	
	public AdstractEntity(){
		
	}
	
	public AdstractEntity(String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		this.created_user = created_user;
		this.created_datetime = created_datetime;
		this.updated_user = updated_user;
		this.updated_datetime = updated_datetime;
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
		return "AdstractEntity [created_user=" + created_user
				+ ", created_datetime=" + created_datetime + ", updated_user="
				+ updated_user + ", updated_datetime=" + updated_datetime + "]";
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((created_datetime == null) ? 0 : created_datetime.hashCode());
		result = prime * result
				+ ((created_user == null) ? 0 : created_user.hashCode());
		result = prime
				* result
				+ ((updated_datetime == null) ? 0 : updated_datetime.hashCode());
		result = prime * result
				+ ((updated_user == null) ? 0 : updated_user.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdstractEntity other = (AdstractEntity) obj;
		if (created_datetime == null) {
			if (other.created_datetime != null)
				return false;
		} else if (!created_datetime.equals(other.created_datetime))
			return false;
		if (created_user == null) {
			if (other.created_user != null)
				return false;
		} else if (!created_user.equals(other.created_user))
			return false;
		if (updated_datetime == null) {
			if (other.updated_datetime != null)
				return false;
		} else if (!updated_datetime.equals(other.updated_datetime))
			return false;
		if (updated_user == null) {
			if (other.updated_user != null)
				return false;
		} else if (!updated_user.equals(other.updated_user))
			return false;
		return true;
	}

	
	
	
}
