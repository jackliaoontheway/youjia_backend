package com.ryit.entity.vo;

import java.util.Date;

import com.ryit.entity.AdstractEntity;

//退房管理
public final class CheckoutQueryVo extends AdstractEntity{

	private int id;
	private String name;//退租人
	private String rid;//房号
	private String bid;//栋号
	private String phone;//电话
	private String Identity_card;//身份证号码
	private String cause;//退租原因
	private double return_cash;//押金
	private double reality_return_cash;//实际退还押金
	private String deduct_cash_cause;//扣款原因
	private Integer state;//退房状态
	
	public CheckoutQueryVo(){
		
	}
	
	public CheckoutQueryVo(int id,String name,String rid,String bid,String phone,String Identity_card,String cause,double return_cash,double reality_return_cash,String deduct_cash_cause,Integer state){
		this.id = id;
		this.name = name;
		this.rid = rid;
		this.bid = bid;
		this.phone = phone;
		this.Identity_card = Identity_card;
		this.cause= cause;
		this.return_cash = return_cash;
		this.reality_return_cash = reality_return_cash;
		this.deduct_cash_cause = deduct_cash_cause;
		this.state = state;
	}
	
	public CheckoutQueryVo(int id,String name,String rid,String bid,String phone,String Identity_card,String cause,double return_cash,double reality_return_cash,String deduct_cash_cause,Integer state,String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		super(created_user,created_datetime,updated_user,updated_datetime);
		this.id = id;
		this.name = name;
		this.rid = rid;
		this.bid = bid;
		this.phone = phone;
		this.Identity_card = Identity_card;
		this.cause= cause;
		this.return_cash = return_cash;
		this.reality_return_cash = reality_return_cash;
		this.deduct_cash_cause = deduct_cash_cause;
		this.state = state;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentity_card() {
		return Identity_card;
	}

	public void setIdentity_card(String identity_card) {
		Identity_card = identity_card;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public double getReturn_cash() {
		return return_cash;
	}

	public void setReturn_cash(double return_cash) {
		this.return_cash = return_cash;
	}

	public double getReality_return_cash() {
		return reality_return_cash;
	}

	public void setReality_return_cash(double reality_return_cash) {
		this.reality_return_cash = reality_return_cash;
	}

	public String getDeduct_cash_cause() {
		return deduct_cash_cause;
	}

	public void setDeduct_cash_cause(String deduct_cash_cause) {
		this.deduct_cash_cause = deduct_cash_cause;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public String toString() {
		return "CheckoutQueryVo [id=" + id + ", name=" + name + ", rid=" + rid
				+ ", bid=" + bid + ", phone=" + phone + ", Identity_card="
				+ Identity_card + ", cause=" + cause + ", return_cash="
				+ return_cash + ", reality_return_cash=" + reality_return_cash
				+ ", deduct_cash_cause=" + deduct_cash_cause + ", state="
				+ state + "]";
	}


	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((Identity_card == null) ? 0 : Identity_card.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((cause == null) ? 0 : cause.hashCode());
		result = prime
				* result
				+ ((deduct_cash_cause == null) ? 0 : deduct_cash_cause
						.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reality_return_cash);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(return_cash);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckoutQueryVo other = (CheckoutQueryVo) obj;
		if (Identity_card == null) {
			if (other.Identity_card != null)
				return false;
		} else if (!Identity_card.equals(other.Identity_card))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (cause == null) {
			if (other.cause != null)
				return false;
		} else if (!cause.equals(other.cause))
			return false;
		if (deduct_cash_cause == null) {
			if (other.deduct_cash_cause != null)
				return false;
		} else if (!deduct_cash_cause.equals(other.deduct_cash_cause))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (Double.doubleToLongBits(reality_return_cash) != Double
				.doubleToLongBits(other.reality_return_cash))
			return false;
		if (Double.doubleToLongBits(return_cash) != Double
				.doubleToLongBits(other.return_cash))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
}
