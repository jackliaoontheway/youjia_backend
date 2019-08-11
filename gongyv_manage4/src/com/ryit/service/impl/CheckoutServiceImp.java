package com.ryit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryit.mapper.CheckoutMapper;
import com.ryit.service.CheckoutService;

@Service
public class CheckoutServiceImp implements CheckoutService{

	@Autowired
	private CheckoutMapper cMapper;
}
