package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RoomQueryAllVo;
import com.ryit.entity.vo.RentVo;
import com.ryit.entity.vo.RenterVo;



public interface RoomMapper {
	
	/**
	 * 指定一个对象来增加
	 * @param bu
	 * @return
	 */
	public void insert(Room bu);
	
	/**
	 * 通过指定编号来删除
	 * @param did
	 * @return
	 */
	public void delete(Integer did);
	
	/**
	 * 查询所有的信息
	 * @return
	 */
	public List<Room> selectAll();
	
	/**
	 * 查询房间所有的信息，包括栋数名称
	 * @return
	 */
	public List<RoomQueryAllVo> selectsAll();
	
	/**
	 * 此方法通过指定条件查询房间信息
	 * @param Room r
	 * @return List<Room> list
	 */
	public List<RoomQueryAllVo> select(Room r);
	
	
	/**
	 * 查询所有房间的房间类型
	 * @return List<Room>
	 */
	public List<Room> findRoom_type();
	
	
	/**
	 * 查询所有的管理员
	 * @return List<Room> list
	 */
	public List<Room> findByAdmin();
	
	
	/**
	 * 通过指定一个条件获取某个房间所有信息 
	 * @param ren
	 * @return
	 */
	public Room findRoomState(Renter ren);
	
	
	/**
	 * 通过指定的条件查询所对应的房号
	 * @param int bid
	 * @return List<Room>
	 */
	public List<Room> findByRoom_num(Integer bid);
	
	
	
	/**
	 * 修改
	 * @param Room rm
	 */
	public void update(Room rm);
	
	
	/**
	 * 通过指定的条件修改房间信息的状态
	 * @param Renter r
	 */
	public void updateState(RenterVo r);
	
	
	/**
	 * 根据给定的条件查询房间的信息
	 * @param rent r
	 * @return Room
	 */
	public Room selectManageFee(Rent rent);
	
	/**
	 * 通过指定的条件获取房号
	 * @param RenterVo renter
	 * @return
	 */
	public Room findRoomName(RenterVo renter);
	
	
	public Room findRoom(RenterVo renter);
	
	/**
	 * 通过指定的条件获取房号
	 * @param RentVo rent
	 * @return
	 */
	public Room findRoomNamez(RentVo rent);
	
	
	/**
	 * 通过指定的条件获取房号
	 * @param RentVo rent
	 * @return
	 */
	public List<Room> findRoomId(RenterVo renter);

}
