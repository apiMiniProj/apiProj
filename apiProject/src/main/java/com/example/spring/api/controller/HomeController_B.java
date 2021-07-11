package com.example.spring.api.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.api.model.ApiUser;
import com.example.spring.api.security.CustomUser;
import com.example.spring.api.security.CustomUserDetailsService;
import com.example.spring.api.security.JwtTokenProvider;
import com.example.spring.api.service.HomeService;

@Controller
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/** 로그인 페이지 **/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info(">>HomeController.home");
		return "login";
	}
	
	/** 로그인 **/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Object login(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>HomeController.login id : "+map.get("userId"));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		map.put("adminYn", "Y");
		
		//세션 생성
		HttpSession session = req.getSession();
		final ApiUser user = homeService.findApiUser(map);
		String userId = "";
		
		if (user != null) {
			logger.info("user : "+user.toString());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userPw", user.getUserPw());
			session.setAttribute("userIp", req.getRemoteAddr());
			
			userId = user.getUserId();
		}
		
		resultMap.put("userId", userId);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
