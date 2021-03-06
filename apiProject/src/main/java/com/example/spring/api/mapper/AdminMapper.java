package com.example.spring.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;

@Mapper
public interface AdminMapper {
	ApiQuery getApiQuery(Map map);
	int saveCustomQuery(Map map);
	ApiUser getApiUser(Map map);
	int saveApiUser(Map map);
	
	List<Map<String, Object>> getCustomsApi(Map map);
}
