package com.ryit.entity;

import java.util.Date;

//租户管理
public class Renter extends AdstractEntity{
	private Integer id;//租户编号
	private String user_name;//用户名
	private String password;//密码
	private String name;//真实姓名
	private Integer bid;//栋数编号
	private Integer rid;//房号编号
	private Integer pid;//小区编号
	private String phone;//联系电话
	private String identity_card;//身份证号码
	private String ide_card_pic_z;//身份证照片正面
	private String ide_card_pic_f;//身份证照片反面
	private String self_pic;//本人照片
	private Integer state;//状态
	private Integer r_state;//租房状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public String getIde_card_pic_z() {
		return ide_card_pic_z;
	}
	public void setIde_card_pic_z(String ide_card_pic_z) {
		this.ide_card_pic_z = ide_card_pic_z;
	}
	public String getIde_card_pic_f() {
		return ide_card_pic_f;
	}
	public void setIde_card_pic_f(String ide_card_pic_f) {
		this.ide_card_pic_f = ide_card_pic_f;
	}
	public String getSelf_pic() {
		return self_pic;
	}
	public void setSelf_pic(String self_pic) {
		this.self_pic = self_pic;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getR_state() {
		return r_state;
	}
	public void setR_state(Integer r_state) {
		this.r_state = r_state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ide_card_pic_f == null) ? 0 : ide_card_pic_f.hashCode());
		result = prime * result + ((ide_card_pic_z == null) ? 0 : ide_card_pic_z.hashCode());
		result = prime * result + ((identity_card == null) ? 0 : identity_card.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((r_state == null) ? 0 : r_state.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((self_pic == null) ? 0 : self_pic.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		Renter other = (Renter) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ide_card_pic_f == null) {
			if (other.ide_card_pic_f != null)
				return false;
		} else if (!ide_card_pic_f.equals(other.ide_card_pic_f))
			return false;
		if (ide_card_pic_z == null) {
			if (other.ide_card_pic_z != null)
				return false;
		} else if (!ide_card_pic_z.equals(other.ide_card_pic_z))
			return false;
		if (identity_card == null) {
			if (other.identity_card != null)
				return false;
		} else if (!identity_card.equals(other.identity_card))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (r_state == null) {
			if (other.r_state != null)
				return false;
		} else if (!r_state.equals(other.r_state))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (self_pic == null) {
			if (other.self_pic != null)
				return false;
		} else if (!self_pic.equals(other.self_pic))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Renter [id=" + id + ", user_name=" + user_name + ", password=" + password + ", name=" + name + ", bid="
				+ bid + ", rid=" + rid + ", pid=" + pid + ", phone=" + phone + ", identity_card=" + identity_card
				+ ", ide_card_pic_z=" + ide_card_pic_z + ", ide_card_pic_f=" + ide_card_pic_f + ", self_pic=" + self_pic
				+ ", state=" + state + ", r_state=" + r_state + "]";
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Renter(Integer id, String user_name, String password, String name, Integer bid, Integer rid, String phone,
			String identity_card, String ide_card_pic_z, String ide_card_pic_f, String self_pic, Integer state,
			Integer r_state) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.name = name;
		this.bid = bid;
		this.rid = rid;
		this.phone = phone;
		this.identity_card = identity_card;
		this.ide_card_pic_z = ide_card_pic_z;
		this.ide_card_pic_f = ide_card_pic_f;
		this.self_pic = self_pic;
		this.state = state;
		this.r_state = r_state;
	}
	public Renter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
