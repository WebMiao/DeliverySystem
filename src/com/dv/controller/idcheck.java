package com.dv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Sellers;
import com.dv.bean.Users;
import com.dv.service.sellerservice;
import com.dv.service.userservice;

@WebServlet("/idcheck")
public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String uname=request.getParameter("username");
		String pword=request.getParameter("password");
		uname = uname.replace("/'", "");
		pword = pword.replace("/'", "");
		Users loginuser=new Users();
		loginuser.setName(uname);
		loginuser.setPassword(pword);
		userservice Userservice=new userservice();
		try {
			if(Userservice.checkidentity(loginuser))
			{
				Userservice.returnuserinfor(loginuser); //返回用户完整信息
				HttpSession session = request.getSession(); 
				session.setAttribute("userinfor", loginuser); //放入session中为后面页面做准备
				ArrayList<Sellers>sellerinfor=new ArrayList<Sellers>();//为下页商家信息做准备
				sellerservice sell=new sellerservice();
				sellerinfor=(ArrayList<Sellers>)(sell.getlist()).clone();
				request.setAttribute("sellers", sellerinfor);
				request.getRequestDispatcher("/WEB-INF/Hall.jsp").forward(request, response);
			}
			else
			{
				//request.setAttribute("alter", "Username does not exist or password is incorrect");
				response.sendRedirect("./modeChange/login.jsp?alter=Username does not exist or password is incorrect");
			}
		} catch (Exception e) {
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
