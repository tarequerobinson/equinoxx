package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.CurrentMarketPrice;
import com.example.demo.jsewebscraper.MarketPrice;

public interface CurrentMarketPricesRepo extends JpaRepository <CurrentMarketPrice, Long> {
	

}
