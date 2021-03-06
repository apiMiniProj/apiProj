package com.example.spring.api.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProdController {

	private static final Logger logger = LoggerFactory.getLogger(ProdController.class);
	
	@RequestMapping(value = "/prods", method = RequestMethod.GET)
	public String prod(Locale locale, Model model) {
			
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date); 
		
		return "prods";
	}
}
