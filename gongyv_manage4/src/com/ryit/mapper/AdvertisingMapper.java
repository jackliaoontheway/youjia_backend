package com.ryit.mapper;

import java.util.List;
import java.util.Map;

import com.ryit.entity.Advertising;

public interface AdvertisingMapper {
	
	//增加广告
	void addAdvertising(Advertising advertising);
	
	//根据id删除广告
	void deleteAdvertising(int id);
	
	//查询所有广告
	List<Advertising> selectAll();
	
	int getAllRecord();
	List<Advertising> getAllAdvertising(Map map);
}
