package com.ryit.service;

import java.util.List;

import com.ryit.entity.Cost;

public interface CostService {
	
	//该方法用来修改费用
	void updateCost(Cost cost);
	
	//根据id查询费用
	List<Cost> findCost();
	
	
}
