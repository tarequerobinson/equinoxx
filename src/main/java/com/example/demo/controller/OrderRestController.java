package com.example.demo.controller;

import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.UserDetailsHelper;
import com.example.demo.entities.Order;
import com.example.demo.gleanernewswebscraper.News;
import com.example.demo.gleanernewswebscraper.NewsScraper;
import com.example.demo.jsewebscraper.JSEScraper;
import com.example.demo.jsewebscraper.MarketPrice;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderServices;
import com.example.demo.utilities.BuyOrderTotal;
import com.example.demo.utilities.MonthlyTotal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;





@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    
    @Autowired
    private OrderRepository orderRepo;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
	
	private JSEScraper jsescraper;
	
    @Autowired
	private OrderServices services ;
    
    public OrderRestController(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}
    
    
	//Show all orders in the Database
    //This should only be accessible for admin, the avg USER should only be able to see their data 
    
    @GetMapping("/showall")
//	@PreAuthorize ("hasAuthority('ROLE_ADMIN')")
    public List<Order> showOrders() {
        return orderRepo.findAll();
    }
    
    
    @GetMapping("/showallUserSpecificOrders")
//	@PreAuthorize ("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Order>> showUserOrders(@AuthenticationPrincipal UserDetails userDetails) {
		Long userId = ((UserDetailsHelper) userDetails).getUserId();

    	String sql = "SELECT * " +
                "FROM equityorders " +
                "WHERE userid = ? ";
    	
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), userId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orders);
    	
    	
    }
    

	//Returns the closing market prices by using the webscraper 
	@GetMapping(value = "/marketprices", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> latestMarketPrices() {
	    try {
	        List<MarketPrice> marketPrices = jsescraper.returnJson();
	        Gson gson = new Gson();
	        String json = gson.toJson(marketPrices);
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .body(json);
	    } catch (Exception e) {
	        // log the error
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	 @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public ResponseEntity<String> latestNews() {
	        try {
	            List<News> newsfeed = NewsScraper.returnJson();
	            Gson gson = new Gson();
	            String json = gson.toJson(newsfeed);
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                    .body(json);
	        } catch (Exception e) {
	            // log the error
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	


    //Returns all filled orders in database 
	@GetMapping("/showFilledOrders")
	//@PreAuthorize ("hasAuthority('ROLE_ADMIN')")

	    public ResponseEntity<List<Order>> getFilledOrders(@AuthenticationPrincipal UserDetails userDetails) {
		List <Order> filledOrders = services.getFilledOrders();
		return ResponseEntity.ok(filledOrders);
	}


	//Delete Order from EquityOders
			@DeleteMapping("/delete_order/{equityOrderNumber}")
			public ResponseEntity<String> deleteOrder(@PathVariable Long equityOrderNumber){
				services.deleteOrder(equityOrderNumber); 
				 return ResponseEntity.ok("Order DELETED successfully");
			}
		
    //Returns the total of all buy orders for each stock as json
	@GetMapping("/allbuyorders")
	//@PreAuthorize ("hasAuthority('ROLE_ADMIN')")
	    public List<BuyOrderTotal> getAllBuyOrderTotals(@AuthenticationPrincipal UserDetails userDetails) {
			Long userId = ((UserDetailsHelper) userDetails).getUserId();

	        String sql = "SELECT stock_symbol, SUM(estimated_value) as total_buy_orders " +
	                     "FROM equityorders " +
	                     "WHERE transaction_type = 'BUY' " +
	                     "AND userid = ? " +
	                     "GROUP BY stock_symbol";
	
			return jdbcTemplate.query(sql, (rs, rowNum) -> {
			        String stockSymbol = rs.getString("stock_symbol");
			        double totalBuyOrders = rs.getDouble("total_buy_orders");
			        return new BuyOrderTotal(stockSymbol, totalBuyOrders);
			    }, userId);	
			}
	
	
	//Returns the Monthly totals for each month 
	@GetMapping("/monthly_totals")
	public List<MonthlyTotal> getMonthlyTotals(@AuthenticationPrincipal UserDetails userDetails) {
		Long userId = ((UserDetailsHelper) userDetails).getUserId();
	    String sql = "SELECT DATE_FORMAT(order_date, '%M %Y') AS month, SUM(estimated_value) AS total_spent " + 
	        "FROM equityorders WHERE order_type = 'BUY' " +
            "AND userid = ? " +
	        "GROUP BY DATE_FORMAT(order_date, '%M %Y') " + 
	        "ORDER BY STR_TO_DATE(CONCAT('01 ', month), '%d %M %Y') ASC";

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {

			    String month = rs.getString("month");
			    double totalSpent =rs.getDouble("total_spent");
			    return new MonthlyTotal (month, totalSpent);
	    	}, userId);

	    }
	
	//Posts orders from a stock portfolio and stores it into a database to 'kickstart' application 
	@RequestMapping("/importOrdersFromFile")
	@PostMapping (consumes = "multipart/form-data")

	public void importOrdersFromFile(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal UserDetails userDetails) {
		
	try {
			services.saveImportedOrders(file, userDetails);
			
			
			System.out.println("Orders IMPORTED successfully");
			
	}
	catch (Exception e ) {
		
		e.printStackTrace();
	}
	}

	//save new transaction to dtabase
	@PostMapping ("/createNewOrder")
	public ResponseEntity<String> createNewOrder(@RequestParam ("orderDate") String orderDate,
								@RequestParam("equityOrderNumber") Integer equityOrderNumber,
								@RequestParam("status") String status,
								@RequestParam("stockExchangCode") String stockExchangCode,
								@RequestParam("currency") String currency,
								@RequestParam("quantity") Integer quantity,
								@RequestParam("avgFillPrice") Double avgFillPrice,
								@RequestParam("estimatedValue") Double estimatedValue,
								@RequestParam("limitPrice") Double limitPrice,
								@RequestParam("orderType")String orderType,
								@RequestParam ("stockSymbol") String stockSymbol,
								@AuthenticationPrincipal UserDetails userDetails)
	
	
	{
	    Map<String, Object> formData = new HashMap<>();
	    formData.put("orderDate", orderDate);
	    formData.put("equityOrderNumber", equityOrderNumber);
	    formData.put("status", status);
	    formData.put("stockExchangCode", stockExchangCode);
	    formData.put("currency", currency);
	    formData.put("quantity", quantity);
	    formData.put("avgFillPrice", avgFillPrice);
	    formData.put("estimatedValue", estimatedValue);
	    formData.put("stockSymbol", stockSymbol);
	    formData.put("orderType", orderType);
	    formData.put("limitPrice", limitPrice);
	    formData.put("stockSymbol", stockSymbol);

	    Order order = Order.createFromFormData(formData);
		Long userId = ((UserDetailsHelper) userDetails).getUserId();
	    order.setUserID(userId);
	    
	    orderRepo.save(order);
	    return ResponseEntity.ok("Track uploaded successfully");
	}
}
