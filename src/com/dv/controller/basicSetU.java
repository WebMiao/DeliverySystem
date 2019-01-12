package com.dv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dv.bean.Users;
import com.dv.service.userservice;

/**
 * Servlet implementation class basicSetU
 */
@WebServlet("/basicSetU")
public class basicSetU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basicSetU() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		userservice Userservice = new userservice();
		HttpSession session=request.getSession();
		session = request.getSession(false);
		Users ur = (Users)session.getAttribute("userinfor");
		Users urnew = new Users();
		urnew.setId(ur.getId());
		urnew.setName(ur.getName());
		urnew.setPassword(ur.getPassword());
		urnew.setGender(ur.getGender());
		urnew.setEmail(ur.getEmail());
		urnew.setAddress(ur.getAddress());
		urnew.setPhoneNO(ur.getPhoneNO());
		try {
			String newName = (String) request.getParameter("username");
			urnew.setName(newName);
			String newGender = (String) request.getParameter("gender");
			urnew.setGender(newGender);
			String newEmail = (String) request.getParameter("email");
			urnew.setEmail(newEmail);
			String newPhonenum = (String) request.getParameter("phoneNo");
			urnew.setPhoneNO(newPhonenum);
			String newAddr = (String) request.getParameter("address");
			urnew.setAddress(newAddr);
			if(Userservice.updateBasicInfor(urnew)) {
				session.setAttribute("userinfor", urnew);
				request.getRequestDispatcher("/WEB-INF/successNotify.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("/WEB-INF/settingInfoU.jsp").forward(request, response);
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
