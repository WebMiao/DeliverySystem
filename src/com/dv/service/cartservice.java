package com.dv.service;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import com.dv.bean.Carts;
import com.dv.bean.Foods;
import com.dv.bean.Users;
import com.dv.connector.DBConnection;

public class cartservice {
	
	public String getSellername(int id) throws Exception
	{
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String sql="select * from seller where seller.id=?";
		String returnValue=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setString(1,Integer.toString(id));
			rs=stm.executeQuery();
		while(rs.next())
		{
			String sellername=rs.getString("name");
			returnValue=sellername;
		}
	    	
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public String getfoodprice(String foodid) throws Exception //返回购物车食物的单价
	{
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String sql="select * from food where foodid=?";
		String returnValue=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setString(1,foodid);
			rs=stm.executeQuery();
		while(rs.next())
		{
			String foodprice=rs.getString("price");
			returnValue=foodprice;
		}
	    	
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnValue;
		
	}
	public boolean isexist(Users user,String fid) throws Exception
	{
		String sql="select * from carts";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		boolean exist=false;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		while(rs.next())
		{
			int userid=rs.getInt("userid");
			int foodid=rs.getInt("foodid");
			if(userid==user.getId()&&foodid==Integer.parseInt(fid)){
				exist=true;
				break;
			}
		}		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return exist;
	}
	public void updatecartrecord(Users user,String fid,String price) throws Exception //增加操作
	{
		String sql1="select * from carts where foodid=? and userid=?";
		String sql2="update carts set foodnum=?,totalamount=? where foodid=? and userid=?";  //加在这价格
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstm=null;
		int foodnum=0;
		try 
		{
			conn=DBConnection.getConnection();
			pstm=conn.prepareStatement(sql1);
			pstm.setString(1, fid);
			pstm.setString(2, Integer.toString(user.getId()));
			rs=pstm.executeQuery();
			if(rs.next())
			{
				foodnum=rs.getInt("foodnum");
			}	
			foodnum++;  //数量加一
			double totalprice = foodnum*(Double.valueOf(price));  //计算价钱 个数*单价
			pstm=conn.prepareStatement(sql2); //准备sql2
			pstm.setInt(1, foodnum); //数量更新
			pstm.setDouble(2,totalprice);
			pstm.setString(3, fid);
			pstm.setString(4, Integer.toString(user.getId()));
			pstm.executeUpdate();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void insertcartrecord(Users user,String fid,String fname,String sellername,String price) throws Exception //新建
	{
		//String sql="insert into carts(userid,foodid,foodname,sellername,foodnum) values(?,?,?,?,?)"; //加一个价格
		Connection conn=null;
		PreparedStatement stm=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement("insert into carts(userid,foodid,foodname,sellername,foodnum,totalamount) values(?,?,?,?,?,?)");
			stm.setObject(1,Integer.toString(user.getId()));
			stm.setObject(2,fid);
			stm.setObject(3, fname);
			stm.setObject(4, sellername);
			stm.setObject(5, "1");
			stm.setObject(6, price);
			stm.execute();
		//	System.out.println("Success");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deletecartrecord(Users user,String fid,String price) throws Exception //减少或消除
	{
		String sql1="select * from carts where foodid=? and userid=?";
		String sql2="update carts set foodnum=?,totalamount=? where foodid=? and userid=?";
		String sql3="delete from carts where userid=? and foodid=?";
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstm=null;
		String foodnum=null;
		try 
		{
			conn=DBConnection.getConnection();
			pstm=conn.prepareStatement(sql1);
			pstm.setString(1, fid);
			pstm.setString(2, Integer.toString(user.getId()));
			rs=pstm.executeQuery();
		while(rs.next())
		{
			foodnum=rs.getString("foodnum");
		}	
		int intnum=Integer.parseInt(foodnum); //获取数量
		intnum--;  //数量减一
		String totalprice=Double.toString(intnum*(Double.valueOf(price)));  
		if(intnum>0)
		{
		pstm=conn.prepareStatement(sql2); //准备sql2
		pstm.setString(1, Integer.toString(intnum)); //数量更新
		pstm.setString(2, totalprice);
		pstm.setString(3, fid);
		pstm.setString(4, Integer.toString(user.getId()));
		int temp = pstm.executeUpdate();
		}
		else
		{
		pstm=conn.prepareStatement(sql3);
		pstm.setString(1, Integer.toString(user.getId()));
		pstm.setString(2,fid); 
		int temp2=pstm.executeUpdate();
		}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearcart (Users user) throws Exception
	{
		String sql="delete from carts where userid=?"; //删除某一用户所有购物车数据
		Connection conn=null;
		PreparedStatement pstm=null;
		try 
		{
		conn=DBConnection.getConnection();
		pstm=conn.prepareStatement(sql);
		pstm.setString(1, Integer.toString(user.getId()));
		pstm.executeUpdate();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Double SumAll(ArrayList<Carts>cartlist) 
	{
		Double sumall=(double) 0;
		for(int i=0;i<cartlist.size();i++)
		{
			sumall=cartlist.get(i).getTotalamount()+sumall;
		}
		return sumall;
	} 
	
	
	public ArrayList<Carts>getcartlist(String userid) throws Exception
	{
		ArrayList<Carts>cartlist=new ArrayList<Carts>();
		String sql="select * from carts where userid=?";
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setString(1,userid);
			rs=stm.executeQuery();
		while(rs.next())
		{
			Carts temp=new Carts();
	    	String foodid=rs.getString("foodid");
	    	String foodname=rs.getString("foodname");
	    	String sellername=rs.getString("sellername");
	    	String foodnum=rs.getString("foodnum");
	    	String totalamount=rs.getString("totalamount");
	    	temp.setFoodid(Integer.parseInt(foodid));
	    	temp.setFoodname(foodname);
	    	temp.setSellername(sellername);
	    	temp.setFoodnum(Integer.parseInt(foodnum));
	    	temp.setUserid(Integer.parseInt(userid));
	    	temp.setTotalamount(Double.valueOf(totalamount));
	    	cartlist.add(temp); //一个bean 对应一条记录
		}		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cartlist;
	}
}
