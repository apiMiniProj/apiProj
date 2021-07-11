package com.example.spring.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.api.model.ApiUser;

@Mapper
public interface HomeMapper {
	List<ApiUser> findApiUsers();
	ApiUser findApiUser(Map map);
	ApiUser loadUserByUsername(String userName);
}
