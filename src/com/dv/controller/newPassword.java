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
 * Servlet implementation class newPassword
 */
@WebServlet("/newPassword")
public class newPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		session = request.getSession(false);
		String newpwd = request.getParameter("password1");
		userservice Userservice = new userservice();
		sellerservice Sellerservice = new sellerservice();
		if(session.getAttribute("userinfor")!=null) {
			Users ur = (Users) session.getAttribute("userinfor");
			ur.setPassword(newpwd);
			try {
				if(Userservice.setNewPassword(ur)) {
					session.setAttribute("userinfor", ur);
					request.getRequestDispatcher("/WEB-INF/successNotify.jsp").forward(request, response);
				}else
					request.getRequestDispatcher("/modeChange/passwordSet.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(session.getAttribute("sellerinfo")!=null) {
			Sellers seller = (Sellers) session.getAttribute("sellerinfo");
			seller.setPassword(newpwd);
			try {
				if(Sellerservice.setNewPassword(seller)) {
					session.setAttribute("sellerinfo", seller);
					request.getRequestDispatcher("/WEB-INF/successNotify.jsp").forward(request, response);
				}else
					request.getRequestDispatcher("/modeChange/passwordSet.jsp").forward(request, response);
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
