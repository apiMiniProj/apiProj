package com.example.spring.api.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.api.model.ApiUser;
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
		logger.info(">>HomeController.login");
		logger.info("id : "+map.get("userId"));
		logger.info("pw : "+map.get("userPw"));
		
		map.put("adminYn", "Y");
		
		//세션 생성
		HttpSession session = req.getSession();
		
		final ApiUser user = homeService.findApiUser(map);
		
		if (user != null) {
			logger.info("user : "+user.toString());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userIp", req.getRemoteAddr());
			session.setAttribute("adminYn", user.getAdminYn());
			return user;
		}else {
			return 0;
		}
	}
}
