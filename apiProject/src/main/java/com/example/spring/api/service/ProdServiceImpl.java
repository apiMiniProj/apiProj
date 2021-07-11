package com.example.spring.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.api.controller.ProdController;
import com.example.spring.api.mapper.ProdMapper;
import com.example.spring.api.model.ApiProd;

@Service
public class ProdServiceImpl implements ProdService{
	@Autowired
	private ProdMapper ProdMapper; 
	private static final Logger logger = LoggerFactory.getLogger(ProdController.class);
		
	@Override 
	public ApiProd SelectApiProds(Map map) { 
		//logger.info("ProdServiceImpl"+ProdMapper.SelectApiProds(map));
		return ProdMapper.SelectApiProds(map);
	}
	
	@Override 
	public int SaveApiProds(Map map) {
		int cnt = ProdMapper.SaveApiProds(map);
		return cnt;
	}
	
	@Override 
	public int DeleteApiProds(Map map) {
		int cntD = ProdMapper.DeleteApiProds(map);
		return cntD;
	}
	
}
