package com.ryit.entity.vo;

public class RoomBuildingQuery {

	private Integer bid;
	private String buildingNo;
	
	public RoomBuildingQuery(){
		
	}
	
	public RoomBuildingQuery(Integer bid,String buildingNo){
		this.bid = bid;
		this.buildingNo = buildingNo;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	
	public String toString() {
		return "RoomBuildingQuery [bid=" + bid + ", buildingNo=" + buildingNo
				+ "]";
	}
	
	
}
