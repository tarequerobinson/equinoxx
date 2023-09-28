package com.example.demo.jsewebscraper;
import java.util.List;

import com.google.gson.Gson;

public class JsonArray {
	
	public JsonArray() {
		// TODO Auto-generated constructor stub
	}

	static String getJsonArray(List<MarketPrice> arraylist) {
	    Gson gson = new Gson();
	    String json = gson.toJson(arraylist);
	    return json;
	}

}
