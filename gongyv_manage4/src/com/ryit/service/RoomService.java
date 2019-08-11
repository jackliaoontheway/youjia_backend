package com.ryit.service;


import java.util.List;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RoomAdd;
import com.ryit.entity.vo.RoomBuildingQuery;
import com.ryit.entity.vo.RoomQueryAllVo;


//房间管理
public interface RoomService {

	/**
	 * 该方法通过指定一个对象来增加房间的信息
	 * @param Room r
	 * @return
	 */
	public String insert(RoomAdd ra);
		
		
	
	
	/**
	 * 通过指定房间的编号来删除房间信息
	 * @param int id
	 * @return
	 */
	public void delete(int id);
		
	
	
	/**
	 * 此方法通过指定条件查询房间信息
	 * @param Room r
	 * @return List<Room> list
	 */
	public List<RoomQueryAllVo> select(Room r);
	
	
	/**
	 * 查询所有的房间信息
	 * @param 
	 * @return List<Room>
	 */
	public List<Room> selectAll();
	
	
	/**
	 * 此方法联合查询，查询小区名称、栋数以及房间所有信息
	 * @return List<RoomQueryAllVo>
	 */
	public List<RoomQueryAllVo> selectAllS();
	
	
	/**
	 * 获取小区编号和小区名称，是用来增加房间信息时，显示小区名称，以便用户选取
	 * @return List<Plot>
	 */
	public List<Plot> findIdAndPname();
	
	
	/**
	 * 查询所有的栋数,此方法获取的是栋数管理中所有的栋数
	 * @return List<RoomBuildingQuery>
	 */
	public List<RoomBuildingQuery> findIdAndBname(int id);
	
	
	/**
	 * 查询所有房间的房间类型
	 * @return List<Room>
	 */
	public List<Room> findRoom_type();
	
	/**
	 * 获取所有的管理员
	 * @return List<Room>
	 */
	public List<Room> findByAdmin();
	
	
	/**
	 * 指定修改房间信息
	 * @param Room r
	 * @return
	 */
	public String update(RoomQueryAllVo ra);
	
	
	/**
	 * 查询所有的小区名称，此方法是在查询功能中使用的方法
	 * @return List<Plot>
	 */
	public List<Plot> findIdPname();
	
	/**
	 * 查询所有的栋数,此方法获取的是房间管理中所有的栋数，是在查询功能中使用的方法
	 * @return List<Building>
	 */
	public List<Building> findBname(int id);
	
	
	/**
	 * 此方法指定房间的栋数，获取指定房间所有信息
	 * @param String buildingNo
	 * @return List<Room>
	 */
	public List<Room> findByRoom_num(Integer bid);
}
