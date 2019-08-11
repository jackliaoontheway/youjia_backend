package com.ryit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Room;
import com.ryit.mapper.RoomMapper;
import com.ryit.mapper.StateMapper;
import com.ryit.service.StateService;
@Service
public class StateServiceImp implements StateService{
	
	@Autowired
	private StateMapper stateMapper;
	
	/**
	 * 该方法用来获取小区
	 * @return  Building
	 */
	public List<Plot> selectPlotState() {
		return stateMapper.selectPlotState();
	}
	/**
	 * 该方法用来获取栋数的栋号
	 * @param Integer id
	 * @return  Building
	 */
	public List<Building> selectBuildingState(Integer id) {
		return stateMapper.selectBuildingState(id);
	}

	/**
	 * 该方法用来获取房屋的房间号
	 * @param  Integer pid
 	 * @return  Room
	 */
	public List<Room> selectRoomstate(Integer pid) {
		return stateMapper.selectRoomstate(pid);
	}


	/**
	 * 该方法用来获取已租房屋的房间号
	 * @param  Integer pid 
 	 * @return  List<Room>
	 */
	public List<Room> selectRoomstates(Integer pid) {
		return stateMapper.selectRoomstates(pid);
	}
	

	/**
	 * 该方法用来获取未租房屋的房间号
	 * @param  Integer pid
 	 * @return  List<Room>
	 */
	public List<Room> selectRoomstatex(Integer pid) {
		return stateMapper.selectRoomstatex(pid);
	}
}
