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
import com.dv.service.sellerservice;

@WebServlet("/idcheckS")
public class idcheckS extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String uname=request.getParameter("username");
		String pword=request.getParameter("password");
		uname = uname.replace("/'", "");
		pword = pword.replace("/'", "");
		Sellers loginseller=new Sellers();
		loginseller.setName(uname);
		loginseller.setPassword(pword);
		sellerservice Sellerservice=new sellerservice();
		try {
			if(Sellerservice.checkidentity(loginseller))
			{
				Sellerservice.returnsellerinfor(loginseller);
				HttpSession session = request.getSession(); 
				session.setAttribute("sellerinfo", loginseller); //放入session中为后面页面做准备
				ArrayList<Sellers>sellerinfor=new ArrayList<Sellers>();//为下页商家信息做准备
				sellerservice sell=new sellerservice();
				sellerinfor=(ArrayList<Sellers>)(sell.getlist()).clone();
				request.setAttribute("sellers", sellerinfor); //放入session中为后面页面做准备
				request.getRequestDispatcher("/WEB-INF/Hall.jsp").forward(request, response);
			}
			else
			{
				//request.setAttribute("alter", "Seller name does not exist or password is incorrect");
				response.sendRedirect("./modeChange/loginS.jsp?alter=Sellername does not exist or password is incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
