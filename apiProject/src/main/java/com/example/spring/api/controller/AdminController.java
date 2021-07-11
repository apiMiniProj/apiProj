package com.example.spring.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;
import com.example.spring.api.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/** 어드민 메인 화면 **/
	@RequestMapping(value = "/main")
	public String main(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info(">>AdminController.main");
		return "main";
	}
	
	/** 어드민 API Intro **/
	@RequestMapping(value = "/apiIntro", method = RequestMethod.GET)
	public String apiIntro() throws Exception {
		logger.info(">>AdminController.apiIntro");
		return "apiIntro";
	}
	
	/** 어드민 API 사용자등록 **/
	@RequestMapping(value = "/apiUser", method = RequestMethod.GET)
	public String apiUser() throws Exception {
		logger.info(">>AdminController.apiUser");
		return "apiUser";
	}
	
	/** 어드민 API 커스텀쿼리등록 화면 **/
	@RequestMapping(value = "/apiCustomQuery", method = RequestMethod.GET)
	public String apiCustomQuery() throws Exception {
		logger.info(">>AdminController.apiCustomQuery");	
		return "apiCustomQuery";
	}
	
	/** 어드민 API 커스텀쿼리등록 조회 **/
	@RequestMapping(value = "/admin/apiQuery", method = RequestMethod.GET)
	public @ResponseBody Object getApiQuery(@RequestParam Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>AdminController.getApiQuery");
		final ApiQuery apiQuery = adminService.getApiQuery(map);
		return apiQuery;
	}
	
	/** 어드민 API 커스텀쿼리등록 조회 **/
	@RequestMapping(value = "/admin/apiQuery", method = RequestMethod.POST)
	public @ResponseBody Object postApiQuery(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>AdminController.postApiQuery");
		
		HttpSession session = req.getSession();
		map.put("ssUserId", session.getAttribute("userId"));
		map.put("userIp", session.getAttribute("userIp"));
		
		final int cnt = adminService.saveCustomQuery(map);
		return cnt;
	}
	
	/** 어드민 API 사용자등록 조회 **/
	@RequestMapping(value = "/admin/apiUser", method = RequestMethod.GET)
	public @ResponseBody Object getApiUser(@RequestParam Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>AdminController.getApiUser");
		final ApiUser apiUser = adminService.getApiUser(map);
		return apiUser;
	}
	
	/** 어드민 API 사용자등록 조회 **/
	@RequestMapping(value = "/admin/apiUser", method = RequestMethod.POST)
	public @ResponseBody Object postApiUser(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>AdminController.postApiUser");
		
		HttpSession session = req.getSession();
		map.put("ssUserId", session.getAttribute("userId"));
		map.put("userIp", session.getAttribute("userIp"));
		
		final int cnt = adminService.saveApiUser(map);
		return cnt;
	}
}
