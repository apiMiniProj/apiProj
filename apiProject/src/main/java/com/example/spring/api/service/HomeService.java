package com.example.spring.api.service;

import java.util.List;

import com.example.spring.api.model.ApiUser;

public interface HomeService {
	public List<ApiUser> findApiUsers();
}
