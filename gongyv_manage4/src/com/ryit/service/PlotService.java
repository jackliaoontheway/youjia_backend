package com.ryit.service;

import java.util.List;

import com.ryit.entity.Plot;

public interface PlotService {

	//添加小区信息
	public String addPlot(Plot p);
	
	//指定删除小区的信息
	public String delPlot(int id);
	
	//查询所有小区的信息
	public List<Plot> selectAll();
	
	//获取小区名称
	public List<Plot> findPname(String pname);
	
	//修改
	public String updatePlot(Plot p);
	
}
