package com.example.demo.utilities;
import java.io.InputStream;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Order;

import fileupload.*;
public class Excel {
	
	
	

    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } 
{
            return false;
        }
    }

	
	public static List<Order> excelToListOfOrders (InputStream is)
	{ 
		
		List<Order> list=new ArrayList<>();
		
		try {
			
			
	        XSSFWorkbook workbook = new XSSFWorkbook(is);
	        XSSFSheet sheet = workbook.getSheet("Worksheet");

	        if (sheet == null) {
	            throw new IllegalArgumentException("Sheet 'Worksheet' not found in Excel file");
	        }

	        int rowNumber = 0;
	        Iterator<Row> iterator = sheet.iterator();

	        while (iterator.hasNext()) {
	            Row row = iterator.next();
	            if (rowNumber == 0) {
	                rowNumber++;
	                continue;
	            }
				 Iterator<Cell> cells  = row.iterator();
				 int cid = 0 ;

				 Order order = new Order();
				 
				 
				 while (cells.hasNext()) {
					 
					 Cell cell = cells.next();
					 
					 switch (cid) 
					 {
					 
					 	case 0:
					 	    //Date date = (Date) DateUtil.getJavaDate(cell.getNumericCellValue());

					 		//order.setOrderDate(cell.getDateCellValue());
					 		order.setOrderDate(cell.getStringCellValue());
					 		break;
					 	case 1:
					 		order.setEquityOrderNumber((int)cell.getNumericCellValue());
					 		break;
					 	case 2:
					 		order.setStatus(cell.getStringCellValue());
					 		break;
					 	case 3:
					 		order.setStockExchangCode(cell.getStringCellValue());
					 		break;
					 	/*case 4:
					 		customer.setAnnualIncome((int)cell.getNumericCellValue());
					 		break;*/
					 	case 4:
					 		order.setCurrency(cell.getStringCellValue());
					 		break;
					 	case 5:
					 		order.setStockSymbol(cell.getStringCellValue());
					 		break;
					 	
					 		
					 	case 6:
					 		order.setOrderType(cell.getStringCellValue());
					 		break;

					 	case 7:
					 		order.setQuantity((int)cell.getNumericCellValue());
					 		break;
					 		
					 		
					 		
					 		
					 	case 8:
					 		order.setAvgFillPrice(cell.getNumericCellValue());
					 		break;
					 		
							
					 	case 9:
					 		order.setEstimatedValue(cell.getNumericCellValue());
					 		break;
					 		
					
					 		
					 	case 10:
					 		order.setTimeInForce(cell.getStringCellValue());
					 		break;
					 		
					 	case 11:
					 		order.setOrderType(cell.getStringCellValue());
					 		break;
					 		
					 		
					 	case 12:
					 		order.setLimitPrice(cell.getNumericCellValue());
					 		break;
					 }
					 cid++;
				 }
				 
		            System.out.println("order: " + order);

				 list.add(order);
			 }
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		return list;
	}

}
