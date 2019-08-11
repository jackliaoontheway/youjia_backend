package com.ryit.controller;

import java.io.IOException;
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
import com.ryit.entity.vo.BuildingQueryVo;
import com.ryit.entity.vo.PlotQueryIdPname;
import com.ryit.service.BuildingService;

@Controller
@RequestMapping("pages/admin")
public class BuildingContrller{

	@Autowired
	private BuildingService buil;
	
	
	/**
	 * 增加房屋栋数信息
	 * @param Building b 
	 * @return 
	 * @throws IOException 
	 */
	@RequestMapping("/saveBuil")
	public @ResponseBody Object saveBuil(HttpServletResponse response, HttpServletRequest request, Building b){
		Map<String,String> map = new HashMap<String,String>();
		String msg="";
		if(b!= null){
			msg = buil.insertDate(b);
		}
		
		if("0".equals(msg)){
			map.put("error","添加失败！栋数的信息在添加时不完整，添加无法完成，请重新补充完数据！");
		}else if("1".equals(msg)){
			map.put("success","添加成功");
		}else if("2".equals(msg)){
			map.put("error","添加失败，在此小区中此栋数已存在！");
		}else{
			map.put("error","用户登录有效期已过，无法操作增加功能，请返回到登录页面，重新登陆！");
		}
		return map;
		
	}
	
	/**
	 *通过指定栋数的编号来删除指定的栋数
	 *@param int id
	 *@return
	 */
	@RequestMapping("/delBuil")
	public @ResponseBody Object delete(HttpServletRequest request,HttpServletResponse response,Integer id){
		String msg="";
		if(id != 0){
			msg = buil.delete(id);
		}
		return msg;
	}
	
	
	
	@RequestMapping("/selectBuil")
	public @ResponseBody Object select(HttpServletRequest request, HttpServletResponse response, Building bu){
		if(bu.getBuildingNo() == null){
			List<BuildingQueryVo> list = buil.selectAll();
			return list;
		}else{
			return buil.findBuilNo(bu);
		}
		
	}
	
	
	@RequestMapping("/findBIdBname")
	public @ResponseBody Object findBIdBname(HttpServletRequest request, HttpServletResponse response,Integer pid){		
		
		return buil.findBIdAndBname(pid);
		
	}
	
	
	/**
	 * 查询小区名称和小区编号，查询使用
	 * @return
	 */
	@RequestMapping("/selectPname")
	public @ResponseBody Object selectPname(){
		
		List<PlotQueryIdPname> list = buil.findPlotName();
		
		return list;
	}
	
	
	/**
	 * 获得所有小区名称
	 * @return
	 */
	@RequestMapping("/findPname")
	public @ResponseBody Object findPname(){
		return buil.findPlotName();
	}
	
	
	
	/**
	 * 修改，指定修改
	 * @param request
	 * @param response
	 * @param bu
	 * @return
	 */
	@RequestMapping("/updateBuil")
	public @ResponseBody Object update(HttpServletRequest request,HttpServletResponse response,Building bu){
		Map<String,String> map = new HashMap<String,String>();
		String msg="";
	
		if(bu != null){
			msg = buil.update(bu);
		}
		if("0".equals(msg)){
			map.put("error","修改失败！栋数的信息在修改时不完整，修改无法完成，请重新补充完数据！");
		}else if("1".equals(msg)){
			map.put("success","修改成功！");
		}else if("2".equals(msg)){
			map.put("error","修改失败！，在此小区中此栋数已存在！");
		}else{
			map.put("error","用户登录有效期已过，无法操作增加功能，请返回到登录页面，重新登陆！");
		}
		return map;
		
	}
}
