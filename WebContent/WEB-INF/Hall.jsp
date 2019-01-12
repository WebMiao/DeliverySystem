<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.dv.bean.*,com.dv.service.sellerservice"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    
</head>
	<style type="text/css">
		.web:hover{text-decoration:underline;}
	</style>
<div class="headerwrapper">
	<div id="header" class="container">
		<div class="logo"> 
			<a href="#"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href="#"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
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
				<li><a href = "./modeChange/login.jsp"> Login</a></li>
                <li><a href = "./modeChange/signupU.jsp"> Register</a></li>
				<%} %>
            </ul>
        </nav>
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->

<!--============ RESTAURANT ============-->
<div class="restaurantwrapper" style="margin-bottom:40px">
  <div class="container">
        <h3>Restaurant</h3>
			<%
			ArrayList<Sellers>list=(ArrayList<Sellers>)request.getAttribute("sellers"); 
			sellerservice Sellerservice = new sellerservice();
			for(int i=0;i<list.size();i++)
			{
			Sellers item=(Sellers)list.get(i);
			%>
				<table style = "width:900px;margin:0 auto">
					<tr style = "height:100px">
						<td>
							<div style = "margin:0px auto; width:100px; cursor:hand;" class="link" onclick="location.href='/DeliverySystem/tofoodpage?sellername=<%=item.getName()%>'">
							<img src="${pageContext.request.contextPath}<%=Sellerservice.getphoto(item.getId()) %>" width="140" height="140" class="myimage wow fadeIn" title="mido" alt="1">
							</div>
						</td>
						<td>
							<div style = "margin:0px auto; width:520px; text-align:left;border-bottom:2px solid #ff5a0b;height:140px;background-color:rgba(255,255,251,0.3);">
								<%-- <h5><a href="/DeliverySystem/tofoodpage?sellername=<%=item.getName()%>">
								<%=item.getTitle() %></a></h5> --%>
								<div onclick="location.href='/DeliverySystem/tofoodpage?sellername=<%=item.getName()%>'" 
								style="color:#ff5a0b; font-size:1.5em; padding: 10px 0px 0px 10px; cursor:hand;" class="web"><%=item.getTitle() %></div>
								
								<div style="font-size:0.9em; padding: 5px 0px 0px 10px;"><%=item.getDescription() %></div>
								<div style="font-size:0.9em; padding: 5px 0px 0px 10px;">Address: <%=item.getAddress() %></div>
							</div>
						</td>
					</tr>
				</table>
				
			<%}%>	
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