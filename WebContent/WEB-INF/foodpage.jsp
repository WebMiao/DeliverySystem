<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.dv.bean.*, com.dv.service.foodservice,com.dv.service.sellerservice"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>FoodPage</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
<div class="headerwrapper">
	<div id="header" class="container">
		<!-- <div class="logo"> 
			<a href="#"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href="#"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> end of Logo -->
        <nav>
            <ul id="navigations">
	<% 
	boolean permissionU = false;
	if((Users)session.getAttribute("userinfor")!=null){
		Users user=(Users)session.getAttribute("userinfor"); //Prepare for Back
		String username=user.getName();
		String password=user.getPassword(); 
		permissionU = true; %>
		<li><a href='/DeliverySystem/idcheck?username=<%=username%>&password=<%=password%>'>Back</a></li>
		<%} %>
	<%
	Sellers seller = (Sellers)request.getAttribute("sellerdata");
	%>
	<%
	boolean permissionS = false;
	if(session.getAttribute("sellerinfo")!= null){
		Sellers sl = (Sellers)session.getAttribute("sellerinfo");
		if(sl.getId()==seller.getId()){
			String username=seller.getName();
			String password=seller.getPassword();
			permissionS = true;%>
		<li><a href='/DeliverySystem/idcheckS?username=<%=username%>&password=<%=password%>'>Back</a></li>
		<li><a href = "./modeChange/newfood.jsp">Add dish</a></li>
		<%} else{%>
			<li><a href='/DeliverySystem/idcheckS?username=<%=sl.getName()%>&password=<%=sl.getPassword()%>'>Back</a></li>
		<%	}
	}
	%>
	
	<%
	if(session.getAttribute("sellerinfo")== null){
		if(permissionU==false){
	%>
	<li><a href='/DeliverySystem/initialToHall'>Back</a></li>
	<li><a href="./modeChange/login.jsp">ViewCart</a></li>
	<%	}else{ %>
	<li><a href='/DeliverySystem/tocart?type=other'>ViewCart</a></li>
	<% 	}
	}%>
	     </ul>
        </nav>
        
        <div class="logo"> 
			<a href="#"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href="#"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!-- end of Logo -->
     </div> <!--end of header-->
   </div>
   

   	<div style ="margin:0 auto; width:800px;hight:500px;border-bottom:3px #ff8e22 solid;">
		<div style="float:right; border-left: 1px black solid; padding-left: 30px; height: 300 px;">
    		<table style="text-align:left; width:450px; padding:50px; background-color:#FFFFFB; border: 3px #ff8e22 solid;border-radius:22px;">
    		<tr><td>
				Seller Title:</td><td> <%= seller.getTitle() %></td></tr>
			<tr><td>
				E-mail:</td><td> <%= seller.getEmail() %></td></tr>
			<tr><td>
				Phone Number:</td><td> <%= seller.getPhoneNO() %></td></tr>
			<tr><td>
				Address:</td><td> <%= seller.getAddress() %></td></tr>
			<tr><td>
				Description:</td><td> <%= seller.getDescription() %></td></tr>
			<tr><td>
				Status:</td><td> <%= seller.getStatus() %></td></tr>
			</table>
    	</div>
    	<%foodservice Foodservice = new foodservice();%>
    <%sellerservice Sellerservice = new sellerservice();%>
    <div style="width:280px; height:340px; padding-left:10px; padding-top:30px;"  >
		<img src="${pageContext.request.contextPath}<%=Sellerservice.getphoto(seller.getId()) %>" height="280" width="280">
		</div>
    </div>
    
    <table style="margin:10px auto;">
   		<%
    	ArrayList<String>ftype=(ArrayList<String>)request.getAttribute("foodtype");
   		ftype.add("ALL");
    	for(int i = 0; i <= ftype.size()/5;++i){%>
    		<tr>
    	<%	for(int j = 0; j < 5; ++j){
    			if(i!=ftype.size()/5){%>
		    	<td>
		    	<div  onclick="location.href='/DeliverySystem/tofoodpage?ftp=<%=(String)ftype.get(5*i+j) %>&sellername=<%=seller.getName() %>'" 
		    	style="margin:10px auto;background-color:#ff8e22; color:white; text-align:center;
		    	font-size:1em; padding:15px;border-radius:8px; cursor:hand;"><%=(String)ftype.get(5*i+j) %></div>
		    	</td>
    			<%}
    			else{
    				if(j==ftype.size()-(ftype.size()/5)*5-1){ %>
    					<td>
    					<div  onclick="location.href='/DeliverySystem/tofoodpage?sellername=<%=seller.getName() %>&ftp='"
		    			style="margin:10px auto;background-color:#ff8e22; color:white; display:inline;text-align:center;
		    			font-size:1em; padding:15px;border-radius:8px; cursor:hand;">ALL</div>
		    			</td>
    				<%	break;
    				}
    				else{%>
	    			<td>
			    	<div  onclick="location.href='/DeliverySystem/tofoodpage?ftp=<%=(String)ftype.get(5*i+j) %>&sellername=<%=seller.getName() %>'" 
			    	style="margin:10px auto;background-color:#ff8e22; color:white; text-align:center;
			    	font-size:1em; padding:15px;border-radius:8px; cursor:hand;"><%=(String)ftype.get(5*i+j) %></div>
			    	</td>
    			  <% }
    			}
    		}%>
    		</tr>
    <%	} %>
    	</table>
  
    <table style = "width:900px;margin:0 auto;padding-bottom:40px;">
	<%
	ArrayList<Foods>list=(ArrayList<Foods>)request.getAttribute("foods");
	for(int i=0;i<list.size();i++)
	{
		Foods item=(Foods)list.get(i);
	%>

	<tr style = "height:100px">
	<td>
		<div style = "margin:0 auto;">
		<img src="${pageContext.request.contextPath}<%=Foodservice.getphoto(item.getFoodid()) %>" width="180" height="160" class="myimage wow fadeIn" title="mido" alt="1">
		</div>
	</td>
	
	<td>
	<div style="padding-left:30px;width:330px;height:160px;margin:0 auto;background-color:rgba(255,255,251,0.3);border-bottom:1px solid #ff5a0b;">
		<div style = "float:left;text-align:left; font-size:1.5em;color:#ff5a0b ;height:30px;padding-top:30px;">
			<%=item.getFoodname() %>
		</div>
	 	<div style = "float:left; width:300px; text-align:left;height:40px;padding-top:10px;/* padding-bottom:37px; */font-size:0.8em">
			<%=item.getDescription() %> </div>
	</div>
	</td>
	
	<td>
	 	<div style = "margin:0 auto; width:130px; text-align:left;border-bottom:1px solid #ff5a0b;height:100px;padding-top:60px;
	 	background-color:rgba(255,255,251,0.3);font-size:1.1em">
			<h4>$ <%=item.getPrice() %></h4>
		</div>
	</td>
	<%if(permissionU == true && seller.getStatus().equals("open")){%>
	<td> 
		<div  onclick="location.href='/DeliverySystem/tocart?type=add&id=<%=item.getFoodid() %>&sid=<%=item.getSellerid()%>&fname=<%=item.getFoodname()%>&price=<%=item.getPrice()%>&ftp=<%=request.getAttribute("ftp") %>'" 
				style="margin:10px auto; background-color:#ff8e22; color:white; width:80px; height:50px;font-size:1em; padding-top:15px;
			    border-radius:8px; cursor:hand; text-align:center">Add
		</div>
	</td>
	<%} if(permissionS == true){%>
		<td>
			<div onclick="location.href='/DeliverySystem/foodOperation?fid=<%=item.getFoodid() %>&ftp=<%=request.getAttribute("ftp") %>'" 
				style="margin:10px auto; background-color:#ff8e22; color:white; width:80px; height:50px;font-size:1em; padding-top:15px;
			    border-radius:8px; cursor:hand; text-align:center">Delete
			</div>
		</td>
	<%} %>
	</tr>
	<% } %>
	</table>

   
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
          <a href="https://www.facebook.com/chen.yizhen.796"><img src="image/fb.png" width="68" height="68" class="facebook"
           alt="fb"></a>
        <a href="https://twitter.com/Mido_A7X"><img src="image/twitter.png" width="68" height="68" class="twitter"
        alt="twitter"></a>
        <a href="#"><img src="image/youtube.png" width="68" height="69" class="youtube" alt="youtube"></a>
        <a href="#"><img src="image/g+.png" width="68" height="68" class="google" alt="g+"></a>
          <a href="#"><img src="image/message.png" width="68" height="68" class="message" alt="message"></a>
      </div>
      
       </div>
    </footer> <!--end of footer-->

</div><!-- end of footer -->
</body>
</html>