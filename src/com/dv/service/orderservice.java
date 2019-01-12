package com.dv.service;

import java.util.ArrayList;

import com.dv.bean.Carts;
import com.dv.bean.Orders;
import com.dv.bean.OrdersContent;
import com.dv.bean.Users;
import com.dv.connector.DBConnection;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class orderservice {
	public ArrayList<Orders> getUserOrder(int userid) throws Exception{
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql="select * from orders where buyerid = "+userid;;
		Connection conn=null;
		Statement stm=null;
		Statement stm1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		while(rs.next())
		{
			Orders temp = new Orders();
			int id = Integer.parseInt(rs.getString("id"));
			int sid = rs.getInt("sellerid");
			int bid = rs.getInt("buyerid");
			String dt = rs.getString("datetime");
			double tA = rs.getDouble("totalAmount");
			String status = rs.getString("status");
			temp.setId(id);
			temp.setSellerid(sid);
			temp.setUserid(bid);
			temp.setDatetime(dt);
			temp.setTotalAmount(tA);
			temp.setStatus(status);
			ArrayList<OrdersContent> content = new ArrayList<OrdersContent>();
			String sql1="select * from orderscontent where orderid="+id;
			try 
			{
				stm1=conn.createStatement();
				rs1=stm1.executeQuery(sql1);
			while(rs1.next())
			{
				OrdersContent cont = new OrdersContent();
				int iid = rs1.getInt("id");
				int oid = rs1.getInt("orderid");
				int fid = rs1.getInt("foodid");
				int amount = rs1.getInt("amount");
				cont.setId(iid);
				cont.setOrderid(oid);
				cont.setFoodid(fid);
				cont.setAmount(amount);
				content.add(cont);
			}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			temp.setContent(content);
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

	public ArrayList<Orders> getSellerOrder(int sellerid) throws Exception {
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql="select * from orders where sellerid = "+sellerid;;
		Connection conn=null;
		Statement stm=null;
		Statement stm1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		while(rs.next())
		{
			Orders temp = new Orders();
			int id = Integer.parseInt(rs.getString("id"));
			int sid = rs.getInt("sellerid");
			int bid = rs.getInt("buyerid");
			String dt = rs.getString("datetime");
			double tA = rs.getDouble("totalAmount");
			String status = rs.getString("status");
			temp.setId(id);
			temp.setSellerid(sid);
			temp.setUserid(bid);
			temp.setDatetime(dt);
			temp.setTotalAmount(tA);
			temp.setStatus(status);
			ArrayList<OrdersContent> content = new ArrayList<OrdersContent>();
			String sql1="select * from orderscontent where orderid="+id;
			try 
			{
				stm1=conn.createStatement();
				rs1=stm1.executeQuery(sql1);
			while(rs1.next())
			{
				OrdersContent cont = new OrdersContent();
				int iid = rs1.getInt("id");
				int oid = rs1.getInt("orderid");
				int fid = rs1.getInt("foodid");
				int amount = rs1.getInt("amount");
				cont.setId(iid);
				cont.setOrderid(oid);
				cont.setFoodid(fid);
				cont.setAmount(amount);
				content.add(cont);
			}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			temp.setContent(content);
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
	
	public boolean takeOrder(int ordid) throws Exception {
		String sql="update orders set status='Accepted' where orders.id=?";
		Connection conn=null;
		PreparedStatement stm=null;
		int rs=0;
		boolean returnvalue=false;
		try 
		{
			conn=DBConnection.getConnection();
			stm= conn.prepareStatement(sql);
			stm.setInt(1, ordid);
			rs=stm.executeUpdate();
			if(rs>0) {
				returnvalue=true;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnvalue;
	}
	
	public void createorderdetail(String sellerid,Carts cart) throws Exception //Éú³É¶©µ¥ÏêÇé
	{
		Connection conn=null;
		PreparedStatement stm=null;		
		ResultSet rs=null;	
		String orderid=null;
		try
		{
			conn=DBConnection.getConnection();	
			String sql1="select * from orders where sellerid=?";
			String sql2="insert into orderscontent(orderid,foodid,amount) values(?,?,?)";
			stm=conn.prepareStatement(sql1); 
			stm.setString(1, sellerid);
			rs=stm.executeQuery();
					while(rs.next())
					{
						 orderid=rs.getString("id");
					
					}
					
			stm=conn.prepareStatement(sql2); 
			stm.setString(1, orderid);
			stm.setString(2, Integer.toString(cart.getFoodid()));
			stm.setString(3, Integer.toString(cart.getFoodnum()));
			stm.execute();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}	
	public void createorderdb(ArrayList<Orders>orderlist) throws Exception //Éú³É¶©µ¥±í
	{
		Connection conn=null;
		PreparedStatement stm=null;		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date date=new Date();
		String dateStr = dateFormat.format(date);//»ñÈ¡Ê±¼ä
		try
		{
			conn=DBConnection.getConnection();
			for(int i=0;i<orderlist.size();i++)
			{
			String sql="insert into orders(sellerid,buyerid,datetime,totalAmount,status) values(?,?,?,?,?)";
			stm=conn.prepareStatement(sql);
			 stm.setObject(1,Integer.toString(orderlist.get(i).getSellerid()));
			 stm.setObject(2,Integer.toString(orderlist.get(i).getUserid()));
			 stm.setObject(3,dateStr);
			 stm.setObject(4,Double.toString(orderlist.get(i).getTotalAmount()));
			 stm.setObject(5, "missed");
			 stm.execute();
			//System.out.println("Success");
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
	public boolean issellerexist(String sellerid,ArrayList<Orders>orderlist)
	{
		for(int i=0;i<orderlist.size();i++)
		{
			if(sellerid.equals(Integer.toString(orderlist.get(i).getSellerid())))
				return true;
		}
		return false;
	}
	public ArrayList<Orders>neworderrecord(Carts cart,String sellerid,String userid,ArrayList<Orders>orderlist)//ÐÂ¼ÍÂ¼
	{
		Orders order=new Orders(); //temp
		order.setSellerid(Integer.parseInt(sellerid));
		order.setUserid(Integer.parseInt(userid));
		order.setTotalAmount(cart.getTotalamount());
		orderlist.add(order);
		return orderlist;
	}
	public ArrayList<Orders>addprice(ArrayList<Orders>orderlist,Carts cart,String sellerid)
	{
		for(int i=0;i<orderlist.size();i++)
		{
			if(sellerid.equals(Integer.toString(orderlist.get(i).getSellerid())))
				orderlist.get(i).setTotalAmount(orderlist.get(i).getTotalAmount()+cart.getTotalamount());
		}
		return orderlist;
	}
}
