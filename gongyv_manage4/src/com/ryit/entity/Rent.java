package com.ryit.entity;

import java.util.Date;

//租金登记
public class Rent extends AdstractEntity{

	private Integer id;//租金编号
	private Integer room_num;//房号编号
	private Integer bid;//栋数编号
	private Integer pid;//小区编号
	private String user;//用户名
	private String identity_card;//身份证
	private Double cold_water;//冷水（用量）
	private Double last_cold_water;//上月冷水费
	private Double hot_water;//热水（用量）
	private Double last_hot_water;//上月热水费
	private Double electric_fee;//电（用量）
	private Double last_electric_fee;//上月电费
	private Double net_fee;//网费
	private Double mgr_fee;//管理费
	private Integer state;//状态
	private Double rent_rental;//房租总额
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoom_num() {
		return room_num;
	}
	public void setRoom_num(Integer room_num) {
		this.room_num = room_num;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public Double getCold_water() {
		return cold_water;
	}
	public void setCold_water(Double cold_water) {
		this.cold_water = cold_water;
	}
	public Double getLast_cold_water() {
		return last_cold_water;
	}
	public void setLast_cold_water(Double last_cold_water) {
		this.last_cold_water = last_cold_water;
	}
	public Double getHot_water() {
		return hot_water;
	}
	public void setHot_water(Double hot_water) {
		this.hot_water = hot_water;
	}
	public Double getLast_hot_water() {
		return last_hot_water;
	}
	public void setLast_hot_water(Double last_hot_water) {
		this.last_hot_water = last_hot_water;
	}
	public Double getElectric_fee() {
		return electric_fee;
	}
	public void setElectric_fee(Double electric_fee) {
		this.electric_fee = electric_fee;
	}
	public Double getLast_electric_fee() {
		return last_electric_fee;
	}
	public void setLast_electric_fee(Double last_electric_fee) {
		this.last_electric_fee = last_electric_fee;
	}
	public Double getNet_fee() {
		return net_fee;
	}
	public void setNet_fee(Double net_fee) {
		this.net_fee = net_fee;
	}
	public Double getMgr_fee() {
		return mgr_fee;
	}
	public void setMgr_fee(Double mgr_fee) {
		this.mgr_fee = mgr_fee;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getRent_rental() {
		return rent_rental;
	}
	public void setRent_rental(Double rent_rental) {
		this.rent_rental = rent_rental;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((cold_water == null) ? 0 : cold_water.hashCode());
		result = prime * result + ((electric_fee == null) ? 0 : electric_fee.hashCode());
		result = prime * result + ((hot_water == null) ? 0 : hot_water.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identity_card == null) ? 0 : identity_card.hashCode());
		result = prime * result + ((last_cold_water == null) ? 0 : last_cold_water.hashCode());
		result = prime * result + ((last_electric_fee == null) ? 0 : last_electric_fee.hashCode());
		result = prime * result + ((last_hot_water == null) ? 0 : last_hot_water.hashCode());
		result = prime * result + ((mgr_fee == null) ? 0 : mgr_fee.hashCode());
		result = prime * result + ((net_fee == null) ? 0 : net_fee.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((rent_rental == null) ? 0 : rent_rental.hashCode());
		result = prime * result + ((room_num == null) ? 0 : room_num.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Rent other = (Rent) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (cold_water == null) {
			if (other.cold_water != null)
				return false;
		} else if (!cold_water.equals(other.cold_water))
			return false;
		if (electric_fee == null) {
			if (other.electric_fee != null)
				return false;
		} else if (!electric_fee.equals(other.electric_fee))
			return false;
		if (hot_water == null) {
			if (other.hot_water != null)
				return false;
		} else if (!hot_water.equals(other.hot_water))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identity_card == null) {
			if (other.identity_card != null)
				return false;
		} else if (!identity_card.equals(other.identity_card))
			return false;
		if (last_cold_water == null) {
			if (other.last_cold_water != null)
				return false;
		} else if (!last_cold_water.equals(other.last_cold_water))
			return false;
		if (last_electric_fee == null) {
			if (other.last_electric_fee != null)
				return false;
		} else if (!last_electric_fee.equals(other.last_electric_fee))
			return false;
		if (last_hot_water == null) {
			if (other.last_hot_water != null)
				return false;
		} else if (!last_hot_water.equals(other.last_hot_water))
			return false;
		if (mgr_fee == null) {
			if (other.mgr_fee != null)
				return false;
		} else if (!mgr_fee.equals(other.mgr_fee))
			return false;
		if (net_fee == null) {
			if (other.net_fee != null)
				return false;
		} else if (!net_fee.equals(other.net_fee))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (rent_rental == null) {
			if (other.rent_rental != null)
				return false;
		} else if (!rent_rental.equals(other.rent_rental))
			return false;
		if (room_num == null) {
			if (other.room_num != null)
				return false;
		} else if (!room_num.equals(other.room_num))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Rent [id=" + id + ", room_num=" + room_num + ", bid=" + bid + ", pid=" + pid + ", user=" + user
				+ ", identity_card=" + identity_card + ", cold_water=" + cold_water + ", last_cold_water="
				+ last_cold_water + ", hot_water=" + hot_water + ", last_hot_water=" + last_hot_water
				+ ", electric_fee=" + electric_fee + ", last_electric_fee=" + last_electric_fee + ", net_fee=" + net_fee
				+ ", mgr_fee=" + mgr_fee + ", state=" + state + ", rent_rental=" + rent_rental + "]";
	}
	public Rent(String created_user, Date created_datetime, String updated_user, Date updated_datetime, Integer id,
			Integer room_num, Integer bid, Integer pid, String user, String identity_card, Double cold_water,
			Double last_cold_water, Double hot_water, Double last_hot_water, Double electric_fee,
			Double last_electric_fee, Double net_fee, Double mgr_fee, Integer state, Double rent_rental) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		this.id = id;
		this.room_num = room_num;
		this.bid = bid;
		this.pid = pid;
		this.user = user;
		this.identity_card = identity_card;
		this.cold_water = cold_water;
		this.last_cold_water = last_cold_water;
		this.hot_water = hot_water;
		this.last_hot_water = last_hot_water;
		this.electric_fee = electric_fee;
		this.last_electric_fee = last_electric_fee;
		this.net_fee = net_fee;
		this.mgr_fee = mgr_fee;
		this.state = state;
		this.rent_rental = rent_rental;
	}
	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
