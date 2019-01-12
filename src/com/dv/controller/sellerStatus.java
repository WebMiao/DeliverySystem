package com.dv.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Sellers;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class sellerStatus
 */
@WebServlet("/sellerStatus")
public class sellerStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Sellers seller = (Sellers)session.getAttribute("sellerinfo");
		String instruction = request.getParameter("type");
		sellerservice Sellerservice = new sellerservice(); 
		if(instruction.equals("open")) {
			try {
				if(Sellerservice.openStatus(seller.getId())) {
					seller.setStatus("open");
					request.getRequestDispatcher("toSellControl").forward(request, response);
				}else {
					out.println("Fail to open!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(instruction.equals("close")) {
			try {
				if(Sellerservice.closeStatus(seller.getId())) {
					seller.setStatus("close");
					request.getRequestDispatcher("toSellControl").forward(request, response);
				}else {
					out.println("Fail to close!");
				}
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
