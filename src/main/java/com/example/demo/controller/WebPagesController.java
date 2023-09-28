package com.example.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebPagesController {
	
//	@GetMapping("/rss-page")
//    public String getRssPage() {
//        return "rss-template";
//    }
	
	
	//Directs USER to homepage
	@RequestMapping(path="/home")
	public String getHomePage() {
		return"index.html";
	}
	
	
	@GetMapping("/analyticsPage")
	public String getAnalyticsPage() {
		return"analytics.html";
	
	}
	

	@RequestMapping(path="/marketupdates")
	public String getMarketUpdatesPage() {
		return"marketupdates.html";
	
	}
	


	@RequestMapping(path="/settings")
	public String getSettingsPage() {
		return"settings.html";
	
	}
	


	@GetMapping(path="/signup")
	public String getSignUpPage() {
		return"signup.html";
	
	}
	

	@GetMapping("/newtransaction")
	public String getNewTransactionPage() {
		return"newtransaction.html";
	
	}
	
	

//	
//	@GetMapping("/login")
//    public String showLoginForm() {
//       return "login.html";
//   }
	
	
	
	
	
	

}
