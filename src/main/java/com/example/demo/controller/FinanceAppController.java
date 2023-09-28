package com.example.demo.controller;

import java.util.List;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderServices;
import com.example.demo.utilities.Excel;

@Controller
//@CrossOrigin("*")
public class FinanceAppController {
	
	@Autowired
	OrderRepository orderRepo ;
	
	
	@Autowired
	private OrderServices service;
	
	//@PostMapping ("")public ResponseEntity<?> upload
//	
//	@PostMapping("/order/upload")
//	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserDetails userDetails) {
//	    if (Excel.checkExcelFormat(file)) {
//	        // true
//	        this.service.saveImportedOrders(file, userDetails);
//	        Map<String, String> response = Map.of("message", "File is uploaded and data is saved to db");
//	        return ResponseEntity.ok(response);
//	    }
//	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file");
//	}
	
	@GetMapping("/analytics")
	public ModelAndView showAnalytics() {
	    ModelAndView modelAndView = new ModelAndView("analytics");
	    // add model data as needed
	    return modelAndView;
	}
	


	

	    @GetMapping("/logout")
	    public String logout() {
	        // Redirect to a login page or any other page after logout
	        return "redirect:/login";
	    }
	
	    
	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login.html";
	    }

	
	 


	



}
