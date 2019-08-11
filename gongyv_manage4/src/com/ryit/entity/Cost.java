package com.ryit.entity;

import java.util.Date;

//费用单价
public class Cost extends AdstractEntity{
	private Integer id;//费用编号
	private Double ele_unit_price;//电费单价
	private Double cold_unit_price;//冷水单价
	private Double hot_unit_price;//热水单价
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cold_unit_price == null) ? 0 : cold_unit_price.hashCode());
		result = prime * result + ((ele_unit_price == null) ? 0 : ele_unit_price.hashCode());
		result = prime * result + ((hot_unit_price == null) ? 0 : hot_unit_price.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Cost other = (Cost) obj;
		if (cold_unit_price == null) {
			if (other.cold_unit_price != null)
				return false;
		} else if (!cold_unit_price.equals(other.cold_unit_price))
			return false;
		if (ele_unit_price == null) {
			if (other.ele_unit_price != null)
				return false;
		} else if (!ele_unit_price.equals(other.ele_unit_price))
			return false;
		if (hot_unit_price == null) {
			if (other.hot_unit_price != null)
				return false;
		} else if (!hot_unit_price.equals(other.hot_unit_price))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cost [id=" + id + ", ele_unit_price=" + ele_unit_price + ", cold_unit_price=" + cold_unit_price
				+ ", hot_unit_price=" + hot_unit_price + "]";
	}
	public Cost(String created_user, Date created_datetime, String updated_user, Date updated_datetime, Integer id,
			Double ele_unit_price, Double cold_unit_price, Double hot_unit_price) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		this.id = id;
		this.ele_unit_price = ele_unit_price;
		this.cold_unit_price = cold_unit_price;
		this.hot_unit_price = hot_unit_price;
	}
	public Cost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cost(String created_user, Date created_datetime, String updated_user, Date updated_datetime) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		// TODO Auto-generated constructor stub
	}
	public Cost(Double ele_unit_price, Double cold_unit_price, Double hot_unit_price) {
		super();
		this.ele_unit_price = ele_unit_price;
		this.cold_unit_price = cold_unit_price;
		this.hot_unit_price = hot_unit_price;
	}
	
	
	
	
	
}
