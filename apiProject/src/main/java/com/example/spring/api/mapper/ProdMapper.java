package com.example.spring.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.example.spring.api.model.ApiProd;

@Mapper
public interface ProdMapper {

	ApiProd SelectApiProds(Map map);
	
	int SaveApiProds(Map map);
	
	int DeleteApiProds(Map map);
}
