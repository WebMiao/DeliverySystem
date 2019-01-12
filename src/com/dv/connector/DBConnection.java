package com.dv.connector;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	private static final String driver="com.mysql.jdbc.Driver"; //driver
	private static final String url="jdbc:mysql://localhost:3306/dvsystem?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";//selected database
	private static final String username="root"; //database name
	private static final String password="1234";
	private static Connection conn=null; //connection object
	
	static {
		try {
			Class.forName(driver); //register driver
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	public static Connection getConnection() throws Exception{
		if(conn==null) {
			conn=DriverManager.getConnection(url,username,password); // get connected and acquire object
			return conn;  
		}
		return conn;
}
	
	public static void main(String[] args) {
		
		try {
			Connection conn=DBConnection.getConnection();
			if(conn!=null) {
				System.out.println("Database connected");
			}else {
				System.out.println("Fail to connect");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
