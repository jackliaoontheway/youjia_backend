package com.ryit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ryit.entity.Building;
import com.ryit.entity.vo.BuildingQueryVo;
import com.ryit.entity.vo.PlotQueryIdPname;
import com.ryit.mapper.BuildingMapper;
import com.ryit.mapper.PlotMapper;
import com.ryit.service.BuildingService;
import com.ryit.utils.UserSession;

@Service
public class BuildingServiceImp implements BuildingService {

	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private PlotMapper plotMapper;
	/**
	 * 增加房屋栋数等信息
	 * @param Building bu 
	 * @return 
	 */
	public String insertDate(Building b) {
		String msg = "2";
		boolean boo = false;
		if(b.getPid() == null || b.getBuildingNo()=="" || b.getDescription()==""){
			msg = "0";
		}else{
			String userName = UserSession.getAdminUser().getLoginName();
			if(userName != null){
				b.setCreated_user(userName);
				b.setCreated_datetime(new Date());
				b.setUpdated_user(userName);
				b.setUpdated_datetime(new Date());
				if(!b.getBuildingNo().contains("栋")){
					b.setBuildingNo(b.getBuildingNo()+"栋");
				}
				List<Building> lists = buildingMapper.select();
				if(lists.size() == 0){
					buildingMapper.insert(b);
					msg = "1";
				}else{
					for(Building bu : lists){
						if(bu.getPid().equals(b.getPid()) & b.getBuildingNo().equals(bu.getBuildingNo())){
							boo = true;
							break;
						}
					}
					if(!boo){
						buildingMapper.insert(b);
						msg = "1";
					}
				}
			}else{
				msg = "3";
			}
		}
		return msg;
	}
	
	

	
	
	/**
	 * 通过指定的编号删除房屋栋数的信息
	 * @param int did
	 * @return 
	 */
	public String delete(int did) {
		String msg ="false";
		boolean boo = false;
		List<BuildingQueryVo> list = selectAll();
		for(BuildingQueryVo bq : list){
			if(bq.getId() == did){
				boo = true;
			}
		}
		if(boo){
			buildingMapper.delete(did);
			msg = "true";
		}
		
		return msg;
	}
	
	/**
	 * 获得所有的信息
	 * @param  
	 * @return List<BuildingQueryVo>
	 */
	public List<BuildingQueryVo> selectAll() {
		List<BuildingQueryVo> li = buildingMapper.selectAll();
		return li;
		
	}
	
	
	/**
	 * 指定查询信息
	 * @param  Building bu
	 * @return List<BuildingQueryVo>
	 */
	public List<BuildingQueryVo> findBuilNo(Building bu){
		return  buildingMapper.findBuilNo(bu);
	}
	
	/**
	 * 查询所有的小区名称
	 * @return List<Plot>
	 */
	public List<PlotQueryIdPname> findPlotName(){
		
		List<PlotQueryIdPname> list = plotMapper.findPlot();
		
		return list;
	}
	
	
	/**
	 * 通过指定条件查询栋数名称，需要小区编号
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	public List<Building> findBIdAndBname(int id){
		return buildingMapper.findIdAndBname(id);
	}
	
	/**
	 * 修改所有的信息
	 * @param  Building bu
	 * @return 
	 */
	public String update(Building b) {
		
		String msg = "2";
		if(b.getPid() == null || b.getBuildingNo()=="" || b.getDescription()==""){
			msg = "0";
		}else{
			String useName = UserSession.getAdminUser().getLoginName();
			if(useName != null){
				b.setUpdated_user(useName);//设置修改用户名
				b.setUpdated_datetime(new Date());//设置修改时间
				List<Building> lists = buildingMapper.select();
				boolean boo = false;
				for(Building bu : lists){
					if((!b.getId().equals(bu.getId())) & b.getPid() == bu.getPid() & b.getBuildingNo().equals(bu.getBuildingNo())){
						boo = true;
					}
				}
				if(!boo){
					if(!b.getBuildingNo().contains("栋")){
						b.setBuildingNo(b.getBuildingNo()+"栋");
					}
					buildingMapper.update(b);
					msg = "1";
				}
				
			
			}else{
				msg = "3";
			}
		}
		return msg;
	}
}
