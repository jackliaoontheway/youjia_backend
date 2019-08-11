package com.ryit.service.impl;

import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Advertising;
import com.ryit.mapper.AdvertisingMapper;
import com.ryit.service.AdvertisingService;
import com.ryit.utils.UserSession;

@Service
public class AdvertisingServiceImpl implements AdvertisingService{
	
	@Autowired
	private AdvertisingMapper advertisingMapper;
	//用来保证图片的不重复
	private int count;
	//增加广告图片
	public void addAdvertising(MultipartFile file,String theme,String pic_num) {
		count++;
		//文件储存路径
		String filePath = "F:\\Pic\\gg\\"+theme+count+".jpg";
        
		try {
			File saveDir = new File(filePath);
			saveDir.getParentFile().mkdirs();
            file.transferTo(saveDir);
            Advertising advertising = new Advertising();
    		advertising.setCreated_user(UserSession.getAdminUser().getLoginName());
    		advertising.setCreated_datetime(new Date());
    		advertising.setUpdated_user(UserSession.getAdminUser().getLoginName());
    		advertising.setUpdated_datetime(new Date());
    		advertising.setPic_num(pic_num);
    		advertising.setPic_url(filePath);
    		
    		advertisingMapper.addAdvertising(advertising);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	//根据id进行删除
	public void deleteAdvertising(int id) {
		advertisingMapper.deleteAdvertising(id);
		
	}
	
	//查询所有的广告
	public List<Advertising> selectAll() {
		
		return advertisingMapper.selectAll();
	}

	public int getAllRecord(){
		return advertisingMapper.getAllRecord();
	}
	
	public List<Advertising> getAllRenter(int currentPage,int pageSize){
		Map map = new HashMap();
		map.put("currentPage",currentPage);
		map.put("pageSize",pageSize);
		return advertisingMapper.getAllAdvertising(map);
	}
	
}
