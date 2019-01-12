package com.dv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.service.foodservice;
import com.dv.bean.Sellers;

/**
 * Servlet implementation class foodOperation
 */
@WebServlet("/foodOperation")
public class deletefood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String sellername = ((Sellers)session.getAttribute("sellerinfo")).getName();
		
		String foodid = request.getParameter("fid");
		foodservice Foodservice = new foodservice();
		try {
			if(Foodservice.deleteRecord(foodid)) {
				String ftype = request.getParameter("ftp");
				request.getRequestDispatcher("/tofoodpage?sellername="+sellername+"&ftp="+ftype).forward(request, response);
			}
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
