package com.ryit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.BuildingView;
import com.ryit.entity.RentView;
import com.ryit.entity.Renter;
import com.ryit.entity.RoomView;
import com.ryit.service.RenterService;

@Controller
@RequestMapping("api")
@CrossOrigin
public class ApiController {

	@Autowired
	private RenterService renterService;

	@PostMapping("/login")
	public @ResponseBody boolean login(HttpServletRequest request, String loginName, String password,
			HttpServletResponse response) {

		if (loginName == null || password == null) {
			return false;
		}

		Renter renter = renterService.findByLoginName(loginName);
		if (renter == null) {
			return false;
		}
		if (!renter.matchPassword(password)) {
			return false;
		}

		HttpSession session = request.getSession();
		session.setAttribute("renter", renter);

		return true;
	}

	@PostMapping("/register")
	public @ResponseBody boolean register(HttpServletRequest request, String phone, String idCard, String password,
			String confirmedPassword, @RequestParam("files") MultipartFile[] files) {

		Renter renter = new Renter();
		renter.setIdentity_card(idCard);
		renter.setUser_name(idCard);
		renter.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		renter.setPhone(phone);
		renter.setBid(0);
		renter.setRid(0);
		renter.setState(1);
		renter.setR_state(1);

		return renterService.register(renter, files);

	}

	/**
	 * 获取当月租金账单信息
	 * 
	 * @param request
	 * @param loginName
	 * @param response
	 * @return
	 */
	@PostMapping("/fetchRent")
	public @ResponseBody RentView fetchRent(HttpServletRequest request, String loginName,
			HttpServletResponse response) {

		if (loginName == null) {
			return null;
		}

		Renter renter = renterService.findByLoginName(loginName);
		if (renter == null) {
			return null;
		}

		RentView rentView = new RentView();
		// TODO get rent data
		return rentView;
	}

	/**
	 * 申请退租
	 * 
	 * @param loginName
	 * @param response
	 * @return
	 */
	@PostMapping("/fetchBuilding")
	public @ResponseBody List<BuildingView> fetchBuilding(HttpServletRequest request, HttpServletResponse response) {

		List<BuildingView> list = new ArrayList<>();

		BuildingView building = new BuildingView();
		building.setBuildingId(1);
		building.setAddressInfo("富裕路118号");
		building.setBuildingNumber("青年社区一栋");
		List<RoomView> roomList = new ArrayList<>();
		RoomView room = new RoomView();
		room.setRoomNumber("101");
		room.setType("一室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		room = new RoomView();
		room.setRoomNumber("102");
		room.setType("一室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		room = new RoomView();
		room.setRoomNumber("103");
		room.setType("一室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		building.setRoomList(roomList);
		list.add(building);
		
		building = new BuildingView();
		building.setBuildingId(2);
		building.setAddressInfo("富裕路119号");
		building.setBuildingNumber("青年社区二栋");
		roomList = new ArrayList<>();
		room = new RoomView();
		room.setRoomNumber("201");
		room.setType("二室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		room = new RoomView();
		room.setRoomNumber("202");
		room.setType("二室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		room = new RoomView();
		room.setRoomNumber("203");
		room.setType("二室一厅");
		room.setStatus("EMPTY");
		roomList.add(room);
		building.setRoomList(roomList);
		list.add(building);

		return list;
	}

	/**
	 * 租户信息 租金 时间等
	 * 
	 * @param request
	 * @param loginName
	 * @param response
	 * @return
	 */
	@PostMapping("/fetchRoom")
	public @ResponseBody RoomView fetchRoom(HttpServletRequest request, String loginName,
			HttpServletResponse response) {

		if (loginName == null) {
			return null;
		}

		Renter renter = renterService.findByLoginName(loginName);
		if (renter == null) {
			return null;
		}

		RoomView roomView = new RoomView();
		roomView.setBuildingNumber("青年社区一栋608");
		roomView.setDeposit(4000d);
		roomView.setLeaseEndDate("2020-05-01");
		roomView.setLeaseStartDate("2019-05-01");
		roomView.setStatus("NORMAL");
		return roomView;
	}

	/**
	 * 申请退租
	 * 
	 * @param loginName
	 * @param response
	 * @return
	 */
	@PostMapping("/chekoutRoom")
	public @ResponseBody boolean chekoutRoom(HttpServletRequest request, String loginName,
			HttpServletResponse response) {

		if (loginName == null) {
			return false;
		}

		Renter renter = renterService.findByLoginName(loginName);
		if (renter == null) {
			return false;
		}

		// 把租户的状态改为 WITHDRAW

		return true;
	}

}
