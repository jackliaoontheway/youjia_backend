package com.ryit.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.entity.Renter;
import com.ryit.entity.vo.RenterVo;
import com.ryit.service.RenterService;


@Controller
@RequestMapping("pages/admin")
public class RenterController {
	@Autowired
	private RenterService renterService;

	
	/**、该方法用来查询数据库中的租户信息，根据RenterVo中的小区、栋数、房间、租房状态、注册状态、身份证字段进行校验,如果都为空则查询全部，否则按条件查询
	 * @param HttpServletResponse request 
	 * @param HttpServletResponse response
	 * @param RenterVo renter
	 * @return Renter
	 */
	@RequestMapping("/selectAllRenter")
	public @ResponseBody Object selectAll(HttpServletResponse response, HttpServletRequest request,RenterVo renter) {	
		//根据RenterVo中的小区、栋数、房间、租房状态、注册状态、身份证字段进行校验
		if(renter.getBid() == null && renter.getRid() == null && renter.getPid() == null  && (renter.getState() ==null || renter.getState() ==0) && 
				(renter.getR_state() == null || renter.getR_state() == 0) && (renter.getIdentity_card() == null || renter.getIdentity_card() == "")) {
			//调查询全部方法并将结果返回
			return getAll(request);
		}else {
			//调按条件查询方法并将结果返回
			return queryAssigned(request,renter);
		}
	}
	

	
	/**、该方法用来对租户的信息进行修改，对Renter中的小区、栋数、房间、租房状态、注册状态、密码、姓名、电话进行修改,
	 * @param HttpServletResponse request 
	 * @param HttpServletResponse response
	 * @param Renter renter
	 * @return Renter
	 */
	@RequestMapping("/updateRenter")	
	public @ResponseBody Object updateRenter(HttpServletResponse response, HttpServletRequest request, RenterVo renter) {
		//设置请求响应编码UTF-8
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		//通过Map集合向前端反馈信息
		Map<String,String> map = new HashMap<String,String>();
		//进行电话号码以及姓名的校验
		if(!(renter.getPhone()).matches("^1[3-9][0-9]\\d{8}$")){
			map.put("message","电话号码格式有误！！！");
		}else if(!renter.getName().matches("[\u0391-\uFFE5]{2,5}")) {
			map.put("message","姓名有误！！！");
		}else{
			//调用修改方法,并将返回结果装进Map集合中
			map.put("message",renterService.updateRenter(renter));
		}
		return map;
	}
	

	/**、该方法用来删除数据库对应的数据根据ID进行删除
	 * @param HttpServletResponse request 
	 * @param HttpServletResponse response
	 * @param Renter renter
	 * @return Renter
	 */
	@RequestMapping("/deleteRenter")
	public @ResponseBody Object deleteRenter(HttpServletResponse response, HttpServletRequest request, Renter renter) {
		return renterService.deleteRenter(renter);
	}
	
	/**、该方法用来做查询全部的分页的
	 * @param HttpServletResponse request 
	 * @return Map<String, Object>
	 */
	 @RequestMapping("renterList")
	 @ResponseBody
	 public Map<String, Object> getAll(HttpServletRequest request){
		 //获取分页数
		 int currentPage=Integer.parseInt(request.getParameter("page"));
		 //获取当前所在的行
		 int pageSize=Integer.parseInt(request.getParameter("rows"));
		 //调用getAllRecord方法获取数据库所以的行
		 int total = renterService.getAllRecord();
		 //将分页数以及当前所在的行传递过去开始分页
		 List<RenterVo> list = renterService.getAllRenter(currentPage, pageSize);
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("total", total);
		 map.put("rows", list);	 
		 return map;
		 
	 }
	 /**、该方法用来做条件查询的分页的
		 * @param HttpServletResponse request 
		 * @param RenterVo renter
		 * @return Map<String, Object>
	*/
	 public Map<String,Object> queryAssigned(HttpServletRequest request,RenterVo renter){
		 //获取分页数
		 int currentPage=Integer.parseInt(request.getParameter("page"));
		//获取当前所在的行
		 int pageSize=Integer.parseInt(request.getParameter("rows"));
		 //调用getAllRecord方法获取数据库所以的行
		 int total = renterService.getAllRecords(renter);
		 //计算分页数
		 renter.setCurrentPage((currentPage-1)*pageSize );
		 renter.setPageSize(pageSize);
		 //调用queryAssigned方法开始分页
		 List<RenterVo> list = renterService.queryAssigned(renter);
		 Map<String, Object> map =new HashMap<String,Object>();
		 map.put("total", total);
		 map.put("rows", list);	 
		return map;
	 }
	 
	 


		@RequestMapping("/showPicZ")
		public @ResponseBody Object showPicZ(HttpServletRequest request,Integer id){
			
			Map map = new HashMap();
			map.put("ide_card_pic_z",renterService.showPicZ(id));
			return map;
		}
		
		@RequestMapping("/showPicF")
		public @ResponseBody Object showPicF(HttpServletRequest request,Integer id){
			Map map = new HashMap();
			map.put("ide_card_pic_f",renterService.showPicF(id));
			return map;
		}
		
		@RequestMapping("/showPicB")
		public @ResponseBody Object showPicB(HttpServletRequest request,Integer id){
			Map map = new HashMap();
			map.put("self_pic",renterService.showPicB(id));
			return map;
		}
		
		
	
	 
}
