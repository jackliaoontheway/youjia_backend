package com.ryit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryit.service.CheckoutService;

//退房管理
@Controller
@RequestMapping("pages/admin")
public class CheckoutController {

	@Autowired
	private CheckoutService checkout;
	
	
	@RequestMapping("/")
	public void add(){
		
	}
	
}
