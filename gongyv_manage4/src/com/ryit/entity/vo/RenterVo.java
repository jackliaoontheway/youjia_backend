package com.ryit.entity.vo;

import com.ryit.entity.Renter;

public class RenterVo extends Renter{
	private Integer currentPage;
	private Integer pageSize;
	private String bname;
	private String rname;
	private String pname;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((currentPage == null) ? 0 : currentPage.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((rname == null) ? 0 : rname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RenterVo other = (RenterVo) obj;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (currentPage == null) {
			if (other.currentPage != null)
				return false;
		} else if (!currentPage.equals(other.currentPage))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (rname == null) {
			if (other.rname != null)
				return false;
		} else if (!rname.equals(other.rname))
			return false;
		return true;
	}

	
	public RenterVo(Integer id, String user_name, String password, String name, Integer bid, Integer rid, String phone,
			String identity_card, String ide_card_pic_z, String ide_card_pic_f, String self_pic, Integer state,
			Integer r_state, Integer currentPage, Integer pageSize, String bname, String rname, String pname) {
		super(id, user_name, password, name, bid, rid, phone, identity_card, ide_card_pic_z, ide_card_pic_f, self_pic,
				state, r_state);
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.bname = bname;
		this.rname = rname;
		this.pname = pname;
	}
	@Override
	public String toString() {
		return "RenterVo [currentPage=" + currentPage + ", pageSize=" + pageSize + ", bname=" + bname + ", rname="
				+ rname + ", pname=" + pname + ", getId()=" + getId() + ", getUser_name()=" + getUser_name()
				+ ", getPassword()=" + getPassword() + ", getName()=" + getName() + ", getBid()=" + getBid()
				+ ", getRid()=" + getRid() + ", getPhone()=" + getPhone() + ", getIdentity_card()=" + getIdentity_card()
				+ ", getIde_card_pic_z()=" + getIde_card_pic_z() + ", getIde_card_pic_f()=" + getIde_card_pic_f()
				+ ", getSelf_pic()=" + getSelf_pic() + ", getState()=" + getState() + ", getR_state()=" + getR_state()
				+ ", toString()=" + super.toString() + ", getCreated_user()=" + getCreated_user()
				+ ", getCreated_datetime()=" + getCreated_datetime() + ", getUpdated_user()=" + getUpdated_user()
				+ ", getUpdated_datetime()=" + getUpdated_datetime() + ", getClass()=" + getClass() + "]";
	}
	public RenterVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
