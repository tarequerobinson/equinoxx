package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entities.CurrentMarketPrice;
import com.example.demo.entities.User;
import com.example.demo.repositories.CurrentMarketPricesRepo;

@Service
@Component

public class CurrentMarketPriceServices {
	
	@Autowired
	public CurrentMarketPricesRepo currentMarketPricesRepo;
	
	public void addCurrentMarketPrice (CurrentMarketPrice currentMarketPrice) {
		
		 this.currentMarketPricesRepo.save(currentMarketPrice);
		 
	}

}
