<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.*, java.util.*, com.dv.service.sellerservice"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<title>Seller Information</title>
</head>
<body>
	<%
	session = request.getSession(false);
	Sellers seller = (Sellers)session.getAttribute("sellerinfo");
	sellerservice Sellerservice = new sellerservice();
	%>
	<!-- |||||||||||||||||||||||||||header|||||||||||||||||||||||||||||||| -->     
	<div class="headerwrapper">
		<div id="header" class="container">
			<div class="logo"> 
				<a href="/DeliverySystem/idcheckS?username=<%=seller.getName() %>&password=<%=seller.getPassword() %>"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
				<a href="/DeliverySystem/idcheckS?username=<%=seller.getName() %>&password=<%=seller.getPassword() %>"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
			</div> <!--end of Logo-->
	        <nav>
	            <ul id="navigations">
						<li><a href="/DeliverySystem/idcheckS?username=<%=seller.getName() %>&password=<%=seller.getPassword() %>">Welcome, <%=seller.getName() %>!</a></li>
		                <li><a href = "/DeliverySystem/toSetpage">Setting</a></li>
	            </ul>
	        </nav>
	      </div> <!--end of header-->
	</div> <!-- end of headerwrapper-->

	<!-- |||||||||||||||||||||||||||Seller Information|||||||||||||||||||||||||||||||| -->
	<div style="margin: 0 auto; width: 860px; margin-bottom:15px; border-bottom: 2px #ff8e22 solid; height:420px; padding:30px">
		<!-- Information Details -->
		<div style="float:right; border-left: 1px black solid; padding-left: 30px; height: 300 px;">
			<table style="text-align:left; width:450px; padding:50px; background-color:#FFFFFB; border: 3px #ff8e22 solid;border-radius:22px;">
				<tr height=34px>
					<td width="250px">Seller Name:</td>
					<td width="250px"> <%= seller.getName() %></td>
				</tr>
				<tr height=34px>
					<td width="250px">Seller ID:</td>
					<td width="250px"><%= seller.getId()%></td>
				</tr>
				<tr height=34px>
					<td width="250px">Seller Title</td>
					<td width="250px"><%= seller.getTitle()%></td>
				</tr>
				<tr height=34px>
					<td width="250px">E-mail:</td>
					<td width="250px"><%= seller.getEmail()%></td>
				</tr>
				<tr height=34px>
					<td width="250px">Address:</td>
					<td width="250px"><%= seller.getAddress() %></td>
				</tr> 
				<tr height=34px>
					<td width="250px">Phone Number:</td>
					<td width="250px"><%= seller.getPhoneNO() %></td>
				</tr>
				<tr height=34px>
					<td width="250px">Description:</td>
					<td width="250px"><%= seller.getDescription() %></td>
				</tr>
				<tr height=34px>
					<td width="250px">Status:</td>
					<td width="250px"><%= seller.getStatus() %></td>
				</tr>
			</table>
		</div>
		<!-- profile photo -->
		<div style="width:280px; height:340px; padding-left:10px; padding-top:30px;"  >
		<img src="${pageContext.request.contextPath}<%=Sellerservice.getphoto(seller.getId()) %>" height="280" width="280">
		</div>
	</div>

	<!-- |||||||||||||||||||||||||||Status Change Operation|||||||||||||||||||||||||||||||| -->
	<div style="padding-bottom:20px; width: 845px;margin: 0px auto; border-bottom:  2px #ff8e22 solid;">
	<% if (seller.getStatus().equals("open")) {%>
		<form method="post" action="sellerStatus" id="operation">
			<input type="hidden" id="type" name="type" value="close"/>
		   <!--  <input type="submit" value="Close Time" /> -->
		    <div onclick = "document.getElementById('operation').submit();" 
		    style="margin: 10px auto; background-color: #ff8e22; color:white; width:400px; height:50px;font-size:1em; padding-top:15px;
		    border-radius:8px; cursor:hand; text-align:center">
		    Close Time</div>
		</form>
	<%}else {%>
		<form method="post" action="sellerStatus" id="operation">
			<input type="hidden" name="type" value="open"/>
		    <!-- <input type="submit" value="Open Time" /> -->
		    <div onclick = "document.getElementById('operation').submit();" 
		    style="margin: 10px auto; background-color: #ff8e22; color:white; width:400px; height:50px;font-size:1em; padding-top:15px;
		    border-radius:8px; cursor:hand; text-align:center" >
		    Open Time</div>
		</form>
	<%} %>
	</div>
	
	<!-- |||||||||||||||||||||||||||Order List|||||||||||||||||||||||||||||||| -->
	<%
	ArrayList<Orders> list = (ArrayList<Orders>)request.getAttribute("orders");
	ArrayList<Users> corresBuyers = (ArrayList<Users>)request.getAttribute("corresBuyers");
	ArrayList<Foods> corresFood = (ArrayList<Foods>)request.getAttribute("corresFood");
	int nf = 0;
	for(int i=0;i<list.size();i++)
	{
		Orders ord = list.get(i);
		Users ur = corresBuyers.get(i);
	%>
	<div style = "border:4px solid #ff8e22; margin:20px auto; padding: 20px 10px 50px 20px; width: 900px; background:#FFFFFB; border-radius:20px;">
		<div style = "font-size: 1.3em">
		Order ID: <%= ord.getId() %>
		</div>
		<div style="padding:5px;">Buyer Name: <%= ur.getName() %></div>
		<div style="padding:5px;">Time: <%= ord.getDatetime() %></div>
		
		<!-- Order Taking Operation -->
		<%if(ord.getStatus().equals("missed")){%>
		<form method="post" action="ordertaking" id="take<%=ord.getId()%>">
			<input type="hidden" name="orderid" value="<%=ord.getId()%>"/>
			<div onclick = "document.getElementById('take<%=ord.getId()%>').submit();" 
			style="float:right; background-color: #ff8e22; color:white; width:210px; height:40px;font-size:1em; padding-top:15px;
		    border-radius:8px; cursor:hand; text-align:center">order taking</div>
		</form>	
		<%} %>
		
		<div style="padding:5px; margin-bottom:25px;">State: <%= ord.getStatus() %></div>
			
			<%
			for(int j = 0; j < ord.getContent().size();++j, ++nf){
			%>
				<div style = "border:1px solid #ff8e22; margin:15px;padding:2px;">
					<div style="margin:5px; float:right; width: 150px; text-align: left;">
					Price: <%= ((int)(corresFood.get(nf).getPrice() * ord.getContent().get(j).getAmount()*100))/100.0%></div>
					<div style = "font-size: 1.2em, margin:5px;">
					<%= corresFood.get(nf).getFoodname() %></div>
					<div style="margin:5px;">Amount: <%= ord.getContent().get(j).getAmount() %></div>
				</div >
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
	
<!-- |||||||||||||||||||||||||||footer|||||||||||||||||||||||||||||||| -->     
 <div class="footerwrapper">
    <footer class="container">
    	<div>
       		 <h2>Developer Reviews</h2>
			<div class = "review">
        	<p><strong>&#8220; </strong>Go to your Big Uncle.<strong>&#8220; </strong></p>
             <h4>- CHEN YI ZHEN</h4>
			</div>
             
			<div class = "review">
        	<p><strong>&#8220; </strong>He doesn't want to talk anything.<strong>&#8221;</strong></p>
             
             <h4>- CHEN ZHE HAN</h4>
            
        	</div>
        </div>
        <div>
        
      <h2>Socialize</h2>
        <div>
          <a href=https:https://www.facebook.com/chen.yizhen.796"><img src="./image/fb.png" width="68" height="68" class="facebook"
           alt="fb"></a>
        <a href="https://twitter.com/Mido_A7X"><img src="./image/twitter.png" width="68" height="68" class="twitter"
        alt="twitter"></a>
        <a href="#"><img src="./image/youtube.png" width="68" height="69" class="youtube" alt="youtube"></a>
        <a href="#"><img src="./image/g+.png" width="68" height="68" class="google" alt="g+"></a>
          <a href="#"><img src="./image/message.png" width="68" height="68" class="message" alt="message"></a>
      </div>
      
       </div>
    </footer>
  </div><!-- end of footer -->
  
</body>
</html>