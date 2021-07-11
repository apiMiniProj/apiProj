package com.example.spring.api.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
public class ApiProd {
	String proCode;
	String proName;
	String proCategory;
	String proBrand;
	int proPrice;
	String proInfo;
	String proAvailable;	
}