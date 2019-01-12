package com.dv.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

import com.dv.bean.Sellers;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class registerSeller
 */
@WebServlet("/registerSeller")
public class registerSeller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			out.println("Nothing to upload");
			return; 
		}
		Sellers temp = new Sellers();
		sellerservice Sellerservice = new sellerservice();
		DiskFileItemFactory itemfactory = new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		String savePath = request.getServletContext().getRealPath("upload/Sellers");
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"目录不存在，需要创建");
			//创建目录
		 	file.mkdir();
		}

		try{
		    List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){
				if(item.isFormField()){ //普通表单对象
					String paraname = item.getFieldName();
					switch (paraname){
					case "username":
						temp.setName(item.getString());
						break;
					case "password1":
						temp.setPassword(item.getString());
						break;
					case "email":
						temp.setEmail(item.getString());
						break;
					case "phoneNum":
						temp.setPhoneNO(item.getString());
						break;
					default:
						//do nothing
					}
					System.out.println("基本表单项 name:"+paraname);
				}else {
					String name=item.getName(); //文件名--绝对路径
					name = name.substring(name.lastIndexOf("\\")+1); //只保留文件名
					System.out.println(savePath + "\\" + Sellerservice.nextId()+name.substring(name.lastIndexOf(".")));
				    InputStream in = item.getInputStream(); //获取item中上传的输入流
				    FileOutputStream outfile = new FileOutputStream(savePath + "\\" + Sellerservice.nextId()+name.substring(name.lastIndexOf(".")));
				    temp.setPhotoAddr("/upload/Sellers/" + Sellerservice.nextId()+name.substring(name.lastIndexOf(".")));
				    byte buffer[] = new byte[1024];
				    int len = 0;
				    while((len=in.read(buffer))>0) { //循环将输入流读入到缓冲区中， >0表示in里面还有数据
				    	outfile.write(buffer,0,len);
				    }
				    in.close(); //关闭输入流
				    outfile.close(); //关闭输出流
				    item.delete(); //删除文件上传时的临时文件
					out.println("File Saved Successfully");
				}
		    }
		}
		catch(Exception ex){
			out.println("can't save");
		}
		
		temp.setStatus("close");
		if(temp.getPhotoAddr()==null)
			temp.setPhotoAddr("/upload/Sellers/default.jpg");
		
		/*
		 * if the sign up process is invalid, the image is still saved at server storage
		 * causing a large amount of garbage! 
		 */
		try {
			if(Sellerservice.addNewUser(temp)) {//successful create a new user record
				response.sendRedirect("./modeChange/loginS.jsp");
			}else {
				response.getWriter().append("Fail to register! Try again!");
				response.wait(10);
				response.sendRedirect("./modeChange/signupS.jsp");
			}
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
