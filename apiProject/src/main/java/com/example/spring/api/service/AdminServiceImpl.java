package com.example.spring.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.api.controller.AdminController;
import com.example.spring.api.mapper.AdminMapper;
import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper; 
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Override 
	public ApiQuery getApiQuery(Map map) { 
		logger.info("AdminServiceImpl.getApiQuery"); 
		return adminMapper.getApiQuery(map); 
	}
	
	@Override 
	public int saveCustomQuery(Map map) {
		logger.info("AdminServiceImpl.saveCustomQuery"); 
		int cnt = adminMapper.saveCustomQuery(map);
		return cnt;
	}
	
	@Override 
	public ApiUser getApiUser(Map map) { 
		logger.info("AdminServiceImpl.getApiUser"); 
		return adminMapper.getApiUser(map); 
	}
	
	@Override 
	public int saveApiUser(Map map) {
		logger.info("AdminServiceImpl.saveApiUser"); 
		int cnt = adminMapper.saveApiUser(map);
		return cnt;
	}
	
	@Override 
	public List<Map<String, Object>> getCustomsApi(Map map){
		logger.info("AdminServiceImpl.getCustomsApi"); 
		return adminMapper.getCustomsApi(map); 
	}
}
