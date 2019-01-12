<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setting Seller Information</title>
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<% Sellers seller = (Sellers)session.getAttribute("sellerinfo"); %>
	<!-- ||||||||||||||||||||| Header ||||||||||||||||||||-->
	<div class="headerwrapper">
	<div id="header" class="container">
		<div class="logo"> 
			<a href='/DeliverySystem/idcheckS?username=<%=seller.getName()%>&password=<%=seller.getPassword()%>'><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href='/DeliverySystem/idcheckS?username=<%=seller.getName()%>&password=<%=seller.getPassword()%>'><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!--end of Logo-->
		 <nav>
            <ul id="navigations">
	<li><a href='/DeliverySystem/idcheckS?username=<%=seller.getName()%>&password=<%=seller.getPassword()%>'>BackToHall</a></li>
			</ul>
		</nav>
	  </div> <!--end of header-->
 	</div>
 	
 	<!-- |||||||||||||||||||||Main Content|||||||||||||||||||||||| -->
	<div class="restaurantwrapper">
   		<div class="container">
	 <h3>Seller Information Setting</h3>
	 
	
	<!-- Basic Information Setting -->
	<div style="margin:10px auto; padding: 8px; border: 3px #ff8e22 solid; border-radius:15px; width:700px; background-color:#FFFFFB;">
		<h2>Basic Information Setting</h2>
		
		<form action="/DeliverySystem/basicSetS" method="get" id="sinfo">
			<table style="margin:0px auto; text-align:left; font-size:1.2em;">
				<tr height=35px;>
					<td>Seller Name:</td>
					<td><input style="font-size:0.8em;" type="text" name="username" value="<%= seller.getName() %>"></td>
				</tr>
				<tr height=35px;>
					<td>Title:</td>
					<td><input style="font-size:0.8em;" type="text" name="title" value="<%= seller.getTitle() %>"></td>
				</tr>
				<tr height=35px;>
					<td>E-mail:</td>
					<td><input style="font-size:0.8em;" type="text" name="email" value="<%= seller.getEmail() %> "></td>
				</tr>
				<tr height=35px;>
					<td>Phone Number: </td>
					<td><input style="font-size:0.8em;" type="text" name="phoneNo" value="<%= seller.getPhoneNO() %>"></td>
				</tr>
				<tr height=35px;>
					<td>Address:</td>
					<td><input style="font-size:0.8em;" type="text" name="address" value="<%= seller.getAddress() %>"></td>
				</tr>
				<tr height=35px;>
					<td>Description:</td>
					<td><input style="font-size:0.8em;" type="text" name="descrip" value="<%= seller.getDescription() %>"/></td>
				</tr>
	     	</table>
	     	
	     	<!-- ||||||||||||Button|||||||||||| -->
			<div style="width:250px; margin:5px auto;">
				<div onclick = "document.getElementById('sinfo').reset();" 
			    style="background-color: #ff8e22; color:white; width:100px; height:30px;font-size:1em; padding-top:10px;
			    border-radius:8px; cursor:hand; text-align:center; float:right">Reset</div>
			    
				<div onclick = "document.getElementById('sinfo').submit();" 
			    style="background-color: #ff8e22; color:white; width:100px; height:30px;font-size:1em; padding-top:10px;
			    border-radius:8px; cursor:hand; text-align:center">Update</div>
		    </div>
		    
	     	<!-- <button type="submit" name="submit">Update</button>
		    <button type="reset">Reset</button> -->
		</form>
	</div>

	<!-- |||||||||||||||Password Setting||||||||||||||| -->
		<div style="margin:20px auto; padding: 8px; border: 3px #ff8e22 solid; border-radius:15px; width:700px; background-color:#FFFFFB;">
				<h2>Password Setting</h2>
				<div style=" font-size:1.1em;">Please input the current password:</div>
				
				<form method="post" action="./modeChange/passwordSet.jsp" id="none">
				<input style="font-size:1.1em; margin:6px 1px 6px 180px" id="password" name="password" type="password"/>
				<input type="hidden" name="pwdc" id="pwdc" value="<%=((Sellers)session.getAttribute("sellerinfo")).getPassword() %>">
				<div style="float:right; margin: 0px 150px 0px 0px; background-color: #ff8e22; color:white; width:70px; height:30px;
				font-size:1em; padding-top:10px; border-radius:8px; cursor:hand; text-align:center" 
		    onclick="return toVaild() && document.getElementById('none').submit()">check</div>
				</form>
			</div>

		</div>
	</div>
		
	<script>
		function toVaild(){
			with(document.all){
				if(password.value != pwdc.value){
					alert("Password is Wrong! try again!");
					return false;
				}else
					return true;
			}
		}
	</script>
	
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

</div><!-- end of footer -->
</body>
</html>