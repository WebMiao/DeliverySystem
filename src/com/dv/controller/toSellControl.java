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
import com.dv.service.userservice;

/**
 * Servlet implementation class toSellControl
 */
@WebServlet("/toSellControl")
public class toSellControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public toSellControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		orderservice Orderservice = new orderservice();
		HttpSession session=request.getSession();
		session = request.getSession(false);
		Sellers sler = (Sellers)session.getAttribute("sellerinfo");
		try {
			ArrayList<Orders> order = Orderservice.getSellerOrder(sler.getId());
			request.setAttribute("orders",order);
			userservice Userservice = new userservice();
			ArrayList<Users> UserList = new ArrayList<Users>();
			for(int i =0; i < order.size(); ++i) {
				UserList.add(Userservice.getOneUser(order.get(i).getUserid()));
			}
			request.setAttribute("corresBuyers", UserList);
			foodservice Foodservice = new foodservice();
			ArrayList<Foods> food = new ArrayList<Foods>();
			for(int i =0; i < order.size(); ++i) {
				for(int j = 0; j < order.get(i).getContent().size(); ++j) {
					Foods temp = Foodservice.getFoodDetail(order.get(i).getContent().get(j).getFoodid());
					food.add(temp);
				}
			}
			request.setAttribute("corresFood", food);
			request.getRequestDispatcher("/WEB-INF/sellerInfo.jsp").forward(request, response);
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
