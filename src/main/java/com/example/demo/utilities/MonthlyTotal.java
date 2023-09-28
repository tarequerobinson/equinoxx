package com.example.demo.utilities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyTotal {
	
	
	@JsonProperty("month")
    private String month;
    @JsonProperty("total_spent")
    private double totalSpent;
    
    public MonthlyTotal(String month, double total_spent) {
        this.month = month;
        this.totalSpent = total_spent;
    }

}
