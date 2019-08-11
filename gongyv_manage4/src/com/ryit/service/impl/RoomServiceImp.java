package com.ryit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RoomAdd;
import com.ryit.entity.vo.RoomBuildingQuery;
import com.ryit.entity.vo.RoomQueryAllVo;
import com.ryit.mapper.BuildingMapper;
import com.ryit.mapper.PlotMapper;
import com.ryit.mapper.RoomMapper;
import com.ryit.service.RoomService;
import com.ryit.utils.UserSession;

@Service
public class RoomServiceImp implements RoomService{
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private BuildingMapper BMapper;
	
	@Autowired
	private PlotMapper PMapper;
	
	/**
	 * 该方法通过指定一个对象来增加房间的信息
	 * @param Room r
	 * @return
	 */
	public String insert(RoomAdd ra) {
		String msg = "2";
		if(ra.getRoom_num() =="" || ra.getMoney()== null || ra.getRoom_type()=="" || ra.getCash_pay()==null 
				|| ra.getMgr_fee()==null || ra.getState()==null || ra.getCash_state()==null ||ra.getAdmin()=="" || ra.getBuildingNo()=="" ){
				msg = "0";
		}else{
			boolean boo = false;
			List<Room> list = selectAll();
			if(list.size() == 0){
				msg = insertValue(ra);
			}else{
				for(Room rm : list){
					if(rm.getBid().equals(ra.getBuildingNo()) & rm.getRoom_num().equals(ra.getRoom_num())){				
							boo = true;
							break;
					}
				}
				if(!boo){
					msg = insertValue(ra);
				}
			}
		}
		return msg;
	}
	
	/**
	 * 插入房间信息,成功插入值，返回状态“1”
	 * @param ra
	 * @return String
	 */
	public String insertValue(RoomAdd ra){
		String userName = UserSession.getAdminUser().getLoginName();
		if(userName != null){
			Room r = new Room();
			r.setRoom_num(ra.getRoom_num());
			r.setBid(ra.getBuildingNo());
			r.setMoney(ra.getMoney());
			r.setRoom_type(ra.getRoom_type());
			r.setCash_pay(ra.getCash_pay());
			r.setMgr_fee(ra.getMgr_fee());
			r.setState(ra.getState());
			r.setCash_state(ra.getCash_state());
			r.setAdmin(ra.getAdmin());
			r.setCreated_user(userName);
			r.setCreated_datetime(new Date());
			r.setUpdated_user(userName);
			r.setUpdated_datetime(new Date());
			roomMapper.insert(r);
			return "1";
		}else{
			return "3";
		}
		
	}
	
	/**
	 * 通过指定房间的编号来删除房间信息
	 * @param int id
	 * @return
	 */
	public void delete(int id) {
		boolean boo = false;
		List<Room> list = selectAll();
		for(Room r : list){
			if(r.getId() == id){
				boo = true;
			}
		}
		if(boo){
			roomMapper.delete(id);
		}	
	}
	
	
	/**
	 * 此方法通过指定条件查询房间信息
	 * @param Room r
	 * @return List<Room> list
	 */
	public List<RoomQueryAllVo> select(Room r){
		
		List<RoomQueryAllVo> list = roomMapper.select(r);
		return list;
	}
	
	
	
	/**
	 * 查询所有的房间信息
	 * @param 
	 * @return List<Room>
	 */
	public List<Room> selectAll() {
		List<Room> li = roomMapper.selectAll();
		return li; 
	}
	
	
	/**
	 * 此方法联合查询，查询小区名称、栋数以及房间所有信息
	 * @return List<RoomQueryAllVo>
	 */
	public List<RoomQueryAllVo> selectAllS(){
		return roomMapper.selectsAll();
		
	}
	
	
	/**
	 * 获取小区编号和小区名称，是用来增加房间信息时，显示小区名称，以便用户选取
	 * @return List<Plot>
	 */
	public List<Plot> findIdAndPname(){
		return PMapper.findIdAndPname();
	}
	
	
	/**
	 * 查询所有的栋数,此方法获取的是栋数管理中所有的栋数
	 * @return List<Building>
	 */
	public List<RoomBuildingQuery> findIdAndBname(int id){
		List<RoomBuildingQuery> list = new ArrayList<RoomBuildingQuery>();
		List<Building> li = BMapper.findIdAndBname(id);
		for(Building b : li){
			list.add(new RoomBuildingQuery(b.getId(),b.getBuildingNo()));
		}
		return list;
	}
	
	
	/**
	 * 查询所有房间的房间类型
	 * @return List<Room>
	 */
	public List<Room> findRoom_type(){
		return roomMapper.findRoom_type();
	}
	
	
	/**
	 * 获取所有的管理员
	 * @return List<Room>
	 */
	public List<Room> findByAdmin(){
		
		return roomMapper.findByAdmin();
	}
	
	
	/**
	 * 指定修改房间信息
	 * @param Room r
	 * @return
	 */
	public String update(RoomQueryAllVo ra) {
		String msg = "2";
		if(ra.getRoom_num() =="" || ra.getMoney()== null || ra.getRoom_type()=="" || ra.getCash_pay()==null 
			|| ra.getMgr_fee()==null || ra.getState()==null || ra.getCash_state()==null ||ra.getAdmin()=="" || ra.getBuildingNo()==""){
			msg = "0";
		}else{
			boolean boo = false;
			List<Room> list = selectAll();	
			if(!ra.getBuildingNo().matches("[0-9]+")){
				
				int pid = PMapper.findId(ra.getPname());
				int bid = BMapper.findBid(new Building(pid,ra.getBuildingNo()));
				ra.setBuildingNo(bid+"");
			}			
			for(Room rm : list){
				if((!rm.getId().equals(ra.getId())) & rm.getBid().equals(ra.getBid()) & ra.getRoom_num().equals(rm.getRoom_num())){
					boo = true;
				}
			}
			if(!boo){
				Room r = new Room();
				r.setId(ra.getId());
				r.setRoom_num(ra.getRoom_num());
				r.setBid(ra.getBuildingNo());
				r.setMoney(ra.getMoney());
				r.setRoom_type(ra.getRoom_type());
				r.setCash_pay(ra.getCash_pay());
				r.setMgr_fee(ra.getMgr_fee());
				r.setState(ra.getState());
				r.setCash_state(ra.getCash_state());
				r.setAdmin(ra.getAdmin());
				r.setUpdated_user(UserSession.getAdminUser().getLoginName());
				r.setUpdated_datetime(new Date());
				
				roomMapper.update(r);
				msg = "1";
			}else{
				msg = "3";
			}
		}
		
		return msg;
	}
	
	
	/**
	 * 查询所有的小区名称，此方法是在查询功能中使用的方法
	 * @return List<Plot>
	 */
	public List<Plot> findIdPname(){
		return PMapper.findIdAndPname();
	}
	
	
	/**
	 * 查询所有的栋数,此方法获取的是房间管理中所有的栋数，是在查询功能中使用的方法
	 * @return List<Building>
	 */
	public List<Building> findBname(int id){
		List<Building> li = BMapper.findIdAndBname(id);
		
		return li;
				
				
	}
	
	
	/**
	 * 此方法指定房间的栋数，获取指定房间所有信息
	 * @param String buildingNo
	 * @return List<Room>
	 */
	public List<Room> findByRoom_num(Integer bid){
		List<Room> li = roomMapper.findByRoom_num(bid);
		return li;
	}
}
