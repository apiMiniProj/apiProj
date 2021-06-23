package com.example.spring.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.api.model.ApiUser;

@Mapper
public interface HomeMapper {
	List<ApiUser> findApiUsers();
}
