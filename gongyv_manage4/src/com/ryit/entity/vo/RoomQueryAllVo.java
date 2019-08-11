package com.ryit.entity.vo;

import java.util.Date;


public class RoomQueryAllVo{
	private Integer id;//房间id
	private String bid;//栋号编号
	private Integer pid;//小区编号
	private String pname;//小区名称
	private String room_num;//房号
	private Double money;//租金
	private String room_type;//房间类型
	private Double cash_pay;//押金
	private Double mgr_fee;//管理费
	private Integer state;//状态
	private Integer cash_state;//押金状态
	private String admin;//管理员
	private String buildingNo;//栋号名称
	public String created_user;
	public Date created_datetime;
	public String updated_user;
	public Date updated_datetime;
	
	public RoomQueryAllVo(){
		
		
	}
	
	
	public RoomQueryAllVo(Integer pid,String pname,Integer id,String room_num,Double money,
			String room_type,Double cash_pay,Double mgr_fee,Integer state,Integer cash_state,
			String admin,String bid,String buildingNo,String created_user,Date created_datetime,
			String updated_user,Date updated_datetime){
		this.pid = pid;
		this.pname = pname;
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
		this.created_user = created_user;
		this.created_datetime = created_datetime;
		this.updated_user = updated_user;
		this.updated_datetime = updated_datetime;
		
	}


	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
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
		return "RoomQueryAllVo [id=" + id + ", bid=" + bid + ", pid=" + pid
				+ ", pname=" + pname + ", room_num=" + room_num + ", money="
				+ money + ", room_type=" + room_type + ", cash_pay=" + cash_pay
				+ ", mgr_fee=" + mgr_fee + ", state=" + state + ", cash_state="
				+ cash_state + ", admin=" + admin + ", buildingNo="
				+ buildingNo + ", created_user=" + created_user
				+ ", created_datetime=" + created_datetime + ", updated_user="
				+ updated_user + ", updated_datetime=" + updated_datetime + "]";
	}


	


	
	


	


	

	

	
	


	
	
	
}
