package com.example.spring.api.service;

import java.util.List;
import java.util.Map;

import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;

public interface AdminService {
	public ApiQuery getApiQuery(Map map);
	public int saveCustomQuery(Map map);
	public ApiUser getApiUser(Map map);
	public int saveApiUser(Map map);
	

	public List<Map<String, Object>> getCustomsApi(Map map);
}
