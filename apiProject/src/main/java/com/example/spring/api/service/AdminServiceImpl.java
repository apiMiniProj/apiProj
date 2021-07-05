package com.example.spring.api.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.api.controller.AdminController;
import com.example.spring.api.mapper.AdminMapper;
import com.example.spring.api.model.ApiQuery;

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
}
