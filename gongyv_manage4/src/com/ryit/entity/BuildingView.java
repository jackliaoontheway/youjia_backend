package com.ryit.entity;

import java.util.List;

public class BuildingView {

	private Integer buildingId;
	private String buildingNumber;
	private String addressInfo;

	private List<RoomView> roomList;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public List<RoomView> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomView> roomList) {
		this.roomList = roomList;
	}

}
