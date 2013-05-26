package com.ctq.test1;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.mongodb.Mongo;
import com.ctq.test1.requestEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/{reqCollec}", method = RequestMethod.GET)
	public String homeget(Locale locale, Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		reqModel.addAttribute("method", "GET");		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		reqModel.addAttribute("serverTime", formattedDate);
		
		HttpHeaders header = requestEntity.getHeaders();
		String rHeader = header.toString();
		reqModel.addAttribute("header", rHeader);	

		String rBody = requestEntity.getBody();
		reqModel.addAttribute("body", rBody);	
		
		addReqentry("GET",formattedDate, rHeader, rBody, reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}
	
	@RequestMapping(value = "/{reqCollec}", method = RequestMethod.HEAD)
	public String homehead(Locale locale, Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		reqModel.addAttribute("method", "HEAD");		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		reqModel.addAttribute("serverTime", formattedDate);
		
		HttpHeaders header = requestEntity.getHeaders();
		String rHeader = header.toString();
		reqModel.addAttribute("header", rHeader);	

		String rBody = requestEntity.getBody();
		reqModel.addAttribute("body", rBody);	
		
		addReqentry("HEAD",formattedDate, rHeader, rBody, reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}
	
	@RequestMapping(value = "/{reqCollec}", method = RequestMethod.POST)
	public String homeput(Locale locale, Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		reqModel.addAttribute("method", "POST");		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		reqModel.addAttribute("serverTime", formattedDate);
		
		HttpHeaders header = requestEntity.getHeaders();
		String rHeader = header.toString();
		reqModel.addAttribute("header", rHeader);	

		String rBody = requestEntity.getBody();
		reqModel.addAttribute("body", rBody);	
		
		addReqentry("POST",formattedDate, rHeader, rBody, reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}
	
	@RequestMapping(value = "/{reqCollec}", method = RequestMethod.POST)
	public String homepost(Locale locale, Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		reqModel.addAttribute("method", "POST");		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		reqModel.addAttribute("serverTime", formattedDate);
		
		HttpHeaders header = requestEntity.getHeaders();
		String rHeader = header.toString();
		reqModel.addAttribute("header", rHeader);	

		String rBody = requestEntity.getBody();
		reqModel.addAttribute("body", rBody);	
		
		addReqentry("POST",formattedDate, rHeader, rBody, reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}
	
    private void addReqentry(String reqMethod, String reqTime, String reqHeader, String reqBody, String reqCollec)
    {
        MongoOperations mongoOps;
		try {
			mongoOps = new MongoTemplate(new Mongo(), "requestsdb");
	        mongoOps.insert(new requestEntry(reqMethod, reqTime, reqHeader, reqBody), reqCollec);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }	  
}