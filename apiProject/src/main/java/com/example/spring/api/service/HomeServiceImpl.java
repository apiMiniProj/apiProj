package com.example.spring.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.api.controller.HomeController;
import com.example.spring.api.mapper.HomeMapper;
import com.example.spring.api.model.ApiUser;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeMapper homeMapper; 
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override 
	public List<ApiUser> findApiUsers() { 
		// TODO Auto-generated method stub 

		logger.info("HomeServiceImpl.findApiUsers"); 
		
		return homeMapper.findApiUsers(); 
	}
}
