package com.example.spring.api.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;
import com.example.spring.api.service.HomeService;

@RestController
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date); 
		
		ApiQuery oj = new ApiQuery();
		oj.setCustomId(1);
		
		logger.info("getId.", oj.getCustomId()); 
		
		return "home";
	}
	
	/** 테스트 **/
	@RequestMapping(value = "/userTest", method = RequestMethod.GET)
	public List<ApiUser> users(@RequestParam Map<String, Object> map) throws Exception {
		final List<ApiUser> userList = homeService.findApiUsers();		
		return userList;
	}
}
