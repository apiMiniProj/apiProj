package com.example.spring.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.model.ApiProd;
import com.example.spring.api.model.ApiQuery;
import com.example.spring.api.model.ApiUser;
import com.example.spring.api.security.CustomUser;
import com.example.spring.api.security.CustomUserDetailsService;
import com.example.spring.api.security.JwtTokenProvider;
import com.example.spring.api.service.AdminService;
import com.example.spring.api.service.HomeService;
import com.example.spring.api.service.ProdService;

@RestController
public class ApiController {
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProdService ProdService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JwtTokenProvider jwtTProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
		
	/** 인증 및 인가 **/
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public @ResponseBody Object createAuthToken(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ApiController.createAuthToken user id : "+map.get("userId"));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		
		final ApiUser user = homeService.findApiUser(map);
		String token = "";
		
		if (user != null) {
			//토큰 가져오기
			CustomUser customUser = (CustomUser)customUserDetailsService.loadUserByUsername(user.getUserId());
			token =  jwtTProvider.createToken(customUser.getUsername(), customUser.getAuthorities());
			
			httpHeaders.add("Authorization", token);
		}
		
		resultMap.put("accessToken", token);
		return new ResponseEntity<>(resultMap, httpHeaders, HttpStatus.OK);
	}
	
	/** 상품조회 **/
	@RequestMapping(value = "/api/SelectApiProds", method = RequestMethod.GET)
	public @ResponseBody Object SelectApiProds(@RequestParam Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ApiController.SelectApiProds");
		final ApiProd apiProd = ProdService.SelectApiProds(map);
		return apiProd;
	}
	
	/** 상품저장 **/
	@RequestMapping(value = "/api/SaveApiProds", method = RequestMethod.POST)
	public @ResponseBody Object SaveApiProds(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
				
		HttpSession session = req.getSession();
		map.put("userId", session.getAttribute("userId"));
		map.put("userIp", session.getAttribute("userIp"));
		
		final int cntS = ProdService.SaveApiProds(map);
		return cntS;
	}
	
	/** 상품삭제 **/
	@RequestMapping(value = "/api/DeleteApiProds", method = RequestMethod.POST)
	public @ResponseBody Object DeleteApiProds(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ApiController.DeleteApiProds");
		
		final int cntD = ProdService.DeleteApiProds(map);
		return cntD;
	}
	
	/** 커스텀 api GET 조회 **/
	@RequestMapping(value = "/api/customs/{customId}", method = RequestMethod.POST)
	public ResponseEntity<Object> getCustomsApi(@PathVariable("customId") String customId, @RequestBody(required = false) Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ApiController.getCustomsApi");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("customId", customId);
		
		// 쿼리내역 있는지 조회
		final ApiQuery apiQuery = adminService.getApiQuery(queryMap);
		
		ResponseEntity<Object> entity = null;
		String query = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		if (apiQuery != null) {
			query = apiQuery.getQueryText();
			logger.info("get query -"+query);
			
			//쿼리 매핑
			if (map != null) {
				logger.info("get map -"+map.toString());
				Iterator<String> keys = map.keySet().iterator();
				while( keys.hasNext() ){
					String key = keys.next();
					String value = (String) map.get(key);
					logger.info("키 : "+key+", 값 : "+value);
					
					if(key != "") {
						query = query.replaceAll(":"+key, "\'"+value+"\'");
					}
				}
			}
			
			//쿼리에서 치환못한 파라미터 있는지 체크
			Pattern p = Pattern.compile("([:])\\w+"); // 검색할 문자열 패턴
			Matcher m = p.matcher(query);
			while (m.find()) {
				logger.info("not bind param : "+m.group());
				query = query.replaceAll(m.group(), "\'\'");
			}
			
			logger.info("mapping param query - "+query);
			
			if(!query.equals("")) {
				queryMap.put("customQueryText", query);
				list = adminService.getCustomsApi(queryMap);
				logger.info("resultMap : "+resultMap.toString());
			}
		}
		
		if (list != null) {
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		}else{
			entity = new ResponseEntity<>("NO DATA", HttpStatus.NOT_FOUND);
		}
		
		return entity;
	}
	
}
