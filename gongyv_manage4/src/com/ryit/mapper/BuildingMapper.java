package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Building;
import com.ryit.entity.vo.BuildingQueryVo;
import com.ryit.entity.vo.RentVo;
import com.ryit.entity.vo.RenterVo;

//房屋栋数
public interface BuildingMapper {
	
	/**
	 * 增加,需要栋数的对象
	 * @param bu
	 */
	public void insert(Building bu);
	
	/**
	 * 删除,需要栋数编号
	 * @param did
	 */
	public void delete(int did);
	
	/**
	 * 查询栋数所有的信息，这个栋数信息中包含了小区名称
	 * @return List<BuildingQueryVo>
	 */
	public List<BuildingQueryVo> selectAll();
	
	/**
	 * 查询所有栋数的栋数id,栋数名称、栋数描述
	 * @return List<Building>
	 */
	public List<Building> select();
	
	
	/**
	 * 通过小区编号这个条件去查询栋数Id和栋数名称
	 * @param Integer id
	 * @return List<Building>
	 */
	public List<Building> findIdAndBname(int id);
	
	
	/**
	 * 指定查询小区名称、栋号、栋数描述,需要栋数名称
	 * @param String bNo
	 * @return
	 */
	public List<BuildingQueryVo> findBuilNo(Building bu);
	

	/**
	 * 通过指定的小区Id、栋数名称去获取栋数Id
	 */
	public int findBid(Building b);
	

	/**
	 * 修改方法,需要一个对象，这个对象中的数据必须是新的，还要指定那一条数据需要修改，它的编号
	 */
	public void update(Building bu);
	
	public Building findBuildingName(RenterVo renterVo);
	
	public Building findBuilding(RenterVo renterVo);
	
	public Building findBuildingNamez(RentVo rentVo);
	
	public Building findBuildingId(RenterVo renterVo);
}
