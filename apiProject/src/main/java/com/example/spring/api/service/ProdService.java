package com.example.spring.api.service;

import java.util.List;
import java.util.Map;

import com.example.spring.api.model.ApiProd;


public interface ProdService {
	public ApiProd SelectApiProds(Map map);
	public int SaveApiProds(Map map);
	public int DeleteApiProds(Map map);
}
