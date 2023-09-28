package com.example.demo.utilities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BuyOrderTotal {
		
		@JsonProperty("stock_symbol")
	    private String stockSymbol;
	    @JsonProperty("total_buy_orders")
	    private double totalBuyOrders;
	    
	    public BuyOrderTotal(String stockSymbol, double totalBuyOrders) {
	        this.stockSymbol = stockSymbol;
	        this.totalBuyOrders = totalBuyOrders;
	    }

	    // Constructors, getters, and setters
	}