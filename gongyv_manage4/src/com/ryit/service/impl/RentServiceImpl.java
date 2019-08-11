package com.ryit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.entity.Building;
import com.ryit.entity.Cost;
import com.ryit.entity.Plot;
import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RentQueryVo;
import com.ryit.entity.vo.RentVo;
import com.ryit.entity.vo.RenterVo;
import com.ryit.mapper.BuildingMapper;
import com.ryit.mapper.CostMapper;
import com.ryit.mapper.PlotMapper;
import com.ryit.mapper.RentMapper;
import com.ryit.mapper.RenterMapper;
import com.ryit.mapper.RoomMapper;
import com.ryit.service.RentService;
import com.ryit.utils.UserSession;
@Service
public class RentServiceImpl implements RentService{
	//租金登记
	@Autowired
	private RentMapper rentMapper;
	//费用管理
	@Autowired
	private CostMapper costMapper;
	//租户管理
	@Autowired
	private RenterMapper renterMapper;
	//房间管理
	@Autowired
	private RoomMapper roomMapper;
	//栋数管理
	@Autowired
	private BuildingMapper buildingMapper;
	//小区管理
	@Autowired
	private PlotMapper plotMapper;
	/**该方法用向数据库添加租金的数据,并返回一个状态
	  * @param Renter renter
	 * @return String
	 */
	public String addRent(Rent rent) {
		if(UserSession.getAdminUser().getLoginName() == null) {
			return "你的身份验证已失效，请重新登录......";
		}
		rent.setCreated_user(UserSession.getAdminUser().getLoginName());
		rent.setCreated_datetime(new Date());
		rent.setUpdated_user(UserSession.getAdminUser().getLoginName());
		rent.setUpdated_datetime(new Date());
		//根据房间ID获取以及房间状态获取用户信息
		Renter renter = renterMapper.selectUser(rent);
		if(renter == null) {
			return "该房间没有租客........";
		}
		//根据房间ID获取房间信息从而获取押金
		Room room =roomMapper.selectManageFee(rent);
		if(room == null) {
			return "该房间信息可以被修改或删除........";
		}
		//设置押金
		rent.setMgr_fee(room.getMgr_fee());
		//
		rent.setRent_rental(rent.getCold_water()+rent.getHot_water()+rent.getElectric_fee()+rent.getNet_fee()+rent.getMgr_fee());
		//获取是个月的水电费
		Rent rent1 = rentMapper.selectDate(rent);
		if(rent1 == null) {
			//获取离当前月最近的租金信息
			Rent rent2 = rentMapper.selectLatelyDate(rent);
			if(rent2 == null) {
				rent.setLast_cold_water(0.0);
				rent.setLast_electric_fee(0.0);
				rent.setLast_hot_water(0.0);
			}else {
				rent.setLast_cold_water(rent2.getCold_water());
				rent.setLast_electric_fee(rent2.getElectric_fee());
				rent.setLast_hot_water(rent2.getHot_water());
			}
			
		}else {
			rent.setLast_cold_water(rent1.getCold_water());
			rent.setLast_electric_fee(rent1.getElectric_fee());
			rent.setLast_hot_water(rent1.getHot_water());
		}
		//设置姓名
		rent.setUser(renter.getName());
		//设置身份证
		rent.setIdentity_card(renter.getIdentity_card());
		//调用添加方法开始Insert
		rentMapper.addRent(rent);
		return "添加成功";
	}
	
