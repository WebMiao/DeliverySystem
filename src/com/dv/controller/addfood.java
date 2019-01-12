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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dv.bean.Foods;
import com.dv.bean.Sellers;
import com.dv.service.foodservice;
import com.dv.service.sellerservice;

/**
 * Servlet implementation class addfood
 */
@WebServlet("/addfood")
public class addfood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addfood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		Sellers seller = (Sellers) session.getAttribute("sellerinfo");
		PrintWriter out = response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			out.println("Nothing to upload");
			return; 
		}
		Foods temp = new Foods();
		foodservice Foodservice = new foodservice();
		DiskFileItemFactory itemfactory = new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		String savePath = request.getServletContext().getRealPath("upload/Foods");;
		File file = new File(savePath);
		//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
			//����Ŀ¼
		 	file.mkdir();
		}

		try{
		    List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){
				if(item.isFormField()){ //��ͨ������
					String paraname = item.getFieldName();
					switch (paraname){
					case "foodname":
						temp.setFoodname(item.getString());
						break;
					case "foodtype":
						temp.setFoodtype(item.getString());
						break;
					case "descrip":
						temp.setDescription(item.getString());
						break;
					case "price":
						temp.setPrice(Double.parseDouble(item.getString()));
						break;
					default:
						//do nothing
					}
					System.out.println("�������� name:"+paraname);
				}else {
					String name=item.getName(); //�ļ���--����·��
					name = name.substring(name.lastIndexOf("\\")+1); //ֻ�����ļ���
					System.out.println(savePath + "\\" + Foodservice.nextId()+name.substring(name.lastIndexOf(".")));
				    InputStream in = item.getInputStream(); //��ȡitem���ϴ���������
				    FileOutputStream outfile = new FileOutputStream(savePath + "\\" + Foodservice.nextId()+name.substring(name.lastIndexOf(".")));
				    temp.setPhotoAddr("/upload/Foods/" + Foodservice.nextId()+name.substring(name.lastIndexOf(".")));
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
		
		temp.setSellerid(seller.getId());
		if(temp.getPhotoAddr()==null)
			temp.setPhotoAddr("/upload/Foods/default.jpg");
		
		try {
			if(Foodservice.addRecord(temp)) {//successful create a new user record
				request.getRequestDispatcher("/tofoodpage?sellername="+seller.getName()).forward(request, response);
			}else {
				response.getWriter().append("Fail to change! Try again!");
				response.wait(10);
				request.getRequestDispatcher("./modeChange/newfood.jsp").forward(request, response);
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
