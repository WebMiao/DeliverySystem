package com.dv.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

import com.dv.bean.Users;
import com.dv.service.userservice;

@SuppressWarnings("serial")
@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()+"\n");
		
		PrintWriter out = response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			out.println("Nothing to upload");
			return; 
		}
		Users temp = new Users();
		userservice Userservice = new userservice();
		DiskFileItemFactory itemfactory = new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		String savePath = request.getServletContext().getRealPath("upload/Users");
		System.out.println(savePath);
				//"D:\\GitHub\\DeliverySystem\\WebContent\\WEB-INF\\upload\\Users"
		File file = new File(savePath);
		//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
			//����Ŀ¼
		 	file.mkdir();
		}

		try{
		    List<FileItem> list = upload.parseRequest(request); //ԭ���İ��������
			for(FileItem item:list){
				if(item.isFormField()){ //��ͨ������
					String paraname = item.getFieldName();
					switch (paraname){
					case "username":
						temp.setName(item.getString());
						break;
					case "password1":
						temp.setPassword(item.getString());
						break;
					case "gender":
						temp.setGender(item.getString());
						break;
					case "inemail":
						temp.setEmail(item.getString());
						break;
					case "phoneNum":
						temp.setPhoneNO(item.getString());
						break;
					default:
						//do nothing
					}
					System.out.println("�������� name:"+paraname);
				}else {
					String name=item.getName(); //�ļ���--����·��
					name = name.substring(name.lastIndexOf("\\")+1); //ֻ�����ļ���
					//String contentType = item.getContentType();
				    /*if(!(contentType.equals("image/png")||contentType.equals("image/jpeg"))){
					    out.println("only png or jpg format image files supported");
					    continue;
				    }*/
					System.out.println(savePath + "\\" + Userservice.nextId()+name.substring(name.lastIndexOf(".")));
				    InputStream in = item.getInputStream(); //��ȡitem���ϴ���������
				    FileOutputStream outfile = new FileOutputStream(savePath + "\\" + Userservice.nextId()+name.substring(name.lastIndexOf(".")));
				    temp.setPhotoAddr(/*request.getContextPath() + */"/upload/Users/" + Userservice.nextId()+name.substring(name.lastIndexOf(".")));
				    byte buffer[] = new byte[1024];
				    int len = 0;
				    while((len=in.read(buffer))>0) { //ѭ�������������뵽�������У� >0��ʾin���滹������
				    	outfile.write(buffer,0,len);
				    }
				    in.close(); //�ر�������
				    outfile.close(); //�ر������
				    item.delete(); //ɾ���ļ��ϴ�ʱ����ʱ�ļ�
					out.println("File Saved Successfully");
				}
		    }
		}
		catch(Exception ex){
			out.println("can't save");
		}
		
		if(temp.getPhotoAddr()==null)
			temp.setPhotoAddr("/upload/Users/default.jpg");

		try {
			if(Userservice.addNewUser(temp)) {//successful create a new user record
				response.sendRedirect("./modeChange/login.jsp");
			}else {
				response.getWriter().append("Fail to register! Try again!");
				response.wait(10);
				response.sendRedirect("./modeChange/signupU.jsp");
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
