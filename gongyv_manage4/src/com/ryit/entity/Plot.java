package com.ryit.entity;

import java.util.Date;

public class Plot extends AdstractEntity{

	private int id;//小区编号
	private String pname;//小区名称
	private String address;//小区地址
	private String description;//小区的描述
	
	public Plot(){
		
	}
	
	public Plot(int id,String pname,String address,String description,String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		super(created_user,created_datetime,updated_user,updated_datetime);
		this.id = id;
		this.pname = pname;
		this.address = address;
		this.description = description;
	}
	
	public Plot(String pname,String address,String description,String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		super(created_user,created_datetime,updated_user,updated_datetime);
		this.pname = pname;
		this.address = address;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plot other = (Plot) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		return true;
	}

	
	public String toString() {
		return "Plot [id=" + id + ", pname=" + pname + ", address=" + address
				+ ", description=" + description + "]";
	}
	
	
	
	
	
}
