package com.ryit.entity.vo;

public class RoomAdd {

	private Integer id;
	private String room_num;//房号
	private Double money;//租金
	private String room_type;//房间类型
	private Double cash_pay;//押金
	private Double mgr_fee;//管理费
	private Integer state;//状态
	private Integer cash_state;//押金状态
	private String admin;//管理员
	private String buildingNo;//栋号
	
	
	public RoomAdd(){
		
	}
	
	public RoomAdd(Integer id,String room_num,Double money,String room_type,Double cash_pay,Double mgr_fee,Integer state,Integer cash_state,String admin){
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

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	
	public String toString() {
		return "RoomAdd [id=" + id + ", room_num=" + room_num + ", money="
				+ money + ", room_type=" + room_type + ", cash_pay=" + cash_pay
				+ ", mgr_fee=" + mgr_fee + ", state=" + state + ", cash_state="
				+ cash_state + ", admin=" + admin + ", buildingNo="
				+ buildingNo + "]";
	}

	


	
	
	

	
	
}
