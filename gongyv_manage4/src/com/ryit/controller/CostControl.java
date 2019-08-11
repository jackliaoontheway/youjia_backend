package com.ryit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.entity.Cost;
import com.ryit.service.CostService;
import com.ryit.service.impl.CostServiceImpl;



@Controller
@RequestMapping("pages/admin")
public class CostControl {
	
	@Autowired
	private CostService costService;
	
	/**该方法用来根据传入的参数查询对应的id修改之后返回
	 * @param cost  Cost 
	 * @return Cost
	 */
	@RequestMapping("/updateCost")
	public@ResponseBody Object updateCost(HttpServletResponse response, HttpServletRequest request, Cost cost){
		
		costService.updateCost(cost);
		return true;
	}
	
	/**该方法用来查询所有的费用
	 * 
	 * @return Cost
	 */
	@RequestMapping("/findCost")
	public @ResponseBody Object findCost(HttpServletResponse response, HttpServletRequest request){
		return costService.findCost();
	}
	
	
	
}
