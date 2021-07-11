package com.example.spring.api.model;

import java.util.Date;

import lombok.Data;

@Data
public class ApiQuery {
	int customId;
	String queryText;
	String userAvailable;
	Date regDate;
	String regEmp;
	String ip;
}
