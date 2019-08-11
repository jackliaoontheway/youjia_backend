package com.ryit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RoomAdd;
import com.ryit.entity.vo.RoomBuildingQuery;
import com.ryit.entity.vo.RoomQueryAllVo;
import com.ryit.service.RoomService;

//房间管理
@Controller
@RequestMapping("pages/admin")
public class RoomController {

	@Autowired
	private RoomService room;
	
	/**
	 * 添加房间信息
	 * @param response
	 * @param request
	 * @param Room
	 * @return
	 */
	@RequestMapping("/addRoom")
	public @ResponseBody Object addRoom(HttpServletResponse response, HttpServletRequest request,RoomAdd rd){
		Map<String,String> map = new HashMap<String,String>();
		String msg = room.insert(rd);
		if("0".equals(msg)){
			map.put("error", "你所填写的数据不完整，无法完成添加，请重新输入数据");
		}else if("1".equals(msg)){
			map.put("success", "添加成功！");
		}else if("2".equals(msg)){
			map.put("error","添加失败！在此栋数此房号已存在，请重新输入！");
		}else{
			map.put("error","用户登录有效期已过，无法操作，请返回到登录页面，重新登陆！");
		}
		return map;
		
		
	}
		
	/**
	 * 通过指定条件删除房间信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/del_room")
	public @ResponseBody Object deleteRoom(HttpServletRequest request,HttpServletResponse response,Integer id){
		if(id != 0){
			room.delete(id);
		}
		return "true";
		
	}
	
	
	/**
	 * 此方法联合查询，查询小区名称、栋数以及房间所有信息
	 * @return Object
	 */
	@RequestMapping("/selectAllS")
	public @ResponseBody Object selectAllS(HttpServletRequest request,HttpServletResponse response,Room r){
		
		if(r.getBid() != null || r.getRoom_num() != null || r.getRoom_type() != null || r.getState() != null){
			if(r.getState() == 0){
				r.setState(null);
			}
			return room.select(r);
			
		}else{
			
			return room.selectAllS();
		}
		
		
	}
	
	/**
	 * 获取小区编号和小区名称,是用来增加房间信息时，显示小区名称，以便用户选取
	 * @return Object
	 */
	@RequestMapping("/findIdAndPname")
	public @ResponseBody Object findIdAndPname(HttpServletRequest request,HttpServletResponse response){
		return room.findIdAndPname();
		
	} 
	
	
	/**
	 * 获取栋数编号和栋数名称,是用来增加房间信息时，显示栋数名称，以便用户选取
	 * @return Object
	 */
	@RequestMapping("/findIdAndBname")
	public @ResponseBody Object findIdAndBname(HttpServletRequest request,HttpServletResponse response,Plot p){
		List<RoomBuildingQuery> li = room.findIdAndBname(p.getId());
		return li;
		
	}
	
	/**
	 * 查询所有房间的房间类型,是用来增加房间信息时，显示房间类型，以便用户选取
	 * @return Object
	 */
	@RequestMapping("/findRoomType")
	public @ResponseBody Object findRoom_type(HttpServletRequest request,HttpServletResponse response){
		List<Room> roo = room.findRoom_type();
		return roo;
		
	}
	
	
	/**
	 * 查询所有的管理员,是用来增加房间信息时，显示管理员，以便用户选取
	 * @return Object
	 */
	@RequestMapping("/findAdmin")
	public @ResponseBody Object findByAdmin(){
		return room.findByAdmin();
	}
	
	/**
	 * 修改，根据指定的条件进行修改房间信息
	 * @param request
	 * @param response
	 * @param rd
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Object updateRoom(HttpServletRequest request,HttpServletResponse response,RoomQueryAllVo rd){
		Map<String,String> map = new HashMap<String,String>();
		String msg = room.update(rd);
		if("0".equals(msg)){
			map.put("error", "你所修改的数据不完整，无法完成修改，请重新把数据修改完整！");
		}else if("1".equals(msg)){
			map.put("success", "修改成功！");
		}else if("2".equals(msg)){
			map.put("error","修改失败！");
		}else{
			map.put("error","用户登录有效期已过，无法操作，请返回到登录页面，重新登陆！");
		}
		return map;
		
	}
	
	
	
	
	
	
	/**
	 * 查询所有的小区名称，此方法是在查询功能中使用的方法
	 * @return List<Plot>
	 */
	@RequestMapping("/findIdPname")
	public  @ResponseBody Object findIdPname(){
		return room.findIdAndPname();
	}
	
	/**
	 * 查询所有的栋数,此方法获取的是房间管理中所有的栋数，是在查询功能中使用的方法
	 * @return List<Building>
	 */
	@RequestMapping("/findBname")
	public  @ResponseBody Object findBname(HttpServletRequest request,HttpServletResponse response,Integer pd){
		List<Building> li = null;
		if(pd != 0){
			li = room.findBname(pd);
		}
		return li;
	}
	
	
	/**
	 * 通过指定的条件查询相应的房间信息
	 * @param request
	 * @param response
	 * @param Room r
	 * @return Object
	 */
	@RequestMapping("/findByRoom_num")
	public @ResponseBody Object findByRoom_num(HttpServletRequest request,HttpServletResponse response,Integer bid){
		List<Room> ro = room.findByRoom_num(bid);		
		return ro;
	}
	
}





