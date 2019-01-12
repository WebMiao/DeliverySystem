package com.dv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Foods;
import com.dv.bean.Orders;
import com.dv.bean.Sellers;
import com.dv.bean.Users;
import com.dv.service.foodservice;
import com.dv.service.orderservice;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class toUserInfo
 */
@WebServlet("/toUserInfo")
public class toUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		orderservice Orderservice = new orderservice();
		HttpSession session=request.getSession();
		session = request.getSession(false);
		Users ur = (Users)session.getAttribute("userinfor");
		try {
			ArrayList<Orders> order = Orderservice.getUserOrder(ur.getId());
			request.setAttribute("orders",order);
			sellerservice Sellerservice = new sellerservice();
			ArrayList<Sellers> seller = new ArrayList<Sellers>();
			for(int i =0; i < order.size(); ++i) {
				seller.add(Sellerservice.getOneSeller(order.get(i).getSellerid()));
			}
			request.setAttribute("corresSellers", seller);
			foodservice Foodservice = new foodservice();
			ArrayList<Foods> food = new ArrayList<Foods>();
			for(int i =0; i < order.size(); ++i) {
				for(int j = 0; j < order.get(i).getContent().size(); ++j) {
					Foods temp = Foodservice.getFoodDetail(order.get(i).getContent().get(j).getFoodid());
					food.add(temp);
				}
			}
			request.setAttribute("corresFood", food);
			request.getRequestDispatcher("/WEB-INF/userInfo.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}











