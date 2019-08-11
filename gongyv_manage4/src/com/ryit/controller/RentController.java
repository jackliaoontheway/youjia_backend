package com.ryit.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.entity.Rent;
import com.ryit.entity.vo.RentQueryVo;
import com.ryit.entity.vo.RentVo;
import com.ryit.service.RentService;


@Controller
@RequestMapping("pages/admin")
public class RentController {
	@Autowired
	private RentService rentService;
	
		/**该方法用来增加租金
		 * @param response
		 * @param request
		 * @param rent
		 * @return Object
		 */
		@RequestMapping("/addRent")
		public @ResponseBody Object addRent(HttpServletResponse response, HttpServletRequest request, Rent rent) {
			Map map = new HashMap();
			map.put("message",rentService.addRent(rent));
			return map;
		}

		/**该方法用来查询所有的租金信息~~~
		 * @param response
		 * @param request
		 * @param rentQueryVo
		 * @return Object 
		 */
		@RequestMapping("/selectAllRent")
		public @ResponseBody Object queryAll(HttpServletResponse response, HttpServletRequest request, RentQueryVo rentQueryVo) {
			 rentQueryVo.setCurrentPage((Integer.parseInt(request.getParameter("page"))-1) * Integer.parseInt(request.getParameter("rows"))); 
			 rentQueryVo.setPageSize(Integer.parseInt(request.getParameter("rows"))); 
			boolean flag = rentQueryVo.getPid() == null && rentQueryVo.getBid()== null && rentQueryVo.getRid() == null && 
					rentQueryVo.getEnd_time()==null && rentQueryVo.getStart_time()== null && (rentQueryVo.getUser() == "" || rentQueryVo.getUser() == null)
					&& (rentQueryVo.getState() == null ||rentQueryVo.getState() == 0 );
			Map<String,Object> map = rentService.getRenVoAll(flag,rentQueryVo);
			
			return map;
		}
		/**该方法用来删除租金信息
		 * @param response
		 * @param request
		 * @param rent
		 * @return Object
		 */
		@RequestMapping("/deleteRent")
		public @ResponseBody Object deleteRent(HttpServletResponse response, HttpServletRequest request, Rent rent) {		
			return rentService.deleteRent(rent);
		}

		/**该方法用来对租金信息进行修改~~~~~~~~~
		 * @param response
		 * @param request
		 * @param rent
		 * @return Object
		 */
		@RequestMapping("/updateRent")
		public @ResponseBody Object updateRent(HttpServletResponse response, HttpServletRequest request, Rent rent) {
			return rentService.updateRent(rent);	
		}
}
