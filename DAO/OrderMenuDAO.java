package DAO;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Order;

public class OrderMenuDAO {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=kitchen; encrypt = true; IntegratedSecurity=true; trustServerCertificate=true" ;
	Connection connection = null;
	
	public OrderMenuDAO () {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String [] getOrder() {
		String [] foodItemArray = new String [25];
		try {  
			
			connection = DriverManager.getConnection(url,"kidus1001","");
			System.out.println("Connection established successfully."); 
			
			String sql = "SELECT foodItem FROM MENU";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			
			int i = 0;
			while(rs.next()) {
				foodItemArray[i] = rs.getString("foodItem");
				i++;
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
		return foodItemArray;
	}
}

