package com.ryit.entity.vo;

import java.util.Date;

import com.ryit.entity.Rent;

public class RentVo extends Rent{
	private Double ele_unit_price;//电费单价
	private Double cold_unit_price;//冷水单价
	private Double hot_unit_price;//热水单价
	private Double cold_water_fe;//冷水费用
	private Double last_cold_water_fe;//上月冷水费用
	private Double hot_water_fe;//热水费
	private Double last_hot_water_fe;//上月热水费
	private Double electric_fee_fe;//电费
	private Double last_electric_fee_fe;//上月电费
	private Double use_cold_water;
	private Double use_hot_water;
	private Double use_electric;
	private String pname;
	private String bname;
	private String rname;
	private Double sum;
	
	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public RentVo(Double ele_unit_price,Double cold_unit_price,Double hot_unit_price) {
		this.ele_unit_price = ele_unit_price;
		this.cold_unit_price = cold_unit_price;
		this.hot_unit_price = hot_unit_price;
	}
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public void setRent(Rent rent) {
		super.setId(rent.getId());
		super.setRoom_num(rent.getRoom_num());
		super.setBid(rent.getBid());
		super.setPid(rent.getPid());
		super.setCold_water(rent.getCold_water());
		super.setLast_cold_water(rent.getLast_cold_water());
		super.setHot_water(rent.getHot_water());
		super.setLast_hot_water(rent.getLast_hot_water());
		super.setElectric_fee(rent.getElectric_fee());
		super.setLast_electric_fee(rent.getLast_electric_fee());
		super.setNet_fee(rent.getNet_fee());
		super.setState(rent.getState());
		super.setRent_rental(rent.getRent_rental());
		super.setCreated_user(rent.getCreated_user());
		super.setCreated_datetime(rent.getCreated_datetime());
		super.setUpdated_user(rent.getUpdated_user());
		super.setUpdated_datetime(rent.getUpdated_datetime());
		super.setUser(rent.getUser());
		super.setIdentity_card(rent.getIdentity_card());
		super.setMgr_fee(rent.getMgr_fee());
	}
	public Double getEle_unit_price() {
		return ele_unit_price;
	}
	public void setEle_unit_price(Double ele_unit_price) {
		this.ele_unit_price = ele_unit_price;
	}
	public Double getCold_unit_price() {
		return cold_unit_price;
	}
	public void setCold_unit_price(Double cold_unit_price) {
		this.cold_unit_price = cold_unit_price;
	}
	public Double getHot_unit_price() {
		return hot_unit_price;
	}
	public void setHot_unit_price(Double hot_unit_price) {
		this.hot_unit_price = hot_unit_price;
	}
	public Double getCold_water_fe() {
		return cold_water_fe;
	}
	public void setCold_water_fe(Double cold_water_fe) {
		this.cold_water_fe = cold_water_fe;
	}
	public Double getLast_cold_water_fe() {
		return last_cold_water_fe;
	}
	public void setLast_cold_water_fe(Double last_cold_water_fe) {
		this.last_cold_water_fe = last_cold_water_fe;
	}
	public Double getHot_water_fe() {
		return hot_water_fe;
	}
	public void setHot_water_fe(Double hot_water_fe) {
		this.hot_water_fe = hot_water_fe;
	}
	public Double getLast_hot_water_fe() {
		return last_hot_water_fe;
	}
	public void setLast_hot_water_fe(Double last_hot_water_fe) {
		this.last_hot_water_fe = last_hot_water_fe;
	}
	public Double getElectric_fee_fe() {
		return electric_fee_fe;
	}
	public void setElectric_fee_fe(Double electric_fee_fe) {
		this.electric_fee_fe = electric_fee_fe;
	}
	public Double getLast_electric_fee_fe() {
		return last_electric_fee_fe;
	}
	public void setLast_electric_fee_fe(Double last_electric_fee_fe) {
		this.last_electric_fee_fe = last_electric_fee_fe;
	}
	public Double getUse_cold_water() {
		return use_cold_water;
	}
	public void setUse_cold_water(Double use_cold_water) {
		this.use_cold_water = use_cold_water;
	}
	public Double getUse_hot_water() {
		return use_hot_water;
	}
	public void setUse_hot_water(Double use_hot_water) {
		this.use_hot_water = use_hot_water;
	}
	public Double getUse_electric() {
		return use_electric;
	}
	public void setUse_electric(Double use_electric) {
		this.use_electric = use_electric;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cold_unit_price == null) ? 0 : cold_unit_price.hashCode());
		result = prime * result + ((cold_water_fe == null) ? 0 : cold_water_fe.hashCode());
		result = prime * result + ((ele_unit_price == null) ? 0 : ele_unit_price.hashCode());
		result = prime * result + ((electric_fee_fe == null) ? 0 : electric_fee_fe.hashCode());
		result = prime * result + ((hot_unit_price == null) ? 0 : hot_unit_price.hashCode());
		result = prime * result + ((hot_water_fe == null) ? 0 : hot_water_fe.hashCode());
		result = prime * result + ((last_cold_water_fe == null) ? 0 : last_cold_water_fe.hashCode());
		result = prime * result + ((last_electric_fee_fe == null) ? 0 : last_electric_fee_fe.hashCode());
		result = prime * result + ((last_hot_water_fe == null) ? 0 : last_hot_water_fe.hashCode());
		result = prime * result + ((use_cold_water == null) ? 0 : use_cold_water.hashCode());
		result = prime * result + ((use_electric == null) ? 0 : use_electric.hashCode());
		result = prime * result + ((use_hot_water == null) ? 0 : use_hot_water.hashCode());
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
		RentVo other = (RentVo) obj;
		if (cold_unit_price == null) {
			if (other.cold_unit_price != null)
				return false;
		} else if (!cold_unit_price.equals(other.cold_unit_price))
			return false;
		if (cold_water_fe == null) {
			if (other.cold_water_fe != null)
				return false;
		} else if (!cold_water_fe.equals(other.cold_water_fe))
			return false;
		if (ele_unit_price == null) {
			if (other.ele_unit_price != null)
				return false;
		} else if (!ele_unit_price.equals(other.ele_unit_price))
			return false;
		if (electric_fee_fe == null) {
			if (other.electric_fee_fe != null)
				return false;
		} else if (!electric_fee_fe.equals(other.electric_fee_fe))
			return false;
		if (hot_unit_price == null) {
			if (other.hot_unit_price != null)
				return false;
		} else if (!hot_unit_price.equals(other.hot_unit_price))
			return false;
		if (hot_water_fe == null) {
			if (other.hot_water_fe != null)
				return false;
		} else if (!hot_water_fe.equals(other.hot_water_fe))
			return false;
		if (last_cold_water_fe == null) {
			if (other.last_cold_water_fe != null)
				return false;
		} else if (!last_cold_water_fe.equals(other.last_cold_water_fe))
			return false;
		if (last_electric_fee_fe == null) {
			if (other.last_electric_fee_fe != null)
				return false;
		} else if (!last_electric_fee_fe.equals(other.last_electric_fee_fe))
			return false;
		if (last_hot_water_fe == null) {
			if (other.last_hot_water_fe != null)
				return false;
		} else if (!last_hot_water_fe.equals(other.last_hot_water_fe))
			return false;
		if (use_cold_water == null) {
			if (other.use_cold_water != null)
				return false;
		} else if (!use_cold_water.equals(other.use_cold_water))
			return false;
		if (use_electric == null) {
			if (other.use_electric != null)
				return false;
		} else if (!use_electric.equals(other.use_electric))
			return false;
		if (use_hot_water == null) {
			if (other.use_hot_water != null)
				return false;
		} else if (!use_hot_water.equals(other.use_hot_water))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RentVo [ele_unit_price=" + ele_unit_price + ", cold_unit_price=" + cold_unit_price + ", hot_unit_price="
				+ hot_unit_price + ", cold_water_fe=" + cold_water_fe + ", last_cold_water_fe=" + last_cold_water_fe
				+ ", hot_water_fe=" + hot_water_fe + ", last_hot_water_fe=" + last_hot_water_fe + ", electric_fee_fe="
				+ electric_fee_fe + ", last_electric_fee_fe=" + last_electric_fee_fe + ", use_cold_water="
				+ use_cold_water + ", use_hot_water=" + use_hot_water + ", use_electric=" + use_electric + ", pname="
				+ pname + ", bname=" + bname + ", rname=" + rname + ", getId()=" + getId() + ", getRoom_num()="
				+ getRoom_num() + ", getBid()=" + getBid() + ", getPid()=" + getPid() + ", getUser()=" + getUser()
				+ ", getIdentity_card()=" + getIdentity_card() + ", getCold_water()=" + getCold_water()
				+ ", getLast_cold_water()=" + getLast_cold_water() + ", getHot_water()=" + getHot_water()
				+ ", getLast_hot_water()=" + getLast_hot_water() + ", getElectric_fee()=" + getElectric_fee()
				+ ", getLast_electric_fee()=" + getLast_electric_fee() + ", getNet_fee()=" + getNet_fee()
				+ ", getMgr_fee()=" + getMgr_fee() + ", getState()=" + getState() + ", getRent_rental()="
				+ getRent_rental() + ", toString()=" + super.toString() + ", getCreated_user()=" + getCreated_user()
				+ ", getCreated_datetime()=" + getCreated_datetime() + ", getUpdated_user()=" + getUpdated_user()
				+ ", getUpdated_datetime()=" + getUpdated_datetime() + ", getClass()=" + getClass() + "]";
	}

	public RentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
