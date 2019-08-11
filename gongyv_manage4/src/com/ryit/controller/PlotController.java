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
import com.ryit.entity.Plot;
import com.ryit.service.PlotService;



@Controller
@RequestMapping("pages/admin")
public class PlotController {

	@Autowired
	private PlotService plot;
	
	

	/**
	 * 增加小区信息
	 * @param plot p
	 * @return 
	 */
	@RequestMapping("/savePlot")
	public  @ResponseBody Object saveBuil(HttpServletResponse response, HttpServletRequest request,Plot p){
		Map<String,String> result = new HashMap<String,String>();
		String msg="";
		if(p != null){
			 msg = plot.addPlot(p);
		}
		if("0".equals(msg)){
			result.put("error", "添加失败，小区添加的信息必不完整，增加无法完成，请重新把小区信息添加完整！");
		}else if("1".equals(msg)){
			result.put("success", "增加成功");
		}else if("2".equals(msg)){
			result.put("error", "添加失败，小区名称已存在，请重新输入！");
		}else{
			result.put("error","用户登录有效期已过，无法操作增加功能，请返回到登录页面，重新登陆！");
		}
		
		return result;
	}
	
	/**
	 *通过指定小区编号来删除指定的小区
	 *@param int id
	 *@return
	 */
	@RequestMapping("/delPlot")
	public @ResponseBody Object delete(HttpServletRequest request,HttpServletResponse response,Integer id){
		String msg = "";
		if(id != 0){
			msg = plot.delPlot(id);
		}
		return msg;
	}
	
	/**
	 * 查询小区所有信息
	 * @param request
	 * @param response
	 * @param p
	 * @return
	 */
	@RequestMapping("/selectPlot")
	public @ResponseBody Object select(HttpServletRequest request, HttpServletResponse response, Plot p){
	
		if(p.getPname() == null){
			List<Plot> list = plot.selectAll();
			return list;
		}else{
			return plot.findPname(p.getPname());
		}
		
	}
	
	
	
	/**
	 * 此方法通过指定的条件修改小区信息
	 * @param request
	 * @param response
	 * @param p
	 */
	@RequestMapping("/updatePlot")
	public @ResponseBody Object update(HttpServletRequest request,HttpServletResponse response, Plot p){
		Map<String,String> result = new HashMap<String,String>();
		String msg = "";
		if(p != null){
			 msg = plot.updatePlot(p);
		}
		
		if("0".equals(msg)){
			result.put("error", "修改失败，小区修改的数据必不完整，修改无法完成，请重新把小区信息补充完整！");
		}else if("1".equals(msg)){
			result.put("success", "修改成功！");
		}else if("2".equals(msg)){
			result.put("error", "修改失败，小区名称已存在，请重新输入！");
		}else{
			result.put("error","用户登录有效期已过，无法操作修改功能，请返回到登录页面，重新登陆！");
		}
		
		return result;
	}
}
