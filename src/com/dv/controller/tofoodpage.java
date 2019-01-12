package com.dv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dv.bean.Foods;
import com.dv.bean.Sellers;
import com.dv.service.foodservice;
import com.dv.service.sellerservice;

@WebServlet("/tofoodpage")
public class tofoodpage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String sellname=request.getParameter("sellername"); //两个页面对应返回foodpage 故用到此操作
 			ArrayList<Foods>foodinfor=new ArrayList<Foods>();
 			ArrayList<String>foodtype=new ArrayList<String>();
			sellerservice Sellerservice=new sellerservice();
			foodservice Foodservice = new foodservice();
			String tempid=Sellerservice.getSellerid(sellname);
			Sellers sl = Sellerservice.getOneSeller(Integer.parseInt(tempid));
			foodtype=(ArrayList<String>)(Foodservice.getFoodTypeList(tempid)).clone();
			if(request.getParameter("ftp")==""||request.getParameter("ftp")==null) {
				foodinfor=(ArrayList<Foods>) (Foodservice.getFoodList(Integer.parseInt(tempid))).clone();
				request.setAttribute("ftp","");
			}else {
				foodinfor=(ArrayList<Foods>) (Foodservice.getTypedFoodList(Integer.parseInt(tempid),request.getParameter("ftp"))).clone();
				request.setAttribute("ftp", request.getParameter("ftp"));
			}
			request.setAttribute("foods",foodinfor);//prepare for food data
			request.setAttribute("foodtype", foodtype);
			request.setAttribute("sellerdata", sl);
			request.getRequestDispatcher("/WEB-INF/foodpage.jsp").forward(request, response);
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
