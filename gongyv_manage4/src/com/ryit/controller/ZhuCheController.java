package com.ryit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Renter;
import com.ryit.service.RenterService;
import com.ryit.service.impl.RenterServiceImpl;


@Controller
@RequestMapping("pages/admin")
public class ZhuCheController {
	
	@Autowired
	private RenterService renterService;
	
	@RequestMapping("/zhuCe")
	public void zhuCe(HttpServletRequest request,String userName,String password,String name,String phone,String identity_card,@RequestParam("files") MultipartFile[] files){
		Renter renter = new Renter();
		renter.setUser_name(userName);
		renter.setPassword(password);
		renter.setName(name);
		renter.setPhone(phone);
		renter.setIdentity_card(identity_card);
		renter.setBid(0);
		renter.setRid(0);
		renter.setState(1);
		renter.setR_state(1);
		
		renterService.addRenter(renter, files,true);
		
	}
	
	
	
	
	
	
	
}
