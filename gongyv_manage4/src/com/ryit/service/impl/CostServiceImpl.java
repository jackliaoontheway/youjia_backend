package com.ryit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ryit.entity.Cost;
import com.ryit.mapper.CostMapper;
import com.ryit.service.CostService;
import com.ryit.utils.UserSession;


public class CostServiceImpl implements CostService{
	
	@Autowired
	private CostMapper costMapper;
	
	/**该方法用来根据传入的参数查询对应的id修改之后返回
	 * @param id  int 
	 * @return Cost
	 */
	public void updateCost(Cost cost) {
		
		cost.setUpdated_user(UserSession.getAdminUser().getLoginName());
		cost.setUpdated_datetime(new Date());
		costMapper.updateCost(cost);
	}

	
	/**该方法用来根据传入的参数查询对应的id并返回
	 * @param id  int 
	 * @return Cost
	 */
	public List<Cost> findCost() {
		return costMapper.findCost();
	}
	
}
