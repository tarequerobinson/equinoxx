package com.example.demo.services;

import java.io.IOException;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.UserDetailsHelper;
import com.example.demo.entities.Order;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.utilities.Excel;


@Service
@Component
public class OrderServices {
	
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	

	public void saveImportedOrders (MultipartFile file,@AuthenticationPrincipal UserDetails userDetails ) {
		
		try {
			
			Long userId = ((UserDetailsHelper) userDetails).getUserId();
			List<Order> orders = Excel.excelToListOfOrders(file.getInputStream());
			for (Order order : orders) {
	            order.setUserID(userId);
	        }
			this.orderRepo.saveAll(orders);
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
	}
	
	public List<Order> getAllOrders(){
		return this.orderRepo.findAll();
	}
	// Method offering the service to get Order by ID 
		public Optional<Order> getOrderByEquityOrderNumber(Long equityOrderNumber){
			return orderRepo.findById(equityOrderNumber);
		}
	
	
	public void deleteOrder(Long equityOrderNumber) {
		// TODO Auto-generated method stub
		Optional<Order> deletedOrder = orderRepo.findById(equityOrderNumber);
		orderRepo.delete(deletedOrder.get());	
	}
	
	//note that the names of each colum should be the names of the column in the database and not the name used as a attribute of the order class
	 public List<Order> getFilledOrders() {
	        String sql = "SELECT * " +
	                     "FROM equityorders.equityorders " +
	                     "WHERE status = 'filled' ";
	     // Execute the SQL query and map the results to a list of Order objects
	        List<Order> filledOrders = jdbcTemplate.query(sql, (rs, rowNum) -> {
	            Order filledOrder = new Order();
	            filledOrder.setOrderDate(rs.getString("order_date"));
	            filledOrder.setEquityOrderNumber(rs.getInt("equity_order_number"));
	            filledOrder.setStatus(rs.getString("status"));
	            filledOrder.setStockExchangCode(rs.getString("stock_Exchang_Code"));
	            filledOrder.setCurrency(rs.getString("currency"));
	            filledOrder.setQuantity(rs.getInt("quantity"));
	            filledOrder.setAvgFillPrice(rs.getDouble("avg_fill_price"));
	            filledOrder.setEstimatedValue(rs.getDouble("estimated_value"));
	            filledOrder.setLimitPrice(rs.getDouble("limit_price"));
	            filledOrder.setTimeInForce(rs.getString("time_in_force"));
	            filledOrder.setStockSymbol(rs.getString("stock_symbol"));
	            filledOrder.setOrderType(rs.getString("order_type"));
	            return filledOrder;
	        });

	        // Return the list of Order objects in the response body
	        return filledOrders;
	 }

}
