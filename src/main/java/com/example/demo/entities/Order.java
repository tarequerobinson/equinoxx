package com.example.demo.entities;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;

@Entity
@Table(name = "equityorders")
public class Order {

	@Column
	//@Temporal(TemporalType.DATE)
	private String orderDate;

	@Id
	private Integer equityOrderNumber;

	@Column
	private String status;

	@Column( length = 25)
	private String stockExchangCode;

	@Column
	private String currency;

	@Column( length = 25)
	private String stockSymbol;

	@Column(length = 25)
	private String orderType;

	@Column
	private Integer quantity;

	@Column
	private Double avgFillPrice;

	@Column
	private Double estimatedValue;

	@Column
	private String timeInForce;

	
	@Column
	private Double limitPrice;
	
	@Column
	private Long userID;
	

	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getEquityOrderNumber() {
		return equityOrderNumber;
	}

	public void setEquityOrderNumber(Integer equityOrderNumber) {
		this.equityOrderNumber = equityOrderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStockExchangCode() {
		return stockExchangCode;
	}

	public void setStockExchangCode(String stockExchangCode) {
		this.stockExchangCode = stockExchangCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAvgFillPrice() {
		return avgFillPrice;
	}

	public void setAvgFillPrice(Double avgFillPrice) {
		this.avgFillPrice = avgFillPrice;
	}

	public Double getEstimatedValue() {
		return estimatedValue;
	}

	public void setEstimatedValue(Double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public String getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
	}

	public Double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}
	
	
	
	//method to create a order object from the data entered in  a form on the frontend 
		//it accepts a Hashmap of string key and value pairs
	public static Order createFromFormData(Map<String, Object> formData) {
	    Order order = new Order();
	    order.setOrderDate(formData.get("orderDate") instanceof String ? (String) formData.get("orderDate") : null);
	    order.setEquityOrderNumber(formData.get("equityOrderNumber") instanceof Integer ? (Integer) formData.get("equityOrderNumber") : null);
	    order.setStatus(formData.get("status") instanceof String ? (String) formData.get("status") : null);
	    order.setStockExchangCode(formData.get("stockExchangCode") instanceof String ? (String) formData.get("stockExchangCode") : null);
	    order.setCurrency(formData.get("currency") instanceof String ? (String) formData.get("currency") : null);
	    order.setQuantity(formData.get("quantity") instanceof Integer ? (Integer) formData.get("quantity") : null);
	    order.setAvgFillPrice(formData.get("avgFillPrice") instanceof Double ? (Double) formData.get("avgFillPrice") : null);
	    order.setEstimatedValue(formData.get("estimatedValue") instanceof Double ? (Double) formData.get("estimatedValue") : null);
	    order.setLimitPrice(formData.get("limitPrice") instanceof Double ? (Double) formData.get("limitPrice") : null);
	    order.setTimeInForce(formData.get("timeInForce") instanceof String ? (String) formData.get("timeInForce") : null);
	    order.setStockSymbol(formData.get("stockSymbol") instanceof String ? (String) formData.get("stockSymbol") : null);
	    order.setOrderType(formData.get("orderType") instanceof String ? (String) formData.get("orderType") : null);
	    return order;
	}

}
