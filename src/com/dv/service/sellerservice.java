package com.dv.service;
import com.dv.bean.Sellers;
import com.dv.bean.Users;
import com.dv.connector.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class sellerservice {
	
	public ArrayList<Sellers>getlist() throws Exception
		{
		ArrayList<Sellers>list=new ArrayList<Sellers>();
		String sql="select * from seller";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		while(rs.next())
		{
			Sellers temp=new Sellers();
	    	String sellid=rs.getString("id");
	    	String sellname=rs.getString("name");
	    	String sellpass=rs.getString("password");
	    	String selltitle=rs.getString("title");
	    	String sellemail = rs.getString("email");
	    	String sellphoneno = rs.getString("phoneNO");
	    	String sellstatus = rs.getString("status");
	    	String selladdr = rs.getString("address");
	    	String selldesc = rs.getString("description");
	    	
	    	temp.setId(Integer.parseInt(sellid));
	    	temp.setName(sellname);
	    	temp.setPassword(sellpass);
	    	temp.setTitle(selltitle);
	    	temp.setEmail(sellemail);
	    	temp.setPhoneNO(sellphoneno);
	    	temp.setStatus(sellstatus);
	    	temp.setAddress(selladdr);
	    	temp.setDescription(selldesc);
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
	
	public String getSellerid(String name) throws Exception  /////****后期cart里直接跟上sellerid
	{
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String sql="select * from seller where seller.name=?";
		String returnValue=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.prepareStatement(sql);
			stm.setString(1,name);
			rs=stm.executeQuery();
		while(rs.next())
		{
			String sellerid=rs.getString("id");
			returnValue=sellerid;
		}
	    	
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public Sellers getOneSeller(int sellerid) throws Exception {
		Sellers temp=new Sellers();
		String sql="select * from seller where id = "+sellerid;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try 
		{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
		if(rs.next())
		{
	    	String sellid=rs.getString("id");
	    	String sellname=rs.getString("name");
	    	String sellpass=rs.getString("password");
	    	String selltitle=rs.getString("title");
	    	String sellemail = rs.getString("email");
	    	String sellphoneno = rs.getString("phoneNO");
	    	String sellstatus = rs.getString("status");
	    	String selladdr = rs.getString("address");
	    	String selldesc = rs.getString("description");
	    	temp.setId(Integer.parseInt(sellid));
	    	temp.setName(sellname);
	    	temp.setPassword(sellpass);
	    	temp.setTitle(selltitle);
	    	temp.setEmail(sellemail);
	    	temp.setPhoneNO(sellphoneno);
	    	temp.setStatus(sellstatus);
	    	temp.setAddress(selladdr);
	    	temp.setDescription(selldesc);
		}		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	public boolean addNewUser(Sellers temp) throws Exception {
		String sql = "insert into seller (name, password, email, phoneNO, status, photo) values (?,?,?,?,?,?)";
		Connection cnn=null;
		PreparedStatement stm=null;
		int rt=0;
		try 
		{
			//**Set the PK[id] as auto-increment in the DB***//
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.prepareStatement(sql); //acquire object to execute query
			stm.setString(1, temp.getName());
			stm.setString(2, temp.getPassword());
			stm.setString(3, temp.getEmail());
			stm.setString(4, temp.getPhoneNO());
			stm.setString(5, temp.getStatus());
			stm.setString(6, temp.getPhotoAddr());
			rt=stm.executeUpdate(); //object to store and operate result
			if(rt > 0)
				return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkidentity(Sellers loginseller) throws Exception {
		String sql="select * from seller";
		Connection cnn=null;
		Statement stm=null;
		ResultSet rt=null;
		try 
		{
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.createStatement(); //acquire object to execute query
			rt=stm.executeQuery(sql); //object to store and operate result
		while (rt.next()) //if next record exists, keep looping
		{
			String username=rt.getString("name");
			String password=rt.getString("password");
			if( (loginseller.getName().equals(username))&&(loginseller.getPassword().equals(password))) {
				loginseller.setId(Integer.parseInt(rt.getString("id")));
				return true; // user exists
			}
		} 
		}catch(ClassNotFoundException e){
			e.printStackTrace();
	    }catch(SQLException e)
		{
			e.printStackTrace();
		}
	    return false;
	}
	
	public Sellers returnsellerinfor(Sellers seller) throws Exception {
		String sql="select * from seller where seller.name=?";
		Connection cnn=null;
		PreparedStatement stm=null;
		ResultSet rt=null;
		try 
		{
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.prepareStatement(sql); //acquire object to execute query
			stm.setString(1, seller.getName());
			rt=stm.executeQuery(); //object to store and operate result
			
		while (rt.next()) //if next record exists, keep looping
		{
		    String id=rt.getString("id");
		    seller.setId(Integer.parseInt(id)); //返回信息！
		    String name=rt.getString("name");
		    seller.setName(name);
		    String password=rt.getString("password");
		    seller.setPassword(password);
		    String title = rt.getString("title");
		    seller.setTitle(title);
		    String email=rt.getString("email");
		    seller.setEmail(email);
    	    String address=rt.getString("address");
    	    seller.setAddress(address);
    	    String phoneNO=rt.getString("phoneNO");
    	    seller.setPhoneNO(phoneNO);
    	    String status=rt.getString("status");
    	    seller.setStatus(status);
    	    String description = rt.getString("description");
    	    seller.setDescription(description);
		} 
		}catch(ClassNotFoundException e){
			e.printStackTrace();
	    }catch(SQLException e)
		{
			e.printStackTrace();
		}
		return seller;
	}
	
    public boolean setNewPassword(Sellers seller) throws Exception {
    	String sql = "update seller set password=? where seller.id=?";
		Connection cnn=null;
		PreparedStatement stm=null;
		int rt=0;
		boolean returnvalue = false;
		try 
		{
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.prepareStatement(sql); //acquire object to execute query
			stm.setString(1, seller.getPassword());
			stm.setInt(2, seller.getId());
			rt=stm.executeUpdate(); //object to store and operate result
			if(rt > 0)
				returnvalue=true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnvalue;
    }

	public boolean updateBasicInfor(Sellers seller) throws Exception{
		String sql="update seller set name=?, title=?,email=?,address=?,phoneNo=?, description=? where seller.id=?";
    	Connection cnn=null;
    	PreparedStatement stm=null;
    	int rt=0;
    	boolean returnvalue = false;
    	try 
    	{
    		cnn=DBConnection.getConnection(); //acquire connection object;
    		stm=cnn.prepareStatement(sql); //acquire object to execute query
    		stm.setString(1, seller.getName());
    		stm.setString(2, seller.getTitle());
    		stm.setString(3, seller.getEmail());
    		stm.setString(4, seller.getAddress());
    		stm.setString(5, seller.getPhoneNO());
    		stm.setString(6, seller.getDescription());
    		stm.setInt(7, seller.getId());
    		rt=stm.executeUpdate(); //object to store and operate result 
    		if(rt>0)
    			returnvalue = true;
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
        }catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return returnvalue;
	}

	public int nextId() throws Exception {
		// TODO Auto-generated method stub
		String sql="select max(id) as max from seller";
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
	
	public String getphoto(int sellerid) throws Exception {
    	String sql="select photo from seller where id ="+sellerid;
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
	
	public boolean getStatus(String sellerid) throws Exception {
		String sql="select status from seller where id ="+sellerid;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try	{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("status").equals("open"))
					return true;
				else
					return false;
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean openStatus(int sellerid) throws Exception {
		String sql="update seller set status = 'open' where id ="+sellerid;
		Connection conn=null;
		Statement stm=null;
		try	{
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			if(stm.executeUpdate(sql)>0)
				return true;
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean closeStatus(int sellerid) throws Exception {
		String sql="update seller set status = 'close' where id ="+sellerid;
		Connection conn=null;
		Statement stm=null;
		try {
			conn=DBConnection.getConnection();
			stm=conn.createStatement();
			if(stm.executeUpdate(sql)>0)
				return true;
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
