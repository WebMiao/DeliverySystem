<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.Users,com.dv.bean.Foods, 
com.dv.bean.Sellers, java.util.*, com.dv.bean.Orders, com.dv.service.userservice"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<title>User's Information</title>
</head>
<body>
	<!-- |||||||||||||||||||||||||||header|||||||||||||||||||||||||||||||| -->     
	<div class="headerwrapper">
		<div id="header" class="container">
		<% if(session.getAttribute("userinfor")!=null){
				Users user=(Users)session.getAttribute("userinfor"); //Prepare for Back%>
			
			<div class="logo"> 
				<a href="/DeliverySystem/idcheck?username=<%=user.getName() %>&password=<%=user.getPassword() %>"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
				<a href="/DeliverySystem/idcheck?username=<%=user.getName() %>&password=<%=user.getPassword() %>"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
			</div> <!--end of Logo-->
	        <nav>
	            <ul id="navigations">
	                <li>Welcome,<%=user.getName() %>!</li>
	                <li> <a href='/DeliverySystem/tocart?type=other'>ViewCart</a></li>
	                <li><a href = "/DeliverySystem/toSetpage">Setting</a></li>
	            </ul>
	    <%        }%>
	        </nav>
	      </div> <!--end of header-->
	</div> <!-- end of headerwrapper-->

	<!-- |||||||||||||||||||||||||||User Infomation|||||||||||||||||||||||||||||||| -->  
	<%
	session = request.getSession(false);
	Users ur = (Users)session.getAttribute("userinfor");
	userservice Userservice = new userservice();
	%>
	<div style="margin: 0 auto; width: 835px; margin-bottom:45px; border-bottom: 2px #ff8e22 solid;">
		<!-- Information Details -->
		<div style="float:right; border-left: 1px black solid; padding-left: 30px; height: 300 px;">
			<table style="text-align:left; width:450px; padding:50px; background-color:#FFFFFB; border: 3px #ff8e22 solid;border-radius:22px;">
				<tr height=30px>
					<td width="250px">User Name:</td>
					<td width="250px"> <%= ur.getName()%></td>
				</tr>
				<tr>
					<td width="250px">User ID:</td>
					<td width="250px"><%= ur.getId()%></td>
				</tr>
				<tr>
					<td width="250px">Gender</td>
					<td width="250px"><% if(ur.getGender().equals("F")){%><%="Female" %><%}else%><%="Male" %></td>
				</tr>
				<tr>
					<td width="250px">E-mail:</td>
					<td width="250px"><%= ur.getEmail()%></td>
				</tr>
				<tr>
					<td width="250px">Address:</td>
					<td width="250px"><%= ur.getAddress() %></td>
				</tr> 
				<tr>
					<td width="250px">Phone Number:</td>
					<td width="250px"><%= ur.getPhoneNO() %></td>
				</tr>
			</table>
		</div>
		<!-- profile photo -->
		<div style="width:280px; height:340px; padding-left:10px"  >
		<img src="${pageContext.request.contextPath}<%=Userservice.getphoto(ur.getId()) %>" height="280" width="280">
		</div>
	</div>
	
	<% 
	ArrayList<Orders> list = (ArrayList<Orders>)request.getAttribute("orders");
	ArrayList<Sellers> corresSellers = (ArrayList<Sellers>)request.getAttribute("corresSellers");
	ArrayList<Foods> corresFood = (ArrayList<Foods>)request.getAttribute("corresFood");
	int nf = 0;
	for(int i=0;i<list.size();i++)
	{
		Orders ord = list.get(i);
		Sellers sel = corresSellers.get(i);
	%>
	<div style = "border:4px solid #ff8e22; margin:20px auto; padding: 20px 10px 50px 20px; width: 900px; background:#FFFFFB; border-radius:20px;">
		<div style = "font-size: 1.8em">
		<%= sel.getTitle() %>
		</div>
		<div  style="padding:5px; margin-top:5px">Time: <%= ord.getDatetime() %></div>
		<div  style="padding:5px">State: <%= ord.getStatus() %></div>
			<%
			for(int j = 0; j < ord.getContent().size();++j, ++nf){
			%>
				<div style = "border:1px solid #ff8e22; margin:15px;padding:2px;">
					<div style="margin:5px; float:right; width: 150px; text-align: left">Price: <%=((int)(corresFood.get(nf).getPrice() * ord.getContent().get(j).getAmount()*100))/100.0%></div>
					<div style = "font-size: 1.2em; margin:5px;">
					<%= corresFood.get(nf).getFoodname() %></div>
					<div style="margin:5px;">Amount: <%= ord.getContent().get(j).getAmount() %></div>
				</div>
				<% if(j==ord.getContent().size()-1) {%>
				<div style="float:right; font-size: 1.3em; width: 180px; text-align: left">
				Total: <%= ord.getTotalAmount() %>
				</div>
			<%
				}
			}//add unit price of each food in DB
			%>
	</div>
	<%
	}
	%>
</body>
</html>