<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.dv.bean.*,com.dv.service.foodservice"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ShoppingCart</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
<div class="headerwrapper">
	<div id="header" class="container">
	<% 
	Users user=(Users)session.getAttribute("userinfor"); //Prepare for BacktoHall
	String username=user.getName();
	String password=user.getPassword(); 
	%>
	
		<div class="logo"> 
			<a href='/DeliverySystem/idcheck?username=<%=username%>&password=<%=password%>'><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href='/DeliverySystem/idcheck?username=<%=username%>&password=<%=password%>'><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!--end of Logo-->
		 <nav>
            <ul id="navigations">
	
	<li><a href='/DeliverySystem/idcheck?username=<%=username%>&password=<%=password%>'>BacktoHall</a></li>
	<%
	ArrayList<Carts>mycarts=(ArrayList<Carts>)request.getAttribute("cartlist");
	if(mycarts.size()>0){%>
	<li><a href='/DeliverySystem/tocheckout'>Checkout</a></li>
	<%} %>
		</ul>
		</nav>
	</div> <!--end of header-->
 </div>
 
 <div class="restaurantwrapper">
   <div class="container">
	 <h3>My Shopping Cart</h3>
	<table style = "width:900px;margin:0 auto; text-align:center;">
	<tr style = "height:100px;color:grey">
	<td><div style = "margin:0 auto;width:100px;"></div></td>
	<td><div style = "margin:0 auto;text-align:center;width:200px;border:1px dotted">Food</div></td>
	<td><div style = "margin:0 auto;text-align:center; width:200px;border:1px dotted">Store</div></td>
	<td><div style = "margin:0 auto;text-align:center;width:100px;border:1px dotted">Amount</div></td>
	<td><div style = "margin:0 auto;text-align:center;width:120px;border:1px dotted">Total Price</div></td>
	<td><div style = "margin:0 auto;text-align:center;width:100px;border:1px dotted">Reduce</div></td></tr>
	<%Double sumall=(Double)request.getAttribute("sumall"); 
	sumall = ((int)(sumall*100))/100.0;
	foodservice Foodservice = new foodservice();
	for(int i=0;i<mycarts.size();i++)
	{
		Carts temp=(Carts)mycarts.get(i);
	%>
	<tr style = "height:100px">
	<td>
	<div style = "margin:0 auto; width:100px">
		<img src="${pageContext.request.contextPath}<%=Foodservice.getphoto(temp.getFoodid()) %>"  width="100" height="100" class="myimage wow fadeIn" title="mido" alt="1">
	</div>
	</td>
	<td>
		<div style = "margin:0 auto; width:200px; text-align:left;border-bottom:1px solid #ff5a0b;height:50px">
			<h5><%=temp.getFoodname()%></h5>
		</div>
	</td>
	<td>
		<div style = "margin:0 auto; width:200px; text-align:left;border-bottom:1px solid #ff5a0b;height:50px">
			<div onclick="location.href='/DeliverySystem/tofoodpage?sellername=<%=temp.getSellername()%>'" 
								style="font-size:1em; padding: 0px 0px 0px 10px;"><%=temp.getSellername()%></div>
		</div>
	</td>
	<td>
		<div style = "margin:0 auto; width:100px; text-align:left;border-bottom:1px solid #ff5a0b;height:50px"> 
			<h5><%=temp.getFoodnum()%></h5>
		</div>
	</td>
	<td>
		<div style = "margin:0 auto; width:100px; text-align:left;border-bottom:1px solid #ff5a0b;height:50px"> 
			<h5>$<%=temp.getTotalamount()%></h5>
		</div>
	</td>
	<td>
		<%-- <div style = "margin:0 auto; width:200px; text-align:left;border-bottom:1px solid #ff5a0b;height:50px"> 
			<h5><a href='/DeliverySystem/tocart?type=del&id=<%=temp.getFoodid()%>'>Reduce</a></h5>
		</div> --%>
		<div onclick="location.href='/DeliverySystem/tocart?type=del&id=<%=temp.getFoodid()%>'" 
			style="margin:10px auto; background-color:#ff8e22; color:white; width:80px; height:40px;font-size:1em; padding-top:15px;
		    border-radius:8px; cursor:hand; text-align:center">Reduce
		</div>
	</td>
	</tr> 
	<%
	}
	%>
	</table>
	<h1 style="color:#ff5a0b;padding-bottom:30px">Total price: <%=sumall%></h1>
</div>	
 </div>
	
	
	
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