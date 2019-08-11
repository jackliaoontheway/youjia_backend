package com.ryit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.service.StateService;
@Controller
@RequestMapping("pages/admin")
public class StateController {
	@Autowired
	private StateService stateService;

	/**该方法用来获取小区的信息
	 * @return Object
	 */
	@RequestMapping("/selectPlotState")
	public @ResponseBody Object selectPlotState() {
		return stateService.selectPlotState();
	} 
	/**该方法用来获取栋数的栋号
	 * @param response
	 * @param request
	 * @param plot 
	 * @return Object
	 */
	@RequestMapping("/selectBuildingState")
	public @ResponseBody Object selectBuildingState(HttpServletResponse response, HttpServletRequest request,Plot plot) {
		return stateService.selectBuildingState(plot.getId());
	}

	/**该方法用来获取房屋的房间号
	 * @param response
	 * @param request
	 * @param building
	 * @return Object
	 */
	@RequestMapping("/selectRoomstate")
	public @ResponseBody Object selectRoomstate(HttpServletResponse response, HttpServletRequest request,Building building) {
		return stateService.selectRoomstate(building.getPid());
	}
	
	
	/**该方法用来获取未租房屋的房间号
	 * @param response
	 * @param request
	 * @param building
	 * @return
	 */
	@RequestMapping("/selectRoomstates")
	public @ResponseBody Object selectRoomstates(HttpServletResponse response, HttpServletRequest request,Building building) {
		return stateService.selectRoomstates(building.getPid());
	}
	

	/**该方法用来获取已租房屋的房间号
	 * @param response
	 * @param request
	 * @param building
	 * @return
	 */
	@RequestMapping("/selectRoomstatex")
	public @ResponseBody Object selectRoomstatex(HttpServletResponse response, HttpServletRequest request,Building building) {
		return stateService.selectRoomstatex(building.getPid());
	}
	
	

}
