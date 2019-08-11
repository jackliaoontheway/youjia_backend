package com.ryit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.Plot;
import com.ryit.mapper.PlotMapper;
import com.ryit.service.PlotService;
import com.ryit.utils.UserSession;

@Service
public class PlotServiceImp implements PlotService{

	@Autowired
	private PlotMapper plotMapper;
	
	

	/**
	 * 增加小区信息
	 * @param Building bu 
	 * @return 
	 */
	public String addPlot(Plot p) {
		String msg="2";
		boolean boo = false;
		String userName = UserSession.getAdminUser().getLoginName();
		if(p.getPname()=="" || p.getAddress()=="" || p.getDescription()==""){
			msg = "0";
		}else{
			 List<Plot> list = selectAll();
			 if(list.size() == 0){
				if(userName != null){
					p.setCreated_user(userName);
					p.setCreated_datetime(new Date());
					p.setUpdated_user(userName);
					p.setUpdated_datetime(new Date());
					plotMapper.addPlot(p);
					msg = "1";
				}
			 }else{
				 for(Plot pt : list){
					 if(pt.getPname().equals(p.getPname()) & p.getAddress().equals(pt.getAddress())){
						 boo = true;
					 }
				 }
				if(boo){
					msg = "2";
				}else{
					if(userName != null){
						p.setCreated_user(userName);
						p.setCreated_datetime(new Date());
						p.setUpdated_user(userName);
						p.setUpdated_datetime(new Date());
						plotMapper.addPlot(p);
						msg = "1";
					}else{
						msg="3";
					}
				}
			}
		}
		return msg;
	}

	
	
	public String delPlot(int id) {
		String msg="false";
		 List<Plot> list = selectAll();
		 for(Plot p : list){
			 if(p.getId() == id){
				 msg = "true";
				plotMapper.deletePlot(id);
				break;
			 }
		 }
		return msg;
	}

	
	/**
	 * 此方法用于查询 小区所有信息
	 * @param String pname
	 * @return List<Plot>
	 */
	public List<Plot> selectAll() {
		
		return plotMapper.selectAll();
	}
	
	/**
	 * 此方法用于通过指定的条件查询小区名称
	 * @param String pname
	 * @return List<Plot>
	 */
	public List<Plot> findPname(String pname){
		List<Plot> li = plotMapper.findPname(pname);		
		return li;
	}

	
	/**
	 * 指定修改小区的信息
	 * @param Plot p
	 * @return
	 */
	public String updatePlot(Plot p) {
		String msg="2";
		if(p.getPname()=="" || p.getAddress()=="" || p.getDescription()==""){
			msg = "0";
		}else{
			boolean boo = false;
			List<Plot> list = selectAll();
			 for(Plot pt : list){
				 if(p.getId() != pt.getId() & p.getPname().equals(pt.getPname()) & p.getAddress().equals(pt.getAddress())){
					 boo = true;
				 }
			 }
			 if(!boo){
				String userName = UserSession.getAdminUser().getLoginName();
				if(userName != null){
					p.setUpdated_user(userName);
					 p.setUpdated_datetime(new Date());
					 plotMapper.updatePlot(p);
					 msg = "1";
				}else{
					msg = "3";
				}
				 
			 }
		}
		 return msg;
	}
}
