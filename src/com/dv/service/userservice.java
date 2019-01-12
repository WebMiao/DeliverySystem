package com.dv.service;
import java.sql.*;

import com.dv.bean.Users;
import com.dv.connector.DBConnection;

public class userservice {

    public boolean checkidentity(Users user) throws Exception {
		String sql="select * from users";
		boolean returnvalue=false;
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
			if( (user.getName().equals(username))&&(user.getPassword().equals(password))) {
				returnvalue=true; // user exists
				break; 
			}
		} 
		}catch(ClassNotFoundException e){
			e.printStackTrace();
	    }catch(SQLException e)
		{
			e.printStackTrace();
		}
	    return returnvalue;
	}

    public Users returnuserinfor(Users user) throws Exception {
    	String sql="select * from users where users.name=?";
    	Connection cnn=null;
    	PreparedStatement stm=null;
    	ResultSet rt=null;
    	try 
    	{
    		cnn=DBConnection.getConnection(); //acquire connection object;
    		stm=cnn.prepareStatement(sql); //acquire object to execute query
    		stm.setString(1, user.getName());
    		rt=stm.executeQuery(); //object to store and operate result
    		
    	while (rt.next()) //if next record exists, keep looping
    	{
    	    String id=rt.getString("id");
    	    user.setId(Integer.parseInt(id)); //·µ»ØÐÅÏ¢£¡
    	    String name=rt.getString("name");
    	    user.setName(name);
    	    String password=rt.getString("password");
    	    user.setPassword(password);
    	    String email=rt.getString("email");
    	    user.setEmail(email);
    	    String address=rt.getString("address");
    	    user.setAddress(address);
    	    String phoneNO=rt.getString("phoneNO");
    	    user.setPhoneNO(phoneNO);
    	    String gender=rt.getString("gender");
    	    user.setGender(gender);
    	} 
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
        }catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
        return user;
    }
    
    public boolean addNewUser(Users user) throws Exception {
		String sql = "insert into Users (name, password, gender, email, address, phoneNO, photo) values (?,?,?,?,?,?,?)";
		Connection cnn=null;
		PreparedStatement stm=null;
		int rt=0;
		try 
		{
			//**Set the PK[id] as auto-increment in the DB***//
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.prepareStatement(sql); //acquire object to execute query
			stm.setString(1, user.getName());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getGender());
			stm.setString(4, user.getEmail());
			stm.setString(5, user.getAddress());
			stm.setString(6, user.getPhoneNO());
			stm.setString(7, user.getPhotoAddr());
			rt=stm.executeUpdate(); //object to store and operate result
			if(rt > 0)
				return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
    
    public boolean updateBasicInfor(Users user)throws Exception {
    	String sql="update users set name=?, gender=?, email=?,phoneNO=?,address=? where users.id=?";
    	Connection cnn=null;
    	PreparedStatement stm=null;
    	int rt=0;
    	boolean returnvalue = false;
    	try 
    	{
    		cnn=DBConnection.getConnection(); //acquire connection object;
    		stm=cnn.prepareStatement(sql); //acquire object to execute query
    		stm.setString(1, user.getName());
    		stm.setString(2, user.getGender());
    		stm.setString(3, user.getEmail());
    		stm.setString(4, user.getPhoneNO());
    		stm.setString(5, user.getAddress());
    		stm.setInt(6, user.getId());
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
    
    public boolean setNewPassword(Users ur) throws Exception {
    	String sql = "update users set password=? where users.id=?";
		Connection cnn=null;
		PreparedStatement stm=null;
		int rt=0;
		boolean returnvalue = false;
		try 
		{
			cnn=DBConnection.getConnection(); //acquire connection object;
			stm=cnn.prepareStatement(sql); //acquire object to execute query
			stm.setString(1, ur.getPassword());
			stm.setInt(2, ur.getId());
			rt=stm.executeUpdate(); //object to store and operate result
			if(rt > 0)
				returnvalue=true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return returnvalue;
    }
    
    public Users getOneUser(int userid) throws Exception {
    	Users temp=new Users();
		String sql="select * from users where id = "+userid;
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
	    	int uid=rs.getInt("id");
	    	String username=rs.getString("name");
	    	String userpass=rs.getString("password");
	    	temp.setId(uid);
	    	temp.setName(username);
	    	temp.setPassword(userpass);
		}		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
    
    public int nextId() throws Exception{
    	String sql="select max(id) as max from users";
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
   
    public String getphoto(int usrid) throws Exception {
    	String sql="select photo from users where id ="+usrid;
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
}
	


