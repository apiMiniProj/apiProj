package com.example.spring.api.model;

import lombok.Data;

@Data
public class ApiUser {
	String userId;
	String userPw;
	String userAvailable;
	String adminYn;
}
