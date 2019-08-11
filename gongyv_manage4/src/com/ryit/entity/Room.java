package com.ryit.entity;

import java.util.Date;

//房间管理
public class Room extends AdstractEntity{

	private Integer id;//房间id
	private String room_num;//房号
	private Double money;//租金
	private String room_type;//房间类型
	private Double cash_pay;//押金
	private Double mgr_fee;//管理费
	private Integer state;//状态
	private Integer cash_state;//押金状态
	private String admin;//管理员
	private String bid;//栋号
	
	public Room(){
		
	}
	
	public Room(String room_num,String bid){
		this.room_num = room_num;
		this.bid = bid;
	}
	
	public Room(Integer id,String room_num,Double money,String room_type,Double cash_pay,Double mgr_fee,Integer state,Integer cash_state,String admin,String bid,String created_user,Date created_datetime,String updated_user,Date updated_datetime){
		super(created_user,created_datetime,updated_user,updated_datetime);
		this.id = id;
		this.room_num = room_num;
		this.money = money;
		this.room_type = room_type;
		this.cash_pay = cash_pay;
		this.mgr_fee = mgr_fee;
		this.state = state;
		this.cash_state = cash_state;
		this.admin = admin;
		this.bid = bid;
	}
	
	public Room(Integer id,String room_num,Double money,String room_type,Double cash_pay,Double mgr_fee,Integer state,Integer cash_state,String admin){
		this.id = id;
		this.room_num = room_num;
		this.money = money;
		this.room_type = room_type;
		this.cash_pay = cash_pay;
		this.mgr_fee = mgr_fee;
		this.state = state;
		this.cash_state = cash_state;
		this.admin = admin;
		
	}
	
	public Room(Integer id,String room_num,Double money,String room_type,Double cash_pay,Double mgr_fee,Integer state,Integer cash_state,String admin,String bid){
		this.id = id;
		this.room_num = room_num;
		this.money = money;
		this.room_type = room_type;
		this.cash_pay = cash_pay;
		this.mgr_fee = mgr_fee;
		this.state = state;
		this.cash_state = cash_state;
		this.admin = admin;
		this.bid = bid;
	}
	
	public Room(String created_user, Date created_datetime, String updated_user, Date updated_datetime, Integer id,
			String room_num, Double money, String room_type, Double cash_pay, Double mgr_fee, Integer state,Integer cash_state,
			String admin, String bid) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		this.id = id;
		this.room_num = room_num;
		this.money = money;
		this.room_type = room_type;
		this.cash_pay = cash_pay;
		this.mgr_fee = mgr_fee;
		this.state = state;
		this.cash_state = cash_state;
		this.admin = admin;
		this.bid = bid;
	}


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public Double getCash_pay() {
		return cash_pay;
	}
	public void setCash_pay(Double cash_pay) {
		this.cash_pay = cash_pay;
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
	
	
	public Integer getCash_state() {
		return cash_state;
	}

	public void setCash_state(Integer cash_state) {
		this.cash_state = cash_state;
	}

	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}

	public String toString() {
		return "Room [id=" + id + ", room_num=" + room_num + ", money=" + money
				+ ", room_type=" + room_type + ", cash_pay=" + cash_pay
				+ ", mgr_fee=" + mgr_fee + ", state=" + state + ", cash_state="
				+ cash_state + ", admin=" + admin + ", bid=" + bid + "]";
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result
				+ ((cash_pay == null) ? 0 : cash_pay.hashCode());
		result = prime * result
				+ ((cash_state == null) ? 0 : cash_state.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mgr_fee == null) ? 0 : mgr_fee.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result
				+ ((room_num == null) ? 0 : room_num.hashCode());
		result = prime * result
				+ ((room_type == null) ? 0 : room_type.hashCode());
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
		Room other = (Room) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (cash_pay == null) {
			if (other.cash_pay != null)
				return false;
		} else if (!cash_pay.equals(other.cash_pay))
			return false;
		if (cash_state == null) {
			if (other.cash_state != null)
				return false;
		} else if (!cash_state.equals(other.cash_state))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mgr_fee == null) {
			if (other.mgr_fee != null)
				return false;
		} else if (!mgr_fee.equals(other.mgr_fee))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (room_num == null) {
			if (other.room_num != null)
				return false;
		} else if (!room_num.equals(other.room_num))
			return false;
		if (room_type == null) {
			if (other.room_type != null)
				return false;
		} else if (!room_type.equals(other.room_type))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}






















