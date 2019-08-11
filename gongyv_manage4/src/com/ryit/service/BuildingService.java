package com.ryit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryit.entity.Building;
import com.ryit.entity.vo.BuildingQueryVo;
import com.ryit.entity.vo.PlotQueryIdPname;

//房屋栋数
@Service
public interface BuildingService {
	
	/**
	 * 增加
	 * @param Building b
	 * @return String
	 */
	public String insertDate(Building b);
	
	/**
	 * 通过指定栋数Id,删除相对应的栋数信息
	 * @param int bid
	 * @return String
	 */
	public String delete(int bid);
	
	/**
	 * 查询小区名称、栋数、栋数描述等信息
	 * @return List<BuildingQueryVo>
	 */
	public List<BuildingQueryVo> selectAll();
	
	/**
	 * 指定查询栋数的信息,此方法是通过查询条件查询栋数信息
	 * @param buildingNo
	 * @return
	 */
	public List<BuildingQueryVo> findBuilNo(Building bu);
	
	/**
	 * 通过指定条件查询栋数名称，需要小区编号
	 * @param Integer id
	 * @return List<Building>
	 */
	public List<Building> findBIdAndBname(int id);
	
	/**
	 * 获取小区Id和小区名称，此方法是在增加和修改时使用
	 * @return List<PlotQueryIdPname>
	 */
	public List<PlotQueryIdPname> findPlotName();
	
	/**
	 * 修改
	 * @param Building b
	 * @return String
	 */
	public String update(Building bu);
	
}
