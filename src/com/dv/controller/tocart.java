package com.dv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Carts;
import com.dv.bean.Foods;
import com.dv.bean.Sellers;
import com.dv.bean.Users;
import com.dv.service.cartservice;
import com.dv.service.foodservice;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class tocart
 */
@WebServlet("/tocart")
public class tocart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String type=request.getParameter("type");
		
		if(type.equals("add"))  
		{
			String fid=request.getParameter("id");  //get food id
			String sid=request.getParameter("sid"); //get seller id //商家id ,为返回使用
			String fname=request.getParameter("fname"); //get food name
			String price=request.getParameter("price");  //get food price
			HttpSession session = request.getSession(); 
			Users user=(Users)session.getAttribute("userinfor");
			cartservice cart=new cartservice();
			//ArrayList<Carts>cartlist=new ArrayList<Carts>();
			//String sellername2=null;
			try {
				String sellername=cart.getSellername(Integer.parseInt(sid));//get seller name;
				if(cart.isexist(user,fid))
				{
				   cart.updatecartrecord(user,fid,price);
				}
				else
				{
				 cart.insertcartrecord(user, fid, fname, sellername,price);
				}
				String ftype = request.getParameter("ftp");
				/*cartlist=(ArrayList<Carts>)(cart.getcartlist(Integer.toString(user.getId()))).clone();
				request.setAttribute("cartlist",cartlist);
				request.setAttribute("sid", sid);    //封装商家id到下一界面
				request.getRequestDispatcher("/WEB-INF/foodpage.jsp").forward(request, response); */
				/*ArrayList<Foods>foodinfor=new ArrayList<Foods>(); //重新封装food列表 为保持在原页面
				ArrayList<String>foodtype=new ArrayList<String>();
				foodservice Foodservice=new foodservice();
				foodtype=(ArrayList<String>)(Foodservice.getFoodTypeList(sid)).clone();
				if(request.getParameter("ftp")==null) {
					foodinfor=(ArrayList<Foods>) (Foodservice.getFoodList(Integer.parseInt(sid))).clone();
				}else {
					foodinfor=(ArrayList<Foods>) (Foodservice.getTypedFoodList(Integer.parseInt(sid),request.getParameter("ftp"))).clone();
				sellerservice Sellerservice=new sellerservice();
				Sellers sl = Sellerservice.getOneSeller(Integer.parseInt(sid));
				request.setAttribute("sellerdata", sl);
				request.setAttribute("foods",foodinfor); 
				request.setAttribute("foodtype", foodtype);*/
				
				request.getRequestDispatcher("/tofoodpage?sellername="+sellername+"&ftp="+ftype).forward(request, response); 
				//}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (type.equals("del"))
		{
			cartservice cart=new cartservice();
			String foodid=request.getParameter("id");
			
			//String sid=request.getParameter("sid"); //get seller id //商家id ,为返回使用
			HttpSession session = request.getSession(); 
			Users user=(Users)session.getAttribute("userinfor");
			ArrayList<Carts>cartlist=new ArrayList<Carts>();
			
			try {
				String price=cart.getfoodprice(foodid);
				cart.deletecartrecord(user, foodid,price);
				cartlist=(ArrayList<Carts>)(cart.getcartlist(Integer.toString(user.getId()))).clone(); //*** 后期加计算总价
				Double sumall=cart.SumAll(cartlist);
				request.setAttribute("sumall", sumall);  //购物车总价
				request.setAttribute("cartlist",cartlist);
				//**写在cartservice 返回int类
				//request.setAttribute("sid", sid);    //封装商家id到下一界面						  
				request.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (type.equals("other"))  // 其他页面跳转到此 (foodpage.jsp hall.jsp checkout.jsp) //单纯取表
		{
			HttpSession session = request.getSession(); 
			Users user=(Users)session.getAttribute("userinfor");
			ArrayList<Carts>cartlist=new ArrayList<Carts>();
			cartservice cart=new cartservice();
			try {
				cartlist=(ArrayList<Carts>)(cart.getcartlist(Integer.toString(user.getId()))).clone(); 
				Double sumall=cart.SumAll(cartlist);
				request.setAttribute("sumall", sumall);  //购物车总价
				request.setAttribute("cartlist",cartlist);  
				request.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
