package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_market_prices")

public class CurrentMarketPrice {
	

	public CurrentMarketPrice(String stockName, Double stockPrice) {
		super();
		this.stockName = stockName;
		this.stockPrice = stockPrice;
	}

	@Column
	private String stockName;
	
	@Column
	private Double stockPrice;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long stockId;
	
	
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	
	
	


}
