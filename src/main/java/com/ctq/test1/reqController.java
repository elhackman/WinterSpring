package com.ctq.test1;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.mongodb.Mongo;

import com.ctq.test1.requestEntry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping(value = "/{reqCollec}")
public class reqController{
	
	@RequestMapping(params = "inspect")
	public String reqShow(Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		model.addAttribute("welcomeMessage", "Last 10 Requests on /" + reqCollec );

		MongoOperations mongoOps;
		try {
			mongoOps = new MongoTemplate(new Mongo(), "requestsdb");
			Query query = new Query().with(new Sort(Sort.Direction.DESC, "Time"));
			List<requestEntry> results = mongoOps.find(query.limit(10),requestEntry.class,reqCollec);
			String lastreq = "";
			for (requestEntry a : results)
			{
			    lastreq += a.toString();
			}
			reqModel.addAttribute("lastreq", lastreq);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "inspect";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String reqGET(Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		
		reqModel.addAttribute("method", "GET");
		reqModel.addAttribute("serverTime", getReqtime());
		reqModel.addAttribute("header", requestEntity.getHeaders().toString());
		reqModel.addAttribute("body", requestEntity.getBody());		
		addReqentry("GET", getReqtime(), requestEntity.getHeaders().toString(), requestEntity.getBody(), reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String reqPOST(Model model, HttpEntity<String> requestEntity, Model reqModel, @PathVariable String reqCollec) {
		
		reqModel.addAttribute("method", "POST");
		reqModel.addAttribute("serverTime", getReqtime());
		reqModel.addAttribute("header", requestEntity.getHeaders().toString());
		reqModel.addAttribute("body", requestEntity.getBody());		
		addReqentry("POST", getReqtime(), requestEntity.getHeaders().toString(), requestEntity.getBody(), reqCollec);
		
		model.addAttribute("welcomeMessage", "POSTBin" );
		
		return "home";
	}	
	
	private String getReqtime(){
		Locale locale = Locale.getDefault();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		return dateFormat.format(date);
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