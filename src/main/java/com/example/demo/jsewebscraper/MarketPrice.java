package com.example.demo.jsewebscraper;

public class MarketPrice {
	
	
	private String stock;
	private String closingPrice;


	MarketPrice(String stock, String closingPrice ) {
		this.stock = stock ;
		this.closingPrice = closingPrice ;
	}


	public String getStock() {
		return stock;
	}


	public void setStock(String stock) {
		this.stock = stock;
	}


	public String getClosingPrice() {
		return closingPrice;
	}


	public void setClosingPrice(String closingPrice) {
		this.closingPrice = closingPrice;
	}
	
	

}
