package com.ryit.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RenterVo;
import com.ryit.mapper.BuildingMapper;
import com.ryit.mapper.PlotMapper;
import com.ryit.mapper.RentMapper;
import com.ryit.mapper.RenterMapper;
import com.ryit.mapper.RoomMapper;
import com.ryit.service.RenterService;
import com.ryit.utils.UserSession;
@Service
public class RenterServiceImpl implements RenterService{
	@Autowired
	private RenterMapper renterMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private RentMapper rentMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private PlotMapper plotMapper;
	

	
	private int count;
	/**该方法用来根据传入的参数查询对应的id并返回
	 * @param id  integer 
	 * @return Renter
	 */
	public Renter findId(Integer id) {
		return renterMapper.findId(id);
	}

	
	
	/**该方法用来查询所有的在数据库的租户信息,将数据添加至List集合并返回
	 * @return List<Renter>
	 */
	public List<Renter> selectAll() {
		return renterMapper.selectAll();
	}



	/**该方法用来根据传入的Renter对象的rid(栋数ID)、bid(房间ID)、state(状态)、phone(电话)、identity_card(身份证)属性进行查询,将数据添加至List集合并返回
	  * @param Renter renter
	 * @return List<Renter>
	 */
	public List<RenterVo> queryAssigned(RenterVo renterVo) {
		List<RenterVo> list = renterMapper.queryAssigned(renterVo);
		for(int i=0,j=list.size();i<j;i++) {
			setIdName(list.get(i));
		}
		return list;
	}



	/**该方法用向数据库添加租户的数据
	  * @param Renter renter
	 * @return String
	 */
	public String addRenter(Renter renter,MultipartFile[] files) {
		if(UserSession.getAdminUser().getLoginName() == null) {
			return "你的身份验证已失效，请重新登录......";
		}
		String filePath = "F:\\Pic\\card\\";
		renter.setCreated_user(UserSession.getAdminUser().getLoginName());
		renter.setCreated_datetime(new Date());
		renter.setUpdated_user(UserSession.getAdminUser().getLoginName());
		renter.setUpdated_datetime(new Date());	
		List<Renter> list = renterMapper.selectAll();
		for(int i=0,j=list.size();i<j;i++) {
			if(renter.getId() !=  list.get(i).getId() && renter.getUser_name().equals(list.get(i).getUser_name())) {
				return "该用户名已存在！！！";
			}
		}
		
		if(files.length != 0){
			for(int a = 0;a<files.length;a++){
				MultipartFile mf = files[a];
				String path = "";
				if(a == 1){
					path=filePath+renter.getName()+"_z.jpg";
					renter.setIde_card_pic_z(path);
				}else if(a == 2){
					path=filePath+renter.getName()+"_f.jpg";
					renter.setIde_card_pic_f(path);
				}else{
					count++;
					path=filePath+renter.getName()+count+".jpg";
					renter.setIdentity_card(path);
				}
				File saveDir = new File(filePath);
				saveDir.getParentFile().mkdirs();
	            try {
					mf.transferTo(saveDir);
				} catch (IllegalStateException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
		renterMapper.addRenter(renter);
		return "添加成功";
	}



	/**该方法用来对用户的信息进行修改
	  * @param Renter renter
	 * @return String
	 */
	public String updateRenter(RenterVo renter) {
		if(UserSession.getAdminUser().getLoginName() == null) {
			return "你的身份验证已失效，请重新登录......";
		}
		if(!transition(renter)) {
			return "没有这个房间或者已经出租.....";
		}
		renter.setUpdated_user(UserSession.getAdminUser().getLoginName());
		renter.setUpdated_datetime(new Date());
		renterMapper.updateRenter(renter);
		if(renter.getState() == 2 || renter.getState() == 1) {
			roomMapper.updateState(renter);
		}	
		return "修改成功！！！";
		
	}
	public boolean transition(RenterVo renter) {
		if(renter.getPname().matches("[0-9]+")) {
			renter.setPid(Integer.parseInt(renter.getPname()));
			renter.setBid(Integer.parseInt(renter.getBname()));
			renter.setRid(Integer.parseInt(renter.getRname()));
			return true;
		}else {
			Plot plot = plotMapper.findPlots(renter);
			if(plot != null ) {
				renter.setPid(plot.getId());
			}
			Building  building = buildingMapper.findBuilding(renter);
			if(building != null) {
				renter.setBid(building.getId());
			}
			Room room = roomMapper.findRoom(renter);
			if(room != null) {
				renter.setRid(room.getId());
			}
			return (building == null || room == null || plot == null)?(false):(true);
		}
	
	}



	/**该方法用来删除数据库对应的数据
	  * @param Renter renter
	 * @return String
	 */
	public String deleteRenter(Renter renter) {
		renterMapper.deleteRenter(renter.getId());
		return (findId(renter.getId()) == null)?("true"):("flase");
	}



	/**该方法用来根据栋号以及房间号查询状态是在租的用户
	  * @param Rent rent
	 * @return Renter
	 */
	public Renter selectUser(Rent rent) {
		return renterMapper.selectUser(rent);
	}
	
	
	/**、该方法用来做查询全部的分页的
	 * @param int currentPage
	 * @param int pageSize
	 * @return Map<String, Object>
	 */
	public List<RenterVo> getAllRenter(int currentPage, int pageSize) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("currentPage",(currentPage-1)*pageSize );
		map.put("pageSize", pageSize);
		List<RenterVo> list = renterMapper.getAllRenter(map);
		for(int i=0,j=list.size();i<j;i++) {
			//、该方法用来获取与小区、栋数、房间ID所对应的名称并设置
			setIdName(list.get(i));
		}
		return renterMapper.getAllRenter(map);
	}
	/**该用来查询数据库所有的行
	 * @return Integer
	 */
	public Integer getAllRecord() {
		return renterMapper.getAllRecord();
	}

	/**该用来按条件查询数据库所有的行
	 * @return Integer
	 */
	public Integer getAllRecords(RenterVo renter) {
		return renterMapper.getAllRecords(renter);
	}
	
	/**该方法用来获取与小区、栋数、房间ID所对应的名称并设置
	 * @RenterVo renterVo
	 * @return RenterVo
	 */
	public  RenterVo setIdName(RenterVo renterVo) {
		Room room = roomMapper.findRoomName(renterVo);
		Building building = buildingMapper.findBuildingName(renterVo);
		Plot plot = plotMapper.findPlotName(renterVo);
		if(room != null) {
			renterVo.setRname(room.getRoom_num());
		}
		if(building != null) {
			renterVo.setBname(building.getBuildingNo());
		}
		if(plot != null) {
			renterVo.setPname(plot.getPname());
		}
		
		return renterVo;
	}
	


	//查看照片
		public String showPicZ(int id){
			Renter renter = renterMapper.findId(id);
			
			return renter.getIde_card_pic_z();
			
		}
		
		public String showPicF(int id){
			Renter renter = renterMapper.findId(id);
			
			return renter.getIde_card_pic_f();
			
		}
		public String showPicB(int id){
			Renter renter = renterMapper.findId(id);
			return renter.getSelf_pic();
		}


}
