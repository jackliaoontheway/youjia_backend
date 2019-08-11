package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Plot;
import com.ryit.entity.vo.PlotQueryIdPname;
import com.ryit.entity.vo.RentVo;
import com.ryit.entity.vo.RenterVo;

public interface PlotMapper {
	/**
	 * 添加小区信息
	 * @param Plot p
	 */
	public void addPlot(Plot p);
	
	/**
	 * 此方法通过指定小区编号去删除指定的数据
	 * @param id
	 */
	public void deletePlot(int id);
	
	
	/**
	 * 查询小区所有的信息
	 * @return List<Plot>
	 */
	public List<Plot> selectAll();
	
	/**
	 * 此方法需要小区名称去指定查询小区全部信息
	 * @param String pname
	 * @return List<Plot>
	 */
	public List<Plot> findPname(String pname);
	
	/**
	 * 查询所有小区的名称和小区Id,此方法是用来提供给栋数管理查询小区Id和小区名称的
	 * @return List<PlotQueryIdAndName>
	 */
	public List<PlotQueryIdPname> findPlot();
	

	/**
	 * 获得小区编号和小区名
	 * @return List<Plot>
	 */
	public List<Plot> findIdAndPname();


	/**
	 * 通过指定的条件获取小区的编号,需要小区名称
	 * @param panem
	 * @return int
	 */
	public int findId(String panem);

	/**
	 * 修改小区信息
	 * @param p
	 */
	public void updatePlot(Plot p);
	
	/**
	 * 通过指定的条件获取房号
	 * @param RenterVo renter
	 * @return
	 */
	public Plot findPlotName(RenterVo renterVo);
	
	public Plot findPlots(RenterVo renterVo);
	
	/**
	 * 通过指定的条件获取房号
	 * @param RentVo rent
	 * @return
	 */
	public Plot findPlotNamez(RentVo rentVo);
	
	
	
	/**
	 * 通过指定的条件获取房号
	 * @param RentVo rent
	 * @return
	 */
	public Plot findPlotId(RenterVo renterVo);
}
