<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.dv.bean.*"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <title>Login</title>
</head>
<body>

<!-- |||||||||||||||||||||||||||header|||||||||||||||||||||||||||||||| -->     
<div class="headerwrapper">
	<div id="header" class="container">
		<div class="logo"> 
			<a href="#"><img src="../image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href="#"><img src="../image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
				<% if(session.getAttribute("userinfor")!=null){
					Users user=(Users)session.getAttribute("userinfor"); //Prepare for Back%>
                <li>Welcome,<%=user.getName() %>!</li>
                <li> <a href='/DeliverySystem/tocart?type=other'>ViewCart</a></li>
                <li><a href = "/DeliverySystem/toUserInfo"> My Profile</a></li>
				
				<% }else if(session.getAttribute("sellerinfo")!=null){ 
					Sellers seller = (Sellers)session.getAttribute("sellerinfo");%>
                <li>Welcome,<%=seller.getName() %>!</li>
                <li><a href = "/DeliverySystem/toSellControl">My Restaurant</a></li>
				
				<%}else{ %>
				<li><a href = "./login.jsp"> Login</a></li>
                <li><a href = "./signupU.jsp"> Register</a></li>
				<%} %>
            </ul>
        </nav>
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->

<!-- |||||||||||||||||||||||||||login|||||||||||||||||||||||||||||||| -->     

<form method="post" action="/DeliverySystem/idcheckS" id = "log">
	<div style="margin: 20px auto; text-align:center; border: 3px #ff8e22 solid;width:300px;background:white">
		<div style="color:white; background-color: #ff8e22; height:45px; width:300px; padding-top:6px; font-size:0.8em">
			<b>SELLER LOGIN</b>
		</div>
		
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Username:<br>
			<input type="text" name="username" style="height: 30px; width: 220px"/>
			<%if(request.getParameter("alter")!=null){ %>
     		<span style="color:red">
			<%=request.getParameter("alter") %>
    		</span><%} %><br>
		</div>
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Password: <br>
			<input type="password" name="password" style="height: 30px; width: 220px"/>
		</div>
		
		<div onclick = "document.getElementById('log').submit();"
			style="margin: 10px auto;background-color: #ff8e22; color:white; width: 180px; height: 40px; margin-bottom: 20px;padding-top:15px; font-size:1em;cursor:hand">
			Submit
		</div>
	</div>
	</form>
     
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
          <a href="https://www.facebook.com/Mido.HHH"><img src="../image/fb.png" width="68" height="68" class="facebook"
           alt="fb"></a>
        <a href="https://twitter.com/Mido_A7X"><img src="../image/twitter.png" width="68" height="68" class="twitter"
        alt="twitter"></a>
        <a href="#"><img src="../image/youtube.png" width="68" height="69" class="youtube" alt="youtube"></a>
        <a href="#"><img src="../image/g+.png" width="68" height="68" class="google" alt="g+"></a>
          <a href="#"><img src="../image/message.png" width="68" height="68" class="message" alt="message"></a>
      </div>
      
       </div>
    </footer> <!--end of footer-->

</div><!-- end of footer -->
</body>
</html>