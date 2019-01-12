package com.dv.controller;

import java.io.IOException;
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

/**
 * Servlet implementation class basicSetS
 */
@WebServlet("/basicSetS")
public class basicSetS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basicSetS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		sellerservice Sellerservice = new sellerservice();
		HttpSession session=request.getSession();
		session = request.getSession(false);
		Sellers seller = (Sellers)session.getAttribute("sellerinfo");
		Sellers sellernew = new Sellers();
		sellernew.setId(seller.getId());
		sellernew.setName(seller.getName());
		sellernew.setPassword(seller.getPassword());
		sellernew.setTitle(seller.getTitle());
		sellernew.setEmail(seller.getEmail());
		sellernew.setAddress(seller.getAddress());
		sellernew.setPhoneNO(seller.getPhoneNO());
		sellernew.setDescription(seller.getDescription());
		try {
			String newName = (String) request.getParameter("username");
			sellernew.setName(newName);
			String newTitle = (String) request.getParameter("title");
			sellernew.setTitle(newTitle);
			String newEmail = (String) request.getParameter("email");
			sellernew.setEmail(newEmail);
			String newPhonenum = (String) request.getParameter("phoneNo");
			sellernew.setPhoneNO(newPhonenum);
			String newAddr = (String) request.getParameter("address");
			sellernew.setAddress(newAddr);
			String newdesc = (String) request.getParameter("descrip");
			sellernew.setDescription(newdesc);
			if(Sellerservice.updateBasicInfor(sellernew)) {
				session.setAttribute("sellerinfo", sellernew);
				request.getRequestDispatcher("/WEB-INF/successNotify.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("/WEB-INF/settingInfoS.jsp").forward(request, response);
		}catch (Exception e) {
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
