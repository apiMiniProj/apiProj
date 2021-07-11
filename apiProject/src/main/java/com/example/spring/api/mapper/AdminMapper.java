package com.example.spring.api.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.api.model.ApiQuery;

@Mapper
public interface AdminMapper {
	ApiQuery getApiQuery(Map map);
	int saveCustomQuery(Map map);
}
