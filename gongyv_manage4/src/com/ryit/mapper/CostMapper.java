 package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Cost;

public interface CostMapper {
	
	//修改费用
	void updateCost(Cost cost);
	
	//进行查询所有的费用
	List<Cost> findCost();
	
}
