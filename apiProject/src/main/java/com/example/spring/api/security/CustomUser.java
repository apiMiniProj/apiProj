package com.example.spring.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.spring.api.model.ApiUser;

public class CustomUser extends User {
	//private ApiUser user;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(ApiUser user) {
		super(user.getUserId(), user.getUserPw(), authorities(user));
		//this.user = user;
	}
	
	private static Collection<? extends GrantedAuthority> authorities(ApiUser user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		if (user.getAdminYn().equals("Y")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
		} else { 
			authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
		} 
		return authorities; 
	}
}