	/**该方法用来查询所有的租金信息以及费用管理的单价信息,并已Map返回
	 * @param RentQueryVo rentQueryVo
	 * @param boolean flag
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getRenVoAll(boolean flag,RentQueryVo rentQueryVo){
		return (flag)?(queryAlls(rentQueryVo)):(queryDate(rentQueryVo));
	}
	/**该方法根据一定范围的时间进行查询,并已Map返回
	 * @param RentQueryVo rentQueryVo
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryDate(RentQueryVo rentQueryVo) {
		 List<Rent> rlist =  rentMapper.getCounts(rentQueryVo);
		 int total = rlist.size();
		 double sum = calculateSum(rlist);
		 List<RentVo> list = setRentVo(rentMapper.queryDate(rentQueryVo),sum);
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("total", total);
		 map.put("rows", list);	 
		return map;
	}
	
	/**该方法用来查询所有的租金信息,并已Map返回
	 * @param RentQueryVo rentQueryVo
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryAlls(RentQueryVo rentQueryVo){
		 List<Rent> rlist = rentMapper.getCount();
		 int total = rlist.size();
		 double sum = calculateSum(rlist);
		 List<RentVo> list = setRentVo(queryAll(rentQueryVo),sum);		 
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("total", total);
		 map.put("rows", list);	 
		return map;
	}
	public double calculateSum(List<Rent> list) {
		double sum =0;
		for(int i=0,j=list.size();i<j;i++) {
			if(list.get(i).getState() == 1) {
				sum =  sum + list.get(i).getRent_rental();
			}
		}
		return sum;
	}
	/**该方法用来计算费用信息并重新赋值
	 * @param List<Rent> list  
	 * @return List<RentVo>
	 */
	public List<RentVo> setRentVo(List<Rent> list,double sum) {
		List<Cost> lcost =costMapper.findCost();
		Cost cost = (lcost.size() > 0)?(costMapper.findCost().get(0)):(new Cost(0.0,0.0,0.0));
		List<RentVo> listVo = new ArrayList();
		for(int i=0,j=list.size();i<j;i++) {
			RentVo rentVo = new RentVo(cost.getEle_unit_price(),cost.getCold_unit_price(),cost.getHot_unit_price());
			rentVo.setRent(list.get(i));
			rentVo.setCold_water_fe(list.get(i).getCold_water()*cost.getCold_unit_price());
			rentVo.setLast_cold_water_fe(list.get(i).getLast_cold_water()*cost.getCold_unit_price());
			rentVo.setHot_water_fe(list.get(i).getHot_water()*cost.getHot_unit_price());
			rentVo.setLast_hot_water_fe(list.get(i).getLast_hot_water()*cost.getHot_unit_price());
			rentVo.setElectric_fee_fe(list.get(i).getElectric_fee()*cost.getEle_unit_price());
			rentVo.setLast_electric_fee_fe(list.get(i).getLast_electric_fee()*cost.getEle_unit_price());
			rentVo.setUse_cold_water(list.get(i).getCold_water() - list.get(i).getLast_cold_water());
			rentVo.setUse_hot_water(list.get(i).getHot_water() - list.get(i).getLast_hot_water());
			rentVo.setUse_electric(list.get(i).getElectric_fee() - list.get(i).getLast_electric_fee());
			rentVo.setSum(sum);
			//根据设置名字
			setIdName(rentVo);
			listVo.add(rentVo);
		}
		return listVo;
	}
	/**该方法用来获取与小区、栋数、房间ID所对应的名称并设置
	 * @RenterVo renterVo
	 * @return RenterVo
	 */
	public  RentVo setIdName(RentVo rentVo) {
		Room room = roomMapper.findRoomNamez(rentVo);
		Building building = buildingMapper.findBuildingNamez(rentVo);
		Plot plot = plotMapper.findPlotNamez(rentVo);
		if(room != null) {
			rentVo.setRname(room.getRoom_num());
		}
		if(building != null) {
			rentVo.setBname(building.getBuildingNo());
		}
		if(plot != null) {
			rentVo.setPname(plot.getPname());
		}
		return rentVo;
	}
	
	/**该方法用来查询所有的租金信息,并已List返回
	 * @return List<Rent>
	 */
	public List<Rent> queryAll(RentQueryVo rentQueryVo) {		
		return rentMapper.queryAll(rentQueryVo);
	}


	/**该方法用来删除租金信息
	 * @param RentVo dateTimeVo
	 * @return String
	 */
	public String deleteRent(Rent rent) {
		rentMapper.deleteRent(rent);
		return (findId(rent.getId()) == null)?("true"):("false");
	}
	/**该方法用来对租金信息进行修改
	 * @param RentVo dateTimeVo
	 * @return String
	 */
	public String updateRent(Rent rent) {
		if(UserSession.getAdminUser().getLoginName() == null) {
			return "你的身份验证已失效，请重新登录......";
		}
		//获取费用管理对象
		List<Cost> lcost =costMapper.findCost();
		Cost cost = (lcost.size() > 0)?(costMapper.findCost().get(0)):(new Cost(0.0,0.0,0.0));
		
		rent.setUpdated_user(UserSession.getAdminUser().getLoginName());
		rent.setUpdated_datetime(new Date());
		//计算总额
		rent.setRent_rental((rent.getCold_water()*cost.getCold_unit_price())+
				(rent.getHot_water()*cost.getHot_unit_price())+(rent.getElectric_fee()*cost.getEle_unit_price())+rent.getNet_fee());
		//调用方法就是修改
		rentMapper.updateRent(rent);
	
		return "修改成功";
		
	}
	/**该方法用来根据传入的参数查询对应的id并返回
	 * @param id  integer 
	 * @return Rent
	 */
	public Rent findId(Integer id) {
		return rentMapper.findId(id);
	}
	/**该方法用来查询离这个月最近的月份并返回
	 * @param Rent rent
	 * @return Rent
	 */
	public Rent selectLatelyDate(Rent rent) {
		return rentMapper.selectLatelyDate(rent);
	}

}
