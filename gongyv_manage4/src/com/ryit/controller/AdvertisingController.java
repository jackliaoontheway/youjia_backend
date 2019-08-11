package com.ryit.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Advertising;
import com.ryit.service.AdvertisingService;


@Controller
@RequestMapping("pages/admin")
public class AdvertisingController {
	
	@Autowired
	private AdvertisingService advertisingService;
	
	
	//增加广告图片
	@RequestMapping("/addPic")
	public @ResponseBody Object add(@RequestParam("file") MultipartFile file, HttpServletRequest request,String pic_num,String theme){
		
		advertisingService.addAdvertising(file,theme,pic_num);
        return true;
	}
	
	
	
	
	//根据id删除图片
	@RequestMapping("/deletePic")
	public @ResponseBody Object deletePic(int id){
		
		advertisingService.deleteAdvertising(id);
		return true;
	}
	
	//查询所有图片
	@RequestMapping("/selectPic")
	public @ResponseBody Object selectAll(HttpServletResponse response, HttpServletRequest request){
		return getAll(request);
	}
	
	
	 @RequestMapping("advertisingList")
	 @ResponseBody
	 public Map<String, Object> getAll(HttpServletRequest request){
		
		 int currentPage=Integer.parseInt(request.getParameter("page"));
		 int pageSize=Integer.parseInt(request.getParameter("rows"));
		 
		 int total = advertisingService.getAllRecord();
		 
		 List<Advertising> list = advertisingService.getAllRenter((currentPage-1)*pageSize, pageSize);
		 
		
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("total", total);
		 map.put("rows", list);	 
		 return map;
		 
	 }
	
}
