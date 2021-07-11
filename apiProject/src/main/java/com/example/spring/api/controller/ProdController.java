package com.example.spring.api.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.model.ApiProd;
import com.example.spring.api.service.ProdService;

@Controller
public class ProdController {
	@Autowired
	private ProdService ProdService;
	private static final Logger logger = LoggerFactory.getLogger(ProdController.class);
	
	@RequestMapping(value = "/prods", method = RequestMethod.GET)
	public String prod(Locale locale, Model model) {
			
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date); 
		
		return "prods";
	}
	
	@RequestMapping(value = "/prods/SelectApiProds", method = RequestMethod.GET)
	public @ResponseBody Object SelectApiProds(@RequestParam Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ProdController.SelectApiProds");
		final ApiProd apiProd = ProdService.SelectApiProds(map);
		return apiProd;
	}
	
	/** 상품저장 **/
	@RequestMapping(value = "/prods/SaveApiProds", method = RequestMethod.POST)
	public @ResponseBody Object SaveApiProds(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
				
		HttpSession session = req.getSession();
		map.put("userId", session.getAttribute("userId"));
		map.put("userIp", session.getAttribute("userIp"));
		
		final int cntS = ProdService.SaveApiProds(map);
		return cntS;
	}
	
	/** 상품삭제 **/
	@RequestMapping(value = "/prods/DeleteApiProds", method = RequestMethod.POST)
	public @ResponseBody Object DeleteApiProds(@RequestBody Map<String, Object> map, HttpServletRequest req) throws Exception {
		logger.info(">>ProdController.DeleteApiProds");
		
		final int cntD = ProdService.DeleteApiProds(map);
		return cntD;
	}
}
