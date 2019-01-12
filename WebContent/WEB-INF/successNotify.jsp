<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.Users,com.dv.bean.Sellers"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<title>Success!</title>
</head>
<body>
	<!-- |||||||||||||||||||||||||||header|||||||||||||||||||||||||||||||| -->     
<div class="headerwrapper">
	<div id="header" class="container">
	<%String url = null; %>
        <nav>
            <ul id="navigations">
				<% if(session.getAttribute("userinfor")!=null){
					Users user=(Users)session.getAttribute("userinfor"); //Prepare for Back
					url = "/DeliverySystem/idcheck?username="+user.getName()+"&password="+user.getPassword();%>
                <li>Welcome,<%=user.getName() %>!</li>
                <li> <a href=<%=url %>>BackToHall</a></li>
				
				<% }else if(session.getAttribute("sellerinfo")!=null){ 
					Sellers seller = (Sellers)session.getAttribute("sellerinfo");
					url = "/DeliverySystem/idcheckS?username="+seller.getName()+"&password="+seller.getPassword();%>
                <li>Welcome,<%=seller.getName() %>!</li>
                <li><a href=<%=url %>>BackToHall</a></li>
				<%}%>
            </ul>
        </nav>
        <div class="logo"> 
			<a href=<%=url %>><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href=<%=url %>><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!--end of Logo-->
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->

	<%if(request.getParameter("order")==null) {%>
		<div style="margin:150px auto; padding:40px; font-size:1.2em; border-radius:10px; border:3px #ff8e22 solid;
		width:400px; background-color:#FFFFFB; text-align:center"><b>Success to change!</b></div>
	<%} else{%>
		<div style="margin:150px auto; padding:40px; font-size:1.2em; border-radius:10px; border:3px #ff8e22 solid;
		width:400px; background-color:#FFFFFB; text-align:center"><b>Congratulations!<br>Your order has been accepted!</b></div>
	<%} %>
	
<%-- 	session = request.getSession(false);
	if(session.getAttribute("userinfor")!=null){
	Users ur = (Users)session.getAttribute("userinfor");
	%>
	<a href="/DeliverySystem/idcheck?username=<%=ur.getName()%>&password=<%=ur.getPassword() %>">Go back hall</a>
	<%
	}if(session.getAttribute("sellerinfo")!=null){
	Sellers ur = (Sellers)session.getAttribute("sellerinfo");
	%>
	<a href="/DeliverySystem/idcheckS?username=<%=ur.getName()%>&password=<%=ur.getPassword() %>">Go back hall</a>
	<%
	}
	%><br> --%>

	
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
          <a href="https://www.facebook.com/Mido.HHH"><img src="image/fb.png" width="68" height="68" class="facebook"
           alt="fb"></a>
        <a href="https://twitter.com/Mido_A7X"><img src="image/twitter.png" width="68" height="68" class="twitter"
        alt="twitter"></a>
        <a href="#"><img src="image/youtube.png" width="68" height="69" class="youtube" alt="youtube"></a>
        <a href="#"><img src="image/g+.png" width="68" height="68" class="google" alt="g+"></a>
          <a href="#"><img src="image/message.png" width="68" height="68" class="message" alt="message"></a>
      </div>
      
       </div>
    </footer> <!--end of footer-->
    </div>
	
</body>
</html>