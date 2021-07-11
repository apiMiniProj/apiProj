package com.example.spring.api.service;

import java.util.List;
import java.util.Map;

import com.example.spring.api.model.ApiUser;

public interface HomeService {
	public List<ApiUser> findApiUsers();
	public ApiUser findApiUser(Map map);
}
