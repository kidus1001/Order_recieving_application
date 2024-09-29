package DAO;

import java.sql.*;
import java.util.ArrayList;

import entity.Menu;

public class MenuDAO {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=kitchen; encrypt = true; IntegratedSecurity=true; trustServerCertificate=true" ;
	Connection connection = null;
	
	public MenuDAO () {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Menu> getMenu() {
		ArrayList<Menu> menus = new ArrayList <> ();
		try {  
			
			connection = DriverManager.getConnection(url,"kidus1001","");
			System.out.println("Connection established successfully."); 
			
			String sql = "SELECT foodItem, price FROM MENU";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			
			while(rs.next()) {
				Menu menu = new Menu(
						rs.getString("foodItem"),
						rs.getInt("price")
				);
				menus.add(menu);
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
		return menus;
	}	
}
