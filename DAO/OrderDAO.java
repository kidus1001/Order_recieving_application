package DAO;


import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Order;

public class OrderDAO {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=kitchen; encrypt = true; IntegratedSecurity=true; trustServerCertificate=true" ;
	Connection connection = null;
	
	public OrderDAO () {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> getOrder() {
		ArrayList<Order> orders = new ArrayList <> ();
		try {  
			
			connection = DriverManager.getConnection(url,"kidus1001","");
			System.out.println("Connection established successfully."); 
			
			String sql = "SELECT foodItem, quantity, orderTime, description FROM FoodOrder";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			
			while(rs.next()) {
				Order order = new Order(
						rs.getString("foodItem"),
						rs.getInt("quantity"),
						rs.getString("orderTime"),
						rs.getString("description")
				);
				orders.add(order);
			}
		} 
		catch (SQLException e) { 
			 e.printStackTrace(); 
		} 
		finally {
			try {
				
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			
			System.out.println("Connection closed."); 
		} 
		return orders;
	}
	
	public void addOrder(Order order) {
		try (Connection connection = DriverManager.getConnection(url, "kidus1001", "")) {
			String sql = "INSERT INTO FoodOrder (foodItem, quantity, orderTime, description) VALUES (?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, order.getFoodItem());
			pst.setInt(2, order.getQuantity());
			pst.setString(3, order.getOrderTime());
			pst.setString(4, order.getDescription());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
}

	







//public class OrderDAO {
//	public OrderDAO () {		
//		String url = "jdbc:sqlserver://localhost:1433;databaseName=kitchen; encrypt = true; IntegratedSecurity=true; trustServerCertificate=true" ;
//		Connection connection = null;
//		try {  
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//			connection = DriverManager.getConnection(url,"kidus1001","");
//			System.out.println("Connection established successfully."); 
//		} 
//		catch (SQLException e) { 
//			 e.printStackTrace(); 
//		} 
//		catch (ClassNotFoundException ex) { 
//			 ex.printStackTrace(); 
//		} 
//		finally {
//			try {
//				connection.close();
//			} 
//			catch (SQLException e) {
//				e.printStackTrace();
//			} 
//			
//			System.out.println("Connection closed."); 
//		} 
//	}
//}
