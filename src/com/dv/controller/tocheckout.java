package com.dv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Carts;
import com.dv.bean.Users;
import com.dv.service.cartservice;

/**
 * Servlet implementation class tocheckout
 */
@WebServlet("/tocheckout")
public class tocheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(); 
		Users user=(Users)session.getAttribute("userinfor");
		cartservice cart=new cartservice();
		ArrayList<Carts>cartlist=new ArrayList<Carts>();
		try {
			cartlist=(ArrayList<Carts>)(cart.getcartlist(Integer.toString(user.getId()))).clone();
			Double sumall=cart.SumAll(cartlist);
			request.setAttribute("sumall", sumall);
			request.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
