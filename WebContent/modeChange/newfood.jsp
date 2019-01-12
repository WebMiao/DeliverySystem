<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.Sellers"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddFood</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
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
				<%if(session.getAttribute("sellerinfo")!=null){ 
					Sellers seller = (Sellers)session.getAttribute("sellerinfo");%>
                <li><a href="/DeliverySystem/idcheckS?username=<%=seller.getName()%>&password=<%=seller.getPassword() %>">
                Welcome,<%=seller.getName() %>!</a></li>
                <li><a href = "/DeliverySystem/toSellControl">My Restaurant</a></li>
				
				<%}else{ %>
				<li><a href = "./login.jsp"> Login</a></li>
                <li><a href = "./signupU.jsp"> Register</a></li>
				<%} %>
            </ul>
        </nav>
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->

	<!-- |||||||||||||||||||||Main Content|||||||||||||||||||||||| -->
	<div class="restaurantwrapper">
   		<div class="container">
	 <h3>Add New Food</h3>
			 <div style="margin:70px auto; padding: 8px; border: 3px #ff8e22 solid; border-radius:15px; width:700px; background-color:#FFFFFB;">
				<form method="post" action="/DeliverySystem/addfood" id="form" enctype="multipart/form-data">
					<table style="margin:0px auto; text-align:left; font-size:1.2em;">
						<tr height=45px;>
							<td>Food Name: </td>
							<td><input style="font-size:0.8em;" type="text" name="foodname"></td>
						<tr>
						<tr height=45px;>
							<td>Food Type: </td>
							<td><input style="font-size:0.8em;" type="text" name="foodtype"></td>
						<tr>
						<tr height=45px;>
							<td>Description: </td>
							<td><input style="font-size:0.8em;" type="text" name="descrip"> </td>
						<tr>
						<tr height=45px;>
							<td>Price for one: </td>
							<td><input style="font-size:0.8em;" type="text" name="price"></td>
						<tr>
						<tr height=45px;>
							<td>Profile photo:</td>
							<td><input type="file" name="photo"></td>
						<tr>
					</table>
					
					<!-- ||||||||||||Button|||||||||||| -->
					<div style="width:250px; margin:5px auto;">
						<div onclick = "document.getElementById('form').reset();" 
					    style="background-color: #ff8e22; color:white; width:100px; height:30px;font-size:1em; padding-top:10px;
					    border-radius:8px; cursor:hand; text-align:center; float:right">Reset</div>
					    
						<div onclick = "document.getElementById('form').submit();" 
					    style="background-color: #ff8e22; color:white; width:100px; height:30px;font-size:1em; padding-top:10px;
					    border-radius:8px; cursor:hand; text-align:center">Update</div>
				    </div>
		    
<!-- 			        <input type="submit" value="submit" onclick="return check();" />
			        <button type="reset">Reset</button> -->
			    </form>
		    </div>
		    
    	</div>
    </div>

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