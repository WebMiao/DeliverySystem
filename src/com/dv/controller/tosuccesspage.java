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
import com.dv.bean.Orders;
import com.dv.bean.Users;
import com.dv.service.cartservice;
import com.dv.service.orderservice;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class tosuccesspage
 */
@WebServlet("/tosuccesspage")
public class tosuccesspage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  //��ҳ����Ҫ�������ɶ�����Ϣ��ɾ�����ﳵ��,һ���ύ ���ɶ������
		response.getWriter().append("Served at: ").append(request.getContextPath());
		orderservice order=new orderservice();
		cartservice cart=new cartservice();
		sellerservice seller=new sellerservice();
		ArrayList<Carts>cartlist=new ArrayList<Carts>();
		ArrayList<Orders>orderlist=new ArrayList<Orders>();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(); 
		Users user=(Users)session.getAttribute("userinfor");
		try {
			cartlist=(ArrayList<Carts>)cart.getcartlist(Integer.toString(user.getId())).clone(); //��ȡ���ﳵ����
			for(int i=0;i<cartlist.size();i++)
			{
				String sellerid=seller.getSellerid(cartlist.get(i).getSellername()); //ȡ�̼�id
				if(!orderlist.isEmpty()) //�ж��Ƿ�Ϊ��  ���״Σ� ->�ǿ� 
				{
					if(order.issellerexist(sellerid, orderlist)) //�ж��Ƿ���ڸ��̼Ҽ�¼->����
					{
						orderlist=(ArrayList<Orders>)order.addprice(orderlist, cartlist.get(i), sellerid).clone();
						//pw.println("exist1");
						
					}
					else  //�������̼Ҽ�¼
					{
						orderlist=(ArrayList<Orders>)order.neworderrecord(cartlist.get(i), sellerid,Integer.toString(user.getId()), orderlist).clone();
						//pw.println("not exist1");
					}
				}
				else  //Ϊ��(�״�)
				{
					orderlist=(ArrayList<Orders>)order.neworderrecord(cartlist.get(i), sellerid,Integer.toString(user.getId()), orderlist).clone();
					//pw.println("not exist2");
				}
			}
			order.createorderdb(orderlist);
			for (int i=0;i<cartlist.size();i++)
			{
				String sellerid=seller.getSellerid(cartlist.get(i).getSellername());
				order.createorderdetail(sellerid, cartlist.get(i));
			}
			cart.clearcart(user); //����û����ﳵ
			request.getRequestDispatcher("/WEB-INF/successNotify.jsp?order=''").forward(request, response);
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
