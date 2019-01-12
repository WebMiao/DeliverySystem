<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.dv.bean.*"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <title>User Sign Up</title>
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

<!-- |||||||||||||||||||||||||||sign up|||||||||||||||||||||||||||||||| -->  
<form method="post" action="/DeliverySystem/registerUser" id = "log" enctype="multipart/form-data">
	<div style="margin: 20px auto; text-align:center; border: 3px #ff8e22 solid;width:300px;background:white">
		<div style="color:white; background-color: #ff8e22; height:45px; width:300px; padding-top:6px; font-size:0.8em">
			<b>USER REGISTER</b>
		</div>
		
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Username:
			<input type="text" name="username" style="height: 30px; width: 180px"/><br>
		</div>
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Password: <input type="password" name="password1" id="password1" style="height: 30px; width: 180px"/>
		</div>
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Re-enter Password:  <input type="password" name="password2" id="password2" style="height: 30px; width: 180px"><br>
		</div>
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			<input type="radio" name="gender" value = "F" checked="checked" >Female		
			<input type="radio" name="gender" value = "M">Male <br>
		</div>
		<div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			E-mail: <input type="text" name="inemail" id="email" style="height: 30px; width: 180px"><br>
		</div><div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Phone Number:   <input type="text" name="phoneNum"  style="height: 30px; width: 180px"><br>
		</div><div style="margin: 10px auto; text-align:left;margin-bottom: 20px;font-size:0.8em; padding-bottom:10px; padding-left:40px" >
			Profile photo:  <input type="file" name="photo"  style="height: 30px; width: 180px"><br>
		</div>
		
		
		<div onclick="myfunc() && document.getElementById('log').submit()"
			style="margin: 10px auto;background-color: #ff8e22; color:white; width: 180px; height: 40px; margin-bottom: 20px;padding-top:15px; font-size:1em;cursor:pointer">
			Submit
		</div>
	</div>
</form>

	<div style="margin:0 auto;font-size:15px;width:300px">Not a user?<a href="./signupS.jsp"> SELLER</a></div>
	<script type = "text/javascript">
	function myfunc(){
		return check() && checkemail();
		
	}
	</script>
	
	<script type="text/javascript">
	function check(){ 
		const password1 = document.getElementById('password1');
		const password2 = document.getElementById('password2');
			if(password1.value!=password2.value){
				alert("Password entered twice is different. Please try again!");
				password1.value = "";
				password2.value = "";
				return false;
			}
			else
				return true;
	}
	</script>
	
	
	<script type="text/javascript">
	function checkemail(){
		const inemail = document.getElementById('email');
	    var pattern = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;  
	    if(pattern.test(inemail.value)){
	        return true;  
	    }else{
	    	alert("Please input valid email");
	        return false;  
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
          <a href="https://www.facebook.com/Mido.HHH"><img src="../image/fb.png" width="68" height="68" class="facebook"
           alt="fb"></a>
        <a href="https://twitter.com/Mido_A7X"><img src="../image/twitter.png" width="68" height="68" class="twitter"
        alt="twitter"></a>
        <a href="#"><img src="../image/youtube.png" width="68" height="69" class="youtube" alt="youtube"></a>
        <a href="#"><img src="../image/g+.png" width="68" height="68" class="google" alt="g+"></a>
          <a href="#"><img src="../image/message.png" width="68" height="68" class="message" alt="message"></a>
      </div>
      
       </div>
    </footer>
  </div><!-- end of footer -->
  
</body>
</html>