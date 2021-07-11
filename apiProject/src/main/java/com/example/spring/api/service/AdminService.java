package com.example.spring.api.service;

import java.util.Map;

import com.example.spring.api.model.ApiQuery;

public interface AdminService {
	public ApiQuery getApiQuery(Map map);
	public int saveCustomQuery(Map map);
}
