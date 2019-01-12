package com.dv.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dv.bean.Foods;
import com.dv.connector.DBConnection;

public class foodservice {
	public Foods getFoodDetail(int foodid) throws Exception {
		Foods fd = new Foods();
		String sql="select * from food where foodid = "+foodid;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		if(rs.next())
		{
			int sid = rs.getInt("sellerid");
			String ft = rs.getString("foodtype");
			String fn = rs.getString("foodname");
			String fdd = rs.getString("description");
			double fp = rs.getDouble("price");
			fd.setFoodid(foodid);
			fd.setSellerid(sid);
			fd.setFoodname(fn);
			fd.setFoodtype(ft);
			fd.setDescription(fdd);
			fd.setPrice(fp);
		}}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return fd;
	}
	
	public ArrayList<Foods> getFoodList(int sellerid) throws Exception
	{
	ArrayList<Foods>list=new ArrayList<Foods>();
	String sql="select * from food where sellerid=?";
	Connection conn=null;
	PreparedStatement stm=null;
	ResultSet rs=null;
	try 
	{
		conn=DBConnection.getConnection();
		stm=conn.prepareStatement(sql);
		stm.setInt(1,sellerid);
		rs=stm.executeQuery();
	while(rs.next())
	{
		Foods temp=new Foods();
    	int sid=rs.getInt("sellerid");
    	int fid=rs.getInt("foodid");
    	String foodtype=rs.getString("foodtype");
    	String foodname=rs.getString("foodname");
    	String description = rs.getString("description");
    	double price = rs.getDouble("price");
    	temp.setFoodid(fid);
    	temp.setSellerid(sid);
    	temp.setFoodname(foodname);
    	temp.setFoodtype(foodtype);
    	temp.setDescription(description);
    	temp.setPrice(price);
    	list.add(temp);
	}		
	}catch (ClassNotFoundException e){
		e.printStackTrace();
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	return list;
	}
	

	public ArrayList<Foods> getTypedFoodList(int sellerid, String type) throws Exception {
		ArrayList<Foods>list=new ArrayList<Foods>();
		System.out.println(sellerid);
		System.out.println(type);
		String sql="select * from food where sellerid=? and foodtype=?";
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setInt(1,sellerid);
			stm.setString(2, type);
			rs=stm.executeQuery();
		while(rs.next())
		{
			Foods temp=new Foods();
	    	int sid=rs.getInt("sellerid");
	    	int fid=rs.getInt("foodid");
	    	String foodtype=rs.getString("foodtype");
	    	String foodname=rs.getString("foodname");
	    	String description = rs.getString("description");
	    	double price = rs.getDouble("price");
	    	temp.setFoodid(fid);
	    	temp.setSellerid(sid);
	    	temp.setFoodname(foodname);
	    	temp.setFoodtype(foodtype);
	    	temp.setDescription(description);
	    	temp.setPrice(price);
	    	list.add(temp);
		}		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteRecord(String foodid) throws Exception {
		String sql="delete from food where foodid=?";
		Connection conn=null;
		PreparedStatement stm=null;
		int rs=-1;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setString(1,foodid);
			rs=stm.executeUpdate();
			if(rs > 0){
				return true;
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addRecord(Foods food) throws Exception {
		String sql="insert into food (sellerid, foodname, foodtype, price, description, photo) values (?, ? ,?, ?, ? ,?)";
		Connection conn=null;
		PreparedStatement stm=null;
		int rs=0;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setInt(1,food.getSellerid());
			stm.setString(2, food.getFoodname());
			stm.setString(3, food.getFoodtype());
			stm.setDouble(4, food.getPrice());
			stm.setString(5, food.getDescription());
			stm.setString(6, food.getPhotoAddr());
			rs=stm.executeUpdate();
			if(rs > 0){
				return true;
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public int nextId() throws Exception {
		// TODO Auto-generated method stub
		String sql="select max(foodid) as max from food";
    	Connection conn=null;
    	Statement stm=null;
    	ResultSet rs=null;
    	int next_id = -1;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		if(rs.next())
		{
			next_id = rs.getInt("max")+1;
		}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return next_id;
	}
	
	public String getphoto(int foodid) throws Exception {
    	String sql="select photo from food where foodid ="+foodid;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		String addr = null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		if(rs.next()){
			addr = rs.getString("photo");
		}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return addr;
    }
	
	public ArrayList<String> getFoodTypeList(String tempid) throws Exception{
		ArrayList<String>list=new ArrayList<String>();
		String sql="select distinct foodtype as ft from food where sellerid ="+tempid;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		String type = null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		while(rs.next()){
			type = rs.getString("ft");
			list.add(type);
		}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}

}
