package com.ryit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Advertising;

@Service
public interface AdvertisingService {
	
	//增加广告图片
	void addAdvertising(MultipartFile file,String theme,String pic_num);
	
	//根据id删除广告
	void deleteAdvertising(int id);
	
	//查询所有的广告
	List<Advertising> selectAll();
	
	int getAllRecord();
	List<Advertising> getAllRenter(int currentPage,int pageSize);
}
