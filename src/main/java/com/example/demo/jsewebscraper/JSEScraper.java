package com.example.demo.jsewebscraper;
import org.jsoup.Jsoup;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;

import com.example.demo.services.CurrentMarketPriceServices;
import com.google.gson.Gson;
import com.example.demo.entities.*;

public  class JSEScraper {
	
     //CurrentMarketPriceServices currentMarketPriceService;
	

		// TODO Auto-generated method stub
	
	public  List<MarketPrice> returnJson () {
		//String output="";
		
		List<MarketPrice> marketPrices = new ArrayList<MarketPrice>();
		
		try {
			Document doc = Jsoup.connect("https://www.jamstockex.com/trading/trade-summary/").timeout(12000).get();
			Elements body = doc.select("tbody");
			for (Element e : body.select("tr")) {
			    Element stockSymbollink = e.select("td.tw-px-3.tw-py-4.tw-text-sm.tw-whitespace-nowrap a[href]").get(1);
			    String stockSymbolhref = stockSymbollink.attr("href");
			    String stockSymbol = stockSymbollink.text();
			   // System.out.println("Stock: " + stockSymbol); // Print the title to the console
			    // Do something with the href and title
			    
			    Elements closingPricelink = e.select("#main > div > section.elementor-section.elementor-top-section.elementor-element.elementor-element-78df525.elementor-section-boxed.elementor-section-height-default.elementor-section-height-default.jet-parallax-section > div.elementor-container.elementor-column-gap-default > div > div > div > div > div > section > div.elementor-container.elementor-column-gap-default > div > div > div > div > div > div > div > div > div:nth-child(5) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4)");
			    String closingPricelinkhreff = closingPricelink.attr("href");
			    //System.out.println("Type of closing price value: " + closingPricelink.text().getClass());

			    Element tdElement = e.select("td.tw-px-3.tw-py-4.tw-text-sm.tw-text-right.tw-whitespace-nowrap").get(1);
			    String value = tdElement.text();
			    //System.out.println("Value: " + value);
			   
			    
			    MarketPrice marketprice = new MarketPrice (stockSymbol, value );

			    marketPrices.add(marketprice);

			    

			}
			
			for (MarketPrice i : marketPrices ) {
//			    CurrentMarketPrice currentmarketprice = new CurrentMarketPrice (i.getStock(), Double.valueOf(i.getClosingPrice())  );
//			    currentMarketPriceService.addCurrentMarketPrice(currentmarketprice);

			    System.out.println("Stock:" + i.getStock() + " Closing Price:"+i.getClosingPrice());
			}
			
			System.out.println("-----------------------------------------------------------------------------------");

			
		
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
	    return marketPrices;
	}
		
		

	

}
